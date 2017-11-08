package io.github.isgarlo.givemeadriver.factories;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_DRIVER_VERSION;


class ChromeDriverFactory implements DriverFactory {

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        String driverVersion = (String) capabilities.getCapability(CAPABILITY_DRIVER_VERSION);
        ChromeDriverManager.getInstance().version(driverVersion).setup();
        return new ChromeDriver((ChromeOptions) capabilities);
    }
}
