package com.telus.dl.profilemanagement.repository;

import com.telus.dl.profilemanagement.document.PrimaryUserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PrimaryUserProfileRepository extends MongoRepository<PrimaryUserProfile, String> {
    List<PrimaryUserProfile> findByMyTelusId(String myTelusId);
}
