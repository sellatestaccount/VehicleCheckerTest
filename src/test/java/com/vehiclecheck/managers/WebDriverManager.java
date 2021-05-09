package com.vehiclecheck.managers;

import com.vehiclecheck.enums.Browser;
import com.vehiclecheck.utils.PropertyReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverManager {
    private WebDriver driver;
    private static Browser browserType;
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

    public WebDriverManager() {
        browserType = PropertyReader.getBrowserType();
    }

    public WebDriver getDriver() {
        if(driver == null)
            driver = createDriver();
        return driver;
    }

    private WebDriver createDriver() {
        switch (browserType) {
            case CHROME:
                System.setProperty(CHROME_DRIVER_PROPERTY, PropertyReader.getBrowserDriverPath());
                driver = new ChromeDriver();
                break;
        }
        return driver;
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = driver ->
                ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState").toString().equals("complete");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(expectation);
    }

    public void closeDriver() {
        driver.close();
        driver.quit();
    }
}
