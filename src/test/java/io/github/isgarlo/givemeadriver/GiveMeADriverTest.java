package io.github.isgarlo.givemeadriver;


import net.jcip.annotations.NotThreadSafe;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

@NotThreadSafe
public class GiveMeADriverTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void createHtmlUnitDriver() {
        System.setProperty("capabilities.browserName", "htmlunit");
        System.setProperty("capabilities.autoclose", "false");

        WebDriver driver = GiveMeADriver.create();
        assertThat(GiveMeADriver.current()).isEqualTo(driver);
        GiveMeADriver.close();
        exception.expect(IllegalStateException.class);
        GiveMeADriver.current();

    }
}