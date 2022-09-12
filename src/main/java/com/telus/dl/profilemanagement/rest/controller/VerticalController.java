package com.telus.dl.profilemanagement.rest.controller;

import com.telus.dl.profilemanagement.dto.VerticalDto;
import com.telus.dl.profilemanagement.service.VerticalService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/profile-management/verticals")
@Validated
public class VerticalController {
    private final VerticalService verticalService;

    public VerticalController(VerticalService verticalService) {
        this.verticalService = verticalService;
    }

    @Operation(
            tags = { "Vertical" },
            summary = "get all verticals"
    )
    @GetMapping
    public List<VerticalDto> findAllVerticals() {
        return verticalService.findAllVerticals();
    }

    @Operation(
            tags = { "Vertical" },
            summary = "create a vertical"
    )
    @PostMapping
    public VerticalDto createVertical(@Valid @RequestBody VerticalDto verticalDto) {
        return verticalService.createVertical(verticalDto);
    }

    @Operation(
            tags = { "Vertical" },
            summary = "delete a vertical"
    )
    @DeleteMapping("/{verticalId}")
    public void deleteVertical(
            @PathVariable("verticalId") String verticalId) {
        verticalService.deleteVertical(verticalId);
    }

}
