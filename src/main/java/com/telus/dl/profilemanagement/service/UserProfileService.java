package com.telus.dl.profilemanagement.service;

import com.telus.core.errorhandling.PlatformErrorCode;
import com.telus.core.errorhandling.exception.BadRequestException;
import com.telus.core.errorhandling.exception.EntityNotFoundException;
import com.telus.dl.profilemanagement.document.userprofile.UserProfile;
import com.telus.dl.profilemanagement.document.userprofile.UserProfileType;
import com.telus.dl.profilemanagement.dto.userprofile.UserProfileDto;
import com.telus.dl.profilemanagement.dto.userprofile.CreateUserProfileRequest;
import com.telus.dl.profilemanagement.dto.userprofile.ProfileStatus;
import com.telus.dl.profilemanagement.dto.userprofile.UpdateUserProfileRequest;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {
    private static final String HOUSEHOLD_ID = "householdId";
    private final MongoTemplate mongoTemplate;
    private final ModelMapper modelMapper;

    @Autowired
    public UserProfileService(
            MongoTemplate mongoTemplate,
            ModelMapper modelMapper) {
        this.mongoTemplate = mongoTemplate;
        this.modelMapper = modelMapper;
    }

    public void assertUserProfileExists(String userProfileId) {
        findUserProfileById(userProfileId);
    }

    public UserProfileDto findUserProfileById(String id) {
        UserProfile userProfile = mongoTemplate.findOne(new Query().addCriteria(Criteria
                .where("id")
                .is(id)), UserProfile.class);

        if (userProfile == null) {
            throw new EntityNotFoundException("No user profile found related to the userProfileId " + id);
        }

        return modelMapper.map(userProfile, UserProfileDto.class);
    }

    private Criteria buildCriteria(String myTelusId, String linkedUserProfileId, String householdId) {
        if (StringUtils.isNotBlank(myTelusId)) {
            Criteria criteria = Criteria.where("myTelusId").is(myTelusId);
            if (StringUtils.isNotBlank(linkedUserProfileId)) {
                criteria = criteria.and("linkedUserProfileId").is(linkedUserProfileId);
            }
            if (StringUtils.isNotBlank(householdId)) {
                criteria = criteria.and(HOUSEHOLD_ID).is(householdId);
            }
            return criteria;
        } else if (StringUtils.isNotBlank(linkedUserProfileId)) {
            Criteria criteria = Criteria.where("linkedUserProfileId").is(linkedUserProfileId);
            if (StringUtils.isNotBlank(householdId)) {
                criteria = criteria.and(HOUSEHOLD_ID).is(householdId);
            }
            return criteria;
        } else if (StringUtils.isNotBlank(householdId)) {
            return Criteria.where(HOUSEHOLD_ID).is(householdId);
        } else {
            throw new BadRequestException(PlatformErrorCode.REQUEST_PARAM_MISSING,
            new RuntimeException("you need to provide at least one of these: myTelusId, linkedUserProfileId and householdId"));
        }
    }

    public List<UserProfileDto> findUserProfiles(String myTelusId, String linkedUserProfileId,
            List<UserProfileType> userProfileTypes, String householdId) {
        Criteria criteria = buildCriteria(myTelusId, linkedUserProfileId, householdId);
        if (userProfileTypes != null && !userProfileTypes.isEmpty()) {
            criteria = criteria.and("userProfileType").in(userProfileTypes);
        }
        List<UserProfile> list = mongoTemplate.find(new Query().addCriteria(criteria),
                UserProfile.class);
        return list.stream().map(userProfile -> modelMapper.map(userProfile, UserProfileDto.class))
                .toList();
    }

    public UserProfileDto createUserProfile(
            CreateUserProfileRequest createUserProfileRequest) {
        UserProfile userProfile = modelMapper.map(createUserProfileRequest, UserProfile.class);
        userProfile.status(ProfileStatus.ACTIVE);

        userProfile = mongoTemplate.insert(userProfile);

        return modelMapper.map(userProfile, UserProfileDto.class);
    }

    public void updateUserProfile(String userProfileId, UpdateUserProfileRequest updateUserProfileRequest) {
        assertUserProfileExists(userProfileId);

        Update update = new Update();
        if (StringUtils.isNotBlank(updateUserProfileRequest.userName())) {
            update.set("userName", updateUserProfileRequest.userName());
        }
        if (StringUtils.isNotBlank(updateUserProfileRequest.myTelusId())) {
            update.set("myTelusId", updateUserProfileRequest.myTelusId());
        }
        if (StringUtils.isNotBlank(updateUserProfileRequest.phoneNumber())) {
            update.set("phoneNumber", updateUserProfileRequest.phoneNumber());
        }
        if (StringUtils.isNotBlank(updateUserProfileRequest.email())) {
            update.set("email", updateUserProfileRequest.email());
        }
        if (updateUserProfileRequest.status() != null) {
            update.set("status", updateUserProfileRequest.status());
        }

        mongoTemplate.updateFirst(new Query().addCriteria(Criteria
                .where("id")
                .is(userProfileId)), update, UserProfile.class);
    }

    public void removeUserProfile(String userProfileId) {
        assertUserProfileExists(userProfileId);

        mongoTemplate.remove(new Query().addCriteria(Criteria
                .where("id")
                .is(userProfileId)), UserProfile.class);
    }
}
