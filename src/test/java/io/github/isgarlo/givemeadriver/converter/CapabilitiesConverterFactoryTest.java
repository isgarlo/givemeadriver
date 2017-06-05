package io.github.isgarlo.givemeadriver.converter;

import io.github.isgarlo.givemeadriver.factories.DriverType;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CapabilitiesConverterFactoryTest {

    private CapabilitiesConverterFactory converterFactory = spy(CapabilitiesConverterFactory.class);

    @Test
    public void createRemoteCapabilitiesConverter() {
        // given
        CapabilitiesConverter expectedConverter = mock(RemoteCapabilitiesConverter.class);
        when(converterFactory.create(DriverType.REMOTE)).thenReturn(expectedConverter);
        // when
        CapabilitiesConverter builtFactory = converterFactory.create(DriverType.REMOTE);
        // then
        assertThat(builtFactory).isEqualTo(expectedConverter);
    }

    @Test
    public void createChromeCapabilitiesConverter() {
        // given
        CapabilitiesConverter expectedConverter = mock(ChromeCapabilitiesConverter.class);
        when(converterFactory.create(DriverType.CHROME)).thenReturn(expectedConverter);
        // when
        CapabilitiesConverter builtFactory = converterFactory.create(DriverType.CHROME);
        // then
        assertThat(builtFactory).isEqualTo(expectedConverter);
    }

    @Test
    public void createPhantomJSCapabilitiesConverter() {
        // given
        CapabilitiesConverter expectedConverter = mock(PhantomJSCapabilitiesConverter.class);
        when(converterFactory.create(DriverType.PHANTOMJS)).thenReturn(expectedConverter);
        // when
        CapabilitiesConverter builtFactory = converterFactory.create(DriverType.PHANTOMJS);
        // then
        assertThat(builtFactory).isEqualTo(expectedConverter);
    }

    @Test
    public void createGenericCapabilitiesConverter() {
        // given
        CapabilitiesConverter expectedConverter = mock(GenericCapabilitiesConverter.class);
        when(converterFactory.create(DriverType.FIREFOX)).thenReturn(expectedConverter);
        // when
        CapabilitiesConverter builtFactory = converterFactory.create(DriverType.FIREFOX);
        // then
        assertThat(builtFactory).isEqualTo(expectedConverter);
    }

}
