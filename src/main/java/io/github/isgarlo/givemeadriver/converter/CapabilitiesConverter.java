package io.github.isgarlo.givemeadriver.converter;

import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.openqa.selenium.remote.DesiredCapabilities;


public interface CapabilitiesConverter {

    DesiredCapabilities convert(WebDriverProperties properties);
}
