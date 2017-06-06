package io.github.isgarlo.givemeadriver.converter;

import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.*;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;


public class ChromeCapabilitiesConverter extends LocalCapabilitiesConverter {

    @Override
    protected void addDriverSpecificCapabilities(WebDriverProperties properties) {
        Map<String, Object> deviceMetrics = new HashMap<>();
        Map<String, Object> mobileEmulation = new HashMap<>();

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
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        }
    }
}