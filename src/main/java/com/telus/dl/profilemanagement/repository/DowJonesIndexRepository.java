package com.telus.dl.profilemanagement.repository;

import com.telus.dl.profilemanagement.document.DowJonesIndex;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DowJonesIndexRepository extends MongoRepository<DowJonesIndex, String> {
    List<DowJonesIndex> findByStock(String stock);
}
