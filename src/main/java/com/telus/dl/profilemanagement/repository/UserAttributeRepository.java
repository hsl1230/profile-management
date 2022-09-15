package com.telus.dl.profilemanagement.repository;

import com.telus.dl.profilemanagement.document.attribute.UserAttribute;
import com.telus.dl.profilemanagement.document.attribute.UserAttributeId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserAttributeRepository extends MongoRepository<UserAttribute, UserAttributeId> {
    List<UserAttribute> findByIdUserProfileId(String userProfileId);
    List<UserAttribute> findByIdUserProfileIdAndIsPrivate(String userProfileId, boolean isPrivate);
    Optional<UserAttribute> findByIdAndIsPrivate(UserAttributeId id, boolean isPrivate);
}
