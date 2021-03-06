package io.github.isgarlo.givemeadriver.converter;


import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_AUTOCLOSE;
import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_BROWSER_SIZE;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

class RemoteCapabilitiesConverter implements CapabilitiesConverter {

    @Override
    public Capabilities convert(WebDriverProperties properties) {
        DesiredCapabilities capabilities = new DesiredCapabilities(properties.asMap());

        capabilities.setCapability(CAPABILITY_AUTOCLOSE, properties.isAutoClose());
        if (isNotEmpty(properties.getBrowserSize())) {
            capabilities.setCapability(CAPABILITY_BROWSER_SIZE, properties.getBrowserSize());
        }
        return capabilities;
    }
}
