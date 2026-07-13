package com.sirajchaudhary.propertyfinder.constants;

/*
 * ============================================================================
 * API Constants
 * ============================================================================
 *
 * Centralizes API endpoint paths and commonly used application messages.
 *
 * Benefits:
 *
 * - Avoids hardcoded strings throughout the application.
 * - Makes API versioning easier.
 * - Improves maintainability.
 * - Keeps endpoint definitions consistent across resources.
 *
 */
public final class ApiConstants {

    private ApiConstants() {
        // Prevent instantiation.
    }

    /*
     * =========================================================================
     * API Versioning
     * =========================================================================
     */

    public static final String API_BASE = "/api";

    public static final String API_VERSION_V1 = "/v1";

    /*
     * =========================================================================
     * Resource Endpoints
     * =========================================================================
     */

    public static final String PROPERTIES = "/properties";

    /*
     * =========================================================================
     * Complete API Endpoints
     * =========================================================================
     */

    public static final String PROPERTY_API = API_BASE + API_VERSION_V1 + PROPERTIES;

    /*
     * =========================================================================
     * Success Messages
     * =========================================================================
     */

    public static final String PROPERTY_CREATED_SUCCESS =
            "Property created successfully.";

    public static final String PROPERTY_UPDATED_SUCCESS =
            "Property updated successfully.";

    public static final String PROPERTY_RETRIEVED_SUCCESS =
            "Property retrieved successfully.";

    public static final String PROPERTIES_RETRIEVED_SUCCESS =
            "Properties retrieved successfully.";

    public static final String PROPERTY_DELETED_SUCCESS =
            "Property deleted successfully.";

}