package com.fleetie.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class CreateServiceLogRequest {

    @NotNull
    private UUID vehicleId;
    @NotNull
    private LocalDate serviceDate;
    @NotBlank
    private String serviceType;
    @NotNull
    private Integer odometer;
    @NotBlank
    private String notes;
    @NotNull
    private BigDecimal cost;

}
