package io.github.isgarlo.givemeadriver.factories;

import io.github.bonigarcia.wdm.EdgeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_DRIVER_VERSION;


public class EdgeDriverFactory implements DriverFactory {

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        String driverVersion = (String) capabilities.getCapability(CAPABILITY_DRIVER_VERSION);
        EdgeDriverManager.getInstance().version(driverVersion).setup();
        return new EdgeDriver(capabilities);
    }
}
