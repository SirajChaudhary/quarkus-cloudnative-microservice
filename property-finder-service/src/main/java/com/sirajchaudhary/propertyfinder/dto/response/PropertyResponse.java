package com.sirajchaudhary.propertyfinder.dto.response;

import com.sirajchaudhary.propertyfinder.enums.FurnishingStatus;
import com.sirajchaudhary.propertyfinder.enums.ListingType;
import com.sirajchaudhary.propertyfinder.enums.PropertyStatus;
import com.sirajchaudhary.propertyfinder.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/*
 * ============================================================================
 * Property Response
 * ============================================================================
 *
 * Represents the property information returned to API consumers.
 *
 * Responsibilities:
 *
 * - Return property details to clients.
 * - Expose both business and system-generated information.
 * - Hide internal JPA entity implementation from the REST layer.
 *
 * Unlike PropertyRequest, this DTO includes server-managed fields such as
 * the property identifier, business property code, property status and
 * audit information.
 *
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyResponse {

    /*
     * =========================================================================
     * Property Identification
     * =========================================================================
     */

    /**
     * Database generated unique identifier.
     */
    private Long id;

    /**
     * Business-friendly property code.
     *
     * Example:
     *
     * PROP-7A91C8F4D2B3
     */
    private String propertyCode;

    /*
     * =========================================================================
     * Basic Property Information
     * =========================================================================
     */

    private String title;

    private String description;

    private ListingType listingType;

    private PropertyType propertyType;

    private BigDecimal price;

    /*
     * =========================================================================
     * Property Location
     * =========================================================================
     */

    private String city;

    private String state;

    private String country;

    private String locality;

    private String address;

    /*
     * =========================================================================
     * Property Specifications
     * =========================================================================
     */

    private Integer bedrooms;

    private Integer bathrooms;

    private Integer balconies;

    private BigDecimal areaSqFt;

    private Integer floor;

    private Integer totalFloors;

    private FurnishingStatus furnishingStatus;

    /*
     * =========================================================================
     * Property Features
     * =========================================================================
     */

    private Boolean parkingAvailable;

    private Boolean petFriendly;

    private Boolean featured;

    /*
     * =========================================================================
     * Availability
     * =========================================================================
     */

    private LocalDate availableFrom;

    /*
     * =========================================================================
     * Owner Information
     * =========================================================================
     */

    private String ownerName;

    private String ownerContact;

    /*
     * =========================================================================
     * Property Status
     * =========================================================================
     */

    /**
     * Indicates whether the property is active.
     */
    private Boolean active;

    /**
     * Current business status of the property.
     *
     * Examples:
     *
     * - AVAILABLE
     * - SOLD
     * - RENTED
     */
    private PropertyStatus status;

    /*
     * =========================================================================
     * Audit Information
     * =========================================================================
     */

    /**
     * Timestamp indicating when the property was created.
     */
    private LocalDateTime createdAt;

    /**
     * Timestamp indicating when the property was last updated.
     */
    private LocalDateTime updatedAt;

}