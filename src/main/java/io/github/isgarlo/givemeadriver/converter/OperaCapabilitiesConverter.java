package io.github.isgarlo.givemeadriver.converter;


import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.opera.OperaOptions;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.*;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class OperaCapabilitiesConverter implements CapabilitiesConverter {

    @Override
    public Capabilities convert(WebDriverProperties properties) {
        OperaOptions operaOptions = new OperaOptions();

        // general options for logging purpose
        operaOptions.setCapability(CAPABILITY_AUTOCLOSE, properties.isAutoClose());
        addToOperaOptionsIfNoEmptyValue(operaOptions, CAPABILITY_DRIVER_VERSION, properties.getDriverVersion());
        addToOperaOptionsIfNoEmptyValue(operaOptions, CAPABILITY_BROWSER_SIZE, properties.getBrowserSize());

        return operaOptions;
    }

    private void addToOperaOptionsIfNoEmptyValue(OperaOptions operaOptions, String key, String value) {
        if (isNotEmpty(value)) {
            operaOptions.setCapability(key, value);
        }
    }
}
