package io.github.isgarlo.givemeadriver.factories;

import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_DRIVER_VERSION;


public class PhantomJSDriverFactory implements DriverFactory {

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        String driverVersion = (String) capabilities.getCapability(CAPABILITY_DRIVER_VERSION);
        PhantomJsDriverManager.getInstance().version(driverVersion).setup();
        return new PhantomJSDriver(capabilities);
    }
}
