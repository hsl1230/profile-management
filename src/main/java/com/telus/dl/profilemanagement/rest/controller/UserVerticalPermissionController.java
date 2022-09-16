package com.telus.dl.profilemanagement.rest.controller;

import com.telus.dl.profilemanagement.dto.permission.PermissionDto;
import com.telus.dl.profilemanagement.dto.permission.UserVerticalPermissionDto;
import com.telus.dl.profilemanagement.service.UserVerticalPermissionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/profile-management/verticals/{verticalId}/user-profiles/{userProfileId}/permissions")
@Validated
public class UserVerticalPermissionController {
    private final UserVerticalPermissionService userVerticalPermissionService;

    @Autowired
    public UserVerticalPermissionController(UserVerticalPermissionService userVerticalPermissionService) {
        this.userVerticalPermissionService = userVerticalPermissionService;
    }

    @Operation(
            tags = {"User Vertical Permissions"},
            summary = "grant permissions to the user profile",
            description = """
                    permissions can be granted to the users(including sub user profile / user profile link) directly.
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
    public List<UserVerticalPermissionDto> grantPermissionsToUser(
            @PathVariable("verticalId") String verticalId,
            @PathVariable("userProfileId") String userProfileId,
            @Valid @RequestBody List<PermissionDto> permissions) {
        return userVerticalPermissionService.grantPermissionsToUserVertical(verticalId, userProfileId, permissions);
    }

    @Operation(
            tags = {"User Vertical Permissions"},
            summary = "get a list of permissions granted to the user profile on the specified vertical"
    )
    @GetMapping()
    public List<UserVerticalPermissionDto> findUserVerticalPermissions(
            @PathVariable("verticalId") String verticalId,
            @PathVariable("userProfileId") String userProfileId) {
        return userVerticalPermissionService.findUserVerticalPermissions(verticalId, userProfileId);
    }

    @Operation(
            tags = {"User Vertical Permissions"},
            summary = "delete a specified list of user vertical permissions from the vertical"
    )
    @DeleteMapping
    public void deleteUserVerticalPermissions(
            @PathVariable("verticalId") String verticalId,
            @PathVariable("userProfileId") String userProfileId,
            @Valid @RequestBody List<String> permissionIds) {
        userVerticalPermissionService.deleteUserVerticalPermissions(permissionIds);
    }

    @Operation(
            tags = {"User Vertical Permissions"},
            summary = "delete all user vertical permissions from the vertical"
    )
    @DeleteMapping("/all")
    public void deleteAllUserVerticalPermissions(
            @PathVariable("verticalId") String verticalId,
            @PathVariable("userProfileId") String userProfileId) {
        userVerticalPermissionService.deleteAllUserVerticalPermissions(verticalId, userProfileId);
    }
}
