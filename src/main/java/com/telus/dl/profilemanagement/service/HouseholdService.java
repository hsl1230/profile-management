package com.telus.dl.profilemanagement.service;

import com.telus.core.errorhandling.exception.EntityNotFoundException;
import com.telus.dl.profilemanagement.document.Household;
import com.telus.dl.profilemanagement.dto.CreateHouseholdRequest;
import com.telus.dl.profilemanagement.dto.HouseholdDto;
import com.telus.dl.profilemanagement.dto.UpdateHouseholdRequest;
import com.telus.dl.profilemanagement.repository.HouseholdRepository;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseholdService {
    private final MongoTemplate mongoTemplate;
    private final HouseholdRepository householdRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HouseholdService(
            MongoTemplate mongoTemplate,
            HouseholdRepository householdRepository,
            CryptService cryptService, ModelMapper modelMapper) {
        this.mongoTemplate = mongoTemplate;
        this.householdRepository = householdRepository;
        this.modelMapper = modelMapper;
    }

    public HouseholdDto createHousehold(String customerId, CreateHouseholdRequest createHouseholdRequest) {
        Household household = modelMapper.map(createHouseholdRequest, Household.class);
        household.customerId(customerId);
        household = householdRepository.save(household);
        return modelMapper.map(household, HouseholdDto.class);
    }

    public void removeHousehold(String householdId) {
        householdRepository.deleteById(householdId);
    }

    public List<HouseholdDto> findHouseholdsByCustomer(String customerId) {
        return householdRepository
                .findByCustomerId(customerId)
                .stream()
                .map(household -> modelMapper.map(household, HouseholdDto.class))
                .toList();
    }

    public HouseholdDto findHouseholdById(String householdId) {
        Household household = householdRepository
                .findById(householdId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No household found for Id=" + householdId));

        return modelMapper.map(household, HouseholdDto.class);
    }

    public void updateHousehold(String householdId, UpdateHouseholdRequest updateHouseholdRequest) {
        findHouseholdById(householdId);

        Update update = new Update();
        if (StringUtils.isNotBlank(updateHouseholdRequest.householdName())) {
            update.set("householdName", updateHouseholdRequest.householdName());
        }
        if (StringUtils.isNotBlank(updateHouseholdRequest.description())) {
            update.set("description", updateHouseholdRequest.description());
        }
        if (StringUtils.isNotBlank(updateHouseholdRequest.address())) {
            update.set("address", updateHouseholdRequest.address());
        }
        
        mongoTemplate.updateFirst(new Query().addCriteria(Criteria
                .where("id")
                .is(householdId)), update, Household.class);
    }

}
