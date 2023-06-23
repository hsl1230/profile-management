package com.telus.dl.profilemanagement.mapper;

import com.telus.core.modelmapping.PropertyMapper;
import com.telus.dl.profilemanagement.document.VerticalRole;
import com.telus.dl.profilemanagement.dto.VerticalRoleDto;

import org.modelmapper.PropertyMap;

@PropertyMapper
public class VerticalRole2VerticalRoleDto
        extends PropertyMap<VerticalRole, VerticalRoleDto> {
    @Override
    protected void configure() {
        map().verticalId(source.getId().verticalId());
        map().roleCode(source.getId().roleCode());
    }
}
