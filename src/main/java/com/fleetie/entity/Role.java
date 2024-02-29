package com.fleetie.entity;

import com.fleetie.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "roles")
@Entity
@Getter
@Setter
public class Role extends BaseEntity {

    enum Name {
        ADMIN,
        CUSTOMER
    }

    private Name name;

}