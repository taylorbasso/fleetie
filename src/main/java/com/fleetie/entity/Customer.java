package com.fleetie.entity;

import com.fleetie.entity.base.BaseEntityAudit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "customers")
@Entity
@Getter
@Setter
public class Customer extends BaseEntityAudit {

    @Column
    private String name;
    @Column
    private boolean enabled;

}
