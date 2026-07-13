package com.sirajchaudhary.propertyfinder.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/*
 * ============================================================================
 * Paged Response
 * ============================================================================
 *
 * Generic response model used for paginated REST APIs.
 *
 * Responsibilities:
 *
 * - Return records for the current page.
 * - Provide pagination metadata.
 * - Improve client-side navigation.
 * - Be reusable across all paginated endpoints.
 *
 * Instead of returning only a collection of records, this response includes
 * additional information that helps API consumers efficiently navigate
 * through large datasets.
 *
 * Since this class is generic, it can be reused by any paginated REST API
 * simply by changing the response type.
 *
 * Examples:
 *
 * - PagedResponse<PropertyResponse>
 * - PagedResponse<UserResponse>
 * - PagedResponse<OrderResponse>
 *
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagedResponse<T> {

    /*
     * =========================================================================
     * Page Content
     * =========================================================================
     */

    /**
     * Records belonging to the current page.
     */
    private List<T> content;

    /*
     * =========================================================================
     * Pagination Information
     * =========================================================================
     */

    /**
     * Current page number.
     *
     * Zero-based indexing is used.
     *
     * Example:
     *
     * 0 = First Page
     * 1 = Second Page
     * 2 = Third Page
     */
    private int page;

    /**
     * Maximum number of records returned per page.
     */
    private int size;

    /**
     * Total number of records matching the search criteria.
     */
    private long totalElements;

    /**
     * Total number of pages available.
     */
    private int totalPages;

    /*
     * =========================================================================
     * Navigation Information
     * =========================================================================
     */

    /**
     * Indicates whether the current page is the first page.
     */
    private boolean first;

    /**
     * Indicates whether the current page is the last page.
     */
    private boolean last;

}