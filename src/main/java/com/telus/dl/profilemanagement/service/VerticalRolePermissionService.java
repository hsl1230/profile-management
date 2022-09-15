package com.telus.dl.profilemanagement.service;

import com.telus.dl.profilemanagement.document.VerticalRoleId;
import com.telus.dl.profilemanagement.document.permission.VerticalRolePermission;
import com.telus.dl.profilemanagement.dto.permission.PermissionDto;
import com.telus.dl.profilemanagement.dto.permission.VerticalRolePermissionDto;
import com.telus.dl.profilemanagement.repository.VerticalRolePermissionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerticalRolePermissionService {
    private final VerticalRolePermissionRepository verticalRolePermissionRepository;
    private final VerticalRoleService verticalRoleService;
    private final ModelMapper modelMapper;

    @Autowired
    public VerticalRolePermissionService(
            VerticalRolePermissionRepository verticalRolePermissionRepository,
            VerticalRoleService verticalRoleService,
            ModelMapper modelMapper) {
        this.verticalRolePermissionRepository = verticalRolePermissionRepository;
        this.verticalRoleService = verticalRoleService;
        this.modelMapper = modelMapper;
    }

    public List<VerticalRolePermissionDto> grantPermissionsToVerticalRole(
            String verticalId,
            String roleCode,
            List<PermissionDto> permissionDtos) {
        verticalRoleService.assertVerticalRoleExists(verticalId, roleCode);

        List<VerticalRolePermission> VerticalRolePermissions = permissionDtos
                .stream()
                .map(permissionDto -> modelMapper.map(permissionDto, VerticalRolePermission.class)
                        .verticalRoleId(VerticalRoleId.builder()
                                .verticalId(verticalId)
                                .roleCode(roleCode)
                                .build()))
                .toList();
        return verticalRolePermissionRepository.saveAll(VerticalRolePermissions)
                .stream()
                .map(VerticalRolePermission -> modelMapper.map(VerticalRolePermission, VerticalRolePermissionDto.class))
                .toList();
    }

    public void deleteVerticalRolePermissions(List<String> permissionIds) {
        verticalRolePermissionRepository.deleteAllById(permissionIds);
    }

    public void deleteAllVerticalRolePermissions(
            String verticalId,
            String roleCode) {
        verticalRolePermissionRepository.deleteByVerticalRoleId(VerticalRoleId
                .builder().
                verticalId(verticalId).
                roleCode(roleCode)
                .build());
    }

    public List<VerticalRolePermissionDto> findVerticalRolePermissions(
            String verticalId,
            String roleCode) {
        return verticalRolePermissionRepository
                .findByVerticalRoleId(VerticalRoleId
                        .builder().
                        verticalId(verticalId).
                        roleCode(roleCode)
                        .build())
                .stream()
                .map(VerticalRolePermission -> modelMapper.map(VerticalRolePermission, VerticalRolePermissionDto.class))
                .toList();
    }
}
