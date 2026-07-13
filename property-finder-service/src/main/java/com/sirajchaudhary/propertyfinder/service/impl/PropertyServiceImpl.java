package com.sirajchaudhary.propertyfinder.service.impl;

import com.sirajchaudhary.propertyfinder.dto.request.PropertyRequest;
import com.sirajchaudhary.propertyfinder.dto.request.PropertySearchRequest;
import com.sirajchaudhary.propertyfinder.dto.response.PagedResponse;
import com.sirajchaudhary.propertyfinder.dto.response.PropertyResponse;
import com.sirajchaudhary.propertyfinder.entity.Property;
import com.sirajchaudhary.propertyfinder.exception.ResourceAlreadyExistsException;
import com.sirajchaudhary.propertyfinder.exception.ResourceNotFoundException;
import com.sirajchaudhary.propertyfinder.mapper.PropertyMapper;
import com.sirajchaudhary.propertyfinder.repository.PropertyRepository;
import com.sirajchaudhary.propertyfinder.service.PropertyService;
import io.quarkus.cache.CacheInvalidate;
import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheKey;
import io.quarkus.cache.CacheResult;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.UUID;

/*
 * ============================================================================
 * Property Service Implementation
 * ============================================================================
 *
 * Implements the business contract defined by PropertyService.
 *
 * Responsibilities:
 *
 * - Validate business rules.
 * - Prevent duplicate property listings.
 * - Generate business property codes.
 * - Coordinate repository operations.
 * - Convert request DTOs into JPA entities.
 * - Convert JPA entities into response DTOs.
 * - Manage caching.
 * - Execute all database operations within transactions.
 *
 * The service layer acts as the bridge between the REST resource and the
 * persistence layer. It contains all business logic while keeping both the
 * REST resource and repository implementations focused on their individual
 * responsibilities.
 *
 */
@ApplicationScoped
public class PropertyServiceImpl implements PropertyService {

    /*
     * =========================================================================
     * Cache Names
     * =========================================================================
     *
     * Centralizing cache names avoids duplicated string literals throughout
     * the service implementation.
     *
     */

    private static final String PROPERTY_BY_ID_CACHE = "property-by-id";

    private static final String PROPERTIES_CACHE = "properties";

    /*
     * Logger used for recording important business events.
     */
    private static final Logger LOGGER =
            Logger.getLogger(PropertyServiceImpl.class);

    /*
     * Repository responsible for database operations.
     */
    private final PropertyRepository propertyRepository;

    /*
     * Mapper responsible for DTO ↔ Entity conversions.
     */
    private final PropertyMapper propertyMapper;

    public PropertyServiceImpl(
            PropertyRepository propertyRepository,
            PropertyMapper propertyMapper) {

        this.propertyRepository = propertyRepository;
        this.propertyMapper = propertyMapper;

    }

    /**
     * Creates a new property listing.
     *
     * Processing Flow:
     *
     * 1. Validate duplicate property rules.
     * 2. Convert request DTO into a JPA entity.
     * 3. Generate a unique business property code.
     * 4. Persist the property.
     * 5. Convert the persisted entity into a response DTO.
     * 6. Invalidate cached property listings.
     *
     * Why invalidate the cache?
     *
     * - A new property changes the complete property list.
     * - Cached search results may become stale.
     * - The next search request should retrieve fresh data.
     *
     * The entire operation executes inside a single database transaction.
     *
     * @param request Property creation request.
     * @return Newly created property.
     */
    @Override
    @Transactional
    @CacheInvalidateAll(cacheName = PROPERTIES_CACHE)
    public PropertyResponse createProperty(
            PropertyRequest request) {

        LOGGER.info("Creating new property listing.");

        /*
         * Prevent duplicate property listings.
         *
         * Business Rule:
         *
         * Two properties having the same title and address are considered
         * duplicates and cannot coexist.
         */
        if (propertyRepository.existsByTitleAndAddressIgnoreCase(
                request.getTitle(),
                request.getAddress())) {

            LOGGER.warn("Duplicate property creation request received.");

            throw new ResourceAlreadyExistsException(
                    "A property with the same title and address already exists."
            );

        }

        /*
         * Convert the incoming request DTO into a JPA entity.
         */
        Property property =
                propertyMapper.toProperty(request);

        /*
         * Generate a business-friendly property code.
         *
         * The property code is independent of the database primary key and
         * can safely be exposed to API consumers.
         */
        property.setPropertyCode(generatePropertyCode());

        /*
         * Persist the property.
         *
         * Panache eliminates the need for writing boilerplate CRUD
         * implementations by providing built-in persistence methods.
         */
        propertyRepository.persist(property);

        LOGGER.infof(
                "Property created successfully with Property Code: %s",
                property.getPropertyCode()
        );

        /*
         * Convert the persisted entity into a response DTO.
         */
        return propertyMapper.toPropertyResponse(property);

    }

