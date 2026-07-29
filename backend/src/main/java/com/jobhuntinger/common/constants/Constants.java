package com.jobhuntinger.common.constants;

import org.springframework.http.HttpStatus;

import java.util.List;

public class Constants {
    public static final int STATUS_CREATED = HttpStatus.CREATED.value();
    public static final int STATUS_BAD_REQUEST = HttpStatus.BAD_REQUEST.value();

    public static final String JOB_CREATED_MSG = "Job registered successfully";

    public static final String TYPE_PDF = "application/pdf";
    public static final String APPLICATION_NAME = "jobhuntiger";

    public static final String DRIVE_FOLDER_ID = "10PTm4BlQWffosdAWBMYw-0a7d5VRLhkR";
    public static final String DRIVE_VIEW_BASE_URL = "https://drive.google.com/uc?id=";

    public static final String AWS_ACCESS_KEY = "AWS_ACCESS_KEY";
    public static final String AWS_SECRET_KEY = "AWS_SECRET_KEY";
    public static final String AWS_REGION = "eu-north-1";
    public static final String AWS_S3_BUCKET = "jobhuntinger-documents-upload";

    public static final List<String> ALLOWED_METHODS = List.of("OPTIONS", "GET", "POST");
    public static final String REST_PATTERN = "/api/**";
    public static final String H2_PATTERN = "/h2-console/**";
    public static final String ACTUATOR_PATTERN = "/actuator/**";
    public static final String WILDCARD = "*";
}
