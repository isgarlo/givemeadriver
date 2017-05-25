package io.github.isgarlo.givemeadriver;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class WebDriverPropertiesTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void defaultPropertiesTest() {
        WebDriverProperties props = new WebDriverProperties(new HashMap<String, String>());

        assertThat(props.getBrowser()).isEqualTo("chrome");
        assertThat(props.isAutoClose()).isTrue();
        assertThat(props.getRemote()).isEmpty();
        assertThat(props.getDeviceName()).isEmpty();
        assertThat(props.getUserAgent()).isEmpty();
        assertThat(props.getDriverVersion()).isEmpty();
        assertThat(props.getPixelRatio()).isEqualTo(0.0);
        assertThat(props.getBrowserSize()).isEmpty();
        assertThat(props.getViewportSize()).isEmpty();
    }

    @Test
    public void throwsExceptionIfBrowserValueIsInvalid() {
        HashMap<String, String> resultFromMapping = new HashMap<String, String>();
        resultFromMapping.put("browser", "unexisting-browser");

        this.exception.expect(IllegalArgumentException.class);
        this.exception.expectMessage("Invalid [capabilities.browser, capabilities.browserName, browser] " +
                    "= [unexisting-browser] not in [chrome, firefox, ie, safari, opera, edge, phantomjs]");
        new WebDriverProperties(resultFromMapping);
    }

    @Test
    public void throwsExceptionIfBrowserSizeValueIsInvalid() {
        HashMap<String, String> resultFromMapping = new HashMap<String, String>();
        resultFromMapping.put("browserSize", "123");

        this.exception.expect(IllegalArgumentException.class);
        this.exception.expectMessage(
                "Invalid [capabilities.browserSize] = [123] not in the format 123x456"
        );
        new WebDriverProperties(resultFromMapping);
    }

    @Test
    public void throwsExceptionIfViewportSizeValueIsInvalid() {
        HashMap<String, String> resultFromMapping = new HashMap<String, String>();
        resultFromMapping.put("viewportSize", "123");

        this.exception.expect(IllegalArgumentException.class);
        this.exception.expectMessage(
            "Invalid [capabilities.viewportSize] = [123] not in the format 123x456"
        );
        new WebDriverProperties(resultFromMapping);
    }
}
