package io.github.isgarlo.givemeadriver;

import io.github.isgarlo.givemeadriver.GiveMeADriver;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class LocalDriverTest {

    @Test
    public void createALocalDriverWithProperties() {
        System.setProperty("capabilities.browser", "phantomjs");
        System.setProperty("capabilities.driverVersion", "2.1.1");
        assertNotNull(GiveMeADriver.create());
    }
}
