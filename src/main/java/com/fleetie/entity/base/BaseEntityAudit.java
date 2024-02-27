package com.fleetie.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntityAudit extends BaseEntity implements Serializable {
    private String createdBy;
    private String updatedBy;

    @CreationTimestamp
    @Column(columnDefinition = "datetime", name = "date_created", updatable = false)
    private Instant dateCreated;

    @UpdateTimestamp
    @Column(columnDefinition = "datetime", name = "date_modified")
    private Instant dateModified;
}
