package com.telus.dl.profilemanagement.mapper;

import com.telus.core.modelmapping.PropertyMapper;
import com.telus.dl.profilemanagement.document.permission.UserVerticalPermission;
import com.telus.dl.profilemanagement.dto.permission.UserVerticalPermissionDto;
import org.modelmapper.PropertyMap;

@PropertyMapper
public class UserVerticalPermission2UserVerticalPermissionDto
        extends PropertyMap<UserVerticalPermission, UserVerticalPermissionDto> {
    @Override
    protected void configure() {
        map().setVerticalId(source.userVerticalId().getVerticalId());
        map().setUserProfileId(source.userVerticalId().getUserProfileId());
    }
}
