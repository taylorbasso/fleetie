package com.fleetie.controller;

import com.fleetie.dto.request.CreateServiceLogRequest;
import com.fleetie.dto.response.ServiceLogResponse;
import com.fleetie.entity.ServiceLog;
import com.fleetie.exception.ResourceNotFoundException;
import com.fleetie.service.ServiceLogService;
import com.fleetie.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/service-logs")
@RequiredArgsConstructor
public class ServiceLogController {

    private final ServiceLogService serviceLogService;
    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<ServiceLogResponse> createServiceLog(@RequestBody CreateServiceLogRequest request) {
        var vehicle = vehicleService.getVehicle(request.getVehicleId()).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
        var entry = serviceLogService.createEntry(
                ServiceLog.builder()
                        .vehicle(vehicle)
                        .serviceDate(request.getServiceDate())
                        .serviceType(request.getServiceType())
                        .odometer(request.getOdometer())
                        .notes(request.getNotes())
                        .cost(request.getCost())
                        .build()
        );
        return new ResponseEntity<>(ServiceLogResponse.fromEntity(entry), HttpStatus.CREATED);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<ServiceLogResponse>> getServiceLogsByVehicle(@PathVariable UUID vehicleId) {
        var entries = serviceLogService.getEntriesByVehicleId(vehicleId);
        var entryResponses = entries.stream().map(ServiceLogResponse::fromEntity).toList();
        return new ResponseEntity<>(entryResponses, HttpStatus.OK);
    }
}