    /**
     * Generates a unique business property code.
     *
     * Characteristics:
     *
     * - Unique across property records.
     * - Independent of the database primary key.
     * - Safe to expose externally.
     * - Generated without requiring a database lookup.
     *
     * Example:
     *
     * PROP-8F3A7C91D2E4
     * PROP-A91D43B8F762
     * PROP-BC67A91234DE
     *
     * UUID-based identifiers are particularly suitable for cloud-native
     * applications because they can be generated independently by each
     * application instance without coordination.
     *
     * @return Unique business property code.
     */
    private String generatePropertyCode() {

        return "PROP-" + UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 12)
                .toUpperCase();

    }

    /**
     * Retrieves a property using its unique database identifier.
     *
     * Processing Flow:
     *
     * 1. Check whether the property is already available in the cache.
     * 2. If cached, immediately return the cached response.
     * 3. Otherwise retrieve the property from the database.
     * 4. Throw ResourceNotFoundException if the property does not exist.
     * 5. Convert the entity into a response DTO.
     * 6. Store the response in the cache for subsequent requests.
     *
     * Why use caching?
     *
     * - Frequently requested properties avoid repeated database queries.
     * - Reduces database load.
     * - Improves overall response time.
     * - Particularly beneficial for read-heavy applications.
     *
     * @param id Property identifier.
     * @return Property details.
     */
    @Override
    @CacheResult(cacheName = PROPERTY_BY_ID_CACHE)
    public PropertyResponse getPropertyById(Long id) {

        LOGGER.infof("Fetching property with id: %d", id);

        /*
         * Retrieve the property from the database.
         */
        Property property =
                propertyRepository.findById(id);

        if (property == null) {

            LOGGER.warnf(
                    "Property not found with id: %d",
                    id
            );

            throw new ResourceNotFoundException(
                    "Property not found with id: " + id
            );

        }

        /*
         * Convert the managed entity into a response DTO.
         */
        return propertyMapper.toPropertyResponse(property);

    }

    /**
     * Searches property listings using dynamic search criteria.
     *
     * This method demonstrates one of the most powerful capabilities of
     * Hibernate Panache—building flexible search APIs with very little code.
     *
     * Processing Flow:
     *
     * 1. Configure dynamic sorting.
     * 2. Delegate searching to the repository.
     * 3. Repository dynamically builds the JPQL query.
     * 4. Apply pagination using Panache Page.
     * 5. Execute the query.
     * 6. Convert entities into response DTOs.
     * 7. Build a paginated response.
     *
     * Supported Features:
     *
     * - Retrieve all properties.
     * - Keyword search.
     * - City filter.
     * - State filter.
     * - Listing type filter.
     * - Property type filter.
     * - Minimum price filter.
     * - Maximum price filter.
     * - Dynamic sorting.
     * - Pagination.
     * - Combination of multiple search filters.
     *
     * Quarkus Panache provides built-in support for:
     *
     * - Dynamic sorting using Sort.
     * - Pagination using Page.
     * - Efficient query execution using PanacheQuery.
     *
     * This significantly reduces the amount of boilerplate code compared to
     * traditional JPA implementations.
     *
     * @param request Search request containing pagination, sorting and
     *                filtering criteria.
     * @return Paginated list of matching properties.
     */
    @Override
    @CacheResult(cacheName = PROPERTIES_CACHE)
    public PagedResponse<PropertyResponse> searchProperties(
            @CacheKey PropertySearchRequest request) {

        LOGGER.infof(
                "Searching properties using criteria: %s",
                request
        );

        /*
         * Configure dynamic sorting.
         *
         * The client can specify both:
         *
         * - Sort field.
         * - Sort direction.
         */
        Sort sort = Sort.by(request.getSortBy());

        if ("desc".equalsIgnoreCase(request.getSortDirection())) {
            sort = sort.descending();
        }

        /*
         * Execute the dynamic search.
         *
         * The repository builds the JPQL query by adding only the filters
         * supplied by the client.
         */
        PanacheQuery<Property> query =
                propertyRepository.searchProperties(
                        request,
                        sort
                );

        /*
         * Apply pagination.
         *
         * Panache automatically adds the appropriate OFFSET and LIMIT clauses
         * based on the requested page number and page size.
         */
        query.page(
                Page.of(
                        request.getPage(),
                        request.getSize()
                )
        );

        /*
         * Convert the retrieved entities into API response DTOs.
         */
        List<PropertyResponse> properties =
                query.list()
                        .stream()
                        .map(propertyMapper::toPropertyResponse)
                        .toList();

        /*
         * Build and return the paginated response.
         *
         * The response contains:
         *
         * - Current page.
         * - Page size.
         * - Total number of records.
         * - Total number of pages.
         * - First page indicator.
         * - Last page indicator.
         * - Property data.
         */
        return PagedResponse.<PropertyResponse>builder()
                .content(properties)
                .page(request.getPage())
                .size(request.getSize())
                .totalElements(query.count())
                .totalPages(query.pageCount())
                .first(query.page().index == 0)
                .last(query.page().index >= query.pageCount() - 1)
                .build();

    }

    /**
     * Updates an existing property.
     *
     * Processing Flow:
     *
     * 1. Retrieve the existing property.
     * 2. Verify that the property exists.
     * 3. Validate business rules.
     * 4. Prevent duplicate property listings.
     * 5. Copy updated values into the managed entity.
     * 6. Let Hibernate automatically synchronize changes with the database.
     * 7. Invalidate affected caches.
     * 8. Return the updated property.
     *
     * Why no explicit update() or persist() call?
     *
     * - Panache returns a managed entity.
     * - The entity remains attached to the persistence context.
     * - Hibernate automatically detects modified fields.
     * - Dirty checking issues the SQL UPDATE statement when the transaction
     *   commits.
     *
     * Cache Strategy:
     *
     * - Invalidate the individual property cache.
     * - Invalidate cached property search results.
     *
     * @param id Property identifier.
     * @param request Updated property details.
     * @return Updated property.
     */
    @Override
    @Transactional
    @CacheInvalidate(cacheName = PROPERTY_BY_ID_CACHE)
    @CacheInvalidateAll(cacheName = PROPERTIES_CACHE)
    public PropertyResponse updateProperty(
            @CacheKey Long id,
            PropertyRequest request) {

        LOGGER.infof(
                "Updating property with id: %d",
                id
        );

        /*
         * Retrieve the existing managed entity.
         */
        Property property =
                propertyRepository.findById(id);

        if (property == null) {

            LOGGER.warnf(
                    "Property not found with id: %d",
                    id
            );

            throw new ResourceNotFoundException(
                    "Property not found with id: " + id
            );

        }

        /*
         * Prevent duplicate property listings.
         *
         * Execute the duplicate validation only when either the property
         * title or address has changed.
         */
        boolean titleChanged =
                !property.getTitle()
                        .equalsIgnoreCase(request.getTitle());

        boolean addressChanged =
                !property.getAddress()
                        .equalsIgnoreCase(request.getAddress());

        if ((titleChanged || addressChanged)
                && propertyRepository.existsByTitleAndAddressIgnoreCase(
                request.getTitle(),
                request.getAddress())) {

            LOGGER.warn(
                    "Duplicate property detected during update."
            );

            throw new ResourceAlreadyExistsException(
                    "A property with the same title and address already exists."
            );

        }

        /*
         * Copy all editable fields into the managed entity.
         *
         * Server-managed fields such as:
         *
         * - id
         * - propertyCode
         * - createdAt
         * - updatedAt
         *
         * remain unchanged.
         */
        propertyMapper.updateProperty(
                property,
                request
        );

        /*
         * No explicit save() or update() operation is required.
         *
         * Since the entity is managed by Hibernate and the method executes
         * within a transaction, Hibernate automatically detects modified
         * fields and synchronizes them with the database during transaction
         * commit.
         */

        LOGGER.infof(
                "Property updated successfully. Property Code: %s",
                property.getPropertyCode()
        );

        return propertyMapper.toPropertyResponse(property);

    }

    /**
     * Deletes an existing property.
     *
     * Processing Flow:
     *
     * 1. Retrieve the property.
     * 2. Verify that it exists.
     * 3. Delete the managed entity.
     * 4. Invalidate affected caches.
     * 5. Complete the transaction.
     *
     * Cache Strategy:
     *
     * - Remove the individual property from cache.
     * - Remove cached property search results.
     *
     * Panache provides a built-in delete() operation, eliminating the need
     * for custom repository implementations.
     *
     * @param id Property identifier.
     */
    @Override
    @Transactional
    @CacheInvalidate(cacheName = PROPERTY_BY_ID_CACHE)
    @CacheInvalidateAll(cacheName = PROPERTIES_CACHE)
    public void deleteProperty(
            @CacheKey Long id) {

        LOGGER.infof(
                "Deleting property with id: %d",
                id
        );

        /*
         * Retrieve the existing managed entity.
         */
        Property property =
                propertyRepository.findById(id);

        if (property == null) {

            LOGGER.warnf(
                    "Property not found with id: %d",
                    id
            );

            throw new ResourceNotFoundException(
                    "Property not found with id: " + id
            );

        }

        /*
         * Delete the managed entity.
         *
         * Hibernate and Panache automatically generate the appropriate SQL
         * DELETE statement.
         */
        propertyRepository.delete(property);

        LOGGER.infof(
                "Property deleted successfully. Property Code: %s",
                property.getPropertyCode()
        );

    }

}