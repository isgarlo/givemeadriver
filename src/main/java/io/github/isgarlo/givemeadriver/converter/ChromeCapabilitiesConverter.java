package io.github.isgarlo.givemeadriver.converter;

import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.*;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;


class ChromeCapabilitiesConverter extends LocalCapabilitiesConverter {

    private static final Logger log = LoggerFactory.getLogger(ChromeCapabilitiesConverter.class);

    @Override
    protected void addDriverSpecificCapabilities(WebDriverProperties properties) {
        Map<String, Object> deviceMetrics = new HashMap<>();
        Map<String, Object> mobileEmulation = new HashMap<>();
        ChromeOptions chromeOptions = new ChromeOptions();

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
            chromeOptions.addArguments("headless", "disable-gpu");

        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        try {
            log.info("ChromeOptions " + chromeOptions.toJson().toString());
        } catch (IOException e) {
            log.warn("Unable to parse ChromeOptions to json", e);
        }
    }
}
