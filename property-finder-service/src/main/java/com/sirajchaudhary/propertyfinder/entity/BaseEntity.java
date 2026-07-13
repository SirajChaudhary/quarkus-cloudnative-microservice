package com.sirajchaudhary.propertyfinder.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/*
 * ============================================================================
 * Base Entity
 * ============================================================================
 *
 * Provides common audit fields for all JPA entities in the application.
 *
 * Responsibilities:
 *
 * - Store entity creation timestamp.
 * - Store entity last modification timestamp.
 * - Automatically populate audit fields during persistence.
 * - Eliminate duplicate audit code across entity classes.
 *
 * Any entity extending this class automatically inherits the audit fields
 * and lifecycle callbacks without requiring additional implementation.
 *
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    /*
     * =========================================================================
     * Audit Fields
     * =========================================================================
     */

    /**
     * Timestamp indicating when the entity was first persisted.
     *
     * Automatically populated by Hibernate during INSERT operations.
     */
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Timestamp indicating when the entity was last modified.
     *
     * Automatically updated by Hibernate during UPDATE operations.
     */
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /*
     * =========================================================================
     * Entity Lifecycle Callbacks
     * =========================================================================
     */

    /**
     * Executed immediately before a new entity is persisted.
     *
     * Responsibilities:
     *
     * - Initialize the creation timestamp.
     * - Initialize the last updated timestamp.
     *
     * Although Hibernate's @CreationTimestamp and @UpdateTimestamp normally
     * populate these fields automatically, explicitly setting them provides
     * an additional safeguard before persistence.
     */
    @PrePersist
    protected void onCreate() {

        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }

        updatedAt = LocalDateTime.now();

    }

    /**
     * Executed immediately before an existing entity is updated.
     *
     * Responsibilities:
     *
     * - Refresh the last updated timestamp.
     *
     * This ensures the entity always reflects the most recent modification
     * time before Hibernate synchronizes changes with the database.
     */
    @PreUpdate
    protected void onUpdate() {

        updatedAt = LocalDateTime.now();

    }

}