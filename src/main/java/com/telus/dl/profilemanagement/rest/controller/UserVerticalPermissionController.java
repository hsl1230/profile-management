package com.telus.dl.profilemanagement.rest.controller;

import com.telus.dl.profilemanagement.dto.permission.PermissionDto;
import com.telus.dl.profilemanagement.dto.permission.UserVerticalPermissionDto;
import com.telus.dl.profilemanagement.service.UserVerticalPermissionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/profile-management/verticals/{verticalId}/user-profiles/{userProfileId}/permissions")
public class UserVerticalPermissionController {
    private final UserVerticalPermissionService userVerticalPermissionService;

    @Autowired
    public UserVerticalPermissionController(UserVerticalPermissionService userVerticalPermissionService) {
        this.userVerticalPermissionService = userVerticalPermissionService;
    }

    @Operation(
            tags = {"User Vertical Permissions"},
            summary = "grant permissions to the sub user profile",
            description = "permissions can be granted to the user directly.\n" +
                    "      \n" +
                    "      Declare <Who>\n" +
                    "        sub user profile\n" +
                    "      is allowed to use <a feature/action> \n" +
                    "        Any feature\n" +
                    "        Watch live stream\n" +
                    "        Zoom in/out \n" +
                    "        Unlock the door\n" +
                    "        Adjust the temperature\n" +
                    "      of <a resource> \n" +
                    "        Any device\n" +
                    "        Device group / Device class?\n" +
                    "        The lock\n" +
                    "        The camera\n" +
                    "        The light\n" +
                    "        The System\n" +
                    "      in which <period of time> \n" +
                    "        Any time\n" +
                    "        From 9am to 5pm\n" +
                    "      in which <period of date> \n" +
                    "        Any Date\n" +
                    "        Every Monday, Wednesday, Friday\n" +
                    "        Every 1, 2, 21\n" +
                    "      effective from <start date> to <end date>"
    )
    @PostMapping()
    public List<UserVerticalPermissionDto> grantPermissionsToUser(
            @PathVariable("verticalId") String verticalId,
            @PathVariable("userProfileId") String userProfileId,
            @RequestBody List<PermissionDto> permissions) {
        return userVerticalPermissionService.grantPermissionsToUserVertical(verticalId, userProfileId, permissions);
    }

    @Operation(
            tags = {"User Vertical Permissions"},
            summary = "get a list of permissions granted to the sub user profile on the specified vertical"
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
            @RequestBody List<String> permissionIds) {
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
