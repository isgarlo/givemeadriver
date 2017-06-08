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


public class ChromeCapabilitiesConverterTest {

    private ChromeCapabilitiesConverter chromeCapabilitiesConverter = new ChromeCapabilitiesConverter();
    private static final String IPHONE_DEVICE = "Apple iPhone 6";
    private static final String ANY_USER_AGENT = "Mozilla/5.0 (iPhone; CPU...";

    @Test
    public void settingAllChromeProperties() throws IOException {
        // given
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty(CAPABILITY_DEVICE_NAME, IPHONE_DEVICE);
        properties.setProperty(CAPABILITY_USER_AGENT, ANY_USER_AGENT);
        properties.setProperty(CAPABILITY_VIEWPORT_SIZE, "378x664");
        properties.setProperty(CAPABILITY_PIXEL_RATIO, "3.0");

        // when
        DesiredCapabilities convertedCapabilities = chromeCapabilitiesConverter.convert(properties);

        // then
        // expected chrome options
        ChromeOptions expectedChromeOptions = new ChromeOptions();
        Map<String, Object> expectedMobileEmulation = new HashMap<>();
        Map<String, Object> expectedDeviceMetrics = new HashMap<>();
        Map<String, Object> prefs = new HashMap<>();
        expectedDeviceMetrics.put("width", 378);
        expectedDeviceMetrics.put("height", 664);
        expectedDeviceMetrics.put(CAPABILITY_PIXEL_RATIO, 3.0);
        expectedMobileEmulation.put("deviceMetrics", expectedDeviceMetrics);
        expectedMobileEmulation.put(CAPABILITY_DEVICE_NAME, IPHONE_DEVICE);
        expectedMobileEmulation.put(CAPABILITY_USER_AGENT, ANY_USER_AGENT);
        expectedChromeOptions.setExperimentalOption("mobileEmulation", expectedMobileEmulation);

        prefs.put("profile.password_manager_enabled", "false");
        prefs.put("credentials_enable_service", "false");
        expectedChromeOptions.setExperimentalOption("prefs", prefs);

        expectedChromeOptions.addArguments("disable-device-discovery-notifications");

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
        expectedCapabilities.setCapability(CAPABILITY_ACCEPT_SSL_CERTS, true);
        expectedCapabilities.setCapability(CAPABILITY_AUTOCLOSE, true);

        ChromeOptions expectedChromeOptions = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();

        prefs.put("profile.password_manager_enabled", "false");
        prefs.put("credentials_enable_service", "false");
        expectedChromeOptions.setExperimentalOption("prefs", prefs);
        expectedChromeOptions.addArguments("disable-device-discovery-notifications");
        expectedCapabilities.setCapability(ChromeOptions.CAPABILITY, expectedChromeOptions);

        assertThat(convertedCapabilities.getCapability(ChromeOptions.CAPABILITY)).isEqualTo(expectedChromeOptions);
        assertThat(convertedCapabilities).isEqualTo(expectedCapabilities);
    }

    @Test
    public void notSettingDeviceMetrics() throws IOException {
        // given
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty(CAPABILITY_DEVICE_NAME, IPHONE_DEVICE);
        properties.setProperty(CAPABILITY_USER_AGENT, ANY_USER_AGENT);

        // when
        DesiredCapabilities convertedCapabilities = chromeCapabilitiesConverter.convert(properties);

        // then
        // expected chrome options
        ChromeOptions expectedChromeOptions = new ChromeOptions();
        Map<String, Object> expectedMobileEmulation = new HashMap<>();
        Map<String, Object> prefs = new HashMap<>();
        expectedMobileEmulation.put(CAPABILITY_DEVICE_NAME, IPHONE_DEVICE);
        expectedMobileEmulation.put(CAPABILITY_USER_AGENT, ANY_USER_AGENT);
        expectedChromeOptions.setExperimentalOption("mobileEmulation", expectedMobileEmulation);
        prefs.put("profile.password_manager_enabled", "false");
        prefs.put("credentials_enable_service", "false");
        expectedChromeOptions.setExperimentalOption("prefs", prefs);
        expectedChromeOptions.addArguments("disable-device-discovery-notifications");

        ChromeOptions actualChromeOptions;
        actualChromeOptions = (ChromeOptions) convertedCapabilities.getCapability(ChromeOptions.CAPABILITY);
        assertThat(actualChromeOptions.toJson()).isEqualTo(expectedChromeOptions.toJson());
    }
}
