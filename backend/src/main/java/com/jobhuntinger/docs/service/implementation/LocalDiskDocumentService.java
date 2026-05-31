package com.jobhuntinger.docs.service.implementation;

import com.jobhuntinger.common.constants.Constants;
import com.jobhuntinger.docs.dto.DocumentDto;
import com.jobhuntinger.docs.entity.Document;
import com.jobhuntinger.docs.mapper.DocumentMapper;
import com.jobhuntinger.docs.repository.DocumentRepository;
import com.jobhuntinger.docs.service.IDocumentService;
import com.jobhuntinger.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 *  Need a specific location on server
 */
@Service
@RequiredArgsConstructor
public class LocalDiskDocumentService implements IDocumentService {
    private final DocumentMapper documentMapper;
    private final DocumentRepository documentRepository;
    private final IUserService userService;

    private static final String assetsPath = "src/main/resources/static/assets";
    private static final Logger logger = LoggerFactory.getLogger(LocalDiskDocumentService.class);

    @Override
    public String saveDocument(Authentication authentication, DocumentDto documentDto) {
        //User user = userService.getAuthenticatedUser(authentication);
        Document document = documentMapper.toDocument(documentDto);
        String docUrl = saveToDisk(documentDto);
        document.setDocumentName(documentDto.getDocument().getOriginalFilename());
        document.setDocumentUrl(docUrl);
        //document.setUser(user);
        Document savedDocument = documentRepository.save(document);
        return savedDocument.getDocumentUrl();
    }

    private String saveToDisk(DocumentDto documentDto) {
        MultipartFile document = documentDto.getDocument();
        if(document.getContentType() == null
                || !document.getContentType().equals(Constants.TYPE_PDF)) {
            throw new RuntimeException("Wrong file type. Please upload PDF");
        }
        String originalName = document.getOriginalFilename();
        String baseName = FilenameUtils.getBaseName(originalName);
        String extension = FilenameUtils.getExtension(originalName);
        String generatedName = baseName + "__" + UUID.randomUUID() + "." + extension;
        File baseDir = new File(assetsPath, documentDto.getDocumentType().toString());
        logger.info(baseDir.getAbsolutePath());
        if(!baseDir.exists()) {
            if(!baseDir.mkdir()) {
                throw new RuntimeException("Cannot create directory");
            }
        }
        File destination = new File(baseDir, generatedName);
        try(OutputStream outputStream = new FileOutputStream(destination)) {
            outputStream.write(document.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .build().toUriString();
        return baseUrl + "/assets/" + documentDto.getDocumentType()
                + "/" + generatedName;
    }
}
