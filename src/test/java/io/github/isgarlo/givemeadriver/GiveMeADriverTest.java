package io.github.isgarlo.givemeadriver;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class GiveMeADriverTest {
    private GiveMeADriver giveMeADriver = spy(GiveMeADriver.class);
    WebDriverContainer container;

    @Before
    public void setUp() {
        container = mock(WebDriverContainer.class);
        doReturn(mock(WebDriver.class)).when(container).createDriver(any(WebDriverProperties.class));

    }

    @Test
    public void getDriverMatches() {
        WebDriver webdriver = mock(WebDriver.class);
        container.WEB_DRIVER = webdriver;
        assertSame(webdriver, container.getDriver());
        verify(giveMeADriver).create();
    }
}
