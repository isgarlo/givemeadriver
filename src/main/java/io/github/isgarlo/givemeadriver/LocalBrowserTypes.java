package io.github.isgarlo.givemeadriver;

public enum LocalBrowserTypes {
    CHROME,
    FIREFOX,
    IE,
    SAFARI,
    OPERA,
    EDGE,
    PHANTOMJS;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

}
