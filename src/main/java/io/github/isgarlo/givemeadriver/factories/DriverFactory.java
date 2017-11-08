package io.github.isgarlo.givemeadriver.factories;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public interface DriverFactory {

    WebDriver createDriver(Capabilities capabilities);
}
