package com.sirajchaudhary.propertyfinder.mapper;

import com.sirajchaudhary.propertyfinder.dto.request.PropertyRequest;
import com.sirajchaudhary.propertyfinder.dto.response.PropertyResponse;
import com.sirajchaudhary.propertyfinder.entity.Property;
import jakarta.enterprise.context.ApplicationScoped;

/*
 * ============================================================================
 * Property Mapper
 * ============================================================================
 *
 * Responsible for converting between API DTOs and JPA entities.
 *
 * Supported Conversions:
 *
 * - PropertyRequest  -> Property
 * - Property         -> PropertyResponse
 * - PropertyRequest  -> Existing Property (Update)
 *
 * Why use a dedicated mapper?
 *
 * - Separates mapping logic from business logic.
 * - Prevents exposing JPA entities through REST APIs.
 * - Improves readability and maintainability.
 * - Makes future changes easier by centralizing all object mappings.
 * - Allows replacing manual mapping with MapStruct later without impacting
 *   the service layer.
 *
 * Since this project contains only a single primary entity, manual mapping
 * provides a simple and dependency-free solution.
 *
 */
@ApplicationScoped
public class PropertyMapper {

    /**
     * Converts a PropertyRequest DTO into a Property entity.
     *
     * Mapping Flow:
     *
     * - Copy all client-managed fields.
     * - Ignore server-managed fields.
     * - Return a new Property entity ready for persistence.
     *
     * The following fields are intentionally excluded because they are managed
     * by the application:
     *
     * - id
     * - propertyCode
     * - active
     * - status
     * - createdAt
     * - updatedAt
     *
     * @param request Property creation request.
     * @return Property entity.
     */
    public Property toProperty(PropertyRequest request) {

        if (request == null) {
            return null;
        }

        return Property.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .listingType(request.getListingType())
                .propertyType(request.getPropertyType())
                .price(request.getPrice())
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .locality(request.getLocality())
                .address(request.getAddress())
                .bedrooms(request.getBedrooms())
                .bathrooms(request.getBathrooms())
                .balconies(request.getBalconies())
                .areaSqFt(request.getAreaSqFt())
                .floor(request.getFloor())
                .totalFloors(request.getTotalFloors())
                .furnishingStatus(request.getFurnishingStatus())
                .parkingAvailable(request.getParkingAvailable())
                .petFriendly(request.getPetFriendly())
                .availableFrom(request.getAvailableFrom())
                .ownerName(request.getOwnerName())
                .ownerContact(request.getOwnerContact())
                .featured(request.getFeatured())
                .build();

    }

    /**
     * Converts a Property entity into a PropertyResponse DTO.
     *
     * Mapping Flow:
     *
     * - Copy all business fields.
     * - Include server-generated values.
     * - Include audit information.
     *
     * The response contains everything required by API consumers while hiding
     * internal persistence implementation details.
     *
     * @param property Persisted Property entity.
     * @return Property response.
     */
    public PropertyResponse toPropertyResponse(Property property) {

        if (property == null) {
            return null;
        }

        return PropertyResponse.builder()
                .id(property.getId())
                .propertyCode(property.getPropertyCode())
                .title(property.getTitle())
                .description(property.getDescription())
                .listingType(property.getListingType())
                .propertyType(property.getPropertyType())
                .price(property.getPrice())
                .city(property.getCity())
                .state(property.getState())
                .country(property.getCountry())
                .locality(property.getLocality())
                .address(property.getAddress())
                .bedrooms(property.getBedrooms())
                .bathrooms(property.getBathrooms())
                .balconies(property.getBalconies())
                .areaSqFt(property.getAreaSqFt())
                .floor(property.getFloor())
                .totalFloors(property.getTotalFloors())
                .furnishingStatus(property.getFurnishingStatus())
                .parkingAvailable(property.getParkingAvailable())
                .petFriendly(property.getPetFriendly())
                .availableFrom(property.getAvailableFrom())
                .ownerName(property.getOwnerName())
                .ownerContact(property.getOwnerContact())
                .featured(property.getFeatured())
                .active(property.getActive())
                .status(property.getStatus())
                .createdAt(property.getCreatedAt())
                .updatedAt(property.getUpdatedAt())
                .build();

    }

    /**
     * Updates an existing managed Property entity using values supplied by the
     * client.
     *
     * Processing Flow:
     *
     * - Copy only editable fields.
     * - Preserve server-managed fields.
     * - Allow Hibernate Dirty Checking to detect modified values.
     *
     * The following fields are intentionally not modified:
     *
     * - id
     * - propertyCode
     * - active
     * - status
     * - createdAt
     * - updatedAt
     *
     * Since the supplied Property entity is already managed by Hibernate,
     * simply modifying its fields is sufficient. Hibernate automatically
     * detects the changes and generates the appropriate SQL UPDATE statement
     * when the transaction commits.
     *
     * @param property Existing managed Property entity.
     * @param request Updated property details.
     */
    public void updateProperty(
            Property property,
            PropertyRequest request) {

        property.setTitle(request.getTitle());
        property.setDescription(request.getDescription());

        property.setListingType(request.getListingType());
        property.setPropertyType(request.getPropertyType());

        property.setPrice(request.getPrice());

        property.setCity(request.getCity());
        property.setState(request.getState());
        property.setCountry(request.getCountry());
        property.setLocality(request.getLocality());
        property.setAddress(request.getAddress());

        property.setBedrooms(request.getBedrooms());
        property.setBathrooms(request.getBathrooms());
        property.setBalconies(request.getBalconies());

        property.setAreaSqFt(request.getAreaSqFt());

        property.setFloor(request.getFloor());
        property.setTotalFloors(request.getTotalFloors());

        property.setFurnishingStatus(request.getFurnishingStatus());

        property.setParkingAvailable(request.getParkingAvailable());
        property.setPetFriendly(request.getPetFriendly());

        property.setAvailableFrom(request.getAvailableFrom());

        property.setOwnerName(request.getOwnerName());
        property.setOwnerContact(request.getOwnerContact());

        property.setFeatured(request.getFeatured());

    }

}