package io.github.isgarlo.givemeadriver.converter;

import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.openqa.selenium.Capabilities;


public interface CapabilitiesConverter {

    Capabilities convert(WebDriverProperties properties);
}
