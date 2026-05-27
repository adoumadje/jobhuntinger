package com.jobhuntinger.docs.config;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.jobhuntinger.common.constants.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class GoogleDriveFactory {
    private static final String CREDENTIALS_PATH = "src/main/resources/secret/google_drive_service_account_key.json";

    public static Drive getDrive() {
        try {
            GoogleCredentials googleCredentials = GoogleCredentials
                    .fromStream(new FileInputStream(CREDENTIALS_PATH))
                    .createScoped(Collections.singleton(DriveScopes.DRIVE));
            HttpRequestInitializer httpRequestInitializer = new HttpCredentialsAdapter(googleCredentials);

            return new Drive.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    GsonFactory.getDefaultInstance(),
                    httpRequestInitializer
            ).setApplicationName(Constants.APPLICATION_NAME).build();
        } catch (IOException | GeneralSecurityException e) {
//            throw new RuntimeException(e);
            return null;
        }
    }
}
