package com.fleetie.entity;

import com.fleetie.entity.base.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Table(name = "users")
@Entity
@Getter
@Setter
public class User extends BaseEntityAudit {

    @Column(unique = true)
    private String username;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String password;
    @Column
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<UserRole> userRoles;

}