package com.fleetie.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateVehicleRequest {

    @NotNull
    private UUID companyId;
    @NotBlank
    private String make;
    @NotNull
    private String model;
    @NotNull
    private Integer year;
    @NotBlank
    private String identifier;
    @NotBlank
    private String vin;
    @NotBlank
    private String type;

}
