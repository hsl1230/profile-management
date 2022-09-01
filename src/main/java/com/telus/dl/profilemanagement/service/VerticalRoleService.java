package com.telus.dl.profilemanagement.service;

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
    private final ModelMapper modelMapper;

    @Autowired
    public VerticalRoleService(VerticalRoleRepository verticalRoleRepository, ModelMapper modelMapper) {
        this.verticalRoleRepository = verticalRoleRepository;
        this.modelMapper = modelMapper;
    }

    public VerticalRoleDto createVerticalRole(
            String verticalId,
            CreateVerticalRoleRequest createVerticalRoleRequest) {
        VerticalRole verticalRole = modelMapper.map(createVerticalRoleRequest, VerticalRole.class);
        verticalRole.getId().setVerticalId(verticalId);
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
                .orElse(null);
    }

    public List<VerticalRoleDto> findVerticalRoles(String verticalId) {
        return verticalRoleRepository.findByIdVerticalId(verticalId).stream()
                .map(verticalRole -> modelMapper.map(verticalRole, VerticalRoleDto.class))
                .toList();
    }
}
