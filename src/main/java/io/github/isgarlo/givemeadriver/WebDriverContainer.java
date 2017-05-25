package io.github.isgarlo.givemeadriver;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.google.common.base.Preconditions.checkState;


class WebDriverContainer {

    private static final Logger log = LoggerFactory.getLogger(WebDriverContainer.class);

    protected WebDriverFactory factory = new WebDriverFactory();
    WebDriver WEB_DRIVER = null;
    private AtomicBoolean shutDownHookTriggered = new AtomicBoolean(false);

    private WebDriverContainer() { }

    static WebDriverContainer getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final WebDriverContainer INSTANCE = new WebDriverContainer();
    }

    WebDriver createDriver(WebDriverProperties properties) {
        checkState(WEB_DRIVER == null,
                "There is a driver already open. Only one instance allowed.");
        WebDriver driver = factory.createWebDriver(properties);
        log.info("Created " + driver);
        log.info(properties.toString());
        WEB_DRIVER = driver;
        if (properties.isAutoClose())
            markForAutoClose();
        return driver;
    }

    WebDriver getDriver() {
        checkState(WEB_DRIVER != null,
                "No driver has been set. Call GiveMeADriver.create();");
        return WEB_DRIVER;
    }

    void closeDriver() {
        if (WEB_DRIVER != null) {
            log.info("Trying to close the browser " + describe(WEB_DRIVER) + " ...");
            //TimeUnit.SECONDS.sleep(5);
            WEB_DRIVER.quit();
            log.info("Browser " + describe(WEB_DRIVER) + " has been closed");
            WEB_DRIVER = null;
        }
    }

    void markForAutoClose() {
        if(!shutDownHookTriggered.getAndSet(true)) {
            Runtime.getRuntime().addShutdownHook(
                    new UnusedWebDriversCleanupThread());
        }
    }

    private String describe(WebDriver webDriver) {
        return webDriver.getClass().getSimpleName();
    }

    protected class UnusedWebDriversCleanupThread extends Thread {

        @Override
        public void run() {
            closeDriver();
        }
    }
}
