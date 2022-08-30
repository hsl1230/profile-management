package com.telus.dl.profilemanagement.service;

import com.telus.dl.profilemanagement.document.PrimaryUserProfile;
import com.telus.dl.profilemanagement.document.SubUserProfile;
import com.telus.dl.profilemanagement.document.UserProfile;
import com.telus.dl.profilemanagement.dto.BaseUserProfile;
import com.telus.dl.profilemanagement.dto.CreatePrimaryUserProfileRequest;
import com.telus.dl.profilemanagement.dto.CreateSubUserProfileRequest;
import com.telus.dl.profilemanagement.dto.HomeAddressDto;
import com.telus.dl.profilemanagement.dto.PrimaryUserProfileDto;
import com.telus.dl.profilemanagement.dto.ProfileStatus;
import com.telus.dl.profilemanagement.dto.SubUserProfileDto;
import com.telus.dl.profilemanagement.dto.UpdateUserProfileRequest;
import com.telus.dl.profilemanagement.repository.PrimaryUserProfileRepository;
import com.telus.dl.profilemanagement.repository.SubUserProfileRepository;
import com.telus.dl.profilemanagement.repository.UserProfileRepository;
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
    private final UserProfileRepository userProfileRepository;
    private final PrimaryUserProfileRepository primaryUserProfileRepository;
    private final SubUserProfileRepository subUserProfileRepository;
    private final MongoTemplate mongoTemplate;
    private final ModelMapper modelMapper;

    @Autowired
    public UserProfileService(
            UserProfileRepository userProfileRepository,
            PrimaryUserProfileRepository primaryUserProfileRepository,
            SubUserProfileRepository subUserProfileRepository,
            MongoTemplate mongoTemplate,
            ModelMapper modelMapper) {
        this.userProfileRepository = userProfileRepository;
        this.primaryUserProfileRepository = primaryUserProfileRepository;
        this.subUserProfileRepository = subUserProfileRepository;
        this.mongoTemplate = mongoTemplate;
        this.modelMapper = modelMapper;
    }

    public List<BaseUserProfile> getUserProfilesByMytelusId(String myTelusId) {
        List<UserProfile> list = userProfileRepository.findByMyTelusId(myTelusId);
        List<BaseUserProfile> result = new ArrayList<>();
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
        PrimaryUserProfile primaryUserProfile = primaryUserProfileRepository.findById(id).orElse(null);
        if (primaryUserProfile == null) {
            return null;
        } else {
            return modelMapper.map(primaryUserProfile, PrimaryUserProfileDto.class);
        }
    }

    public List<PrimaryUserProfileDto> getPrimaryUserProfilesByMytelusId(String myTelusId) {
        return primaryUserProfileRepository
                .findByMyTelusId(myTelusId).stream().map(
                        primaryUserProfile -> modelMapper.map(primaryUserProfile, PrimaryUserProfileDto.class))
                .toList();
    }

    public List<SubUserProfileDto> getSubUserProfilesByPrimaryUserProfileId(String primaryUserProfileId) {
        PrimaryUserProfileDto primaryUserProfileDto = findPrimaryUserProfileById(primaryUserProfileId);
        return subUserProfileRepository
                .findByPrimaryUserProfileId(primaryUserProfileId).stream().map(
                        subUserProfile -> modelMapper.map(subUserProfile, SubUserProfileDto.class)
                                .primaryUserProfile(primaryUserProfileDto))
                .toList();
    }

    public PrimaryUserProfileDto createPrimaryUserProfile(
            CreatePrimaryUserProfileRequest createPrimaryUserProfileRequest) {
        PrimaryUserProfile primaryUserProfile = modelMapper.map(createPrimaryUserProfileRequest, PrimaryUserProfile.class);
        primaryUserProfile.setStatus(ProfileStatus.ACTIVE);

        primaryUserProfile = primaryUserProfileRepository.save(primaryUserProfile);
        return modelMapper.map(primaryUserProfile, PrimaryUserProfileDto.class);
    }

    public SubUserProfileDto createSubUserProfile(
            String primaryUserProfileId,
            CreateSubUserProfileRequest createSubUserProfileRequest) {
        SubUserProfile subUserProfile = modelMapper.map(createSubUserProfileRequest, SubUserProfile.class);
        subUserProfile.setStatus(ProfileStatus.ACTIVE);
        subUserProfile.setPrimaryUserProfileId(primaryUserProfileId);
        subUserProfile = subUserProfileRepository.save(subUserProfile);

        PrimaryUserProfileDto primaryUserProfileDto = findPrimaryUserProfileById(primaryUserProfileId);

        return modelMapper.map(subUserProfile, SubUserProfileDto.class).primaryUserProfile(primaryUserProfileDto);
    }

    public void updateUserProfile(String userProfileId, UpdateUserProfileRequest updateUserProfileRequest) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userProfileId));
        Update update = new Update();
        if (StringUtils.isNotBlank(updateUserProfileRequest.firstName())) {
            update.set("firstName", updateUserProfileRequest.firstName());
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
        mongoTemplate.updateFirst(query, update, UserProfile.class);
    }

    public void bindMyTelusId(String subUserProfileId, String myTelusId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(subUserProfileId));
        Update update = new Update();
        update.set("myTelusId", myTelusId);
        mongoTemplate.updateFirst(query, update, SubUserProfile.class);
    }

    public void updateHomeAddress(String primaryUserProfileId, HomeAddressDto homeAddressDto) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(primaryUserProfileId));
        Update update = new Update();

        PrimaryUserProfile.HomeAddress homeAddress = modelMapper.map(homeAddressDto, PrimaryUserProfile.HomeAddress.class);
        update.set("homeAddress", homeAddress);
        mongoTemplate.updateFirst(query, update, PrimaryUserProfile.class);
    }

    public void removeSubUserProfile(String subUserProfileId) {
        subUserProfileRepository.deleteById(subUserProfileId);
    }
}
