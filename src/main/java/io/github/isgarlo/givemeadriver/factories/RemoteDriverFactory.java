package io.github.isgarlo.givemeadriver.factories;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_REMOTE;

class RemoteDriverFactory implements DriverFactory {

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        URL remoteUrl = null;
        try {
            remoteUrl = new URL((String) capabilities.getCapability(CAPABILITY_REMOTE));
        } catch (MalformedURLException e) {
            // can't get here. Already handled in WebDriverProperties.validate()
        }
        return new RemoteWebDriver(remoteUrl, capabilities);
    }
}
