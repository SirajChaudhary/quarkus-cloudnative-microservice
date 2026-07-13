package com.sirajchaudhary.propertyfinder.health;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

/*
 * ============================================================================
 * Database Health Check
 * ============================================================================
 *
 * Performs a readiness check for the application's PostgreSQL database.
 *
 * Purpose:
 *
 * - Verify database connectivity.
 * - Ensure the application is ready to serve client requests.
 * - Detect database outages early.
 * - Provide health information to monitoring tools.
 *
 * This health check executes a lightweight SQL query against the configured
 * PostgreSQL datasource. If the query succeeds, the application is considered
 * ready. Otherwise, the application reports itself as unavailable.
 *
 * In production environments, container orchestration platforms such as
 * Kubernetes and OpenShift can use this readiness endpoint to determine
 * whether traffic should be routed to this application instance.
 *
 */
@Readiness
@ApplicationScoped
public class DatabaseHealthCheck implements HealthCheck {

    /*
     * EntityManager used to execute a lightweight database query.
     */
    @Inject
    EntityManager entityManager;

    /**
     * Performs the database readiness check.
     *
     * Processing Flow:
     *
     * 1. Execute a lightweight SQL query.
     * 2. Verify that the database responds successfully.
     * 3. Return an UP health status when the query succeeds.
     * 4. Return a DOWN health status when an exception occurs.
     *
     * Why use "SELECT 1"?
     *
     * - Extremely lightweight.
     * - Executes very quickly.
     * - Does not access application tables.
     * - Commonly used for database connectivity checks.
     *
     * @return Database readiness status.
     */
    @Override
    public HealthCheckResponse call() {

        try {

            /*
             * Execute a lightweight native SQL query to verify database
             * connectivity.
             */
            entityManager
                    .createNativeQuery("SELECT 1")
                    .getSingleResult();

            /*
             * Database connection is healthy.
             */
            return HealthCheckResponse
                    .named("PostgreSQL Database")
                    .up()
                    .withData("database", "PostgreSQL")
                    .withData("status", "Connected")
                    .build();

        } catch (Exception exception) {

            /*
             * Database is unavailable or an unexpected error occurred.
             */
            return HealthCheckResponse
                    .named("PostgreSQL Database")
                    .down()
                    .withData("database", "PostgreSQL")
                    .withData("status", "Disconnected")
                    .withData("error", exception.getMessage())
                    .build();

        }

    }

}