package io.github.isgarlo.givemeadriver;

import com.google.common.collect.Maps;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.openqa.selenium.WebDriver;
import java.util.Map;
import java.util.Properties;

public final class GiveMeADriver {

    private GiveMeADriver() {
        // utility class
    }

    /**
     * Creates a new WebDriver instance based on the defines system properties
     * prefixed with 'capabilities.'
     * @return the WebDriver instance
     */
    public static WebDriver create() {
        WebDriverProperties properties = new WebDriverProperties(mapFromSystemProperties());
        return WebDriverContainer.getInstance().createDriver(properties);
    }

    /**
     * Gets the underlying instance of Selenium WebDriver.
     * This can be used for any operations directly with WebDriver.
     * @return the WebDriver instance
     */
    public static WebDriver current() {
        return WebDriverContainer.getInstance().getDriver();
    }

    /**
     * Closes the underlying instance of Selenium WebDriver.
     */
    public static void close() {
        WebDriverContainer.getInstance().closeDriver();
    }

    private static Map<String, String> mapFromSystemProperties() {
        Properties browserSystemProperties = PropertiesUtil.extractSubset(
                PropertiesUtil.getSystemProperties(), "capabilities.");
        return Maps.fromProperties(browserSystemProperties);
    }

}
