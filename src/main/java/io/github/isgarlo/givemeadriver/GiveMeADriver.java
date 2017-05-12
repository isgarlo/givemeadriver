package io.github.isgarlo.givemeadriver;

import org.openqa.selenium.WebDriver;

public class GiveMeADriver {

    private static WebDriverThreadLocalContainer driverContainer = new WebDriverThreadLocalContainer();

    /**
     * Get the underlying instance of Selenium WebDriver.
     * This can be used for any operations directly with WebDriver.
     */
    public static WebDriver current() {
        return driverContainer.getLastDriver();
    }

    public static WebDriver create() {
        return driverContainer.createDriver();
    }

    public static void close() {
        driverContainer.closeLastDriver();
    }

    public static WebDriver first() {
        return driverContainer.getFirstDriver();
    }

    public static WebDriver last() {
        return driverContainer.getLastDriver();
    }

    public static WebDriver get(final int index) {
        return driverContainer.getDriverBy(index);
    }

}