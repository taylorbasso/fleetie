package com.fleetie.controller;

import com.fleetie.dto.request.CreateCompanyRequest;
import com.fleetie.dto.response.CompanyResponse;
import com.fleetie.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(@RequestBody CreateCompanyRequest request) {
        var company = companyService.createCompany(request.getName());
        return new ResponseEntity<>(CompanyResponse.fromEntity(company), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompany(@PathVariable UUID id) {
        Optional<CompanyResponse> company = companyService.getCompany(id).map(CompanyResponse::fromEntity);
        return company.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
