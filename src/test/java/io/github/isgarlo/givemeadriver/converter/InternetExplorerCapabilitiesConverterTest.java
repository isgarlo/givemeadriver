package io.github.isgarlo.givemeadriver.converter;


import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.junit.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ie.InternetExplorerOptions;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_AUTOCLOSE;
import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_BROWSER_SIZE;
import static org.assertj.core.api.Assertions.assertThat;

public class InternetExplorerCapabilitiesConverterTest {

    private InternetExplorerCapabilitiesConverter internetExplorerCapabilitiesConverter = new InternetExplorerCapabilitiesConverter();

    @Test
    public void settingBrowserSize() {
        // given
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty("browserSize", "1690x1000");

        // when
        Capabilities convertedCapabilities = internetExplorerCapabilitiesConverter.convert(properties);

        // then
        // expected safari options
        InternetExplorerOptions expectedInternetExplorerOptions = new InternetExplorerOptions();
        expectedInternetExplorerOptions.setCapability(CAPABILITY_AUTOCLOSE, false);
        expectedInternetExplorerOptions.setCapability(CAPABILITY_BROWSER_SIZE, "1690x1000");

        assertThat(convertedCapabilities.asMap()).isEqualTo(expectedInternetExplorerOptions.asMap());

    }
}
