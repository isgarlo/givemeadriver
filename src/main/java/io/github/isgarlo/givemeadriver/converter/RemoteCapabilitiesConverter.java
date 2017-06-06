package io.github.isgarlo.givemeadriver.converter;


import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_ACCEPT_SSL_CERTS;
import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_AUTOCLOSE;

public class RemoteCapabilitiesConverter implements CapabilitiesConverter {

    @Override
    public DesiredCapabilities convert(WebDriverProperties properties) {
        DesiredCapabilities capabilities = new DesiredCapabilities(properties.asMap());
        capabilities.setCapability(CAPABILITY_ACCEPT_SSL_CERTS, properties.isAcceptSslCerts());
        capabilities.setCapability(CAPABILITY_AUTOCLOSE, properties.isAutoClose());
        return capabilities;
    }
}
