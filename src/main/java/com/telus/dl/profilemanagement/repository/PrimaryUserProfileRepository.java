package com.telus.dl.profilemanagement.repository;

import com.telus.dl.profilemanagement.document.PrimaryUserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PrimaryUserProfileRepository extends MongoRepository<PrimaryUserProfile, String> {
    List<PrimaryUserProfile> findByMyTelusId(String myTelusId);
    Optional<PrimaryUserProfile> findById(String id);
}
