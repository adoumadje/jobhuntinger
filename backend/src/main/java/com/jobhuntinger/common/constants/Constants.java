package com.jobhuntinger.common.constants;

import org.springframework.http.HttpStatus;

public class Constants {
    public static final int STATUS_CREATED = HttpStatus.CREATED.value();
    public static final int STATUS_BAD_REQUEST = HttpStatus.BAD_REQUEST.value();

    public static final String JOB_CREATED_MSG = "Job registered successfully";

    public static final String TYPE_PDF = "application/pdf";
    public static final String APPLICATION_NAME = "jobhuntiger";

    public static final String DRIVE_FOLDER_ID = "10PTm4BlQWffosdAWBMYw-0a7d5VRLhkR";
    public static final String DRIVE_VIEW_BASE_URL = "https://drive.google.com/uc?id=";

    public static final String AWS_REGION = "eu-north-1";
    public static final String AWS_S3_BUCKET = "jobhuntinger-documents-upload";
}
