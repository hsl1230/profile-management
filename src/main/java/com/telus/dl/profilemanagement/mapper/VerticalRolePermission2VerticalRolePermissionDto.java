package com.telus.dl.profilemanagement.mapper;

import com.telus.core.modelmapping.PropertyMapper;
import com.telus.dl.profilemanagement.document.permission.VerticalRolePermission;
import com.telus.dl.profilemanagement.dto.permission.VerticalRolePermissionDto;
import org.modelmapper.PropertyMap;

@PropertyMapper
public class VerticalRolePermission2VerticalRolePermissionDto
        extends PropertyMap<VerticalRolePermission, VerticalRolePermissionDto> {
    @Override
    protected void configure() {
        map().setVerticalId(source.verticalRoleId().verticalId());
        map().setRoleCode(source.verticalRoleId().roleCode());
    }
}
