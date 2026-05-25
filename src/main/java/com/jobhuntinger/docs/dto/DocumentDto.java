package com.jobhuntinger.docs.dto;

import com.jobhuntinger.docs.enums.DocumentType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class DocumentDto {
    DocumentType documentType;
    MultipartFile multipartFile;
}
