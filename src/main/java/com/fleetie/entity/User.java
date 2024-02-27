package com.fleetie.entity;

import com.fleetie.entity.base.BaseEntityAudit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntityAudit {

    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column
    private boolean enabled;

}