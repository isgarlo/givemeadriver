package io.github.isgarlo.givemeadriver;

import io.github.isgarlo.givemeadriver.factories.DriverType;
import io.github.isgarlo.givemeadriver.factories.FactoryHandler;
import io.github.isgarlo.givemeadriver.factories.IFactoryHandler;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkState;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;


class WebDriverContainer {

    private static final Logger log = LoggerFactory.getLogger(WebDriverContainer.class);
    IFactoryHandler factory = new FactoryHandler();
    WebDriverCleanupThread cleanupThread = new WebDriverCleanupThread();
    WebDriver WEB_DRIVER;

    WebDriver createDriver(DriverType type, DesiredCapabilities capabilities) {
        checkState(WEB_DRIVER == null,
                "There is a driver already open. Only one instance allowed.");

        WEB_DRIVER = factory.create(type).createDriver(capabilities);
        log.info("Created " + WEB_DRIVER);
        log.info(capabilities.toString());
        logChromeOptions((ChromeOptions) capabilities.getCapability(ChromeOptions.CAPABILITY));

        return WEB_DRIVER;
    }

    WebDriver getDriver() {
        checkState(WEB_DRIVER != null,
                "No driver has been set. Call GiveMeADriver.create();");
        return WEB_DRIVER;
    }

    void closeDriver() {
        if (WEB_DRIVER != null) {
            log.info("Trying to close the browser " + describe(WEB_DRIVER) + " ...");
            WEB_DRIVER.quit();
            log.info("Browser " + describe(WEB_DRIVER) + " has been closed");
            WEB_DRIVER = null;
        }
    }

    void markDriverForAutoClose(boolean value) {
        if(value) {
            Runtime.getRuntime().addShutdownHook(cleanupThread);
            log.info("Shutdown hook added. The driver will be auto closed.");
        }
    }

    void setDriverWindowSize(final String browserSize) {
        if(isNotEmpty(browserSize)) {
            try {
                WEB_DRIVER.manage().window().setPosition(new Point(0, 0));
                String[] dimension = browserSize.split("x");
                int width = Integer.parseInt(dimension[0]);
                int height = Integer.parseInt(dimension[1]);
                WEB_DRIVER.manage().window().setSize(new Dimension(width, height));
                log.info("Browser size set to " + browserSize);
            } catch (Exception e) {
                log.warn("Cannot resize " + describe(WEB_DRIVER) + ": " + e);
            }
        }
    }

    private String describe(WebDriver webDriver) {
        return webDriver.getClass().getSimpleName();
    }

    private void logChromeOptions(ChromeOptions chromeOptions) {
        try {
            if (chromeOptions != null)
                log.info("ChromeOptions " + chromeOptions.toJson().toString());
        } catch (IOException e) {
            log.warn("Unable to parse ChromeOptions to json", e);
        }
    }

    class WebDriverCleanupThread extends Thread {

        @Override
        public void run() {
            closeDriver();
        }
    }
}
