package io.github.isgarlo.givemeadriver.converter;


import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_AUTOCLOSE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.remote.CapabilityType.ACCEPT_SSL_CERTS;


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
        expectedCapabilities.setCapability(ACCEPT_SSL_CERTS, true);
        expectedCapabilities.setCapability(CAPABILITY_AUTOCLOSE, false);

        assertThat(convertedCapabilities).isEqualTo(expectedCapabilities);
    }

    @Test
    public void settingManyRemoteCapabilities() throws MalformedURLException {
        // given
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty("remote", "https://remote.grid.url");
        properties.setProperty("os", "windows");
        properties.setProperty("os_version", "7");
        properties.setProperty("browser", "firefox");
        properties.setProperty("browser_version", "48.0");
        properties.setProperty("resolution", "1680x1050");

        // when
        DesiredCapabilities convertedCapabilities = remoteCapabilitiesConverter.convert(properties);

        // then
        // expected chrome capabilities
        DesiredCapabilities expectedCapabilities = new DesiredCapabilities();
        expectedCapabilities.setCapability("remote", new URL("https://remote.grid.url"));
        expectedCapabilities.setCapability("os", "windows");
        expectedCapabilities.setCapability("os_version", "7");
        expectedCapabilities.setCapability("browser", "firefox");
        expectedCapabilities.setCapability("browser_version", "48.0");
        expectedCapabilities.setCapability("resolution", "1680x1050");
        expectedCapabilities.setCapability(ACCEPT_SSL_CERTS, true);
        expectedCapabilities.setCapability(CAPABILITY_AUTOCLOSE, false);

        assertThat(convertedCapabilities).isEqualTo(expectedCapabilities);
    }
}
