package io.github.isgarlo.givemeadriver.converter;


import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.*;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

class PhantomJSCapabilitiesConverter implements CapabilitiesConverter {

    @Override
    public Capabilities convert(WebDriverProperties properties) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // general options for logging purpose
        capabilities.setCapability(CAPABILITY_BROWSER_NAME, properties.getBrowser());
        capabilities.setCapability(CAPABILITY_AUTOCLOSE, properties.isAutoClose());
        addToCapabilitiesIfNoEmptyValue(capabilities, CAPABILITY_DRIVER_VERSION, properties.getDriverVersion());
        addToCapabilitiesIfNoEmptyValue(capabilities, CAPABILITY_BROWSER_SIZE, properties.getBrowserSize());

        capabilities.setCapability("phantomjs.cli.args",
                new String[]{"--web-security=no", "--ignore-ssl-errors=yes"});

        return capabilities;
    }

    private void addToCapabilitiesIfNoEmptyValue(MutableCapabilities capabilities, String key, String value) {
        if (isNotEmpty(value)) {
            capabilities.setCapability(key, value);
        }
    }
}
