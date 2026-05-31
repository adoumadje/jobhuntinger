package com.jobhuntinger.docs.mapper;

import com.jobhuntinger.docs.dto.DocumentDto;
import com.jobhuntinger.docs.entity.Document;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DocumentMapper {
    Document toDocument(DocumentDto documentDto);
}
