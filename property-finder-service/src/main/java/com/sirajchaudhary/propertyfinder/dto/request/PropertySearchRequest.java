package com.sirajchaudhary.propertyfinder.dto.request;

import com.sirajchaudhary.propertyfinder.enums.ListingType;
import com.sirajchaudhary.propertyfinder.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/*
 * ============================================================================
 * Property Search Request
 * ============================================================================
 *
 * Encapsulates all search criteria used while retrieving property listings.
 *
 * Responsibilities:
 *
 * - Store pagination information.
 * - Store sorting preferences.
 * - Store optional search filters.
 * - Transfer search criteria between application layers.
 *
 * Instead of passing numerous parameters between the resource, service and
 * repository layers, all search criteria are grouped into a single request
 * object. This keeps method signatures clean, improves readability and makes
 * the search functionality easier to extend in the future.
 *
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PropertySearchRequest {

    /*
     * =========================================================================
     * Pagination
     * =========================================================================
     *
     * Controls which subset of records should be returned.
     *
     * Default Values:
     *
     * - Page Number : 0
     * - Page Size   : 10
     *
     */

    @Builder.Default
    private int page = 0;

    @Builder.Default
    private int size = 10;

    /*
     * =========================================================================
     * Sorting
     * =========================================================================
     *
     * Determines how the search results should be ordered.
     *
     * Default Values:
     *
     * - Sort Field     : createdAt
     * - Sort Direction : desc
     *
     * Supported Sort Directions:
     *
     * - asc
     * - desc
     *
     */

    @Builder.Default
    private String sortBy = "createdAt";

    @Builder.Default
    private String sortDirection = "desc";

    /*
     * =========================================================================
     * Search & Filters
     * =========================================================================
     *
     * All filters are optional.
     *
     * Clients can supply none, one or multiple filters in a single request.
     * The repository dynamically builds the JPQL query by including only the
     * filters that are provided.
     *
     */

    /**
     * Keyword used to search across multiple text fields.
     *
     * Current Search Fields:
     *
     * - Title
     * - Description
     * - Locality
     */
    private String keyword;

    /**
     * Filters properties by city.
     */
    private String city;

    /**
     * Filters properties by state.
     */
    private String state;

    /**
     * Filters properties by listing type.
     *
     * Examples:
     *
     * - SALE
     * - RENT
     */
    private ListingType listingType;

    /**
     * Filters properties by property type.
     *
     * Examples:
     *
     * - APARTMENT
     * - VILLA
     * - HOUSE
     * - PLOT
     */
    private PropertyType propertyType;

    /**
     * Returns properties having a price greater than or equal to this value.
     */
    private BigDecimal minPrice;

    /**
     * Returns properties having a price less than or equal to this value.
     */
    private BigDecimal maxPrice;

}