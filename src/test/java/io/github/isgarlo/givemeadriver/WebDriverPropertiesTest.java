package io.github.isgarlo.givemeadriver;

import io.github.isgarlo.givemeadriver.factories.DriverType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.*;
import static org.assertj.core.api.Java6Assertions.assertThat;


public class WebDriverPropertiesTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void defaultPropertiesTest() {
        WebDriverProperties props = new WebDriverProperties(new HashMap<String, String>());

        assertThat(props.getBrowser()).isEqualTo("chrome");
        assertThat(props.isAutoClose()).isFalse();
        assertThat(props.getRemote()).isEmpty();
        assertThat(props.getDeviceName()).isEmpty();
        assertThat(props.getUserAgent()).isEmpty();
        assertThat(props.getDriverVersion()).isEmpty();
        assertThat(props.getPixelRatio()).isEqualTo(0.0);
        assertThat(props.getBrowserSize()).isEmpty();
        assertThat(props.getViewportSize()).isEmpty();
        assertThat(props.getDriverType()).isEqualTo(DriverType.CHROME);
        assertThat(props.isHeadless()).isFalse();
    }

    @Test
    public void throwsExceptionIfBrowserValueIsInvalid() {
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty("browser", "unexisting-browser");

        this.exception.expect(IllegalArgumentException.class);
        this.exception.expectMessage("Invalid [capabilities.browser, capabilities.browserName, browser] " +
                    "= [unexisting-browser]");
        properties.validate();
    }

    @Test
    public void throwsExceptionIfBrowserSizeValueIsInvalid() {
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty("browserSize", "123");

        this.exception.expect(IllegalArgumentException.class);
        this.exception.expectMessage(
                "Invalid [capabilities.browserSize] = [123] not in the format 123x456"
        );
        properties.validate();
    }

    @Test
    public void throwsExceptionIfViewportSizeValueIsInvalid() {
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty("viewportSize", "123");

        this.exception.expect(IllegalArgumentException.class);
        this.exception.expectMessage(
            "Invalid [capabilities.viewportSize] = [123] not in the format 123x456"
        );
        properties.validate();
    }

    @Test
    public void throwsExceptionIfDeviceNameAndUserAgentAreBothSet() {
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty("device", "Apple iPhone 6");
        properties.setProperty("userAgent", "Mozilla/5.0 (iPhone; CPU...");

        this.exception.expect(IllegalArgumentException.class);
        this.exception.expectMessage(
                "Invalid capabilities setup: " +
                        "[capabilities.deviceName] and [capabilities.userAgent] cannot coexist."
        );
        properties.validate();
    }

    @Test
    public void throwsExceptionIfRemoteValueIsInvalid() {
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty("remote", "malformed.url");

        this.exception.expect(IllegalArgumentException.class);
        this.exception.expectMessage("Invalid 'capabilities.remote' parameter: ");
        properties.validate();
    }

    @Test
    public void returnPropertiesWhenAllAreValid() {
        WebDriverProperties properties = new WebDriverProperties();
        assertThat(properties.validate()).isEqualTo(properties);

    }

    @Test
    public void getBrowserWhenSetByManyCapabilities() {
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty("browser", "browser_from_browser");
        properties.setProperty("browserName", "browser_from_browserName");

        assertThat(properties.getBrowser()).isEqualTo(properties.getProperty(CAPABILITY_BROWSER));
    }

    @Test
    public void setAutocloseWithInvalidDataReturnsFalse() {
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty("autoclose", "this_is_not_a_boolean");
        assertThat(properties.isAutoClose()).isFalse();
    }

    @Test
    public void setPixelRatioWithInvalidDataReturnsDefaultDouble() {
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty("pixelRatio", "this_is_not_a_double");
        assertThat(properties.getPixelRatio()).isEqualTo(0.0);
    }

    @Test
    public void driverTypeIsRemoteWhenRemoteIsSet() {
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty("remote", "http://my_remote_grid");
        assertThat(properties.getDriverType()).isEqualTo(DriverType.REMOTE);
    }

    @Test
    public void driverTypeIsLocalWhenRemoteIsNotSet() {
        WebDriverProperties properties = new WebDriverProperties();
        assertThat(properties.getDriverType()).isEqualTo(DriverType.CHROME);
    }

    @Test
    public void asMapReturns() {
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty("userAgent", "Mozilla/5.0 (iPhone; CPU...");
        properties.setProperty("pixelRatio", "3.0");
        properties.setProperty("autoclose", "true");

        HashMap<String, String> expectedMap = new HashMap<>();
        expectedMap.put(CAPABILITY_USER_AGENT, "Mozilla/5.0 (iPhone; CPU...");
        expectedMap.put(CAPABILITY_PIXEL_RATIO, "3.0");
        expectedMap.put(CAPABILITY_AUTOCLOSE, "true");

        assertThat(properties.asMap()).isEqualTo(expectedMap);

    }

    @Test
    public void setHeadlessWithInvalidDataReturnsFalse() {
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty("headless", "true");

        assertThat(properties.isHeadless()).isTrue();
    }
}
