package io.github.isgarlo.givemeadriver.factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_REMOTE;

class RemoteDriverFactory implements DriverFactory {

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        URL remoteUrl = (URL) capabilities.getCapability(CAPABILITY_REMOTE);
        return new RemoteWebDriver(remoteUrl, capabilities);
    }
}
