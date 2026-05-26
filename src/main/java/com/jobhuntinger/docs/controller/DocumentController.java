package com.jobhuntinger.docs.controller;

import com.jobhuntinger.docs.dto.DocumentDto;
import com.jobhuntinger.docs.service.IDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/documents")
public class DocumentController {
    private final IDocumentService documentService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveDocument(Authentication authentication, @ModelAttribute DocumentDto documentDto) {
        String url = documentService.saveDocument(authentication, documentDto);
        return new ResponseEntity<>(url, HttpStatus.CREATED);
    }
}
