package com.jobhuntinger.docs.config;

import com.jobhuntinger.common.constants.Constants;
import com.jobhuntinger.docs.service.AWSCredentialsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AwsS3Config {
    @Bean
    public S3Client s3Client() {
        S3Client s3Client = S3Client.builder()
                .region(Region.of(Constants.AWS_REGION))
                .credentialsProvider(StaticCredentialsProvider.create(AWSCredentialsService.getCredentials()))
                .build();
        return s3Client;
    }
}
