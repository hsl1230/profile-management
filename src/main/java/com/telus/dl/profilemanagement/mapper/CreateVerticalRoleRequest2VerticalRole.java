package com.telus.dl.profilemanagement.mapper;

import com.telus.core.modelmapping.PropertyMapper;
import com.telus.dl.profilemanagement.document.VerticalRole;
import com.telus.dl.profilemanagement.dto.CreateVerticalRoleRequest;
import org.modelmapper.PropertyMap;

@PropertyMapper
public class CreateVerticalRoleRequest2VerticalRole
        extends PropertyMap<CreateVerticalRoleRequest, VerticalRole> {
    @Override
    protected void configure() {
        map().getId().roleCode(source.roleCode());
    }
}
