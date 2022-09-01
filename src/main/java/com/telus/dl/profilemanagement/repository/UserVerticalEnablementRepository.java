package com.telus.dl.profilemanagement.repository;

import com.telus.dl.profilemanagement.document.UserVerticalEnablement;
import com.telus.dl.profilemanagement.document.UserVerticalId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserVerticalEnablementRepository extends MongoRepository<UserVerticalEnablement, UserVerticalId> {
}
