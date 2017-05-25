package io.github.isgarlo.givemeadriver;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

public class WebDriverContainerTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();
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

    @Test
    public void noAdditionalDriversAllowed() {
        WebDriverProperties props = new WebDriverProperties(new HashMap<>());
        container.createDriver(props);

        exception.expect(IllegalStateException.class);
        exception.expectMessage("There is a driver already open. Only one instance allowed.");
        container.createDriver(props);
    }

    @Test
    public void throwsExceptionIfNoCurrentDriver() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("No driver has been set. Call GiveMeADriver.create();");
        container.getDriver();
    }

    @Test
    public void closeDriverNormally() {
        container.WEB_DRIVER = mock(WebDriver.class);
        container.closeDriver();

        verify(container).closeDriver();
        assertThat(container.WEB_DRIVER).isNull();
    }

}
