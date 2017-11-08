package io.github.isgarlo.givemeadriver.converter;


import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ie.InternetExplorerOptions;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.*;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class InternetExplorerCapabilitiesConverter implements CapabilitiesConverter {

    @Override
    public Capabilities convert(WebDriverProperties properties) {
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();

        // general options for logging purpose
        internetExplorerOptions.setCapability(CAPABILITY_AUTOCLOSE, properties.isAutoClose());
        addToInternetExplorerOptionsIfNoEmptyValue(internetExplorerOptions, CAPABILITY_DRIVER_VERSION, properties.getDriverVersion());
        addToInternetExplorerOptionsIfNoEmptyValue(internetExplorerOptions, CAPABILITY_BROWSER_SIZE, properties.getBrowserSize());

        return internetExplorerOptions;
    }

    private void addToInternetExplorerOptionsIfNoEmptyValue(InternetExplorerOptions internetExplorerOptions, String key, String value) {
        if (isNotEmpty(value)) {
            internetExplorerOptions.setCapability(key, value);
        }
    }
}
