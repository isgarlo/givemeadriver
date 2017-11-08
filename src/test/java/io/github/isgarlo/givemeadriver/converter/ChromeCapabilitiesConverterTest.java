package io.github.isgarlo.givemeadriver.converter;


import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.junit.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.*;
import static org.assertj.core.api.Assertions.assertThat;


public class ChromeCapabilitiesConverterTest {

    private ChromeCapabilitiesConverter chromeCapabilitiesConverter = new ChromeCapabilitiesConverter();
    private static final String IPHONE_DEVICE = "Apple iPhone 6";
    private static final String ANY_USER_AGENT = "Mozilla/5.0 (iPhone; CPU...";

    @Test
    public void settingAllChromeProperties() throws IOException {
        // given
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty("device", IPHONE_DEVICE);
        properties.setProperty("userAgent", ANY_USER_AGENT);
        properties.setProperty("viewportSize", "378x664");
        properties.setProperty("pixelRatio", "3.0");
        properties.setProperty("headless", "true");

        // when
        Capabilities convertedCapabilities = chromeCapabilitiesConverter.convert(properties);

        // then
        // expected chrome options
        ChromeOptions expectedChromeOptions = new ChromeOptions();
        expectedChromeOptions.setCapability(CAPABILITY_BROWSER_NAME, "chrome");
        expectedChromeOptions.setCapability(CAPABILITY_AUTOCLOSE, false);
        Map<String, Object> expectedMobileEmulation = new HashMap<>();
        Map<String, Object> expectedDeviceMetrics = new HashMap<>();
        expectedDeviceMetrics.put("width", 378);
        expectedDeviceMetrics.put("height", 664);
        expectedDeviceMetrics.put(CAPABILITY_PIXEL_RATIO, 3.0);
        expectedMobileEmulation.put("deviceMetrics", expectedDeviceMetrics);
        expectedMobileEmulation.put(CAPABILITY_DEVICE_NAME, IPHONE_DEVICE);
        expectedMobileEmulation.put(CAPABILITY_USER_AGENT, ANY_USER_AGENT);
        expectedChromeOptions.setExperimentalOption("mobileEmulation", expectedMobileEmulation);
        expectedChromeOptions.addArguments("--disable-device-discovery-notifications");
        expectedChromeOptions.addArguments("--disable-infobars");
        expectedChromeOptions.addArguments("--headless", "--disable-gpu");

        assertThat(convertedCapabilities.asMap()).isEqualTo(expectedChromeOptions.asMap());
    }

    @Test
    public void notSettingAnyProperty() {
        // given
        WebDriverProperties properties = new WebDriverProperties();

        // when
        Capabilities convertedCapabilities = chromeCapabilitiesConverter.convert(properties);

        // then
        // expected chrome capabilities
        ChromeOptions expectedChromeOptions = new ChromeOptions();
        expectedChromeOptions.setCapability(CAPABILITY_BROWSER_NAME, "chrome");
        expectedChromeOptions.setCapability(CAPABILITY_AUTOCLOSE, false);

        expectedChromeOptions.addArguments("--disable-device-discovery-notifications");
        expectedChromeOptions.addArguments("--disable-infobars");

        assertThat(convertedCapabilities.asMap()).isEqualTo(expectedChromeOptions.asMap());
        assertThat(convertedCapabilities).isEqualTo(expectedChromeOptions);
    }

    @Test
    public void notSettingDeviceMetrics() throws IOException {
        // given
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty("device", IPHONE_DEVICE);
        properties.setProperty("userAgent", ANY_USER_AGENT);

        // when
        Capabilities convertedCapabilities = chromeCapabilitiesConverter.convert(properties);

        // then
        // expected chrome options
        ChromeOptions expectedChromeOptions = new ChromeOptions();
        Map<String, Object> expectedMobileEmulation = new HashMap<>();
        expectedChromeOptions.setCapability(CAPABILITY_BROWSER_NAME, "chrome");
        expectedChromeOptions.setCapability(CAPABILITY_AUTOCLOSE, false);
        expectedMobileEmulation.put(CAPABILITY_DEVICE_NAME, IPHONE_DEVICE);
        expectedMobileEmulation.put(CAPABILITY_USER_AGENT, ANY_USER_AGENT);
        expectedChromeOptions.setExperimentalOption("mobileEmulation", expectedMobileEmulation);
        expectedChromeOptions.addArguments("--disable-device-discovery-notifications");
        expectedChromeOptions.addArguments("--disable-infobars");

        assertThat(convertedCapabilities.asMap()).isEqualTo(expectedChromeOptions.asMap());
    }
}
