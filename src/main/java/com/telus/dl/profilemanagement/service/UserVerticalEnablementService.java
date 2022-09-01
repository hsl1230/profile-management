package com.telus.dl.profilemanagement.service;

import com.telus.dl.profilemanagement.document.UserVerticalEnablement;
import com.telus.dl.profilemanagement.document.UserVerticalId;
import com.telus.dl.profilemanagement.dto.UserVerticalEnablementDto;
import com.telus.dl.profilemanagement.repository.UserVerticalEnablementRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserVerticalEnablementService {
    private final UserVerticalEnablementRepository userVerticalEnablementRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserVerticalEnablementService(
            UserVerticalEnablementRepository userVerticalEnablementRepository,
            ModelMapper modelMapper) {
        this.userVerticalEnablementRepository = userVerticalEnablementRepository;
        this.modelMapper = modelMapper;
    }

    public UserVerticalEnablementDto createUserVerticalEnablement(
            UserVerticalEnablementDto userVerticalEnablementDto) {
        UserVerticalEnablement userVerticalEnablement =
                modelMapper.map(userVerticalEnablementDto, UserVerticalEnablement.class);
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
                .orElse(null);
    }
}
