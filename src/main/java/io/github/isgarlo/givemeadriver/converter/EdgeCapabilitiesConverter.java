package io.github.isgarlo.givemeadriver.converter;


import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.edge.EdgeOptions;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.*;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class EdgeCapabilitiesConverter implements CapabilitiesConverter {

    @Override
    public Capabilities convert(WebDriverProperties properties) {
        EdgeOptions edgeOptions = new EdgeOptions();

        // general options for logging purpose
        edgeOptions.setCapability(CAPABILITY_AUTOCLOSE, properties.isAutoClose());
        addToEdgeOptionsIfNoEmptyValue(edgeOptions, CAPABILITY_DRIVER_VERSION, properties.getDriverVersion());
        addToEdgeOptionsIfNoEmptyValue(edgeOptions, CAPABILITY_BROWSER_SIZE, properties.getBrowserSize());

        return edgeOptions;
    }

    private void addToEdgeOptionsIfNoEmptyValue(EdgeOptions edgeOptions, String key, String value) {
        if (isNotEmpty(value)) {
            edgeOptions.setCapability(key, value);
        }
    }
}
