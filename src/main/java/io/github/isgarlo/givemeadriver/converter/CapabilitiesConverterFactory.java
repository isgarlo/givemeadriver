package io.github.isgarlo.givemeadriver.converter;

import io.github.isgarlo.givemeadriver.factories.*;

public class CapabilitiesConverterFactory {

    public CapabilitiesConverter create(DriverType type) {
        switch (type) {
            case REMOTE:
                return new RemoteCapabilitiesConverter();
            case FIREFOX:
                return new FirefoxCapabilitiesConverter();
            case IE:
                return new InternetExplorerCapabilitiesConverter();
            case EDGE:
                return new EdgeCapabilitiesConverter();
            case SAFARI:
                return new SafariCapabilitiesConverter();
            case OPERA:
                return new OperaCapabilitiesConverter();
            case HTMLUNIT:
                return new HtmlUnitCapabilitiesConverter();
            case PHANTOMJS:
                return  new PhantomJSCapabilitiesConverter();
            case CHROME:
            default:
                return new ChromeCapabilitiesConverter();
        }
    }

}
