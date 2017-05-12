import io.github.isgarlo.givemeadriver.GiveMeADriver;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class LocalDriverTest {

    @Test
    public void createADefaultLocalDriver() {
        // Will create a local ChromeDriver with the latest available binaries.
        assertNotNull(GiveMeADriver.create());
    }

    @Test
    public void createALocalDriverWithProperties() {
        System.setProperty("capabilities.browser", "firefox");
        System.setProperty("capabilities.driverVersion", "0.14.0");
        assertNotNull(GiveMeADriver.create());
    }
}
