package com.telus.dl.profilemanagement.service;

import com.telus.dl.profilemanagement.document.UserProfileLink;
import com.telus.dl.profilemanagement.document.PrimaryUserProfile;
import com.telus.dl.profilemanagement.document.SubUserProfile;
import com.telus.dl.profilemanagement.document.UserProfile;
import com.telus.dl.profilemanagement.document.UserProfileType;
import com.telus.dl.profilemanagement.dto.AbstractUserProfileDto;
import com.telus.dl.profilemanagement.dto.CreatePrimaryUserProfileRequest;
import com.telus.dl.profilemanagement.dto.CreateSubUserProfileRequest;
import com.telus.dl.profilemanagement.dto.PropertyDto;
import com.telus.dl.profilemanagement.dto.PrimaryUserProfileDto;
import com.telus.dl.profilemanagement.dto.ProfileStatus;
import com.telus.dl.profilemanagement.dto.SubUserProfileDto;
import com.telus.dl.profilemanagement.dto.UpdateUserProfileRequest;
import com.telus.dl.profilemanagement.dto.UserProfileLinkDto;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserProfileService {
    private final MongoTemplate mongoTemplate;
    private final ModelMapper modelMapper;

    @Autowired
    public UserProfileService(
            MongoTemplate mongoTemplate,
            ModelMapper modelMapper) {
        this.mongoTemplate = mongoTemplate;
        this.modelMapper = modelMapper;
    }

    public List<AbstractUserProfileDto> findUserProfilesByMyTelusId(String myTelusId) {
        List<UserProfile> list = mongoTemplate.find(new Query().addCriteria(Criteria
                        .where("myTelusId")
                        .is(myTelusId)), UserProfile.class);
        List<AbstractUserProfileDto> result = new ArrayList<>();
        list.forEach(userProfile -> {
            if (userProfile instanceof PrimaryUserProfile) {
                result.add(modelMapper.map(userProfile, PrimaryUserProfileDto.class));
            } else {
                SubUserProfileDto subUserProfileDto = modelMapper.map(userProfile, SubUserProfileDto.class);

                subUserProfileDto.primaryUserProfile(
                        findPrimaryUserProfileById(
                                ((SubUserProfile)userProfile).getPrimaryUserProfileId()
                        )
                );
                result.add(subUserProfileDto);
            }
        });

        return result;
    }

    private PrimaryUserProfileDto findPrimaryUserProfileById(String id) {
        PrimaryUserProfile primaryUserProfile = mongoTemplate.findOne(new Query().addCriteria(Criteria
                        .where("id")
                        .is(id)
                        .and("userProfileType")
                        .is(UserProfileType.PRIMARY)), PrimaryUserProfile.class);
        if (primaryUserProfile == null) {
            return null;
        } else {
            return modelMapper.map(primaryUserProfile, PrimaryUserProfileDto.class);
        }
    }

    public List<PrimaryUserProfileDto> findPrimaryUserProfilesByMyTelusId(String myTelusId) {
        return mongoTemplate.find(new Query().addCriteria(Criteria
                        .where("myTelusId")
                        .is(myTelusId)
                        .and("userProfileType")
                        .is(UserProfileType.PRIMARY)), PrimaryUserProfile.class)
                .stream().map(
                        primaryUserProfile -> modelMapper.map(primaryUserProfile, PrimaryUserProfileDto.class))
                .toList();
    }

    public List<SubUserProfileDto> getSubUserProfilesByPrimaryUserProfileId(String primaryUserProfileId) {
        PrimaryUserProfileDto primaryUserProfileDto = findPrimaryUserProfileById(primaryUserProfileId);
        return mongoTemplate.find(new Query().addCriteria(Criteria
                        .where("primaryUserProfileId")
                        .is(primaryUserProfileId)
                        .and("userProfileType")
                        .is(UserProfileType.SUB)), SubUserProfile.class)
                .stream().map(
                        subUserProfile -> modelMapper.map(subUserProfile, SubUserProfileDto.class)
                                .primaryUserProfile(primaryUserProfileDto))
                .toList();
    }

    public PrimaryUserProfileDto createPrimaryUserProfile(
            CreatePrimaryUserProfileRequest createPrimaryUserProfileRequest) {
        PrimaryUserProfile primaryUserProfile = modelMapper.map(createPrimaryUserProfileRequest, PrimaryUserProfile.class);
        primaryUserProfile.setStatus(ProfileStatus.ACTIVE);

        primaryUserProfile = mongoTemplate.insert(primaryUserProfile);
        return modelMapper.map(primaryUserProfile, PrimaryUserProfileDto.class);
    }

    public SubUserProfileDto createSubUserProfile(
            String primaryUserProfileId,
            CreateSubUserProfileRequest createSubUserProfileRequest) {
        SubUserProfile subUserProfile = modelMapper.map(createSubUserProfileRequest, SubUserProfile.class);
        subUserProfile.setStatus(ProfileStatus.ACTIVE);
        subUserProfile.setPrimaryUserProfileId(primaryUserProfileId);

        subUserProfile = mongoTemplate.insert(subUserProfile);

        return modelMapper.map(subUserProfile, SubUserProfileDto.class)
                .primaryUserProfile((PrimaryUserProfileDto)new PrimaryUserProfileDto().id(primaryUserProfileId));
    }

    public UserProfileLinkDto createUserProfileLink(String primaryUserProfileId, String linkedUserProfileId) {
        UserProfileLink userProfileLink = new UserProfileLink()
                .primaryUserProfileId(primaryUserProfileId)
                .linkedUserProfileId(linkedUserProfileId)
                .status(ProfileStatus.ACTIVE);

        userProfileLink = mongoTemplate.insert(userProfileLink);

        return modelMapper.map(userProfileLink, UserProfileLinkDto.class);
    }

    public List<UserProfileLinkDto> findLinkedUserProfiles(String linkedUserProfileId) {
        return mongoTemplate.find(new Query().addCriteria(Criteria
                        .where("linkedUserProfileId")
                        .is(linkedUserProfileId)
                        .and("userProfileType")
                        .is(UserProfileType.LINK)), UserProfileLink.class)
                .stream()
                .map(userProfileLink -> modelMapper.map(userProfileLink, UserProfileLinkDto.class)
                        .primaryUserProfile(findPrimaryUserProfileById(userProfileLink.primaryUserProfileId())))
                .toList();
    }

    public void removeUserProfile(String userProfileId) {
        mongoTemplate.remove(new Query().addCriteria(Criteria
                .where("id")
                .is(userProfileId)), UserProfile.class);
    }

    public void updateUserProfile(String userProfileId, UpdateUserProfileRequest updateUserProfileRequest) {
        Update update = new Update();
        if (StringUtils.isNotBlank(updateUserProfileRequest.firstName())) {
            update.set("firstName", updateUserProfileRequest.firstName());
        }
        if (StringUtils.isNotBlank(updateUserProfileRequest.middleName())) {
            update.set("middleName", updateUserProfileRequest.middleName());
        }
        if (StringUtils.isNotBlank(updateUserProfileRequest.lastName())) {
            update.set("lastName", updateUserProfileRequest.lastName());
        }
        if (StringUtils.isNotBlank(updateUserProfileRequest.phoneNumber())) {
            update.set("phoneNumber", updateUserProfileRequest.phoneNumber());
        }
        if (StringUtils.isNotBlank(updateUserProfileRequest.email())) {
            update.set("email", updateUserProfileRequest.email());
        }

        mongoTemplate.updateFirst(new Query().addCriteria(Criteria
                .where("id")
                .is(userProfileId)
                .and("userProfileType")
                .in(UserProfileType.SUB, UserProfileType.PRIMARY)), update, UserProfile.class);
    }

    public void bindMyTelusId(String subUserProfileId, String myTelusId) {
        Update update = new Update();
        update.set("myTelusId", myTelusId);
        mongoTemplate.updateFirst(new Query().addCriteria(Criteria
                .where("id")
                .is(subUserProfileId)
                .and("userProfileType")
                .is(UserProfileType.SUB)), update, SubUserProfile.class);
    }

    public void updateHomeAddress(String primaryUserProfileId, PropertyDto homeAddressDto) {
        PrimaryUserProfile.Property homeAddress = modelMapper.map(homeAddressDto, PrimaryUserProfile.Property.class);
        Update update = new Update();
        update.set("homeAddress", homeAddress);
        mongoTemplate.updateFirst(new Query().addCriteria(Criteria
                .where("id")
                .is(primaryUserProfileId)
                .and("userProfileType")
                .is(UserProfileType.PRIMARY)), update, PrimaryUserProfile.class);
    }
}
