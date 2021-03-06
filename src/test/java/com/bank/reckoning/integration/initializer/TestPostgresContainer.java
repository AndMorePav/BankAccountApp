package com.bank.reckoning.integration.initializer;

import org.testcontainers.containers.PostgreSQLContainer;

/**
 * Class for creating database in Docker container
 */
public class TestPostgresContainer extends PostgreSQLContainer<TestPostgresContainer> {

    private static final String IMAGE_VERSION = "postgres:11.1";
    private static TestPostgresContainer container;

    private TestPostgresContainer() {
        super(IMAGE_VERSION);
    }

    public static TestPostgresContainer getInstance() {
        if (container == null) {
            container = new TestPostgresContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}
