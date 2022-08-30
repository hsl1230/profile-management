package com.telus.dl.profilemanagement.mapper;

import com.telus.dl.profilemanagement.document.PrimaryUserProfile;
import com.telus.dl.profilemanagement.dto.CreatePrimaryUserProfileRequest;
import org.modelmapper.PropertyMap;

public class CreatePrimaryUserProfileRequest2PrimaryUserProfile extends PropertyMap<CreatePrimaryUserProfileRequest, PrimaryUserProfile> {
    @Override
    protected void configure() {
        skip(destination.getId());
    }
}
