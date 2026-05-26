package com.jobhuntinger.docs.service.implementation;

import com.jobhuntinger.common.constants.Constants;
import com.jobhuntinger.docs.dto.DocumentDto;
import com.jobhuntinger.docs.entity.Document;
import com.jobhuntinger.docs.mapper.DocumentMapper;
import com.jobhuntinger.docs.repository.DocumentRepository;
import com.jobhuntinger.docs.service.GoogleDriveService;
import com.jobhuntinger.docs.service.IDocumentService;
import com.jobhuntinger.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 *  Need shared drive
 */
@Service
@RequiredArgsConstructor
public class GoogleDriveDocumentService implements IDocumentService {
    private final DocumentMapper documentMapper;
    private final DocumentRepository documentRepository;
    private final IUserService userService;
    private final GoogleDriveService googleDriveService;

    @Override
    public String saveDocument(Authentication authentication, DocumentDto documentDto) {
        //User user = userService.getAuthenticatedUser(authentication);
        Document document = documentMapper.toDocument(documentDto);
        String googleDriveId = saveToDrive(documentDto.getDocument());
        String docUrl = Constants.DRIVE_VIEW_BASE_URL + googleDriveId;
        document.setDocumentName(documentDto.getDocument().getOriginalFilename());
        document.setDocumentUrl(docUrl);
        document.setGoogleDriveId(googleDriveId);
        //document.setUser(user);
        Document savedDocument = documentRepository.save(document);
        return savedDocument.getDocumentUrl();
    }

    private String saveToDrive(MultipartFile document) {
        try {
            if(document.getContentType() == null
                    || !document.getContentType().equals(Constants.TYPE_PDF)) {
                throw new RuntimeException("Wrong file type. Please upload PDF");
            }
            File tempFile = File.createTempFile("upload-", document.getOriginalFilename());
            document.transferTo(tempFile);
            return googleDriveService.uploadFile(tempFile, document.getContentType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
