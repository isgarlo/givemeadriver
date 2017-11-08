package io.github.isgarlo.givemeadriver.converter;


import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.firefox.FirefoxOptions;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.*;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class FirefoxCapabilitiesConverter implements CapabilitiesConverter {

    @Override
    public Capabilities convert(WebDriverProperties properties) {
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        // general options for logging purpose
        firefoxOptions.setCapability(CAPABILITY_AUTOCLOSE, properties.isAutoClose());
        addToFirefoxOptionsIfNoEmptyValue(firefoxOptions, CAPABILITY_DRIVER_VERSION, properties.getDriverVersion());
        addToFirefoxOptionsIfNoEmptyValue(firefoxOptions, CAPABILITY_BROWSER_SIZE, properties.getBrowserSize());

        return firefoxOptions;
    }

    private void addToFirefoxOptionsIfNoEmptyValue(FirefoxOptions firefoxOptions, String key, String value) {
        if (isNotEmpty(value)) {
            firefoxOptions.setCapability(key, value);
        }
    }
}
