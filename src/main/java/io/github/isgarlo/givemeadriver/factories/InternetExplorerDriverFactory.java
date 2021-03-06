package io.github.isgarlo.givemeadriver.factories;

import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_DRIVER_VERSION;


class InternetExplorerDriverFactory implements DriverFactory {

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        String driverVersion = (String) capabilities.getCapability(CAPABILITY_DRIVER_VERSION);
        InternetExplorerDriverManager.getInstance().version(driverVersion).setup();
        return new InternetExplorerDriver((InternetExplorerOptions) capabilities);
    }
}
