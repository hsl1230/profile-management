package com.telus.dl.profilemanagement.service;

import com.telus.core.errorhandling.exception.EntityNotFoundException;
import com.telus.dl.profilemanagement.document.VerticalRole;
import com.telus.dl.profilemanagement.document.VerticalRoleId;
import com.telus.dl.profilemanagement.dto.CreateVerticalRoleRequest;
import com.telus.dl.profilemanagement.dto.VerticalRoleDto;
import com.telus.dl.profilemanagement.repository.VerticalRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerticalRoleService {
    private final VerticalRoleRepository verticalRoleRepository;
    private final VerticalService verticalService;
    private final ModelMapper modelMapper;

    @Autowired
    public VerticalRoleService(
            VerticalRoleRepository verticalRoleRepository,
            VerticalService verticalService,
            ModelMapper modelMapper) {
        this.verticalRoleRepository = verticalRoleRepository;
        this.verticalService = verticalService;
        this.modelMapper = modelMapper;
    }

    public VerticalRoleDto createVerticalRole(
            String verticalId,
            CreateVerticalRoleRequest createVerticalRoleRequest) {
        verticalService.assertVerticalExists(verticalId);

        VerticalRole verticalRole = modelMapper.map(createVerticalRoleRequest, VerticalRole.class);
        verticalRole.getId().verticalId(verticalId);
        verticalRole = verticalRoleRepository.save(verticalRole);
        return modelMapper.map(verticalRole, VerticalRoleDto.class);
    }

    public void deleteVerticalRole(String verticalId, String roleCode) {
        verticalRoleRepository.deleteById(VerticalRoleId.builder().verticalId(verticalId).roleCode(roleCode).build());
    }

    public VerticalRoleDto findVerticalRoleById(String verticalId, String roleCode) {
        return verticalRoleRepository
                .findById(VerticalRoleId.builder().verticalId(verticalId).roleCode(roleCode).build())
                .map(verticalRole -> modelMapper.map(verticalRole, VerticalRoleDto.class))
                .orElseThrow(() -> new EntityNotFoundException(
                        "vertical role not found verticalId=" + verticalId + ", roleCode=" + roleCode));
    }

    public void assertVerticalRoleExists(String verticalId, String roleCode) {
        findVerticalRoleById(verticalId, roleCode);
    }

    public List<VerticalRoleDto> findVerticalRoles(String verticalId) {
        return verticalRoleRepository.findByIdVerticalId(verticalId).stream()
                .map(verticalRole -> modelMapper.map(verticalRole, VerticalRoleDto.class))
                .toList();
    }
}
