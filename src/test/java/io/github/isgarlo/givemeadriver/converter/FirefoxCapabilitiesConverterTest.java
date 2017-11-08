package io.github.isgarlo.givemeadriver.converter;


import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.junit.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.firefox.FirefoxOptions;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_AUTOCLOSE;
import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_BROWSER_SIZE;
import static org.assertj.core.api.Assertions.assertThat;

public class FirefoxCapabilitiesConverterTest {

    private FirefoxCapabilitiesConverter firefoxCapabilitiesConverter = new FirefoxCapabilitiesConverter();

    @Test
    public void settingBrowserSize() {
        // given
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty("browserSize", "1690x1000");

        // when
        Capabilities convertedCapabilities = firefoxCapabilitiesConverter.convert(properties);

        // then
        // expected safari options
        FirefoxOptions expectedFirefoxOptions = new FirefoxOptions();
        expectedFirefoxOptions.setCapability(CAPABILITY_AUTOCLOSE, false);
        expectedFirefoxOptions.setCapability(CAPABILITY_BROWSER_SIZE, "1690x1000");

        assertThat(convertedCapabilities.asMap()).isEqualTo(expectedFirefoxOptions.asMap());

    }
}
