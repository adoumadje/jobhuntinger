package com.jobhuntinger.docs.repository;

import com.jobhuntinger.docs.entity.Document;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends CrudRepository<Document, Long> {
}
