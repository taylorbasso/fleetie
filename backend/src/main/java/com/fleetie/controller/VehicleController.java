package com.fleetie.controller;

import com.fleetie.dto.request.CreateVehicleRequest;
import com.fleetie.dto.response.VehicleResponse;
import com.fleetie.entity.Vehicle;
import com.fleetie.service.CompanyService;
import com.fleetie.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;
    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<VehicleResponse> createVehicle(@RequestBody CreateVehicleRequest request) {
        var company = companyService.getCompany(request.getCompanyId()).orElseThrow(() -> new IllegalArgumentException("Company not found"));
        var vehicle = vehicleService.createVehicle(
                Vehicle.builder()
                        .company(company)
                        .make(request.getMake())
                        .model(request.getModel())
                        .year(request.getYear())
                        .vin(request.getVin())
                        .identifier(request.getIdentifier())
                        .type(Vehicle.Type.valueOf(request.getType()))
                        .build()
        );
        return new ResponseEntity<>(VehicleResponse.fromEntity(vehicle), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> getVehicle(@PathVariable UUID id) {
        Optional<VehicleResponse> vehicle = vehicleService.getVehicle(id).map(VehicleResponse::fromEntity);
        return vehicle.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<VehicleResponse>> getVehiclesByCompany(@PathVariable UUID companyId) {
        var vehicles = vehicleService.getVehiclesByCompanyId(companyId);
        var vehicleResponses = vehicles.stream().map(VehicleResponse::fromEntity).toList();
        return new ResponseEntity<>(vehicleResponses, HttpStatus.OK);
    }
}
