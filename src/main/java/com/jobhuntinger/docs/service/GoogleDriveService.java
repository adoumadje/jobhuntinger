package com.jobhuntinger.docs.service;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.jobhuntinger.common.constants.Constants;
import com.jobhuntinger.docs.config.GoogleDriveFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;

@Service
public class GoogleDriveService {
    private final Drive drive;

    public GoogleDriveService() {
        this.drive = GoogleDriveFactory.getDrive();
    }

    public String uploadFile(java.io.File tempFile, String fileType) {
        try {
            File fileMetadata = new File();
            fileMetadata.setName(tempFile.getName());
            fileMetadata.setParents(Collections.singletonList(Constants.DRIVE_FOLDER_ID));

            FileContent fileContent = new FileContent(fileType, tempFile);

            File uploadedFile = drive.files().create(fileMetadata, fileContent).setFields("id").execute();
            tempFile.delete();
            return uploadedFile.getId();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
