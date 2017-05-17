package io.github.isgarlo.givemeadriver;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CapabilitiesMapperTest {

    @Before
    public void setup() {

    }

    @Test
    public void setCapabilityWithPrefix() {
        System.setProperty("capabilities.remote", "http://REMOTE_URL");
        WebDriverCapabilities mappedProperties = new WebDriverCapabilities();
        mappedProperties.mapFromSystemProperties();
        assertTrue(mappedProperties.isRemote());
        assertEquals("remote", mappedProperties.getRemote());
    }

    @Test
    public void setCapabilityWithoutPrefix() {
        WebDriverCapabilities.setToSystemProperties("browser", "chrome");
        assertEquals(System.getProperty("capabilities.browser"), "chrome");
        assertNull(System.getProperty("browser"));
    }
}
