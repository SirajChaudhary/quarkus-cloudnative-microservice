package com.sirajchaudhary.propertyfinder.dto.request;

import com.sirajchaudhary.propertyfinder.enums.FurnishingStatus;
import com.sirajchaudhary.propertyfinder.enums.ListingType;
import com.sirajchaudhary.propertyfinder.enums.PropertyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/*
 * ============================================================================
 * Property Request
 * ============================================================================
 *
 * Represents the request payload used to create or update a property.
 *
 * Responsibilities:
 *
 * - Receive property details from API clients.
 * - Validate incoming request data.
 * - Transfer data from the REST layer to the service layer.
 *
 * This DTO contains only client-managed fields. Server-managed fields such as
 * id, propertyCode, status, active and audit information are intentionally
 * excluded because they are generated and maintained by the application.
 *
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyRequest {

    /*
     * =========================================================================
     * Basic Property Information
     * =========================================================================
     */

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Listing type is required")
    private ListingType listingType;

    @NotNull(message = "Property type is required")
    private PropertyType propertyType;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private BigDecimal price;

    /*
     * =========================================================================
     * Property Location
     * =========================================================================
     */

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "Locality is required")
    private String locality;

    @NotBlank(message = "Address is required")
    private String address;

    /*
     * =========================================================================
     * Property Specifications
     * =========================================================================
     */

    @NotNull(message = "Number of bedrooms is required")
    @Positive(message = "Number of bedrooms must be greater than zero")
    private Integer bedrooms;

    @NotNull(message = "Number of bathrooms is required")
    @Positive(message = "Number of bathrooms must be greater than zero")
    private Integer bathrooms;

    @Positive(message = "Number of balconies cannot be negative")
    private Integer balconies;

    @NotNull(message = "Area is required")
    @Positive(message = "Area must be greater than zero")
    private BigDecimal areaSqFt;

    @Positive(message = "Floor cannot be negative")
    private Integer floor;

    @Positive(message = "Total floors must be greater than zero")
    private Integer totalFloors;

    @NotNull(message = "Furnishing status is required")
    private FurnishingStatus furnishingStatus;

    /*
     * =========================================================================
     * Property Features
     * =========================================================================
     */

    @NotNull(message = "Parking availability is required")
    private Boolean parkingAvailable;

    @NotNull(message = "Pet friendly flag is required")
    private Boolean petFriendly;

    @Builder.Default
    private Boolean featured = Boolean.FALSE;

    /*
     * =========================================================================
     * Availability
     * =========================================================================
     */

    @NotNull(message = "Available from date is required")
    private LocalDate availableFrom;

    /*
     * =========================================================================
     * Owner Information
     * =========================================================================
     */

    @NotBlank(message = "Owner name is required")
    private String ownerName;

    @NotBlank(message = "Owner contact is required")
    private String ownerContact;

}