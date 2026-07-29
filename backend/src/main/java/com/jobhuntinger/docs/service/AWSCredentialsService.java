package com.jobhuntinger.docs.service;

import com.jobhuntinger.common.constants.Constants;
import com.jobhuntinger.docs.dto.AwsCredentialsDto;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AWSCredentialsService {
    private static final String CREDENTIALS_CSV_LOCATION = "secret/serverless_dev_accessKeys.csv";
    private static final String COMMA_DELIMITER = ",";

    public static AwsCredentials getCredentials() {
        if(System.getenv(Constants.AWS_ACCESS_KEY) != null
        && System.getenv(Constants.AWS_SECRET_KEY) != null) {
            return getFromEnv();
        } else {
            return getFromCSV();
        }
    }

    private static AwsCredentials getFromEnv() {
        AwsCredentialsDto awsCredentialsDto = readFromEnv();
        return AwsBasicCredentials.create(awsCredentialsDto.accessKey(), awsCredentialsDto.secretKey());
    }

    private static AwsCredentials getFromCSV() {
        AwsCredentialsDto awsCredentialsDto = readFromCSV();
        return AwsBasicCredentials.create(awsCredentialsDto.accessKey(), awsCredentialsDto.secretKey());
    }

    private static AwsCredentialsDto readFromEnv() {
        String accessKey = System.getenv(Constants.AWS_ACCESS_KEY);
        String secretKey = System.getenv(Constants.AWS_SECRET_KEY);
        return new AwsCredentialsDto(accessKey, secretKey);
    }

    private static AwsCredentialsDto readFromCSV() {
        List<List<String>> records = new ArrayList<>();
        try (InputStream inputStream = AwsBasicCredentials.class
                .getClassLoader()
                .getResourceAsStream(CREDENTIALS_CSV_LOCATION)) {
            assert inputStream != null;
            try(Scanner scanner = new Scanner(inputStream)) {
                while (scanner.hasNextLine()) {
                    records.add(getRecordFromLine(scanner.nextLine()));
                }
            }
            List<String> credsLine = records.get(1);
            return new AwsCredentialsDto(credsLine.get(0), credsLine.get(1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }
}
