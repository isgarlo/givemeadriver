package io.github.isgarlo.givemeadriver;

import io.github.isgarlo.givemeadriver.CapabilitiesMapper;
import org.junit.Test;

import static io.github.isgarlo.givemeadriver.DefaultCapabilities.getBrowser;
import static io.github.isgarlo.givemeadriver.DefaultCapabilities.getDriverVersion;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CapabilitiesMapperTest {

    @Test
    public void setCapabilityWithPrefix() {
        CapabilitiesMapper.setToSystemProperties("capabilities.browser", "firefox");
        assertEquals(System.getProperty("capabilities.browser"), "firefox");
    }

    @Test
    public void setCapabilityWithoutPrefix() {
        CapabilitiesMapper.setToSystemProperties("browser", "chrome");
        assertEquals(System.getProperty("capabilities.browser"), "chrome");
        assertNull(System.getProperty("browser"));
    }

    @Test
    public void mapCapabilities() {
        System.setProperty("capabilities.browser", "phantomjs");
        System.setProperty("capabilities.driverVersion", "2.1.1");
        CapabilitiesMapper.mapFromSystemProperties();
        assertEquals("phantomjs", getBrowser());
        assertEquals("2.1.1", getDriverVersion());
    }
}
