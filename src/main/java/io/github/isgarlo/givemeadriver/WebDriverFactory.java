package io.github.isgarlo.givemeadriver;

import io.github.bonigarcia.wdm.*;
import org.apache.commons.lang3.EnumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkState;

class WebDriverFactory {

    private static final Logger log = LoggerFactory.getLogger(WebDriverFactory.class);

    WebDriver createWebDriver(WebDriverCapabilities mappedCaps) {
        return mappedCaps.isRemote() ? createRemoteDriver(mappedCaps.getRemote(), mappedCaps.toDesiredCapabilities()) :
                mappedCaps.isDeviceName() ? createChromeEmulationDriver(mappedCaps.getDeviceName()) :
                        //mappedCaps.areDevicePropertiesSet() ? createChromeEmulationDriver() :
                                createDesktopLocalDriver(mappedCaps.getBrowser(), mappedCaps.getDriverVersion());
    }

    private WebDriver createRemoteDriver(final String remote, final DesiredCapabilities capabilities) {
        try {
            return new RemoteWebDriver(new URL(remote), capabilities);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid 'capabilities.remote' parameter: " + remote, e);
        }
    }

    private WebDriver createDesktopLocalDriver(final String browser, final String driverVersion) {
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

    private WebDriver createChromeEmulationDriver(String deviceName) {
        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceName", deviceName);
        return createChromeEmulationDriver(mobileEmulation);
    }

    private WebDriver createChromeEmulationDriver () {

        /*Dimension viewportSize = mapViewPortSize();
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", viewportSize.getWidth());
        deviceMetrics.put("height", viewportSize.getHeight());
        deviceMetrics.put("pixelRatio", getDevicePixelRatio());

        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", getDeviceUserAgent());

        return createChromeEmulationDriver(mobileEmulation);*/
        return null;
    }

    private WebDriver createChromeEmulationDriver(Map<String, Object> mobileEmulation) {
        ChromeDriverManager.getInstance().setup();

        Map<String, Object> chromeOptions = new HashMap<String, Object>();
        chromeOptions.put("mobileEmulation", mobileEmulation);

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return new ChromeDriver(capabilities);
    }

    /*private WebDriver adjustBrowserSize(final WebDriver driver) {
        if (getViewportSize() != null) {
            log.info("Set browser size to " + getViewportSize());
            Dimension browserSize = mapViewPortSize();
            driver.manage().window().setSize(new Dimension(browserSize.getWidth(), browserSize.getHeight()));
        } else if (true) {//startMaximized
            try {
                if (false) {//isChrome()
                    //maximizeChromeBrowser(driver.manage().window());
                } else {
                    driver.manage().window().maximize();
                }
            } catch (Exception cannotMaximize) {
                log.warn("Cannot maximize " + driver.getClass().getSimpleName() + ": " + cannotMaximize);
            }
        }
        return driver;
    }*/

    public static void main(String[] args) {

    }

}