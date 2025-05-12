package com.fleetie.service;

import com.fleetie.entity.Company;
import com.fleetie.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company createCompany(String name) {
        if (companyRepository.existsByName(name)) {
            throw new IllegalArgumentException("Company name already exists");
        }
        return companyRepository.save(Company.builder().name(name).build());
    }

    public Optional<Company> getCompany(UUID id) {
        return companyRepository.findById(id);
    }
}
