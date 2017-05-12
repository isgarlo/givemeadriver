# GiveMeADriver

Get a Java WebDriver instance changing no line of your test code.

System properties prefixed with '**capabilities.**' will be automatically processed as DesiredCapabilities. Among other things, it helps you to launch any test on popular cloud services like BrowserStack or Saucelabs, or spin up a local browser whilst you're coding your tests.

GiveMeADriver is open source, released under the terms of [LGPL License 2.1].

## Usage

In order to use GiveMeADriver, add the following dependency to your Maven project:

```xml
<dependency>
	<groupId>io.github.isgarlo</groupId>
	<artifactId>givemeadriver</artifactId>
	<version>1.0.0</version>
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
		// Your test code here
	}
```
## Special capabilities
 - **capabilities.remote**: remote url. If not set, GiveMeADriver.create() will try to instantiate a local ChromeDriver.
 - **capabilities.browser**: *chrome* by default, so if you´re testing in Chrome, you can skip this one.
 - **capabilities.autoclose**: *true* by default. GiveMeADriver will close the browser for you once the last test has been executed. That means there is no need to call the quit() method.
 - **capabilities.driverVersion**: if set and *capabilities.remote* is not, the specified binary version will be fetched to create the local WebDriver.

 Please refer to the below documentation for a complete list of capabilities of the main cloud services.
https://www.browserstack.com/automate/capabilities
https://wiki.saucelabs.com/display/DOCS/Test+Configuration+Options

 ## Other commands
 ```java
 // gets the current WebDriver instance
 GiveMeADriver.current();
 // close the current WebDriver instance. No need to call it if capabilities.autoclose=true
 GiveMeADriver.close();
```
