package io.github.isgarlo.givemeadriver.factories;

import java.util.EnumSet;

public enum DriverType {
    REMOTE,
    CHROME,
    FIREFOX,
    OPERA,
    SAFARI,
    PHANTOMJS,
    HTMLUNIT,
    IE,
    EDGE;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public static EnumSet<DriverType> local = EnumSet.of(CHROME, FIREFOX, OPERA, SAFARI, IE, EDGE, PHANTOMJS, HTMLUNIT);
}
