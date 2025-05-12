package com.fleetie.service;

import com.fleetie.entity.Company;
import com.fleetie.entity.Role;
import com.fleetie.entity.User;
import com.fleetie.exception.ResourceNotFoundException;
import com.fleetie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompanyService companyService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       CompanyService companyService) {
        this.userRepository = userRepository;
        this.companyService = companyService;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(String username, String plainTextPassword, UUID companyId) {
        String hashedPassword = passwordEncoder.encode(plainTextPassword);
        Company company = companyService.getCompany(companyId).orElseThrow(() -> new ResourceNotFoundException("companyId not found."));

        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already in use");
        }
        return userRepository.save(
                User.builder()
                        .username(username)
                        .password(hashedPassword)
                        .company(company)
                        .authorities(Set.of(Role.ROLE_USER))
                        .build()
        );
    }

    @Transactional(readOnly = true)
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> getById(UUID id) {
        return userRepository.findById(id);
    }
}
