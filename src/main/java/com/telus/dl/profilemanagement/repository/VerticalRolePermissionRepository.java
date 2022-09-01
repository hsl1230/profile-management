package com.telus.dl.profilemanagement.repository;

import com.telus.dl.profilemanagement.document.VerticalRoleId;
import com.telus.dl.profilemanagement.document.permission.VerticalRolePermission;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface VerticalRolePermissionRepository extends MongoRepository<VerticalRolePermission, String> {
    List<VerticalRolePermission> findByVerticalRoleId(VerticalRoleId verticalRoleId);
    void deleteByVerticalRoleId(VerticalRoleId verticalRoleId);
}
