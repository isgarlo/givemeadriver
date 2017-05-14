package io.github.isgarlo.givemeadriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.Killable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.github.isgarlo.givemeadriver.DefaultCapabilities.autoCloseBrowsers;


public class WebDriverThreadLocalContainer implements WebDriverContainer {

    private static final Logger log = LoggerFactory.getLogger(WebDriverThreadLocalContainer.class);

    private WebDriverFactory factory = new WebDriverFactory();
    private LinkedList<WebDriver> ALL_WEB_DRIVERS = new LinkedList<>();
    private AtomicBoolean shutDownHookTriggered = new AtomicBoolean(false);

    @Override
    public WebDriver createDriver() {
        WebDriver driver = factory.createWebDriver();
        log.info("Created " + driver);
        ALL_WEB_DRIVERS.add(driver);
        markForAutoClose(autoCloseBrowsers());
        return driver;
    }

    @Override
    public WebDriver getFirstDriver() {
        return ALL_WEB_DRIVERS.getFirst();
    }

    @Override
    public WebDriver getLastDriver() {
        return ALL_WEB_DRIVERS.getLast();
    }

    @Override
    public WebDriver getDriverBy(final int index) {
        return ALL_WEB_DRIVERS.get(index);
    }

    @Override
    public void closeLastDriver() {
        close(getLastDriver());
    }

    private void markForAutoClose(final boolean value) {
        if(!shutDownHookTriggered.getAndSet(true) && value) {
            Runtime.getRuntime().addShutdownHook(
                    new UnusedWebDriversCleanupThread());
        }
    }

    private void closeUnusedWebDrivers() {
        for (WebDriver webDriver : ALL_WEB_DRIVERS) {
            close(webDriver);
        }
    }

    private void close(WebDriver webdriver) {
        log.info("Trying to close the browser " + describe(webdriver) + " ...");
        try {
            TimeUnit.SECONDS.sleep(5);
            webdriver.quit();
            log.info("Browser " + describe(webdriver) + " has been closed");
        } catch (Exception cannotCloseBrowser) {
            log.error("Cannot close browser normally: " + cannotCloseBrowser);
            killBrowser(webdriver);
        } finally {
            ALL_WEB_DRIVERS.remove(webdriver);
        }
    }

    private void killBrowser(WebDriver webdriver) {
        if (webdriver instanceof Killable) {
            try {
                ((Killable) webdriver).kill();
            } catch (Exception e) {
                log.error("Failed to kill browser " + webdriver + ':', e);
            }
        }
    }

    private String describe(WebDriver webDriver) {
        return webDriver.getClass().getSimpleName();
    }

    protected class UnusedWebDriversCleanupThread extends Thread {

        @Override
        public void run() {
            closeUnusedWebDrivers();
        }
    }
}
