package com.jobhuntinger.docs.service;

import com.jobhuntinger.docs.dto.DocumentDto;
import org.springframework.security.core.Authentication;

public interface IDocumentService {
    String saveDocument(Authentication authentication, DocumentDto documentDto);
}
