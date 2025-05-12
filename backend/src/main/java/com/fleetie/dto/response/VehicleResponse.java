package com.fleetie.dto.response;

import com.fleetie.entity.Vehicle;
import lombok.Data;

import java.util.UUID;

@Data
public class VehicleResponse {
    private UUID id;
    private UUID companyId;
    private String make;
    private String model;
    private Integer year;
    private String vin;
    private String identifier;
    private String type;

    public static VehicleResponse fromEntity(Vehicle vehicle) {
        VehicleResponse dto = new VehicleResponse();
        dto.setId(vehicle.getId());
        dto.setCompanyId(vehicle.getCompany().getId());
        dto.setMake(vehicle.getMake());
        dto.setModel(vehicle.getModel());
        dto.setYear(vehicle.getYear());
        dto.setVin(vehicle.getVin());
        dto.setIdentifier(vehicle.getIdentifier());
        dto.setType(vehicle.getType().toString());
        return dto;
    }
}
