package com.telus.dl.profilemanagement.mapper;

import com.telus.core.modelmapping.PropertyMapper;
import com.telus.dl.profilemanagement.document.UserVerticalEnablement;
import com.telus.dl.profilemanagement.dto.UserVerticalEnablementDto;

import org.modelmapper.PropertyMap;

@PropertyMapper
public class UserVerticalEnablement2UserVerticalEnablementDto
        extends PropertyMap<UserVerticalEnablement, UserVerticalEnablementDto> {
    @Override
    protected void configure() {
        map().verticalId(source.getId().getVerticalId());
        map().userProfileId(source.getId().getUserProfileId());
    }
}
