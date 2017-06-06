package io.github.isgarlo.givemeadriver.converter;


import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.*;
import static org.assertj.core.api.Assertions.assertThat;


public class GenericCapabilitiesConverterTest {

    private GenericCapabilitiesConverter genericCapabilitiesConverter = new GenericCapabilitiesConverter();

    @Test
    public void notSettingAnyProperty() {
        // given
        WebDriverProperties properties = new WebDriverProperties();

        // when
        DesiredCapabilities convertedCapabilities = genericCapabilitiesConverter.convert(properties);

        // then
        // expected chrome capabilities
        DesiredCapabilities expectedCapabilities = new DesiredCapabilities();
        expectedCapabilities.setCapability(CAPABILITY_ACCEPT_SSL_CERTS, true);
        expectedCapabilities.setCapability(CAPABILITY_BROWSER_NAME, "chrome");
        expectedCapabilities.setCapability(CAPABILITY_AUTOCLOSE, true);

        assertThat(convertedCapabilities).isEqualTo(expectedCapabilities);
    }

    @Test
    public void settingSslCertsToFalse() {
        // given
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty(CAPABILITY_ACCEPT_SSL_CERTS, "false");

        // when
        DesiredCapabilities convertedCapabilities = genericCapabilitiesConverter.convert(properties);

        // then
        // expected chrome capabilities
        DesiredCapabilities expectedCapabilities = new DesiredCapabilities();
        expectedCapabilities.setCapability(CAPABILITY_ACCEPT_SSL_CERTS, false);
        expectedCapabilities.setCapability(CAPABILITY_BROWSER_NAME, "chrome");
        expectedCapabilities.setCapability(CAPABILITY_AUTOCLOSE, true);

        assertThat(convertedCapabilities).isEqualTo(expectedCapabilities);
    }

    @Test
    public void settingDriverVersion() {
        // given
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty(CAPABILITY_DRIVER_VERSION, "2.25");

        // when
        DesiredCapabilities convertedCapabilities = genericCapabilitiesConverter.convert(properties);

        // then
        // expected chrome capabilities
        DesiredCapabilities expectedCapabilities = new DesiredCapabilities();
        expectedCapabilities.setCapability(CAPABILITY_ACCEPT_SSL_CERTS, true);
        expectedCapabilities.setCapability(CAPABILITY_BROWSER_NAME, "chrome");
        expectedCapabilities.setCapability(CAPABILITY_DRIVER_VERSION, "2.25");
        expectedCapabilities.setCapability(CAPABILITY_AUTOCLOSE, true);

        assertThat(convertedCapabilities).isEqualTo(expectedCapabilities);
    }
}