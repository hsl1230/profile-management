package com.telus.dl.profilemanagement.service;

import com.telus.core.errorhandling.exception.EntityNotFoundException;
import com.telus.dl.profilemanagement.document.UserVerticalEnablement;
import com.telus.dl.profilemanagement.document.UserVerticalId;
import com.telus.dl.profilemanagement.dto.CreateUserVerticalEnablementRequest;
import com.telus.dl.profilemanagement.dto.UserVerticalEnablementDto;
import com.telus.dl.profilemanagement.repository.UserVerticalEnablementRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserVerticalEnablementService {
    private final UserVerticalEnablementRepository userVerticalEnablementRepository;
    private final UserProfileService userProfileService;
    private final VerticalRoleService verticalRoleService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserVerticalEnablementService(
            UserVerticalEnablementRepository userVerticalEnablementRepository,
            UserProfileService userProfileService,
            VerticalRoleService verticalRoleService,
            ModelMapper modelMapper) {
        this.userVerticalEnablementRepository = userVerticalEnablementRepository;
        this.userProfileService = userProfileService;
        this.verticalRoleService = verticalRoleService;
        this.modelMapper = modelMapper;
    }

    public UserVerticalEnablementDto createUserVerticalEnablement(
            String verticalId,
            String userProfileId,
            CreateUserVerticalEnablementRequest createUserVerticalEnablementRequest) {

        userProfileService.assertUserProfileExists(userProfileId);
        verticalRoleService.assertVerticalRoleExists(verticalId, createUserVerticalEnablementRequest.roleCode());

        UserVerticalEnablement userVerticalEnablement =
                modelMapper.map(createUserVerticalEnablementRequest, UserVerticalEnablement.class);
        userVerticalEnablement.setId(new UserVerticalId(verticalId, userProfileId));
        userVerticalEnablement = userVerticalEnablementRepository.save(userVerticalEnablement);
        return modelMapper.map(userVerticalEnablement, UserVerticalEnablementDto.class);
    }

    public void deleteUserVerticalEnablement(String verticalId, String userProfileId) {
        userVerticalEnablementRepository.deleteById(
                UserVerticalId.builder().verticalId(verticalId).userProfileId(userProfileId).build());
    }

    public UserVerticalEnablementDto findUserVerticalEnablementById(String verticalId, String userProfileId) {
        return userVerticalEnablementRepository
                .findById(UserVerticalId.builder().verticalId(verticalId).userProfileId(userProfileId).build())
                .map(userVerticalEnablement -> modelMapper.map(userVerticalEnablement, UserVerticalEnablementDto.class))
                .orElseThrow(() -> new EntityNotFoundException(
                        "No user vertical enablement found for verticalId=" + verticalId + ", userProfileId=" + userProfileId));
    }

    public void assertUserVerticalEnablementExists(String verticalId, String userProfileId) {
        findUserVerticalEnablementById(verticalId, userProfileId);
    }
}
