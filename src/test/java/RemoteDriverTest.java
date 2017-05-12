import io.github.isgarlo.givemeadriver.GiveMeADriver;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RemoteDriverTest {

    @Test
    public void createARemoteDriverOnBrowserStack() {

        /*
        Setup capabilities via system properties using prefix 'capabilities.'
        For a full list of capabilities: https://www.browserstack.com/automate/capabilities
         */

        /*
        Remote url to connect to browserstack.
         */
        System.setProperty("capabilities.remote",
                "https://israellozano1:2YoWvHiymq4nh6khtbqJ@hub-cloud.browserstack.com/wd/hub");

        /*
        If browser and browserName are both defined, browser has precedence
        (except if browserName is either android, iphone, or ipad, in which cases
        browser is ignored and the default browser on those devices is selected).

        Default browser is chrome when no browser is passed by the user
        or the selenium API (implicitly).
         */
        System.setProperty("capabilities.browser", "chrome");

        /*
        If browser_version and version are both defined, browser_version has precedence.
         */
        System.setProperty("capabilities.browser_version", "57");

        /*
        If os and platform are both defined, os has precedence.
         */
        System.setProperty("capabilities.os", "OS X");

        /*
        Platform and os_version cannot be defined together, if os has not been defined.
        os_version can only be defined when os has been defined.
         */
        System.setProperty("capabilities.os_version", "Sierra");

        assertNotNull(GiveMeADriver.create());
    }
}
