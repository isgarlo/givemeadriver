package io.github.isgarlo.givemeadriver;

import io.github.bonigarcia.wdm.*;
import org.apache.commons.lang3.EnumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

import static com.google.common.base.Preconditions.checkState;
import static io.github.isgarlo.givemeadriver.DefaultCapabilities.*;

class WebDriverFactory {

    private static final Logger log = LoggerFactory.getLogger(WebDriverFactory.class);

    WebDriver createWebDriver() {
        DesiredCapabilities capabilities = CapabilitiesMapper.extractFromSystemProperties();
        log.info("Mapped " + capabilities.toString());
        return remote != null ? createRemoteDriver(remote, capabilities) :
                createLocalDriver(getBrowser(), getDriverVersion());
    }

    private WebDriver createRemoteDriver(final String remote, final DesiredCapabilities capabilities) {
        try {
            return new RemoteWebDriver(new URL(remote), capabilities);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid 'capabilities.remote' parameter: " + remote, e);
        }
    }

    private WebDriver createLocalDriver(final String browser, final String driverVersion) {
        LocalBrowserTypes browserType = EnumUtils.getEnum(LocalBrowserTypes.class, browser.toUpperCase());
        checkState(browserType != null, "Invalid 'capabilities.browser' parameter: " + browser);

        switch (browserType) {
            case CHROME:
            default:
                ChromeDriverManager.getInstance().version(driverVersion).setup();
                return new ChromeDriver();
            case FIREFOX:
                FirefoxDriverManager.getInstance().version(driverVersion).setup();
                return new FirefoxDriver();
            case SAFARI:
                return new SafariDriver();
            case IE:
                InternetExplorerDriverManager.getInstance().version(driverVersion).setup();
                return new InternetExplorerDriver();
            case EDGE:
                EdgeDriverManager.getInstance().version(driverVersion).setup();
                return new EdgeDriver();
            case OPERA:
                OperaDriverManager.getInstance().version(driverVersion).setup();
                return  new OperaDriver();
            case PHANTOMJS:
                PhantomJsDriverManager.getInstance().version(driverVersion).setup();
                return new PhantomJSDriver();

        }
    }

}