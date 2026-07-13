package com.sirajchaudhary.propertyfinder.entity;

import com.sirajchaudhary.propertyfinder.enums.FurnishingStatus;
import com.sirajchaudhary.propertyfinder.enums.ListingType;
import com.sirajchaudhary.propertyfinder.enums.PropertyStatus;
import com.sirajchaudhary.propertyfinder.enums.PropertyType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/*
 * ============================================================================
 * Property Entity
 * ============================================================================
 *
 * Represents a real estate property managed by the Property Finder
 * application.
 *
 * This entity is mapped to the "properties" database table and serves as the
 * primary persistence model for the application.
 *
 * Responsibilities:
 *
 * - Store property information.
 * - Define database table mapping.
 * - Define validation constraints.
 * - Maintain audit information.
 * - Initialize default values before persistence.
 *
 * The entity is intentionally persistence-focused. Business logic is
 * implemented in the service layer, while API communication is handled using
 * DTOs to avoid exposing JPA entities directly.
 *
 */
@Entity
@Table(name = "properties")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Property {

    /*
     * =========================================================================
     * Primary Key
     * =========================================================================
     */

    /**
     * Database generated primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Business-friendly unique property code exposed to API consumers.
     *
     * Example:
     *
     * PROP-7A91C8F4D2B3
     */
    @Column(nullable = false, unique = true, length = 20)
    private String propertyCode;

    /*
     * =========================================================================
     * Basic Property Information
     * =========================================================================
     */

    @NotBlank(message = "Title is required")
    @Column(nullable = false, length = 150)
    private String title;

    @NotBlank(message = "Description is required")
    @Column(nullable = false, length = 2000)
    private String description;

    @NotNull(message = "Listing type is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ListingType listingType;

    @NotNull(message = "Property type is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PropertyType propertyType;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal price;

    /*
     * =========================================================================
     * Location Information
     * =========================================================================
     */

    @NotBlank(message = "City is required")
    @Column(nullable = false, length = 100)
    private String city;

    @NotBlank(message = "State is required")
    @Column(nullable = false, length = 100)
    private String state;

    @NotBlank(message = "Country is required")
    @Column(nullable = false, length = 100)
    private String country;

    @NotBlank(message = "Locality is required")
    @Column(nullable = false, length = 100)
    private String locality;

    @NotBlank(message = "Address is required")
    @Column(nullable = false, length = 500)
    private String address;

    /*
     * =========================================================================
     * Property Specifications
     * =========================================================================
     */

    @NotNull(message = "Number of bedrooms is required")
    private Integer bedrooms;

    @NotNull(message = "Number of bathrooms is required")
    private Integer bathrooms;

    private Integer balconies;

    @NotNull(message = "Area is required")
    @Positive(message = "Area must be greater than zero")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal areaSqFt;

    private Integer floor;

    private Integer totalFloors;

    @NotNull(message = "Furnishing status is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FurnishingStatus furnishingStatus;

    /*
     * =========================================================================
     * Property Features
     * =========================================================================
     */

    @Builder.Default
    private Boolean parkingAvailable = Boolean.FALSE;

    @Builder.Default
    private Boolean petFriendly = Boolean.FALSE;

    @NotNull(message = "Available from date is required")
    private LocalDate availableFrom;

    /*
     * =========================================================================
     * Owner Information
     * =========================================================================
     */

    @NotBlank(message = "Owner name is required")
    @Column(nullable = false, length = 100)
    private String ownerName;

    @NotBlank(message = "Owner contact is required")
    @Column(nullable = false, length = 20)
    private String ownerContact;

    /*
     * =========================================================================
     * Property Status
     * =========================================================================
     */

    /**
     * Indicates whether the property should be highlighted in searches.
     */
    @Builder.Default
    @Column(nullable = false)
    private Boolean featured = Boolean.FALSE;

    /**
     * Indicates whether the property is active.
     */
    @Builder.Default
    @Column(nullable = false)
    private Boolean active = Boolean.TRUE;

    /**
     * Current business status of the property.
     *
     * Examples:
     *
     * - AVAILABLE
     * - SOLD
     * - RENTED
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PropertyStatus status;

    /*
     * =========================================================================
     * Audit Information
     * =========================================================================
     */

    /**
     * Automatically populated when the property is first persisted.
     */
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Automatically updated whenever the property is modified.
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
     * Initializes default values before the entity is persisted.
     *
     * This method guarantees that important business fields always have valid
     * default values, even if they were not explicitly supplied by the
     * application.
     *
     * Default Values:
     *
     * - Property Status : AVAILABLE
     * - Active          : true
     * - Featured        : false
     */
    @PrePersist
    public void prePersist() {

        if (status == null) {
            status = PropertyStatus.AVAILABLE;
        }

        if (active == null) {
            active = Boolean.TRUE;
        }

        if (featured == null) {
            featured = Boolean.FALSE;
        }

    }

}