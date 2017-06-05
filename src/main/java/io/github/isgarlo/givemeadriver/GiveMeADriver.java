package io.github.isgarlo.givemeadriver;

import com.google.common.collect.Maps;
import io.github.isgarlo.givemeadriver.converter.CapabilitiesConverter;
import io.github.isgarlo.givemeadriver.converter.CapabilitiesConverterFactory;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.Properties;

public final class GiveMeADriver {

    private static final WebDriverContainer container = new WebDriverContainer();
    private static final CapabilitiesConverterFactory converterFactory = new CapabilitiesConverterFactory();

    private GiveMeADriver() {
        // utility class
    }

    /**
     * Creates a new WebDriver instance based on the defines system properties
     * prefixed with 'capabilities.'
     * @return the WebDriver instance
     */
    public static WebDriver create() {
        // map properties from system & validate
        WebDriverProperties properties = new WebDriverProperties(mapFromSystemProperties()).validate();

        // convert properties to DesiredCapabilities & create WebDriver instance
        CapabilitiesConverter converter = converterFactory.create(properties.getDriverType());
        WebDriver driver = container.createDriver(properties.getDriverType(), converter.convert(properties));

        // add hook to auclose the driver
        container.markDriverForAutoClose(properties.isAutoClose());

        // resize the driver window
        container.setDriverWindowSize(properties.getBrowserSize());
        return driver;
    }

    /**
     * Gets the underlying instance of Selenium WebDriver.
     * This can be used for any operations directly with WebDriver.
     * @return the WebDriver instance
     */
    public static WebDriver current() {
        return container.getDriver();
    }

    /**
     * Closes the underlying instance of Selenium WebDriver.
     */
    public static void close() {
        container.closeDriver();
    }

    private static Map<String, String> mapFromSystemProperties() {
        Properties browserSystemProperties = PropertiesUtil.extractSubset(
                PropertiesUtil.getSystemProperties(), "capabilities.");
        return Maps.fromProperties(browserSystemProperties);
    }

}
