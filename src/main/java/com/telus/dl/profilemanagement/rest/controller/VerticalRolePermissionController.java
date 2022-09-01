package com.telus.dl.profilemanagement.rest.controller;

import com.telus.dl.profilemanagement.dto.permission.PermissionDto;
import com.telus.dl.profilemanagement.dto.permission.VerticalRolePermissionDto;
import com.telus.dl.profilemanagement.service.VerticalRolePermissionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/profile-management/verticals/{verticalId}/roles/{roleCode}/permissions")
public class VerticalRolePermissionController {
    private final VerticalRolePermissionService verticalRolePermissionService;

    public VerticalRolePermissionController(VerticalRolePermissionService verticalRolePermissionService) {
        this.verticalRolePermissionService = verticalRolePermissionService;
    }

    @Operation(
            tags = {"Vertical Role Permissions"},
            summary = "grant permissions to the role",
            description = """
                    permissions can be granted to the user directly.
                         \s
                          Declare <Who>
                            sub user profile
                          is allowed to use <a feature/action>\s
                            Any feature
                            Watch live stream
                            Zoom in/out\s
                            Unlock the door
                            Adjust the temperature
                          of <a resource>\s
                            Any device
                            Device group / Device class?
                            The lock
                            The camera
                            The light
                            The System
                          in which <period of time>\s
                            Any time
                            From 9am to 5pm
                          in which <period of date>\s
                            Any Date
                            Every Monday, Wednesday, Friday
                            Every 1, 2, 21
                          effective from <start date> to <end date>"""
    )
    @PostMapping()
    public List<VerticalRolePermissionDto> grantPermissionsToRole(
            @PathVariable("verticalId") String verticalId,
            @PathVariable("roleCode") String roleCode,
            @RequestBody List<PermissionDto> permissions) {
        return verticalRolePermissionService.grantPermissionsToVerticalRole(verticalId, roleCode, permissions);
    }

    @Operation(
            tags = {"Vertical Role Permissions"},
            summary = "get a list of permissions granted to the vertical role"
    )
    @GetMapping()
    public List<VerticalRolePermissionDto> findVerticalRolePermissions(
            @PathVariable("verticalId") String verticalId,
            @PathVariable("roleCode") String roleCode) {
        return verticalRolePermissionService.findVerticalRolePermissions(verticalId, roleCode);
    }

    @Operation(
            tags = {"Vertical Role Permissions"},
            summary = "delete a specified list of permissions from the vertical role"
    )
    @DeleteMapping
    public void deleteVerticalRolePermissions(
            @PathVariable("verticalId") String verticalId,
            @PathVariable("roleCode") String roleCode,
            @RequestBody List<String> permissionIds) {
        verticalRolePermissionService.deleteVerticalRolePermissions(permissionIds);
    }

    @Operation(
            tags = {"Vertical Role Permissions"},
            summary = "delete all user vertical role permissions"
    )
    @DeleteMapping("/all")
    public void deleteAllVerticalRolePermissions(
            @PathVariable("verticalId") String verticalId,
            @PathVariable("roleCode") String roleCode) {
        verticalRolePermissionService.deleteAllVerticalRolePermissions(verticalId, roleCode);
    }
}
