import io.github.isgarlo.givemeadriver.CapabilitiesMapper;
import org.junit.Test;

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
}
