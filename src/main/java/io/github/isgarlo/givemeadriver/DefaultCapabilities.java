package io.github.isgarlo.givemeadriver;

import static io.github.isgarlo.givemeadriver.CapabilitiesMapper.PREFIX;

class DefaultCapabilities {

    /**
     * URL of remote web driver in case of using Selenium Grid or any
     * service like BrowserStack or Saucelabs.
     * Can be configured either programmatically or by system property
     * "-Dcapabilities.remote=http://localhost:5678/wd/hub"
     * "-Dcapabilities.remote=https://USERNAME:USER_KEY@hub-cloud.browserstack.com/wd/hub"
     *
     * Default value: null. Local execution.
     */
    static String remote = System.getProperty(PREFIX.concat("remote"));

    /**
     * Which browser to use.
     * Can be configured either programmatically or by system property "-Dcapabilities.browser=ie" or "-Dbrowser=ie".
     * Supported values: "chrome", "firefox", "ie", "htmlunit", "phantomjs", "opera", "marionette"
     * <p/>
     * Default value: "chrome"
     */
    static String getBrowser() {
        return System.getProperty(PREFIX.concat("browser"),
                System.getProperty(PREFIX.concat("browserName"),
                        "chrome"));
    }

    /**
     * Which browser version to use (for Internet Explorer).
     * Can be configured either programmatically or by system property "-Dselenide.browserVersion=8" or "-Dbrowser.version=8".
     * <p/>
     * Default value: none
     */
    static String getDriverVersion() {
        return System.getProperty(PREFIX.concat("driverVersion"));
    }

    static boolean autoCloseBrowsers() {
        return Boolean.parseBoolean(System.getProperty(PREFIX.concat("autoclose"), "true"));
    }

}
