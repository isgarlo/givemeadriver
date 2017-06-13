package io.github.isgarlo.givemeadriver.converter;


import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_AUTOCLOSE;
import static org.openqa.selenium.remote.CapabilityType.ACCEPT_SSL_CERTS;

class RemoteCapabilitiesConverter implements CapabilitiesConverter {

    @Override
    public DesiredCapabilities convert(WebDriverProperties properties) {
        DesiredCapabilities capabilities = new DesiredCapabilities(properties.asMap());
        capabilities.setCapability(ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(CAPABILITY_AUTOCLOSE, properties.isAutoClose());
        return capabilities;
    }
}
