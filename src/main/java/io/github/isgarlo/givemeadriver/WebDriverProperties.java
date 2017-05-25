package io.github.isgarlo.givemeadriver;


import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

class WebDriverProperties extends DesiredCapabilities {

    private static final String CAPABILITY_BROWSER = "browser";
    private static final String CAPABILITY_BROWSER_NAME = "browserName";
    private static final String CAPABILITY_DEVICE = "device";
    private static final String CAPABILITY_DEVICE_NAME = "deviceName";
    private static final String CAPABILITY_AUTOCLOSE = "autoclose";
    private static final String CAPABILITY_DRIVER_VERSION = "driverVersion";
    private static final String CAPABILITY_REMOTE = "remote";
    private static final String CAPABILITY_BROWSER_SIZE = "browserSize";
    private static final String CAPABILITY_VIEWPORT_SIZE = "viewportSize";
    private static final String CAPABILITY_USER_AGENT = "userAgent";
    private static final String CAPABILITY_PIXEL_RATIO = "pixelRatio";

    WebDriverProperties(Map<String, String> rawMap) {
        super(rawMap);
        validate();
    }

    String getBrowser() {
        return String.valueOf(Optional.ofNullable(getCapability(CAPABILITY_BROWSER))
                .orElse(Optional.ofNullable(getCapability(CAPABILITY_BROWSER_NAME))
                        .orElse(Optional.ofNullable(System.getProperty(CAPABILITY_BROWSER))
                                .orElse("chrome"))));
    }

    String getBrowserSize() {
        return String.valueOf(Optional.ofNullable(getCapability(CAPABILITY_BROWSER_SIZE))
                .orElse(StringUtils.EMPTY));
    }

    String getViewportSize() {
        return String.valueOf(Optional.ofNullable(getCapability(CAPABILITY_VIEWPORT_SIZE))
                .orElse(StringUtils.EMPTY));
    }

    boolean isAutoClose() {
        return BooleanUtils.toBooleanDefaultIfNull((Boolean) getCapability(CAPABILITY_AUTOCLOSE), true);
    }

    String getRemote() {
        return String.valueOf(Optional.ofNullable(getCapability(CAPABILITY_REMOTE))
                .orElse(StringUtils.EMPTY));
    }

    String getDeviceName() {
        return String.valueOf(Optional.ofNullable(getCapability(CAPABILITY_DEVICE_NAME))
                .orElse(Optional.ofNullable(System.getProperty(CAPABILITY_DEVICE))
                        .orElse(StringUtils.EMPTY)));
    }

    String getDriverVersion() {
        return String.valueOf(Optional.ofNullable(getCapability(CAPABILITY_DRIVER_VERSION))
                .orElse(StringUtils.EMPTY));
    }

    String getUserAgent() {
        return String.valueOf(Optional.ofNullable(getCapability(CAPABILITY_USER_AGENT))
                .orElse(StringUtils.EMPTY));
    }

    double getPixelRatio() {
        return NumberUtils.toDouble((String) getCapability(CAPABILITY_PIXEL_RATIO));
    }

    private void validate() {
        // validate CAPABILITY_BROWSER when testing in local
        if(getRemote().isEmpty()) {
            LocalBrowserTypes browserType = EnumUtils.getEnum(LocalBrowserTypes.class, getBrowser().toUpperCase());
            checkArgument(browserType != null,
                    String.format("Invalid [capabilities.browser, capabilities.browserName, browser] " +
                                    "= [%s] not in %s", getBrowser(), EnumUtils.getEnumList(LocalBrowserTypes.class)));
        }
        // validate CAPABILITY_BROWSER_SIZE
        if(!getBrowserSize().isEmpty()) {
            checkArgument(getBrowserSize().matches("\\d+[x]\\d+"),
                    String.format("Invalid [capabilities.browserSize] = [%s] " +
                                    "not in the format 123x456",
                            getBrowserSize()));
        }
        // validate CAPABILITY_VIEWPORT_SIZE
        if(!getViewportSize().isEmpty()) {
            checkArgument(getViewportSize().matches("\\d+[x]\\d+"),
                    String.format("Invalid [capabilities.viewportSize] = [%s] " +
                                    "not in the format 123x456",
                            getViewportSize()));
        }
    }

}
