package com.fleetie.dto.response;

import com.fleetie.entity.User;
import lombok.Data;

import java.util.UUID;

@Data
public class UserResponse {
    private UUID id;
    private String username;
    private String role;
    private UUID companyId;

    public static UserResponse fromEntity(User user) {
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setCompanyId(user.getCompany().getId());
        return dto;
    }
}
