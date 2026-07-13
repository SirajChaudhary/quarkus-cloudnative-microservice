package com.sirajchaudhary.propertyfinder.resource;

import com.sirajchaudhary.propertyfinder.constants.ApiConstants;
import com.sirajchaudhary.propertyfinder.dto.request.PropertyRequest;
import com.sirajchaudhary.propertyfinder.dto.request.PropertySearchRequest;
import com.sirajchaudhary.propertyfinder.dto.response.ApiResponse;
import com.sirajchaudhary.propertyfinder.dto.response.PagedResponse;
import com.sirajchaudhary.propertyfinder.dto.response.PropertyResponse;
import com.sirajchaudhary.propertyfinder.enums.ListingType;
import com.sirajchaudhary.propertyfinder.enums.PropertyType;
import com.sirajchaudhary.propertyfinder.service.PropertyService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
 * ============================================================================
 * Property Resource
 * ============================================================================
 *
 * REST resource responsible for exposing Property Finder APIs.
 *
 * Responsibilities:
 *
 * - Accept incoming HTTP requests.
 * - Validate request payloads and query parameters.
 * - Delegate business operations to the service layer.
 * - Return standardized API responses.
 *
 * Design Principles:
 *
 * - Keep the resource layer lightweight.
 * - Do not implement business logic in the REST layer.
 * - Communicate with clients only through DTOs.
 * - Return consistent API responses for every endpoint.
 *
 * The resource acts as the entry point into the application while the service
 * layer contains all business rules and the repository layer manages database
 * interactions.
 *
 */
