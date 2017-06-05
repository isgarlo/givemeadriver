package io.github.isgarlo.givemeadriver.factories;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class FactoryHandlerTest {

    private FactoryHandler factoryHandler = spy(FactoryHandler.class);

    @Test
    public void createRemoteDriverFactory() {
        // given
        DriverFactory driverFactory = mock(RemoteDriverFactory.class);
        when(factoryHandler.create(DriverType.REMOTE)).thenReturn(driverFactory);

        // when
        DriverFactory builtFactory = factoryHandler.create(DriverType.REMOTE);

        // then
        assertThat(builtFactory).isEqualTo(driverFactory);
    }

    @Test
    public void createFirefoxDriverFactory() {
        // given
        DriverFactory driverFactory = mock(FirefoxDriverFactory.class);
        when(factoryHandler.create(DriverType.FIREFOX)).thenReturn(driverFactory);

        // when
        DriverFactory builtFactory = factoryHandler.create(DriverType.FIREFOX);

        // then
        assertThat(builtFactory).isEqualTo(driverFactory);
    }

    @Test
    public void createInternetExplorerDriverFactory() {
        // given
        DriverFactory driverFactory = mock(InternetExplorerDriverFactory.class);
        when(factoryHandler.create(DriverType.IE)).thenReturn(driverFactory);

        // when
        DriverFactory builtFactory = factoryHandler.create(DriverType.IE);

        // then
        assertThat(builtFactory).isEqualTo(driverFactory);
    }

    @Test
    public void createEdgeDriverFactory() {
        // given
        DriverFactory driverFactory = mock(EdgeDriverFactory.class);
        when(factoryHandler.create(DriverType.EDGE)).thenReturn(driverFactory);

        // when
        DriverFactory builtFactory = factoryHandler.create(DriverType.EDGE);

        // then
        assertThat(builtFactory).isEqualTo(driverFactory);
    }

    @Test
    public void createSafariDriverFactory() {
        // given
        DriverFactory driverFactory = mock(SafariDriverFactory.class);
        when(factoryHandler.create(DriverType.SAFARI)).thenReturn(driverFactory);

        // when
        DriverFactory builtFactory = factoryHandler.create(DriverType.SAFARI);

        // then
        assertThat(builtFactory).isEqualTo(driverFactory);
    }

    @Test
    public void createOperaDriverFactory() {
        // given
        DriverFactory driverFactory = mock(OperaDriverFactory.class);
        when(factoryHandler.create(DriverType.OPERA)).thenReturn(driverFactory);

        // when
        DriverFactory builtFactory = factoryHandler.create(DriverType.OPERA);

        // then
        assertThat(builtFactory).isEqualTo(driverFactory);
    }

    @Test
    public void createPhantomJSDriverFactory() {
        // given
        DriverFactory driverFactory = mock(PhantomJSDriverFactory.class);
        when(factoryHandler.create(DriverType.PHANTOMJS)).thenReturn(driverFactory);

        // when
        DriverFactory builtFactory = factoryHandler.create(DriverType.PHANTOMJS);

        // then
        assertThat(builtFactory).isEqualTo(driverFactory);
    }

    @Test
    public void createChromeDriverFactory() {
        // given
        DriverFactory driverFactory = mock(ChromeDriverFactory.class);
        when(factoryHandler.create(DriverType.CHROME)).thenReturn(driverFactory);

        // when
        DriverFactory builtFactory = factoryHandler.create(DriverType.CHROME);

        // then
        assertThat(builtFactory).isEqualTo(driverFactory);
    }

    @Test
    public void createHtmlUnitDriverFactory() {
        // given
        DriverFactory driverFactory = mock(HtmlUnitDriverFactory.class);
        when(factoryHandler.create(DriverType.HTMLUNIT)).thenReturn(driverFactory);

        // when
        DriverFactory builtFactory = factoryHandler.create(DriverType.HTMLUNIT);

        // then
        assertThat(builtFactory).isEqualTo(driverFactory);
    }
}
