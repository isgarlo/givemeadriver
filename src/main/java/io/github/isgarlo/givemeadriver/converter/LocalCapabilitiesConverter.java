package io.github.isgarlo.givemeadriver.converter;


import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.*;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public abstract class LocalCapabilitiesConverter implements CapabilitiesConverter {

    protected DesiredCapabilities capabilities = new DesiredCapabilities();

    @Override
    public DesiredCapabilities convert(WebDriverProperties properties) {
        capabilities.setCapability(CAPABILITY_BROWSER_NAME, properties.getBrowser());
        capabilities.setCapability(CAPABILITY_ACCEPT_SSL_CERTS, properties.isAcceptSslCerts());
        capabilities.setCapability(CAPABILITY_AUTOCLOSE, properties.isAutoClose());
        addToCapabilitiesIfNoEmptyValue(capabilities, CAPABILITY_DRIVER_VERSION, properties.getDriverVersion());
        addToCapabilitiesIfNoEmptyValue(capabilities, CAPABILITY_BROWSER_SIZE, properties.getBrowserSize());
        addDriverSpecificCapabilities(properties);
        return capabilities;
    }

    protected abstract void addDriverSpecificCapabilities(WebDriverProperties properties);

    void addToMapIfNoEmptyValue(Map<String, Object> sourceMap, String key, String value) {
        if (isNotEmpty(value)) {
            sourceMap.put(key, value);
        }
    }

    private void addToCapabilitiesIfNoEmptyValue(DesiredCapabilities capabilities, String key, String value) {
        if (isNotEmpty(value)) {
            capabilities.setCapability(key, value);
        }
    }


}
