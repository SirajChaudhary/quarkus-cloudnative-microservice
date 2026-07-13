package com.sirajchaudhary.propertyfinder.dto.response;

import java.time.LocalDateTime;

/*
 * ============================================================================
 * API Response
 * ============================================================================
 *
 * Standard success response returned by all REST endpoints.
 *
 * Responsibilities:
 *
 * - Provide a consistent response structure.
 * - Return the requested business data.
 * - Include HTTP status information.
 * - Include a user-friendly success message.
 * - Include the response timestamp.
 *
 * Using a generic response model ensures every successful API returns the
 * same JSON structure, making the API easier to consume and maintain.
 *
 * Since this is a generic record, it can wrap any response type.
 *
 * Examples:
 *
 * - ApiResponse<PropertyResponse>
 * - ApiResponse<PagedResponse<PropertyResponse>>
 * - ApiResponse<Void>
 *
 * Example:
 *
 * {
 *   "timestamp": "2026-07-12T15:30:45",
 *   "status": 200,
 *   "message": "Property retrieved successfully.",
 *   "data": {
 *      ...
 *   }
 * }
 *
 */
public record ApiResponse<T>(

        /**
         * Date and time when the response was generated.
         */
        LocalDateTime timestamp,

        /**
         * HTTP status code.
         *
         * Examples:
         *
         * - 200
         * - 201
         * - 204
         */
        int status,

        /**
         * Human-readable success message.
         */
        String message,

        /**
         * Business data returned by the API.
         *
         * Depending on the endpoint, this may contain:
         *
         * - A single resource
         * - A paginated response
         * - No data (null)
         */
        T data

) {
}