package com.telus.dl.profilemanagement.mapper;

import com.telus.core.modelmapping.PropertyMapper;
import com.telus.dl.profilemanagement.document.attribute.UserAttribute;
import com.telus.dl.profilemanagement.dto.attribute.UserAttributeDto;
import org.modelmapper.PropertyMap;

@PropertyMapper
public class UserAttribute2UserAttributeDto
        extends PropertyMap<UserAttribute, UserAttributeDto> {
    @Override
    protected void configure() {
        map().setUserProfileId(source.id().getUserProfileId());
        map().getAttribute().setName(source.id().getName());
        map().getAttribute().setValue(source.value());
    }
}
