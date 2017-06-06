package io.github.isgarlo.givemeadriver;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class GiveMeADriverTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        System.setProperty("capabilities.browserName", "htmlunit");
        System.setProperty("capabilities.autoclose", "false");
        System.setProperty("capabilities.browserSize", "1024x768");
    }

    @Test
    public void createHtmlUnitDriver() {
        WebDriver driver = GiveMeADriver.create();
        assertThat(driver).isInstanceOf(HtmlUnitDriver.class);
        assertThat(GiveMeADriver.current()).isEqualTo(driver);
        GiveMeADriver.close();
        exception.expect(IllegalStateException.class);
        GiveMeADriver.current();

    }

    @After
    public void tearDown() {
        System.clearProperty("capabilities.browserName");
        System.clearProperty("capabilities.autoclose");
        System.clearProperty("capabilities.browserSize");
    }
}