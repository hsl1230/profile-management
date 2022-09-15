package com.telus.dl.profilemanagement.rest.controller;

import com.telus.dl.profilemanagement.dto.CreateVerticalRoleRequest;
import com.telus.dl.profilemanagement.dto.VerticalRoleDto;
import com.telus.dl.profilemanagement.service.VerticalRoleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/profile-management/verticals/{verticalId}/roles")
@Validated
public class VerticalRoleController {
    private final VerticalRoleService verticalRoleService;

    public VerticalRoleController(VerticalRoleService verticalRoleService) {
        this.verticalRoleService = verticalRoleService;
    }

    @Operation(
            tags = {"Vertical Role"},
            summary = "get a list of roles related to the vertical"
    )
    @GetMapping
    public List<VerticalRoleDto> findVerticalRoles(
            @PathVariable("verticalId") String verticalId) {
        return verticalRoleService.findVerticalRoles(verticalId);
    }

    @Operation(
            tags = {"Vertical Role"},
            summary = "create a vertical role"
    )
    @PostMapping
    public VerticalRoleDto createVerticalRole(
            @PathVariable("verticalId") String verticalId,
            @Valid @RequestBody CreateVerticalRoleRequest createVerticalRoleRequest) {
        return verticalRoleService.createVerticalRole(verticalId, createVerticalRoleRequest);
    }

    @Operation(
            tags = {"Vertical Role"},
            summary = "delete a vertical role"
    )
    @DeleteMapping("/{roleCode}")
    public void deleteVerticalRole(
            @PathVariable("verticalId") String verticalId,
            @PathVariable("roleCode") String roleCode
    ) {
        verticalRoleService.deleteVerticalRole(verticalId, roleCode);
    }
}