@Path(ApiConstants.PROPERTY_API)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PropertyResource {

    private final PropertyService propertyService;

    @Inject
    public PropertyResource(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    /**
     * Creates a new property listing.
     *
     * Request Processing Flow:
     *
     * 1. Receive HTTP POST request.
     * 2. Validate the request using Jakarta Bean Validation.
     * 3. Delegate business processing to the service layer.
     * 4. Persist the property.
     * 5. Return HTTP 201 (Created) with the newly created resource.
     *
     * Business validations such as duplicate property detection and property
     * code generation are handled by the service layer.
     *
     * Example:
     *
     * POST /api/v1/properties
     *
     * @param request Property creation request.
     * @return Newly created property.
     */
    @POST
    public Response createProperty(
            @Valid PropertyRequest request) {

        PropertyResponse propertyResponse =
                propertyService.createProperty(request);

        ApiResponse<PropertyResponse> response =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        Response.Status.CREATED.getStatusCode(),
                        ApiConstants.PROPERTY_CREATED_SUCCESS,
                        propertyResponse
                );

        return Response.status(Response.Status.CREATED)
                .entity(response)
                .build();

    }

    /**
     * Retrieves a property using its unique database identifier.
     *
     * Request Processing Flow:
     *
     * 1. Receive property identifier.
     * 2. Delegate lookup to the service layer.
     * 3. Service retrieves the entity from the repository.
     * 4. Entity is converted into a response DTO.
     * 5. Return HTTP 200 (OK).
     *
     * If the property does not exist, the service throws a
     * ResourceNotFoundException which is translated into a standardized
     * HTTP 404 response by the GlobalExceptionMapper.
     *
     * Example:
     *
     * GET /api/v1/properties/1
     *
     * @param id Property identifier.
     * @return Matching property.
     */
    @GET
    @Path("/{id}")
    public Response getPropertyById(
            @PathParam("id") Long id) {

        PropertyResponse propertyResponse =
                propertyService.getPropertyById(id);

        ApiResponse<PropertyResponse> response =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        Response.Status.OK.getStatusCode(),
                        ApiConstants.PROPERTY_RETRIEVED_SUCCESS,
                        propertyResponse
                );

        return Response.ok(response)
                .build();

    }

    /**
     * IMPORTANT AND INTERESTING API: Learn very useful features of Hibernate Panache
     *
     * Searches property listings using dynamic search criteria.
     *
     * This endpoint demonstrates one of Hibernate Panache's most useful
     * capabilities—building a single endpoint that supports searching,
     * filtering, sorting and pagination without creating multiple repository
     * methods.
     *
     * Supported Features:
     *
     * - Retrieve all properties.
     * - Search using a keyword.
     * - Filter by city.
     * - Filter by state.
     * - Filter by listing type.
     * - Filter by property type.
     * - Filter by minimum price.
     * - Filter by maximum price.
     * - Sort by any supported property field.
     * - Sort in ascending or descending order.
     * - Retrieve paginated results.
     * - Combine all search criteria in a single request.
     *
     * How Quarkus & Panache make this possible:
     *
     * - All query parameters are collected into a PropertySearchRequest DTO.
     * - The service layer delegates the request to the repository.
     * - The repository dynamically builds a JPQL query by adding only the
     *   filters supplied by the client.
     * - Hibernate Panache executes the dynamic query.
     * - Panache automatically applies pagination using Page.
     * - Panache automatically applies sorting using Sort.
     * - The result is mapped into a paginated response object and returned
     *   to the client.
     *
     * Since every search criterion is optional, this single endpoint can
     * support virtually every search scenario without introducing additional
     * REST APIs.
     *
     * Example Requests:
     *
     * GET /api/v1/properties
     *
     * GET /api/v1/properties?page=0&size=10
     *
     * GET /api/v1/properties?sortBy=price&sortDirection=asc
     *
     * GET /api/v1/properties?city=Hyderabad
     *
     * GET /api/v1/properties?listingType=SALE
     *
     * GET /api/v1/properties?propertyType=VILLA
     *
     * GET /api/v1/properties?keyword=luxury
     *
     * GET /api/v1/properties?minPrice=10000000&maxPrice=30000000
     *
     * GET /api/v1/properties?city=Hyderabad
     *                         &listingType=SALE
     *                         &sortBy=price
     *                         &sortDirection=desc
     *                         &page=0
     *                         &size=5
     *
     * @return Paginated list of properties matching the supplied criteria.
     */
    @GET
    public Response searchProperties(

            @DefaultValue("0")
            @QueryParam("page")
            int page,

            @DefaultValue("10")
            @QueryParam("size")
            int size,

            @DefaultValue("createdAt")
            @QueryParam("sortBy")
            String sortBy,

            @DefaultValue("desc")
            @QueryParam("sortDirection")
            String sortDirection,

            @QueryParam("keyword")
            String keyword,

            @QueryParam("city")
            String city,

            @QueryParam("state")
            String state,

            @QueryParam("listingType")
            ListingType listingType,

            @QueryParam("propertyType")
            PropertyType propertyType,

            @QueryParam("minPrice")
            BigDecimal minPrice,

            @QueryParam("maxPrice")
            BigDecimal maxPrice) {

        /*
         * Aggregate all optional HTTP query parameters into a dedicated
         * search request object.
         *
         * Benefits:
         *
         * - Avoids long method signatures.
         * - Improves readability.
         * - Simplifies future enhancements.
         * - Keeps the service and repository APIs clean.
         * - Provides a single object representing the complete search request.
         */
        PropertySearchRequest request =
                PropertySearchRequest.builder()
                        .page(page)
                        .size(size)
                        .sortBy(sortBy)
                        .sortDirection(sortDirection)
                        .keyword(keyword)
                        .city(city)
                        .state(state)
                        .listingType(listingType)
                        .propertyType(propertyType)
                        .minPrice(minPrice)
                        .maxPrice(maxPrice)
                        .build();

        PagedResponse<PropertyResponse> pagedResponse =
                propertyService.searchProperties(request);

        ApiResponse<PagedResponse<PropertyResponse>> response =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        Response.Status.OK.getStatusCode(),
                        ApiConstants.PROPERTIES_RETRIEVED_SUCCESS,
                        pagedResponse
                );

        return Response.ok(response)
                .build();

    }

    /**
     * Updates an existing property.
     *
     * Request Processing Flow:
     *
     * 1. Receive the property identifier.
     * 2. Validate the incoming request.
     * 3. Delegate the update operation to the service layer.
     * 4. Service validates business rules.
     * 5. Existing property is updated and persisted.
     * 6. Return the updated property details.
     *
     * The service layer is responsible for:
     *
     * - Verifying that the property exists.
     * - Applying business validations.
     * - Updating only editable fields.
     * - Persisting the latest state.
     *
     * Example:
     *
     * PUT /api/v1/properties/1
     *
     * @param id Property identifier.
     * @param request Updated property details.
     * @return Updated property.
     */
    @PUT
    @Path("/{id}")
    public Response updateProperty(
            @PathParam("id") Long id,
            @Valid PropertyRequest request) {

        PropertyResponse propertyResponse =
                propertyService.updateProperty(id, request);

        ApiResponse<PropertyResponse> response =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        Response.Status.OK.getStatusCode(),
                        ApiConstants.PROPERTY_UPDATED_SUCCESS,
                        propertyResponse
                );

        return Response.ok(response)
                .build();

    }

    /**
     * Deletes an existing property.
     *
     * Request Processing Flow:
     *
     * 1. Receive the property identifier.
     * 2. Delegate the delete request to the service layer.
     * 3. Service verifies that the property exists.
     * 4. Property is removed from the database.
     * 5. Return a success response.
     *
     * If the supplied identifier does not exist, the service layer throws a
     * ResourceNotFoundException which is converted into a standardized HTTP
     * error response by the GlobalExceptionMapper.
     *
     * Example:
     *
     * DELETE /api/v1/properties/1
     *
     * @param id Property identifier.
     * @return Success response indicating the property was deleted.
     */
    @DELETE
    @Path("/{id}")
    public Response deleteProperty(
            @PathParam("id") Long id) {

        propertyService.deleteProperty(id);

        ApiResponse<Void> response =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        Response.Status.OK.getStatusCode(),
                        ApiConstants.PROPERTY_DELETED_SUCCESS,
                        null
                );

        return Response.ok(response)
                .build();

    }

}