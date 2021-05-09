package com.vehiclecheck.utils;

import com.vehiclecheck.enums.Browser;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyReader {
    private static Configuration config;
    private static final String ROOT_PATH = "src/test/resources/config/";
    private static final String TEST_CONFIG = "testConfig.properties";
    private static final Logger log = LoggerFactory.getLogger(PropertyReader.class);

    public static void loadTestConfigProperties() {
        try {
            config = new PropertiesConfiguration(ROOT_PATH + TEST_CONFIG);
        } catch (ConfigurationException ce) {
            ce.printStackTrace();
        }
    }

    public static String getInputDataFilePath() {
        loadTestConfigProperties();
        return config.getProperty("input.data").toString();
    }

    public static String getExpectedDataFilePath() {
        loadTestConfigProperties();
        return config.getProperty("expected.data").toString();
    }

    public static String getCarRegistrationPattern() {
        loadTestConfigProperties();
        return config.getProperty("car.registration.pattern").toString();
    }

    public static Browser getBrowserType() {
        loadTestConfigProperties();
        String browser = config.getProperty("browser").toString();
        if(browser.equals("chrome"))
            return Browser.CHROME;
        else
            throw new RuntimeException("Test is configured to support only chrome browser.");
    }

    public static String getBrowserDriverPath() {
        loadTestConfigProperties();
        return config.getProperty("chrome.driver.path").toString();
    }

    public static String getWindowMaximizeProperty() {
        loadTestConfigProperties();
        return config.getProperty("window.maximize").toString();
    }

    public static Long getImplicitWait() {
        loadTestConfigProperties();
        return Long.parseLong(config.getProperty("implicit.wait").toString());
    }

    public static String getCarCheckHomePageUrl() {
        loadTestConfigProperties();
        return config.getProperty("carcheck.url").toString();
    }
}
