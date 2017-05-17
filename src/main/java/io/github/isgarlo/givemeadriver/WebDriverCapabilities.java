package io.github.isgarlo.givemeadriver;


import com.google.common.collect.Maps;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.*;

import static org.apache.logging.log4j.util.PropertiesUtil.extractSubset;

class WebDriverCapabilities {

    private static final String PREFIX = "capabilities.";
    private static final String BROWSER_CHROME = "chrome";
    private static final String AUTOCLOSE_TRUE = "true";

    private static final String CAPABILITY_BROWSER = "browser";
    private static final String CAPABILITY_BROWSER_NAME = "browserName";
    private static final String CAPABILITY_DEVICE_NAME = "deviceName";
    private static final String CAPABILITY_AUTOCLOSE = "autoclose";
    private static final String CAPABILITY_DRIVER_VERSION = "driverVersion";
    private static final String CAPABILITY_REMOTE = "remote";

    private final Map<String, String> capabilities = new HashMap<>();

    WebDriverCapabilities() {
        capabilities.put(CAPABILITY_BROWSER, BROWSER_CHROME);
        capabilities.put(CAPABILITY_AUTOCLOSE, AUTOCLOSE_TRUE);
    }

    void mapFromSystemProperties() {
        Properties browserSystemProperties = extractSubset(PropertiesUtil.getSystemProperties(), PREFIX);
        capabilities.putAll(Maps.fromProperties(browserSystemProperties));

        // The browser can be passed as "browser" or "browserName".
        // We make sure both capabilities have the same value.
        if(capabilities.containsKey(CAPABILITY_BROWSER_NAME))
            setBrowser(capabilities.get(CAPABILITY_BROWSER_NAME));
    }

    private void setBrowser(String browser) {
        capabilities.put(CAPABILITY_BROWSER, browser);
    }

    /**
     * Which browser to use.
     * Can be configured either programmatically or by system property "-Dcapabilities.browser=ie" or "-Dbrowser=ie".
     * Supported values: "chrome", "firefox", "ie", "htmlunit", "phantomjs", "opera", "marionette"
     * <p/>
     * Default value: "chrome"
     */
    String getBrowser() {
        return capabilities.get(CAPABILITY_BROWSER);
    }

    /**
     * URL of remote web driver in case of using Selenium Grid or any
     * service like BrowserStack or Saucelabs.
     * Can be configured either programmatically or by system property
     * "-Dcapabilities.remote=http://localhost:5678/wd/hub"
     * "-Dcapabilities.remote=https://USERNAME:USER_KEY@hub-cloud.browserstack.com/wd/hub"
     *
     * Default value: null. Local execution.
     */
    String getRemote() {
        return capabilities.get(CAPABILITY_REMOTE);
    }

    String getDeviceName() {
        return capabilities.get(CAPABILITY_DEVICE_NAME);
    }

    String getDriverVersion() {
        return capabilities.get(CAPABILITY_DRIVER_VERSION);
    }

    /**
     * The browser window size.
     * Can be configured either programmatically or by system property "-Dcapabilities.browserSize=1024x768".
     *
     * Default value: none (browser size will not be set explicitly)
     */
    /*public Dimension mapViewPortSize() {
        String[] dimension = getViewportSize().split("x");
        int width = Integer.parseInt(dimension[0]);
        int height = Integer.parseInt(dimension[1]);
        return new Dimension(width, height);

    }*/

    /*public void setToSystemProperties(String capability, String value) {
        if(!capability.startsWith(PREFIX)) {
            capability = PREFIX.concat(capability);
        }
        System.setProperty(capability, value);
    }*/

    boolean isRemote() {
        return capabilities.containsKey(CAPABILITY_REMOTE);
    }

    boolean isDeviceName() {
        return capabilities.containsKey(CAPABILITY_DEVICE_NAME);
    }

    boolean isAutoClose() {
        return capabilities.containsKey(CAPABILITY_AUTOCLOSE);
    }

    public boolean areDevicePropertiesSet() {
        List<String> deviceProperties = Arrays.asList("viewportSize", "pixelRatio", "userAgent");
        return capabilities.keySet().containsAll(deviceProperties);
    }

    DesiredCapabilities toDesiredCapabilities() {
        return new DesiredCapabilities(capabilities);
    }

    @Override
    public String toString() {
        return String.format("Mapped capabilities: [%s]", capabilities);
    }

}
