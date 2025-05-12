package com.fleetie.dto.response;

import com.fleetie.dto.request.CreateServiceLogRequest;
import com.fleetie.entity.ServiceLog;
import lombok.Data;

import java.util.UUID;

@Data
public class ServiceLogResponse extends CreateServiceLogRequest {
    private UUID id;

    public static ServiceLogResponse fromEntity(ServiceLog entry) {
        ServiceLogResponse dto = new ServiceLogResponse();
        dto.setId(entry.getId());
        dto.setVehicleId(entry.getVehicle().getId());
        dto.setServiceDate(entry.getServiceDate());
        dto.setServiceType(entry.getServiceType());
        dto.setOdometer(entry.getOdometer());
        dto.setNotes(entry.getNotes());
        dto.setCost(entry.getCost());
        return dto;
    }
}
