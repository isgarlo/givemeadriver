package io.github.isgarlo.givemeadriver.factories;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_DRIVER_VERSION;


public class ChromeDriverFactory implements DriverFactory {

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        String driverVersion = (String) capabilities.getCapability(CAPABILITY_DRIVER_VERSION);
        ChromeDriverManager.getInstance().version(driverVersion).setup();
        return new ChromeDriver(capabilities);
    }
}
