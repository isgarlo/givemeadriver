package io.github.isgarlo.givemeadriver.converter;

import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.*;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;


class ChromeCapabilitiesConverter implements CapabilitiesConverter {

    @Override
    public Capabilities convert(WebDriverProperties properties) {
        Map<String, Object> deviceMetrics = new HashMap<>();
        Map<String, Object> mobileEmulation = new HashMap<>();
        ChromeOptions chromeOptions = new ChromeOptions();

        // general options for logging purpose
        chromeOptions.setCapability(CAPABILITY_AUTOCLOSE, properties.isAutoClose());
        addToChromeOptionsIfNoEmptyValue(chromeOptions, CAPABILITY_DRIVER_VERSION, properties.getDriverVersion());
        addToChromeOptionsIfNoEmptyValue(chromeOptions, CAPABILITY_BROWSER_SIZE, properties.getBrowserSize());

        //https://cs.chromium.org/chromium/src/chrome/test/chromedriver/chrome/mobile_device_list.cc
        addToMapIfNoEmptyValue(mobileEmulation, CAPABILITY_DEVICE_NAME, properties.getDeviceName());
        addToMapIfNoEmptyValue(mobileEmulation, CAPABILITY_USER_AGENT, properties.getUserAgent());

        // calculate the deviceMetrics
        if (isNotEmpty(properties.getViewportSize())) {
            String[] dimension = properties.getViewportSize().split("x");
            int width = Integer.parseInt(dimension[0]);
            int height = Integer.parseInt(dimension[1]);
            deviceMetrics.put("width", width);
            deviceMetrics.put("height", height);
        }
        if (properties.getPixelRatio() != 0.0) {
            deviceMetrics.put(CAPABILITY_PIXEL_RATIO, properties.getPixelRatio());
        }

        // add deviceMetrics and mobileEmulation
        if(!deviceMetrics.isEmpty())
            mobileEmulation.put("deviceMetrics", deviceMetrics);

        if(!mobileEmulation.isEmpty()) {
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        }

        // setting additional arguments
        // https://sites.google.com/a/chromium.org/chromedriver/capabilities
        chromeOptions.addArguments("--disable-device-discovery-notifications");
        chromeOptions.addArguments("--disable-infobars");
        if(properties.isHeadless())
            chromeOptions.setHeadless(true);

        return chromeOptions;
    }

    private void addToMapIfNoEmptyValue(Map<String, Object> sourceMap, String key, String value) {
        if (isNotEmpty(value)) {
            sourceMap.put(key, value);
        }
    }

    private void addToChromeOptionsIfNoEmptyValue(ChromeOptions chromeOptions, String key, String value) {
        if (isNotEmpty(value)) {
            chromeOptions.setCapability(key, value);
        }
    }
}
