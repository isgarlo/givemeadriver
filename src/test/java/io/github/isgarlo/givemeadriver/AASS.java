package io.github.isgarlo.givemeadriver;

/**
 * Created by israel on 24/05/2017.
 */
public class AASS {

    public static void main(String[] args) {
        System.setProperty("capabilities.userAgent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) CriOS/56.0.2924.75 Mobile/14E5239e Safari/602.1");
        System.setProperty("capabilities.viewportSize", "700x600");
        GiveMeADriver.create();
    }
}
