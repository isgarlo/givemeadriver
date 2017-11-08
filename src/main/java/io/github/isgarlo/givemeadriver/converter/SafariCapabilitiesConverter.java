package io.github.isgarlo.givemeadriver.converter;


import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.safari.SafariOptions;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.*;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class SafariCapabilitiesConverter implements CapabilitiesConverter {

    @Override
    public Capabilities convert(WebDriverProperties properties) {
        SafariOptions safariOptions = new SafariOptions();

        // general options for logging purpose
        safariOptions.setCapability(CAPABILITY_AUTOCLOSE, properties.isAutoClose());
        if (isNotEmpty(properties.getBrowserSize())) {
            safariOptions.setCapability(CAPABILITY_BROWSER_SIZE, properties.getBrowserSize());
        }

        return safariOptions;
    }
}
