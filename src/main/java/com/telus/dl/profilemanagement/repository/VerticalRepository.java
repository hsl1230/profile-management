package com.telus.dl.profilemanagement.repository;

import com.telus.dl.profilemanagement.document.Vertical;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VerticalRepository extends MongoRepository<Vertical, String> {
}
