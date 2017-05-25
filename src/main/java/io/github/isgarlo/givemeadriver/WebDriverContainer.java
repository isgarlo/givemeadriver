package io.github.isgarlo.givemeadriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.Killable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;


class WebDriverContainer {

    private static final Logger log = LoggerFactory.getLogger(WebDriverContainer.class);

    protected WebDriverFactory factory = new WebDriverFactory();
    WebDriver WEB_DRIVER = null;
    private AtomicBoolean shutDownHookTriggered = new AtomicBoolean(false);

    private WebDriverContainer() { }

    static WebDriverContainer getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper{
        private static final WebDriverContainer INSTANCE = new WebDriverContainer();
    }

    WebDriver createDriver(WebDriverProperties properties) {
        if (WEB_DRIVER == null) {
            WebDriver driver = factory.createWebDriver(properties);
            log.info("Created " + driver);
            log.info(properties.toString());
            WEB_DRIVER = driver;
            if (properties.isAutoClose())
                markForAutoClose();
            return driver;
        } else {
            log.info("There is a driver already open: " + describe(WEB_DRIVER));
            return WEB_DRIVER;
        }
    }

    WebDriver getDriver() {
        return WEB_DRIVER;
    }

    void closeDriver() {
        close(WEB_DRIVER);
    }

    private void markForAutoClose() {
        if(!shutDownHookTriggered.getAndSet(true)) {
            Runtime.getRuntime().addShutdownHook(
                    new UnusedWebDriversCleanupThread());
        }
    }

    private void close(WebDriver webDriver) {
        log.info("Trying to close the browser " + describe(webDriver) + " ...");
        try {
            TimeUnit.SECONDS.sleep(5);
            webDriver.quit();
            log.info("Browser " + describe(webDriver) + " has been closed");
        } catch (Exception cannotCloseBrowser) {
            log.error("Cannot close browser normally: " + cannotCloseBrowser);
            killBrowser(webDriver);
        } finally {
            WEB_DRIVER = null;
        }
    }

    private void killBrowser(WebDriver webDriver) {
        if (webDriver instanceof Killable) {
            try {
                ((Killable) webDriver).kill();
            } catch (Exception e) {
                log.error("Failed to kill browser " + webDriver + ':', e);
            }
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
