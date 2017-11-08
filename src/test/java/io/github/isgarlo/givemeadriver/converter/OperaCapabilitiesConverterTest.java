package io.github.isgarlo.givemeadriver.converter;


import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.junit.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.opera.OperaOptions;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.*;
import static org.assertj.core.api.Assertions.assertThat;

public class OperaCapabilitiesConverterTest {

    private OperaCapabilitiesConverter operaCapabilitiesConverter = new OperaCapabilitiesConverter();

    @Test
    public void settingBrowserSize() {
        // given
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty("browserSize", "1690x1000");

        // when
        Capabilities convertedCapabilities = operaCapabilitiesConverter.convert(properties);

        // then
        // expected safari options
        OperaOptions expectedOperaOptions = new OperaOptions();
        expectedOperaOptions.setCapability(CAPABILITY_AUTOCLOSE, false);
        expectedOperaOptions.setCapability(CAPABILITY_BROWSER_SIZE, "1690x1000");

        assertThat(convertedCapabilities.asMap()).isEqualTo(expectedOperaOptions.asMap());

    }
}
