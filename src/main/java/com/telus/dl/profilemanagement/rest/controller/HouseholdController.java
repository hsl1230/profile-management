package com.telus.dl.profilemanagement.rest.controller;

import com.telus.dl.profilemanagement.dto.CreateHouseholdRequest;
import com.telus.dl.profilemanagement.dto.HouseholdDto;
import com.telus.dl.profilemanagement.dto.UpdateHouseholdRequest;
import com.telus.dl.profilemanagement.service.HouseholdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile-management")
@Validated
public class HouseholdController {

  private final HouseholdService householdService;

  @Autowired
  public HouseholdController(HouseholdService householdService) {
    this.householdService = householdService;
  }

  @Operation(
    tags = { "Household" },
    summary = "get a list of household of a customer",
    description = "get a list of household of a customer",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "get a list of household of a customer",
        content = @Content(
          mediaType = "application/json",
          array = @ArraySchema(
            schema = @Schema(anyOf = { HouseholdDto.class }, type = "object")
          )
        )
      ),
    }
  )
  @GetMapping("/customers/{customerId}/households")
  public List<HouseholdDto> findHouseholds(
    @Parameter(
      name = "customerId",
      in = ParameterIn.PATH,
      description = "Customer Id",
      required = false
    ) @PathVariable(value = "customerId", required = true) String customerId
  ) {
    return householdService.findHouseholdsByCustomer(customerId);
  }

  @Operation(
    tags = { "Household" },
    summary = "Create a household",
    description = "create a household for the customer"
  )
  @PostMapping("/customers/{customerId}/households")
  public HouseholdDto createHousehold(
    @Parameter(
      name = "customerId",
      in = ParameterIn.PATH,
      description = "Customer Id",
      required = false
    ) @PathVariable(value = "customerId", required = true) String customerId,
    @Valid @RequestBody CreateHouseholdRequest createHouseholdRequest
  ) {
    return householdService.createHousehold(customerId, createHouseholdRequest);
  }

  @Operation(
    tags = { "Household" },
    summary = "find a household of the customer by id"
  )
  @GetMapping("/households/{householdId}")
  public HouseholdDto findHousehold(
    @Parameter(
      in = ParameterIn.PATH,
      description = "household id"
    ) @PathVariable(value = "householdId") String householdId
  ) {
    return householdService.findHouseholdById(householdId);
  }

  @Operation(
    tags = { "Household" },
    summary = "remove a household"
  )
  @DeleteMapping("/households/{householdId}")
  public void removeHousehold(
    @Parameter(
      in = ParameterIn.PATH,
      description = "household id"
    ) @PathVariable(value = "householdId") String householdId
  ) {
    householdService.removeHousehold(householdId);
  }

  @Operation(
    tags = { "Household" },
    summary = "update a household",
    description = "update a household"
  )
  @PutMapping("/{householdId}")
  public void updateHousehold(
    @Valid @RequestBody UpdateHouseholdRequest updateHouseholdRequest,
    @Parameter(
      in = ParameterIn.PATH,
      description = "household id"
    ) @PathVariable(value = "householdId") String householdId
  ) {
    householdService.updateHousehold(
      householdId,
      updateHouseholdRequest
    );
  }
}
