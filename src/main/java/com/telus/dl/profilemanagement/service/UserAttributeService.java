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

    public UserAttributeDto createUserAttribute(
            String userProfileId,
            AttributeDto attributeDto) {
        userProfileService.assertUserProfileExists(userProfileId);

        UserAttribute userAttribute = new UserAttribute()
                .id(UserAttributeId.builder().userProfileId(userProfileId).name(attributeDto.getName()).build())
                .isSensitive(attributeDto.isSensitive())
                .value(attributeDto.isSensitive()? encrypt(attributeDto.getValue()) : attributeDto.getValue());

        userAttribute = userAttributeRepository.save(userAttribute);
        return modelMapper.map(userAttribute, UserAttributeDto.class);
    }

    private String encrypt(Object value) {
        return cryptService.encrypt(value);
    }

    /**
     * delete a user attribute
     * @param userProfileId user profile id
     * @param name attribute name
     */
    public void deleteUserAttribute(String userProfileId, String name) {
        userAttributeRepository.deleteById(
                UserAttributeId.builder().userProfileId(userProfileId).name(name).build());
    }

    private UserAttribute findPureUserAttributeById(String userProfileId, String name) {
        return userAttributeRepository
                .findById(UserAttributeId.builder().userProfileId(userProfileId).name(name).build())
                .orElseThrow(() -> new EntityNotFoundException(
                        "No user attribute found for userProfileId=" + userProfileId + ", name=" + name));
    }

    private void decrypt(UserAttribute userAttribute, boolean decrypt) {
        if (decrypt && userAttribute.isSensitive()) {
            userAttribute.value(cryptService.decrypt(userAttribute.value()));
        }
    }

    /**
     * find a user profile by id.
     * @param userProfileId user profile id
     * @param name attribute name
     * @return value of UserAttributeDto
     */
    public UserAttributeDto findUserAttributeById(String userProfileId, String name, boolean decrypt) {
        UserAttribute userAttribute = findPureUserAttributeById(userProfileId, name);
        decrypt(userAttribute, decrypt);
        return modelMapper.map(userAttribute, UserAttributeDto.class);
    }

    /**
     * @param userProfileId user profile id
     * @return a list of UserAttributeDto
     */
    public List<UserAttributeDto> findAllAttributesByUserProfile(String userProfileId, boolean decrypt) {
        return userAttributeRepository
                .findByIdUserProfileId(userProfileId)
                .stream()
                .map(userAttribute -> {
                    decrypt(userAttribute, decrypt);
                    return modelMapper.map(userAttribute, UserAttributeDto.class);
                })
                .toList();
    }

    /**
     * verify attribute.
     * @param userProfileId user profile id
     * @param name attribute name
     * @param value attribute value
     * @return a boolean
     */
    public boolean verifyAttribute(String userProfileId, String name, Object value) {
        UserAttribute userAttribute = findPureUserAttributeById(userProfileId, name);
        if (userAttribute.isSensitive()) {
            return encrypt(value).equals(userAttribute.value());
        } else {
            return value.equals(userAttribute.value());
        }
    }
}
