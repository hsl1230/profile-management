package com.telus.dl.profilemanagement.repository;

import com.telus.dl.profilemanagement.document.SubUserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SubUserProfileRepository extends MongoRepository<SubUserProfile, String> {
    List<SubUserProfile> findByMyTelusId(String myTelusId);
    List<SubUserProfile> findByPrimaryUserProfileId(String primaryUserProfileId);
}
