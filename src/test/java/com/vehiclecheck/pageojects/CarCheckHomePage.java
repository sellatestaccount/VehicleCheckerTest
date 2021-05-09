package com.vehiclecheck.pageojects;

import com.vehiclecheck.utils.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CarCheckHomePage {
    WebDriver driver;

    public CarCheckHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "vrm-input")
    private WebElement txt_enterRegistration;

    @FindBy(how = How.XPATH, using = "//*[@id='m']/div[2]/div/div/div/div/form/button")
    private WebElement btn_freeCarCheck;

    public void setTxt_enterRegistration(String registration) {
        txt_enterRegistration.sendKeys(registration);
    }

    public void clickBtn_freeCarCheck() {
        btn_freeCarCheck.click();
    }

    public void navigate_to_homePage() {
        driver.get(PropertyReader.getCarCheckHomePageUrl());
    }

    public void do_free_car_check(String registration) {
        setTxt_enterRegistration(registration);
        clickBtn_freeCarCheck();
    }
}
