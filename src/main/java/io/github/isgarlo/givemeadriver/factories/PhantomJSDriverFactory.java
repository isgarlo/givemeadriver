package io.github.isgarlo.givemeadriver.factories;

import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import static io.github.isgarlo.givemeadriver.WebDriverProperties.CAPABILITY_DRIVER_VERSION;


class PhantomJSDriverFactory implements DriverFactory {

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        String driverVersion = (String) capabilities.getCapability(CAPABILITY_DRIVER_VERSION);
        PhantomJsDriverManager.getInstance().version(driverVersion).setup();
        return new PhantomJSDriver(capabilities);
    }
}
