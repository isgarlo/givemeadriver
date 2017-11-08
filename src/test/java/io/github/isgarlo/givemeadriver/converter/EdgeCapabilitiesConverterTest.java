package io.github.isgarlo.givemeadriver.converter;


import io.github.isgarlo.givemeadriver.WebDriverProperties;
import org.junit.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.edge.EdgeOptions;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_AUTOCLOSE;
import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_BROWSER_SIZE;
import static org.assertj.core.api.Assertions.assertThat;

public class EdgeCapabilitiesConverterTest {

    private EdgeCapabilitiesConverter edgeCapabilitiesConverter = new EdgeCapabilitiesConverter();

    @Test
    public void settingBrowserSize() {
        // given
        WebDriverProperties properties = new WebDriverProperties();
        properties.setProperty("browserSize", "1690x1000");

        // when
        Capabilities convertedCapabilities = edgeCapabilitiesConverter.convert(properties);

        // then
        // expected safari options
        EdgeOptions expectedEdgeOptions = new EdgeOptions();
        expectedEdgeOptions.setCapability(CAPABILITY_AUTOCLOSE, false);
        expectedEdgeOptions.setCapability(CAPABILITY_BROWSER_SIZE, "1690x1000");

        assertThat(convertedCapabilities.asMap()).isEqualTo(expectedEdgeOptions.asMap());

    }
}
