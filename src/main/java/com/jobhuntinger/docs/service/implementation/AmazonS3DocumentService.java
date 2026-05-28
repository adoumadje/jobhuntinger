package com.jobhuntinger.docs.service.implementation;

import com.jobhuntinger.common.constants.Constants;
import com.jobhuntinger.docs.dto.DocumentDto;
import com.jobhuntinger.docs.entity.Document;
import com.jobhuntinger.docs.mapper.DocumentMapper;
import com.jobhuntinger.docs.repository.DocumentRepository;
import com.jobhuntinger.docs.service.IDocumentService;
import com.jobhuntinger.user.entity.User;
import com.jobhuntinger.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Primary
@Service
@RequiredArgsConstructor
public class AmazonS3DocumentService implements IDocumentService {
    private final S3Client s3Client;
    private final DocumentMapper documentMapper;
    private final DocumentRepository documentRepository;
    private final IUserService userService;

    @Override
    public String saveDocument(Authentication authentication, DocumentDto documentDto) {
        String amazonS3Key = saveToS3Bucket(documentDto);
        User user = userService.getAuthenticatedUser(authentication);
        String docUrl = String.format("https://%s.s3.%s.amazonaws.com/%s", Constants.AWS_S3_BUCKET,
                s3Client.serviceClientConfiguration().region().id(), amazonS3Key);
        Document document = documentMapper.toDocument(documentDto);
        document.setUser(user);
        document.setDocumentName(documentDto.getDocument().getOriginalFilename());
        document.setDocumentUrl(docUrl);
        document.setAmazonS3Key(amazonS3Key);
        Document savedDocument = documentRepository.save(document);
        return savedDocument.getDocumentUrl();
    }

    private String saveToS3Bucket(DocumentDto documentDto) {
        MultipartFile document = documentDto.getDocument();
        if(document.getContentType() == null || !document.getContentType().equals(Constants.TYPE_PDF)) {
            throw new RuntimeException("Wrong document type. Please upload a PDF");
        }
        String destination = generateDestination(documentDto);
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(Constants.AWS_S3_BUCKET)
                .key(destination)
                .contentType(document.getContentType())
                .build();
        try {
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(document.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return destination;
    }

    private String generateDestination(DocumentDto documentDto) {
        String originalName = documentDto.getDocument().getOriginalFilename();
        String baseName = FilenameUtils.getBaseName(originalName);
        String extension = FilenameUtils.getExtension(originalName);
        String generatedName = baseName + "__" + UUID.randomUUID() + "." + extension;
        return documentDto.getDocumentType() + "/" + generatedName;
    }
}
