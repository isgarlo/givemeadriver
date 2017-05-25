package io.github.isgarlo.givemeadriver;


import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class WebDriverContainerTest {
    private WebDriverContainer container = spy(WebDriverContainer.getInstance());

    @Before
    public void setUp() {
        container.factory = mock(WebDriverFactory.class);
        doReturn(mock(WebDriver.class)).when(container.factory).createWebDriver(any(WebDriverProperties.class));

    }

    @Test
    public void getDriverMatches() {
        WebDriver webdriver = mock(WebDriver.class);
        container.WEB_DRIVER = webdriver;
        assertSame(webdriver, container.getDriver());
    }

    @Test
    public void sameArgumentsToTheFactory() {
        WebDriverProperties props = new WebDriverProperties(new HashMap<>());
        container.createDriver(props);

        ArgumentCaptor<WebDriverProperties> captor = ArgumentCaptor.forClass(WebDriverProperties.class);
        verify(container.factory).createWebDriver(captor.capture());
        assertThat(captor.getValue().getBrowser()).isEqualTo("chrome");
        assertThat(captor.getValue().isAutoClose()).isTrue();
    }
}
