package com.telus.dl.profilemanagement.repository;

import com.telus.dl.profilemanagement.document.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
    List<UserProfile> findByMyTelusId(String myTelusId);
}
