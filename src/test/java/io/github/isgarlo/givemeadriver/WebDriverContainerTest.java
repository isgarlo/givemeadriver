package io.github.isgarlo.givemeadriver;


import io.github.isgarlo.givemeadriver.factories.DriverFactory;
import io.github.isgarlo.givemeadriver.factories.DriverType;
import io.github.isgarlo.givemeadriver.factories.FactoryHandler;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class WebDriverContainerTest {

    private WebDriverContainer container = new WebDriverContainer();

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void getDriverMatches() {
        WebDriver webdriver = mock(WebDriver.class);
        container.WEB_DRIVER = webdriver;
        assertThat(container.getDriver()).isEqualTo(webdriver);
    }

    @Test
    public void noAdditionalDriversAllowed() {
        container.WEB_DRIVER = mock(WebDriver.class);
        exception.expect(IllegalStateException.class);
        exception.expectMessage("There is a driver already open. Only one instance allowed.");
        container.createDriver(DriverType.CHROME, mock(DesiredCapabilities.class));
        verify(container).createDriver(any(DriverType.class), any(DesiredCapabilities.class));
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
        assertThat(container.WEB_DRIVER).isNull();
    }

    @Test
    public void markForAutoClose() {
        container.markDriverForAutoClose(true);
        assertThat(Runtime.getRuntime().removeShutdownHook(container.cleanupThread)).isTrue();
    }

    @Test
    public void doNotMarkForAutoClose() {
        container.markDriverForAutoClose(false);
        assertThat(Runtime.getRuntime().removeShutdownHook(container.cleanupThread)).isFalse();
    }

    @Test
    public void cleanupThreadClosesTheDriver() {
        // given
        container.WEB_DRIVER = mock(WebDriver.class);
        WebDriverContainer.WebDriverCleanupThread cleanupThread = container.new WebDriverCleanupThread();
        // when
        cleanupThread.run();
        // then
        assertThat(container.WEB_DRIVER).isNull();

    }

    @Test
    public void theFactoryCallsTheCreateDriverMethod() {
        // given
        DriverFactory driverFactory = mock(DriverFactory.class);
        container.factory = new FactoryHandler() {
            public DriverFactory create(DriverType type) { return driverFactory;}
        };

        // when
        DesiredCapabilities capabilities = mock(DesiredCapabilities.class);
        container.createDriver(any(DriverType.class), capabilities);

        // then
        verify(driverFactory).createDriver(capabilities);
    }

    @Test
    public void doesNotChangeWindowSizeByDefault() {
        container.WEB_DRIVER = mock(WebDriver.class);
        container.setDriverWindowSize("");
        verifyNoMoreInteractions(container.WEB_DRIVER);
    }

    @Test
    public void canConfigureBrowserWindowSize() {
        container.WEB_DRIVER = mock(WebDriver.class, RETURNS_DEEP_STUBS);
        container.setDriverWindowSize("1600x800");
        verify(container.WEB_DRIVER.manage().window()).setSize(new Dimension(1600, 800));
    }

}
