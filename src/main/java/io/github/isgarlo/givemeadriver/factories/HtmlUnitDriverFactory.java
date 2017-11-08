package io.github.isgarlo.givemeadriver.factories;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


class HtmlUnitDriverFactory implements DriverFactory {

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        return new HtmlUnitDriver(capabilities);
    }
}
