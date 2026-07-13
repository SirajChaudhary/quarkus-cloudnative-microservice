package com.sirajchaudhary.propertyfinder.service;

import com.sirajchaudhary.propertyfinder.dto.request.PropertyRequest;
import com.sirajchaudhary.propertyfinder.dto.request.PropertySearchRequest;
import com.sirajchaudhary.propertyfinder.dto.response.PagedResponse;
import com.sirajchaudhary.propertyfinder.dto.response.PropertyResponse;

/*
 * ============================================================================
 * Property Service
 * ============================================================================
 *
 * Defines the business contract for managing property listings.
 *
 * Responsibilities:
 *
 * - Create new property listings.
 * - Retrieve property details.
 * - Search properties using dynamic criteria.
 * - Filter property listings.
 * - Sort search results.
 * - Paginate large result sets.
 * - Update existing properties.
 * - Delete properties.
 * - Enforce business rules and validations.
 *
 * This interface defines what operations are supported by the application
 * without exposing how those operations are implemented.
 *
 * The actual business logic is implemented in PropertyServiceImpl while the
 * REST resource communicates only with this interface, promoting loose
 * coupling and easier maintenance.
 *
 */
public interface PropertyService {

    /**
     * Creates a new property listing.
     *
     * Processing includes:
     *
     * - Validate business rules.
     * - Prevent duplicate property listings.
     * - Generate a unique property code.
     * - Persist the property.
     * - Return the newly created property.
     *
     * @param request Property creation request.
     * @return Newly created property.
     */
    PropertyResponse createProperty(PropertyRequest request);

    /**
     * Retrieves a property using its unique identifier.
     *
     * If the property does not exist, a ResourceNotFoundException is thrown.
     *
     * @param id Property identifier.
     * @return Property details.
     */
    PropertyResponse getPropertyById(Long id);

    /**
     * Searches property listings using dynamic search criteria.
     *
     * Supported Features:
     *
     * - Retrieve all properties.
     * - Keyword search.
     * - Filter by city.
     * - Filter by state.
     * - Filter by listing type.
     * - Filter by property type.
     * - Filter by minimum price.
     * - Filter by maximum price.
     * - Dynamic sorting.
     * - Pagination.
     * - Combination of multiple search criteria.
     *
     * All search criteria are optional, allowing a single operation to
     * support a wide variety of search scenarios.
     *
     * @param request Search, filtering, sorting and pagination criteria.
     * @return Paginated list of matching properties.
     */
    PagedResponse<PropertyResponse> searchProperties(
            PropertySearchRequest request
    );

    /**
     * Updates an existing property.
     *
     * Processing includes:
     *
     * - Verify the property exists.
     * - Validate business rules.
     * - Update editable fields.
     * - Persist the latest property information.
     *
     * @param id Property identifier.
     * @param request Updated property details.
     * @return Updated property.
     */
    PropertyResponse updateProperty(
            Long id,
            PropertyRequest request
    );

    /**
     * Deletes an existing property.
     *
     * Processing includes:
     *
     * - Verify the property exists.
     * - Remove the property from the database.
     *
     * @param id Property identifier.
     */
    void deleteProperty(Long id);

}