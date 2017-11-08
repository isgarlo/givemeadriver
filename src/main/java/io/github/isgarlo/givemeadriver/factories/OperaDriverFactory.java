package io.github.isgarlo.givemeadriver.factories;

import io.github.bonigarcia.wdm.OperaDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_DRIVER_VERSION;


class OperaDriverFactory implements DriverFactory {

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        String driverVersion = (String) capabilities.getCapability(CAPABILITY_DRIVER_VERSION);
        OperaDriverManager.getInstance().version(driverVersion).setup();
        return  new OperaDriver((OperaOptions) capabilities);
    }
}
