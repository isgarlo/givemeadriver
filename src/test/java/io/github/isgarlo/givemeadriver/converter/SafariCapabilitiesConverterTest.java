package io.github.isgarlo.givemeadriver.converter;


import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.junit.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.safari.SafariOptions;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_AUTOCLOSE;
import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_BROWSER_SIZE;
import static org.assertj.core.api.Assertions.assertThat;

public class SafariCapabilitiesConverterTest {

    private SafariCapabilitiesConverter safariCapabilitiesConverter = new SafariCapabilitiesConverter();

    @Test
    public void settingBrowserSize() {
        // given
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty("browserSize", "1690x1000");

        // when
        Capabilities convertedCapabilities = safariCapabilitiesConverter.convert(properties);

        // then
        // expected safari options
        SafariOptions expectedSafariOptions = new SafariOptions();
        expectedSafariOptions.setCapability(CAPABILITY_AUTOCLOSE, false);
        expectedSafariOptions.setCapability(CAPABILITY_BROWSER_SIZE, "1690x1000");

        assertThat(convertedCapabilities.asMap()).isEqualTo(expectedSafariOptions.asMap());

    }
}
