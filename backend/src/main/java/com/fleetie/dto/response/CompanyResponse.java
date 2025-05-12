package com.fleetie.dto.response;

import com.fleetie.entity.Company;
import lombok.Data;

import java.util.UUID;

@Data
public class CompanyResponse {
    private UUID id;
    private String name;

    public static CompanyResponse fromEntity(Company company) {
        CompanyResponse dto = new CompanyResponse();
        dto.setId(company.getId());
        dto.setName(company.getName());
        return dto;
    }
}
