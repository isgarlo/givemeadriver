package io.github.isgarlo.givemeadriver;

import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
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

import static com.google.common.base.Preconditions.checkArgument;

class WebDriverFactory {

    private static final Logger log = LoggerFactory.getLogger(WebDriverFactory.class);

    WebDriver createWebDriver(WebDriverProperties props) {

        WebDriver webDriver =  !props.getRemote().isEmpty() ? createRemoteDriver(props.getRemote(), props) :
                !props.getDeviceName().isEmpty() ? createChromeEmulationDriver(props.getDeviceName()) :
                        !props.getUserAgent().isEmpty() ? createChromeEmulationDriver(props.getUserAgent(),
                                props.getViewportSize(), props.getPixelRatio()) :
                                createDesktopLocalDriver(props.getBrowser(), props.getDriverVersion());

        if(!props.getBrowserSize().isEmpty())
            adjustBrowserSize(webDriver, props.getBrowserSize());
        return webDriver;
    }

    private WebDriver createRemoteDriver(final String remote, final DesiredCapabilities capabilities) {
        try {
            return new RemoteWebDriver(new URL(remote), capabilities);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid 'capabilities.remote' parameter: " + remote, e);
        }
    }

    private WebDriver createDesktopLocalDriver(final String browser, final String driverVersion) {
        switch (LocalBrowserTypes.valueOf(browser.toUpperCase())) {
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

    private WebDriver createChromeEmulationDriver(final String deviceName) {
        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceName", deviceName);
        return createChromeEmulationDriver(mobileEmulation);
    }

    private WebDriver createChromeEmulationDriver(final String userAgent, final String viewportSize, final double pixelRatio) {
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();

        if(!viewportSize.isEmpty()) {
            Dimension dimension = parseStringSize(viewportSize);
            deviceMetrics.put("width", dimension.getWidth());
            deviceMetrics.put("height", dimension.getHeight());
        }

        if(pixelRatio != 0.0) {
            deviceMetrics.put("pixelRatio", pixelRatio);
        }

        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", userAgent);

        return createChromeEmulationDriver(mobileEmulation);
    }

    private WebDriver createChromeEmulationDriver(Map<String, Object> mobileEmulation) {
        ChromeDriverManager.getInstance().setup();

        Map<String, Object> chromeOptions = new HashMap<String, Object>();
        chromeOptions.put("mobileEmulation", mobileEmulation);

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return new ChromeDriver(capabilities);
    }

    private void adjustBrowserSize(final WebDriver driver, final String browserSize) {
        try {
            driver.manage().window().setPosition(new Point(0, 0));
            driver.manage().window().setSize(parseStringSize(browserSize));
            log.info("Set browser size to " + browserSize);
        } catch (Exception e) {
            log.warn("Cannot resize " + driver.getClass().getSimpleName() + ": " + e);
        }
    }

    private Dimension parseStringSize(String size) {
        checkArgument(size.matches("\\d+[x]\\d+"));
        String[] dimension = size.split("x");
        int width = Integer.parseInt(dimension[0]);
        int height = Integer.parseInt(dimension[1]);
        return new Dimension(width, height);
    }

}