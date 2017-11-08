<img src="https://cloud.githubusercontent.com/assets/15626602/26036362/7e36f35e-38dc-11e7-8fc9-a198bb5c7dc6.png">

[![DevOps By Rultor.com](http://www.rultor.com/b/jcabi/jcabi-aspects)](http://www.rultor.com)
[![We recommend IntelliJ IDEA](http://img.teamed.io/intellij-idea-recommend.svg)](https://www.jetbrains.com/idea/)
[![Build Status](https://travis-ci.org/isgarlo/givemeadriver.svg?branch=master)](https://travis-ci.org/isgarlo/givemeadriver)
[![Code coverage](https://codecov.io/github/isgarlo/givemeadriver/coverage.svg?branch=master)](https://codecov.io/gh/isgarlo/givemeadriver)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.isgarlo/givemeadriver/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.isgarlo/givemeadriver)

Get a Java WebDriver instance changing no line of your test code.

System properties prefixed with '**capabilities.**' will be automatically processed as DesiredCapabilities. Among other things, it helps you to launch any test on popular cloud services like BrowserStack, or Saucelabs, or to spin up a local browser whilst you're coding your tests.

GiveMeADriver is open source, released under the terms of [![License (LGPL version 2.1)](https://img.shields.io/badge/license-GNU%20LGPL%20version%202.1-brightgreen.svg?style=flat-square)](http://opensource.org/licenses/LGPL-2.1)

## Setup & Basic Usage

In order to use GiveMeADriver, add the following dependency to your Maven project:

```xml
<dependency>
  <groupId>io.github.isgarlo</groupId>
  <artifactId>givemeadriver</artifactId>
  <version>0.0.9</version>
</dependency>
```

Once the dependency is set, just pass the parameters to your test runner making sure all of them are prefixed by **capabilities.** :
```java
-Dcapabilities.remote=https://USERNAME:AUTH_KEY@hub-cloud.browserstack.com/wd/hub
-Dcapabilities.os=windows
-Dcapabilities.os_version=7
-Dcapabilities.browser=firefox
-Dcapabilities.browser_version=48.0
-Dcapabilities.resolution=1680x1050
```
Then, simply get your WebDriver instance:
```java
@Test
public void test() {
  WebDriver driver = GiveMeADriver.create();
}
```
## Capabilities setup
Capability | Description
------------ | -----------
**capabilities.remote** | Remote url. If not set, GiveMeADriver.create() will instantiate a local driver.
**capabilities.browser** | *chrome* by default, so if you´re testing in Chrome, you can skip this one.
**capabilities.browserSize** | A value in the format *1024x768*. If set, the browser window will be resized.
**capabilities.headless** | *false* by default. If set to true, GiveMeADriver will return the headless version of the requested browser. [Chrome](https://developers.google.com/web/updates/2017/04/headless-chrome) is now supporting it!
**capabilities.autoclose** | *false* by default. GiveMeADriver can manage the browser for you closing it once the last test has been executed.
**capabilities.driverVersion** | If set, the specified binary version will be downloaded to create a local driver. If not, the latest binary version will be fetched.
**capabilities.device** | Make use of the [Chrome Mobile Emulation](https://sites.google.com/a/chromium.org/chromedriver/mobile-emulation) to test on devices. Check the available ones [here](https://cs.chromium.org/chromium/src/chrome/test/chromedriver/chrome/mobile_device_list.cc).
**capabilities.userAgent** | Set your custom user agent string.
**capabilities.viewportSize** | A value in the format *414x736*. If set, the viewport will be resized.
**capabilities.pixelRatio** | A value in the format *2.0* to manually define the device pixel ratio.

Additionally, some capabilities can be refered by their aliases:

Capability | Aliases
------------ | -----------
**capabilities.browser** | **capabilities.browserName**, **browser**
**capabilities.device** | **capabilities.deviceName**, **device**

For a complete list of capabilities of the main cloud services take a look to the following links:
- https://www.browserstack.com/automate/capabilities
- https://wiki.saucelabs.com/display/DOCS/Test+Configuration+Options
- https://testingbot.com/support/other/test-options

## Other commands
 ```java
// get the current WebDriver instance
GiveMeADriver.current();
// close the current WebDriver instance. No need to call it if capabilities.autoclose=true
GiveMeADriver.close();
```

## Examples
1. Create a local driver: Firefox with driver binary 0.11.4
```java
public class LocalDriverTest {

  WebDriver driver;

  @Before
  public void setup() {
    // setting capabilities programmatically
    System.setProperty("capabilities.browser", "firefox");
    System.setProperty("capabilities.driverVersion", "0.11.4");
    driver = GiveMeADriver.create();
  }
  
  @Test
  public void myTest() {
    // use the driver here or
    // get it by GiveMeADriver.current();
  }
	
  @After
  public void tearDown() {
    // no need to driver.quit(); nor GiveMeADriver.close();
    // if capabilities.autoclose=true
  }
}
```
Notice that **capabilities.remote** has not been set. A local Firefox browser will be open.
Currently, the local supported browsers are: *chrome*, *firefox*, *opera*, *safari*, *ie*, *edge*, *phantomjs*, *htmlunit*.

2. Create a local driver: Chrome with latest binaries.
```java
public class LocalDriverTest {
	
  @Test
  public void createLocalDriver() {
    GiveMeADriver.create();
  }
}
```
3. Create a remote driver: Chrome 57 in BrowserStack.
```java
public class RemoteDriverTest {
	
  @Test
  public void createRemoteDriver() {
    System.setProperty("capabilities.remote", 
      "https://USERNAME:AUTH_KEY@hub-cloud.browserstack.com/wd/hub");
    System.setProperty("capabilities.browser", "chrome");
    System.setProperty("capabilities.browser_version", "57");
    GiveMeADriver.create();
  }
}
```
4. Create a local mobile emulator: iPhone 6.
 ```java
public class LocalDriverTest {
	
  @Test
  public void createLocalDriver() {
    System.setProperty("capabilities.device", "iPhone 6");
    GiveMeADriver.create();
  }
}
```
5. Create a custom local mobile emulator: Generic Apple device at 1024x1366
 ```java
public class LocalDriverTest {
	
  @Test
  public void createLocalDriver() {
    System.setProperty("capabilities.userAgent", 
	    "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) CriOS/56.0.2924.75 Mobile/14E5239e Safari/602.1");
    System.setProperty("capabilities.viewportSize", "1024x1366");
    System.setProperty("capabilities.pixelRatio", "2.0");
    GiveMeADriver.create();
  }
}
```
6. Create a headless local driver: Chrome with latest binaries.
```java
public class LocalDriverTest {

  @Test
  public void createLocalDriver() {
    System.setProperty("capabilities.browser", "chrome");
    System.setProperty("capabilities.headless", "true");
    GiveMeADriver.create();
  }
}
```
