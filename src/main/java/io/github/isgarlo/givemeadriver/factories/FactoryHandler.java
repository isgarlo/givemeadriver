package io.github.isgarlo.givemeadriver.factories;

public class FactoryHandler implements IFactoryHandler {

    public DriverFactory create(DriverType type) {
        switch (type) {
            case REMOTE:
                return new RemoteDriverFactory();
            case FIREFOX:
                return new FirefoxDriverFactory();
            case IE:
                return new InternetExplorerDriverFactory();
            case EDGE:
                return new EdgeDriverFactory();
            case SAFARI:
                return new SafariDriverFactory();
            case OPERA:
                return new OperaDriverFactory();
            case PHANTOMJS:
                return new PhantomJSDriverFactory();
            case HTMLUNIT:
                return new HtmlUnitDriverFactory();
            case CHROME:
            default:
                return new ChromeDriverFactory();
        }
    }

}
