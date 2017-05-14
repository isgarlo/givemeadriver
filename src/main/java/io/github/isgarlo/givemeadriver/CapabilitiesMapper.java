package io.github.isgarlo.givemeadriver;


import com.google.common.collect.Maps;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Properties;

import static org.apache.logging.log4j.util.PropertiesUtil.extractSubset;

class CapabilitiesMapper {

    static final String PREFIX = "capabilities.";

    static DesiredCapabilities mapFromSystemProperties() {
        Properties browserSystemProperties = extractSubset(PropertiesUtil.getSystemProperties(), PREFIX);
        return new DesiredCapabilities(Maps.fromProperties(browserSystemProperties));
    }

    static void setToSystemProperties(String capability, String value) {
        if(!capability.startsWith(PREFIX)) {
            capability = PREFIX.concat(capability);
        }
        System.setProperty(capability, value);
    }

}
