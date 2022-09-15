package com.telus.dl.profilemanagement.dto.userprofile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Accessors(fluent = true)
public class AddressDto {
    @JsonProperty(value = "unit")
    private String unit;

    @JsonProperty(value = "street", required = true)
    @NotBlank
    private String street;

    @JsonProperty(value = "city", required = true)
    @NotBlank
    private String city;

    @JsonProperty(value = "province", required = true)
    @NotBlank
    private String province;

    @JsonProperty(value = "country", required = true)
    @NotBlank
    private String country;

    @JsonProperty(value = "postCode", required = true)
    @NotBlank
    @Pattern(regexp = "^[ABCEGHJ-NPRSTVXY]\\d[ABCEGHJ-NPRSTV-Z][ -]?\\d[ABCEGHJ-NPRSTV-Z]\\d$")
    private String postCode;
}
