package com.jobhuntinger.docs.service.implementation;

import com.jobhuntinger.common.constants.Constants;
import com.jobhuntinger.docs.dto.DocumentDto;
import com.jobhuntinger.docs.service.IDocumentService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@Service
public class LocalDiskDocumentService implements IDocumentService {
    private static final String assetsPath = "src/main/resources/static/assets/";

    private static final Logger logger = LoggerFactory.getLogger(LocalDiskDocumentService.class);

    @Override
    public String saveDocument(DocumentDto documentDto) {
        MultipartFile multipartFile = documentDto.getMultipartFile();
        if(multipartFile.getContentType() == null
                || !multipartFile.getContentType().equals(Constants.TYPE_PDF)) {
            throw new RuntimeException("Wrong file type. Please upload PDF");
        }
        String originalName = multipartFile.getOriginalFilename();
        String baseName = FilenameUtils.getBaseName(originalName);
        String extension = FilenameUtils.getExtension(originalName);
        String generatedName = baseName + "__" + UUID.randomUUID() + "." + extension;
        File baseDir = new File(System.getProperty("user.dir"), assetsPath + documentDto.getDocumentType());
        logger.info(baseDir.getAbsolutePath());
        if(!baseDir.exists()) {
            if(!baseDir.mkdir()) {
                throw new RuntimeException("Cannot create directory");
            }
        }
        File destination = new File(baseDir, generatedName);
        try(OutputStream outputStream = new FileOutputStream(destination)) {
            outputStream.write(multipartFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String baseUrl = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .build().toUriString();
        String docUrl = baseUrl + "/assets/" + documentDto.getDocumentType()
                + "/" + generatedName;
        return docUrl;
    }
}
