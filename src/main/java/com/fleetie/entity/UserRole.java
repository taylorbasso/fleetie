package com.fleetie.entity;

import com.fleetie.entity.base.BaseEntityAudit;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "user_roles")
@Entity
@Getter
@Setter
public class UserRole extends BaseEntityAudit {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
