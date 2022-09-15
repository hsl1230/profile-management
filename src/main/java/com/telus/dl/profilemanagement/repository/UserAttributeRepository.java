package com.telus.dl.profilemanagement.repository;

import com.telus.dl.profilemanagement.document.VerticalRole;
import com.telus.dl.profilemanagement.document.VerticalRoleId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VerticalRoleRepository extends MongoRepository<VerticalRole, VerticalRoleId> {
    List<VerticalRole> findByIdVerticalId(String id);
}
