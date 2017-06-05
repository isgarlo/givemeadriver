package io.github.isgarlo.givemeadriver.converter;


import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_ACCEPT_SSL_CERTS;
import static org.assertj.core.api.Assertions.assertThat;


public class RemoteCapabilitiesConverterTest {

    private RemoteCapabilitiesConverter remoteCapabilitiesConverter = new RemoteCapabilitiesConverter();

    @Test
    public void notSettingAnyProperty() {
        // given
        WebDriverProperties properties = new WebDriverProperties();

        // when
        DesiredCapabilities convertedCapabilities = remoteCapabilitiesConverter.convert(properties);

        // then
        // expected chrome capabilities
        DesiredCapabilities expectedCapabilities = new DesiredCapabilities();
        expectedCapabilities.setCapability(CAPABILITY_ACCEPT_SSL_CERTS, true);

        assertThat(convertedCapabilities).isEqualTo(expectedCapabilities);
    }

    @Test
    public void settingManyRemoteCapabilities() {
        // given
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty("remote", "https://remote.grid.url");
        properties.setProperty("os", "windows");
        properties.setProperty("os_version", "7");
        properties.setProperty("browser", "firefox");
        properties.setProperty("browser_version", "48.0");
        properties.setProperty("resolution", "1680x1050");
        properties.setProperty(CAPABILITY_ACCEPT_SSL_CERTS, "false");

        // when
        DesiredCapabilities convertedCapabilities = remoteCapabilitiesConverter.convert(properties);

        // then
        // expected chrome capabilities
        DesiredCapabilities expectedCapabilities = new DesiredCapabilities();
        expectedCapabilities.setCapability("remote", "https://remote.grid.url");
        expectedCapabilities.setCapability("os", "windows");
        expectedCapabilities.setCapability("os_version", "7");
        expectedCapabilities.setCapability("browser", "firefox");
        expectedCapabilities.setCapability("browser_version", "48.0");
        expectedCapabilities.setCapability("resolution", "1680x1050");
        expectedCapabilities.setCapability(CAPABILITY_ACCEPT_SSL_CERTS, false);

        assertThat(convertedCapabilities).isEqualTo(expectedCapabilities);
    }
}
