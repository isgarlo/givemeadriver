package io.github.isgarlo.givemeadriver.converter;


import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.*;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

class HtmlUnitCapabilitiesConverter implements CapabilitiesConverter {


    @Override
    public Capabilities convert(WebDriverProperties properties) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // general options for logging purpose
        capabilities.setCapability(CAPABILITY_BROWSER_NAME, properties.getBrowser());
        capabilities.setCapability(CAPABILITY_AUTOCLOSE, properties.isAutoClose());
        if (isNotEmpty(properties.getBrowserSize())) {
            capabilities.setCapability(CAPABILITY_BROWSER_SIZE, properties.getBrowserSize());
        }

        return capabilities;
    }

}
