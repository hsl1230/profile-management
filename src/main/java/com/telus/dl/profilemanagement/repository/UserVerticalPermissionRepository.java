package com.telus.dl.profilemanagement.repository;

import com.telus.dl.profilemanagement.document.UserVerticalId;
import com.telus.dl.profilemanagement.document.permission.UserVerticalPermission;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserVerticalPermissionRepository extends MongoRepository<UserVerticalPermission, String> {
    List<UserVerticalPermission> findByUserVerticalId(UserVerticalId userVerticalId);
    void deleteByUserVerticalId(UserVerticalId userVerticalId);
}
