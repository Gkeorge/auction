package com.nanor;

import org.junit.jupiter.api.*;

import java.util.logging.Logger;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public interface TestLifecycleLogger {

    Logger logger = Logger.getLogger(TestLifecycleLogger.class.getName());

    @BeforeAll
    default void beforeAllTests(TestInfo testInfo) {
        logger.info(() -> String.format("Before all tests for test class [%s]",
                testInfo.getTestClass().get().getSimpleName()));
    }

    @AfterAll
    default void afterAllTests(TestInfo testInfo) {
        logger.info(() -> String.format("After all tests for test class [%s]",
                testInfo.getTestClass().get().getSimpleName()));
    }

    @BeforeEach
    default void beforeEachTest(TestInfo testInfo) {
        logger.info(() -> String.format("About to execute [%s]",
                testInfo.getDisplayName()));
    }

    @AfterEach
    default void afterEachTest(TestInfo testInfo) {
        logger.info(() -> String.format("Finished executing [%s]",
                testInfo.getDisplayName()));
    }

}
