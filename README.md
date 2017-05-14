<img src="https://cloud.githubusercontent.com/assets/15626602/26036362/7e36f35e-38dc-11e7-8fc9-a198bb5c7dc6.png">

Get a Java WebDriver instance changing no line of your test code.

System properties prefixed with '**capabilities.**' will be automatically processed as DesiredCapabilities. Among other things, it helps you to launch any test on popular cloud services like BrowserStack, or Saucelabs, or to spin up a local browser whilst you're coding your tests.

GiveMeADriver is open source, released under the terms of [LGPL License 2.1].

## Setup & Basic Usage

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
}
```
## Special capabilities
 - **capabilities.remote**: remote url. If not set, GiveMeADriver.create() will instantiate a local driver.
 - **capabilities.browser**: *chrome* by default, so if you´re testing in Chrome, you can skip this one.
 - **capabilities.autoclose**: *true* by default. GiveMeADriver will close the browser for you once the last test has been executed. That means there is no need to call the quit() method.
 - **capabilities.driverVersion**: if set, the specified binary version will be downloaded to create a local driver. If not, the latest binary version will be fetched.

 Please refer to the below documentation for a complete list of capabilities of the main cloud services.
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
Currently, the local supported browsers are: *chrome*, *firefox*, *opera*, *safari*, *ie*, *edge*, *phantomjs*.

2. Create a local driver: Chrome with latest binaries.
```java
public class LocalDriverTest {
	
	@Test
	public void createLocalDriver() {
		WebDriver driver = GiveMeADriver.create();
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
		WebDriver driver = GiveMeADriver.create();
	}
}
```
 
