package io.github.isgarlo.givemeadriver.factories;

import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_DRIVER_VERSION;


class FirefoxDriverFactory implements DriverFactory {

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        String driverVersion = (String) capabilities.getCapability(CAPABILITY_DRIVER_VERSION);
        FirefoxDriverManager.getInstance().version(driverVersion).setup();
        return new FirefoxDriver((FirefoxOptions) capabilities);
    }
}
