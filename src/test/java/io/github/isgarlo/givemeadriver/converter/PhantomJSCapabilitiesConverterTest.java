package io.github.isgarlo.givemeadriver.converter;


import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.assertj.core.api.Assertions.assertThat;


public class PhantomJSCapabilitiesConverterTest {

    private PhantomJSCapabilitiesConverter phantomJSCapabilitiesConverter = new PhantomJSCapabilitiesConverter();

    @Test
    public void notSettingAnyProperty() {
        // given
        WebDriverProperties properties = new WebDriverProperties();

        // when
        DesiredCapabilities convertedCapabilities = phantomJSCapabilitiesConverter.convert(properties);

        // then
        String[] expectedPhantomJSCliArgs = new String[]{"--web-security=no", "--ignore-ssl-errors=yes"};
        assertThat(convertedCapabilities.getCapability("phantomjs.cli.args")).isEqualTo(expectedPhantomJSCliArgs);
    }
}
