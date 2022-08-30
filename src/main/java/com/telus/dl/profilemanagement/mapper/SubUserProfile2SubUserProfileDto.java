package com.telus.dl.profilemanagement.mapper;

import com.telus.dl.profilemanagement.document.SubUserProfile;
import com.telus.dl.profilemanagement.dto.SubUserProfileDto;
import org.modelmapper.PropertyMap;

public class SubUserProfile2SubUserProfileDto extends PropertyMap<SubUserProfile, SubUserProfileDto> {
    @Override
    protected void configure() {
        skip(destination.primaryUserProfile());
    }
}
