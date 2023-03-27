package com.telus.dl.profilemanagement.repository;

import com.telus.dl.profilemanagement.document.Household;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface HouseholdRepository extends MongoRepository<Household, String> {
    List<Household> findByCustomerId(String customerId);
}
