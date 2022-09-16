package com.telus.dl.profilemanagement.service;

import com.telus.core.errorhandling.exception.EntityNotFoundException;
import com.telus.dl.profilemanagement.document.attribute.UserAttribute;
import com.telus.dl.profilemanagement.document.attribute.UserAttributeId;
import com.telus.dl.profilemanagement.dto.attribute.AttributeDto;
import com.telus.dl.profilemanagement.dto.attribute.UserAttributeDto;
import com.telus.dl.profilemanagement.repository.UserAttributeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAttributeService {
    private final UserAttributeRepository userAttributeRepository;
    private final UserProfileService userProfileService;
    private final CryptService cryptService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserAttributeService(
            UserAttributeRepository userAttributeRepository,
            UserProfileService userProfileService,
            CryptService cryptService, ModelMapper modelMapper) {
        this.userAttributeRepository = userAttributeRepository;
        this.userProfileService = userProfileService;
        this.cryptService = cryptService;
        this.modelMapper = modelMapper;
    }

    public UserAttributeDto createPublicUserAttribute(
            String userProfileId,
            AttributeDto attributeDto) {
        userProfileService.assertUserProfileExists(userProfileId);

        UserAttribute userAttribute = new UserAttribute()
                .id(UserAttributeId.builder().userProfileId(userProfileId).name(attributeDto.getName()).build())
                .isPrivate(false)
                .value(attributeDto.getValue());

        userAttribute = userAttributeRepository.save(userAttribute);
        return modelMapper.map(userAttribute, UserAttributeDto.class);
    }

    private String encrypt(Object value) {
        return cryptService.encrypt(value);
    }

    public void createPrivateAttribute(
            String userProfileId,
            AttributeDto attributeDto) {
        userProfileService.assertUserProfileExists(userProfileId);

        UserAttribute userAttribute = new UserAttribute()
                .id(UserAttributeId.builder().userProfileId(userProfileId).name(attributeDto.getName()).build())
                .isPrivate(true)
                .value(encrypt(attributeDto.getValue()));

        userAttributeRepository.save(userAttribute);
    }

    public void deleteUserAttribute(String userProfileId, String name) {
        userAttributeRepository.deleteById(
                UserAttributeId.builder().userProfileId(userProfileId).name(name).build());
    }

    private UserAttribute findPureUserAttributeById(String userProfileId, String name, boolean isPrivate) {
        return userAttributeRepository
                .findByIdAndIsPrivate(UserAttributeId.builder().userProfileId(userProfileId).name(name).build(), isPrivate)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No user attribute found for userProfileId=" + userProfileId + ", name=" + name + ", isPrivate=" + isPrivate));
    }

    public UserAttributeDto findPublicUserAttributeById(String userProfileId, String name) {
        return modelMapper.map(findPureUserAttributeById(userProfileId, name, false), UserAttributeDto.class);
    }

    public UserAttributeDto findUserAttributeById(String userProfileId, String name) {
        return userAttributeRepository
                .findById(UserAttributeId.builder().userProfileId(userProfileId).name(name).build())
                .map(userAttribute -> modelMapper.map(userAttribute, UserAttributeDto.class))
                .orElseThrow(() -> new EntityNotFoundException(
                        "No user attribute found for userProfileId=" + userProfileId + ", name=" + name));
    }

    public UserAttributeDto findPrivateUserAttributeById(String userProfileId, String name) {
        UserAttribute userAttribute = findPureUserAttributeById(userProfileId, name, true);
        userAttribute.value(cryptService.decrypt((String)userAttribute.value()));
        return modelMapper.map(userAttribute, UserAttributeDto.class);
    }

    public List<UserAttributeDto> findPublicUserAttributesByUserProfile(String userProfileId) {
        return userAttributeRepository
                .findByIdUserProfileIdAndIsPrivate(userProfileId, false)
                .stream()
                .map(userAttribute -> modelMapper.map(userAttribute, UserAttributeDto.class))
                .toList();
    }

    public List<UserAttributeDto> findPrivateUserAttributesByUserProfile(String userProfileId) {
        return userAttributeRepository
                .findByIdUserProfileIdAndIsPrivate(userProfileId, true)
                .stream()
                .map(userAttribute -> modelMapper.map(userAttribute.value(cryptService.decrypt(userAttribute.value())),
                        UserAttributeDto.class))
                .toList();
    }

    public List<UserAttributeDto> findAllAttributesByUserProfile(String userProfileId) {
        return userAttributeRepository
                .findByIdUserProfileId(userProfileId)
                .stream()
                .map(userAttribute -> modelMapper.map(userAttribute, UserAttributeDto.class))
                .toList();
    }

    public boolean verifyPrivateAttribute(String userProfileId, String name, Object value) {
        UserAttribute userAttribute = findPureUserAttributeById(userProfileId, name, true);
        return encrypt(value).equals(userAttribute.value());
    }
}
