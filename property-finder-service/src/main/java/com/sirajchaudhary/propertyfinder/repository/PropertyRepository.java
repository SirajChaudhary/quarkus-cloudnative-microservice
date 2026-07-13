package com.sirajchaudhary.propertyfinder.repository;

import com.sirajchaudhary.propertyfinder.dto.request.PropertySearchRequest;
import com.sirajchaudhary.propertyfinder.entity.Property;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.Map;

/*
 * ============================================================================
 * Property Repository
 * ============================================================================
 *
 * Repository responsible for all database operations related to the Property
 * entity.
 *
 * By extending PanacheRepository, Quarkus automatically provides a rich set
 * of CRUD operations without requiring any implementation code.
 *
 * Responsibilities:
 *
 * - Persist Property entities.
 * - Retrieve properties.
 * - Delete properties.
 * - Execute business-specific database queries.
 * - Build dynamic search queries.
 *
 * Panache significantly reduces boilerplate compared to traditional JPA
 * repositories, allowing developers to focus primarily on business
 * requirements instead of infrastructure code.
 *
 */
@ApplicationScoped
public class PropertyRepository implements PanacheRepository<Property> {

    /*
     * =========================================================================
     * Built-in Panache Operations
     * =========================================================================
     *
     * PanacheRepository already provides common persistence operations such as:
     *
     * - persist()
     * - persistAndFlush()
     * - findById()
     * - listAll()
     * - find()
     * - delete()
     * - deleteById()
     * - count()
     * - page()
     * - sort()
     *
     * Therefore, this repository contains only business-specific database
     * queries that cannot be handled directly using the built-in methods.
     *
     */

    /**
     * Determines whether a property already exists with the same title and
     * address.
     *
     * The comparison is performed in a case-insensitive manner to prevent
     * duplicate records such as:
     *
     * - Luxury Villa
     * - luxury villa
     * - LUXURY VILLA
     *
     * @param title Property title.
     * @param address Property address.
     * @return true if a matching property exists; otherwise false.
     */
    public boolean existsByTitleAndAddressIgnoreCase(
            String title,
            String address) {

        return count(
                "LOWER(title)=LOWER(?1) AND LOWER(address)=LOWER(?2)",
                title,
                address
        ) > 0;

    }

    /**
     * Searches properties using dynamic search criteria.
     *
     * Rather than creating separate repository methods for every search
     * combination, this method dynamically builds a single JPQL query by
     * including only the filters supplied by the client.
     *
     * Supported Features:
     *
     * - Keyword search.
     * - City filter.
     * - State filter.
     * - Listing type filter.
     * - Property type filter.
     * - Minimum price filter.
     * - Maximum price filter.
     * - Dynamic sorting.
     *
     * Pagination is intentionally not applied here. The service layer invokes
     * Panache's page() API after obtaining the PanacheQuery, keeping search
     * construction and pagination responsibilities separate.
     *
     * Quarkus Panache automatically converts the JPQL query into the
     * appropriate SQL statement for the configured database.
     *
     * @param request Search criteria.
     * @param sort Dynamic sorting configuration.
     * @return Configured PanacheQuery ready for pagination and execution.
     */
    public PanacheQuery<Property> searchProperties(
            PropertySearchRequest request,
            Sort sort) {

        /*
         * Begin with a condition that is always true.
         *
         * This allows optional search conditions to be appended using AND
         * without requiring special handling for the first filter.
         */
        StringBuilder jpql = new StringBuilder("1 = 1");

        /*
         * Named query parameters.
         *
         * Named parameters improve readability and help prevent SQL injection.
         */
        Map<String, Object> parameters = new HashMap<>();

        /*
         * ---------------------------------------------------------------------
         * Keyword Search
         * ---------------------------------------------------------------------
         *
         * Searches across:
         *
         * - Title
         * - Description
         * - Locality
         *
         */
        if (request.getKeyword() != null && !request.getKeyword().isBlank()) {

            jpql.append("""

                    AND (
                        LOWER(title) LIKE LOWER(:keyword)
                        OR LOWER(description) LIKE LOWER(:keyword)
                        OR LOWER(locality) LIKE LOWER(:keyword)
                    )
                    """);

            parameters.put(
                    "keyword",
                    "%" + request.getKeyword().trim() + "%"
            );

        }

        /*
         * ---------------------------------------------------------------------
         * City Filter
         * ---------------------------------------------------------------------
         */
        if (request.getCity() != null && !request.getCity().isBlank()) {

            jpql.append(" AND LOWER(city)=LOWER(:city)");

            parameters.put(
                    "city",
                    request.getCity()
            );

        }

        /*
         * ---------------------------------------------------------------------
         * State Filter
         * ---------------------------------------------------------------------
         */
        if (request.getState() != null && !request.getState().isBlank()) {

            jpql.append(" AND LOWER(state)=LOWER(:state)");

            parameters.put(
                    "state",
                    request.getState()
            );

        }

        /*
         * ---------------------------------------------------------------------
         * Listing Type Filter
         * ---------------------------------------------------------------------
         */
        if (request.getListingType() != null) {

            jpql.append(" AND listingType=:listingType");

            parameters.put(
                    "listingType",
                    request.getListingType()
            );

        }

        /*
         * ---------------------------------------------------------------------
         * Property Type Filter
         * ---------------------------------------------------------------------
         */
        if (request.getPropertyType() != null) {

            jpql.append(" AND propertyType=:propertyType");

            parameters.put(
                    "propertyType",
                    request.getPropertyType()
            );

        }

        /*
         * ---------------------------------------------------------------------
         * Minimum Price Filter
         * ---------------------------------------------------------------------
         */
        if (request.getMinPrice() != null) {

            jpql.append(" AND price>=:minPrice");

            parameters.put(
                    "minPrice",
                    request.getMinPrice()
            );

        }

        /*
         * ---------------------------------------------------------------------
         * Maximum Price Filter
         * ---------------------------------------------------------------------
         */
        if (request.getMaxPrice() != null) {

            jpql.append(" AND price<=:maxPrice");

            parameters.put(
                    "maxPrice",
                    request.getMaxPrice()
            );

        }

        /*
         * Build and return the dynamic Panache query.
         *
         * The returned query can later be:
         *
         * - Paginated.
         * - Executed.
         * - Counted.
         * - Sorted.
         *
         * without rebuilding the JPQL statement.
         */
        return find(
                jpql.toString(),
                sort,
                parameters
        );

    }

}