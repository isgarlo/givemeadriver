package io.github.isgarlo.givemeadriver.converter;


import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.remote.CapabilityType.ACCEPT_SSL_CERTS;


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
        DesiredCapabilities convertedCapabilities = chromeCapabilitiesConverter.convert(properties);

        // then
        // expected chrome options
        ChromeOptions expectedChromeOptions = new ChromeOptions();
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
        expectedChromeOptions.addArguments("headless", "disable-gpu");

        ChromeOptions actualChromeOptions;
        actualChromeOptions = (ChromeOptions) convertedCapabilities.getCapability(ChromeOptions.CAPABILITY);
        assertThat(actualChromeOptions.toJson()).isEqualTo(expectedChromeOptions.toJson());
    }

    @Test
    public void notSettingAnyProperty() {
        // given
        WebDriverProperties properties = new WebDriverProperties();

        // when
        DesiredCapabilities convertedCapabilities = chromeCapabilitiesConverter.convert(properties);

        // then
        // expected chrome capabilities
        DesiredCapabilities expectedCapabilities = new DesiredCapabilities();
        expectedCapabilities.setCapability(CAPABILITY_BROWSER_NAME, "chrome");
        expectedCapabilities.setCapability(ACCEPT_SSL_CERTS, true);
        expectedCapabilities.setCapability(CAPABILITY_AUTOCLOSE, true);

        ChromeOptions expectedChromeOptions = new ChromeOptions();
        expectedChromeOptions.addArguments("--disable-device-discovery-notifications");
        expectedChromeOptions.addArguments("--disable-infobars");
        expectedCapabilities.setCapability(ChromeOptions.CAPABILITY, expectedChromeOptions);

        assertThat(convertedCapabilities.getCapability(ChromeOptions.CAPABILITY)).isEqualTo(expectedChromeOptions);
        assertThat(convertedCapabilities).isEqualTo(expectedCapabilities);
    }

    @Test
    public void notSettingDeviceMetrics() throws IOException {
        // given
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty("device", IPHONE_DEVICE);
        properties.setProperty("userAgent", ANY_USER_AGENT);

        // when
        DesiredCapabilities convertedCapabilities = chromeCapabilitiesConverter.convert(properties);

        // then
        // expected chrome options
        ChromeOptions expectedChromeOptions = new ChromeOptions();
        Map<String, Object> expectedMobileEmulation = new HashMap<>();
        expectedMobileEmulation.put(CAPABILITY_DEVICE_NAME, IPHONE_DEVICE);
        expectedMobileEmulation.put(CAPABILITY_USER_AGENT, ANY_USER_AGENT);
        expectedChromeOptions.setExperimentalOption("mobileEmulation", expectedMobileEmulation);

        expectedChromeOptions.addArguments("--disable-device-discovery-notifications");
        expectedChromeOptions.addArguments("--disable-infobars");

        ChromeOptions actualChromeOptions;
        actualChromeOptions = (ChromeOptions) convertedCapabilities.getCapability(ChromeOptions.CAPABILITY);
        assertThat(actualChromeOptions.toJson()).isEqualTo(expectedChromeOptions.toJson());
    }
}
