package io.github.isgarlo.givemeadriver.factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public interface DriverFactory {

    WebDriver createDriver(DesiredCapabilities capabilities);
}
