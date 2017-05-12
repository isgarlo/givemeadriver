package io.github.isgarlo.givemeadriver;

import org.openqa.selenium.WebDriver;

public interface WebDriverContainer {
  WebDriver createDriver();
  WebDriver getFirstDriver();
  WebDriver getLastDriver();
  WebDriver getDriverBy(int index);
  void closeLastDriver();
}
