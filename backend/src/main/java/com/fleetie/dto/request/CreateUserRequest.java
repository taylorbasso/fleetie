package com.fleetie.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateUserRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotNull
    private UUID companyId;

}
