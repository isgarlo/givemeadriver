package io.github.isgarlo.givemeadriver;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(GiveMeADriver.class)
public class GiveMeADriverTest {

    @Test
    public void createReturnsADriver() {
        mockStatic(GiveMeADriver.class);
        when(GiveMeADriver.create()).thenReturn(mock(WebDriver.class));
        GiveMeADriver.create();

        verifyStatic();
        GiveMeADriver.create();

    }

    @Test
    public void currentReturnsTheDriver() {
        mockStatic(GiveMeADriver.class);
        when(GiveMeADriver.current()).thenReturn(mock(WebDriver.class));
        GiveMeADriver.current();

        verifyStatic();
        GiveMeADriver.current();
    }

    @Test
    public void closeReturnsTheDriver() {
        mockStatic(GiveMeADriver.class);
        GiveMeADriver.close();

        verifyStatic();
        GiveMeADriver.close();
    }

}
