package io.github.isgarlo.givemeadriver;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class LocalDriverTest {

    @Before

    @Test
    public void createADefaultLocalDriver() {
        // Will create a local ChromeDriver with the latest available binaries.
        assertNotNull(GiveMeADriver.create());
    }

    @Test
    public void createALocalDriverWithProperties() {
        System.setProperty("capabilities.browser", "phantomjs");
        System.setProperty("capabilities.driverVersion", "2.1.1");
        assertNotNull(GiveMeADriver.create());
    }
}
