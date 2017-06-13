package io.github.isgarlo.givemeadriver.converter;


import io.github.isgarlo.givemeadriver.WebDriverProperties;

class PhantomJSCapabilitiesConverter extends LocalCapabilitiesConverter {
    @Override
    protected void addDriverSpecificCapabilities(WebDriverProperties properties) {
        capabilities.setCapability("phantomjs.cli.args",
                new String[]{"--web-security=no", "--ignore-ssl-errors=yes"});
    }
}
