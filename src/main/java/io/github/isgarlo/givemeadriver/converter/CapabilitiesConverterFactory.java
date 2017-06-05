package io.github.isgarlo.givemeadriver.converter;

import io.github.isgarlo.givemeadriver.factories.*;

public class CapabilitiesConverterFactory {

    public CapabilitiesConverter create(DriverType type) {
        switch (type) {
            case REMOTE:
                return new RemoteCapabilitiesConverter();
            case FIREFOX:
            case IE:
            case EDGE:
            case SAFARI:
            case OPERA:
            case HTMLUNIT:
                return new GenericCapabilitiesConverter();
            case PHANTOMJS:
                return  new PhantomJSCapabilitiesConverter();
            case CHROME:
            default:
                return new ChromeCapabilitiesConverter();
        }
    }

}
