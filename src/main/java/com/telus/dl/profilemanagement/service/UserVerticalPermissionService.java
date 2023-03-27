package com.telus.dl.profilemanagement.service;

import com.telus.dl.profilemanagement.document.UserVerticalId;
import com.telus.dl.profilemanagement.document.permission.UserVerticalPermission;
import com.telus.dl.profilemanagement.dto.permission.PermissionDto;
import com.telus.dl.profilemanagement.dto.permission.UserVerticalPermissionDto;
import com.telus.dl.profilemanagement.repository.UserVerticalPermissionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserVerticalPermissionService {
    private final UserVerticalPermissionRepository userVerticalPermissionRepository;
    private final UserVerticalEnablementService userVerticalEnablementService;
    private final MongoTemplate mongoTemplate;
    private final ModelMapper modelMapper;

    @Autowired
    public UserVerticalPermissionService(
            UserVerticalPermissionRepository userVerticalPermissionRepository,
            UserVerticalEnablementService userVerticalEnablementService,
            MongoTemplate mongoTemplate,
            ModelMapper modelMapper) {
        this.userVerticalPermissionRepository = userVerticalPermissionRepository;
        this.userVerticalEnablementService = userVerticalEnablementService;
        this.mongoTemplate = mongoTemplate;
        this.modelMapper = modelMapper;
    }

    public List<UserVerticalPermissionDto> grantPermissionsToUserVertical(
            String verticalId,
            String userProfileId,
            List<PermissionDto> permissionDtos) {
        userVerticalEnablementService.assertUserVerticalEnablementExists(verticalId, userProfileId);

        List<UserVerticalPermission> userVerticalPermissions = permissionDtos
                .stream()
                .map(permissionDto -> modelMapper.map(permissionDto, UserVerticalPermission.class)
                        .userVerticalId(UserVerticalId.builder()
                                .verticalId(verticalId)
                                .userProfileId(userProfileId)
                                .build()))
                .toList();
        return userVerticalPermissionRepository.saveAll(userVerticalPermissions)
                .stream()
                .map(userVerticalPermission -> modelMapper.map(userVerticalPermission, UserVerticalPermissionDto.class))
                .toList();
    }

    public void deleteUserVerticalPermissions(List<String> permissionIds) {
        userVerticalPermissionRepository.deleteAllById(permissionIds);
    }

    public void deleteAllUserVerticalPermissions(
            String verticalId,
            String userProfileId) {
        userVerticalPermissionRepository.deleteByUserVerticalId(UserVerticalId
                .builder().
                verticalId(verticalId).
                userProfileId(userProfileId)
                .build());
    }

    public List<UserVerticalPermissionDto> findUserVerticalPermissions(
            String verticalId,
            String userProfileId) {
        return userVerticalPermissionRepository
                .findByUserVerticalId(UserVerticalId
                        .builder().
                        verticalId(verticalId).
                        userProfileId(userProfileId)
                        .build())
                .stream()
                .map(userVerticalPermission -> modelMapper.map(userVerticalPermission, UserVerticalPermissionDto.class))
                .toList();
    }

    public List<UserVerticalPermissionDto> findUserVerticalPermissionsForResource(
            String verticalId,
            String userProfileId,
            String resourceId,
            String resourceType) {
        return mongoTemplate.find(
                        new Query().addCriteria(
                                Criteria.where("UserVerticalId.verticalId").is(verticalId)
                                        .and("UserVerticalId.userProfileId").is(userProfileId)
                                        .and("resource.resourceId").is(resourceId)
                                        .and("resource.resourceType").is(resourceType)),
                        UserVerticalPermission.class)
                .stream()
                .map(userVerticalPermission -> modelMapper.map(userVerticalPermission, UserVerticalPermissionDto.class))
                .toList();
    }

}
