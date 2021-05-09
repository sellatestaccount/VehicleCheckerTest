package com.vehiclecheck.pageojects;

import com.vehiclecheck.dto.CarDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FreeCarCheckPage {
    WebDriver driver;

    public FreeCarCheckPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private WebElement get_txt_vehicle_registration() {
        By by_xpath = By.xpath("//*[@id='m']/div[2]/div[5]/div[1]/div/span/div[2]/dl[1]/dd");
        waitForElementLocated(by_xpath);
        return driver.findElement(by_xpath);
    }

    private void waitForElementLocated(By by_xpath) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by_xpath));
    }

    private WebElement get_txt_vehicle_make() {
        By by_xpath = By.xpath("//*[@id='m']/div[2]/div[5]/div[1]/div/span/div[2]/dl[2]/dd");
        waitForElementLocated(by_xpath);
        return driver.findElement(by_xpath);
    }

    private WebElement get_txt_vehicle_model() {
        By by_xpath = By.xpath("//*[@id='m']/div[2]/div[5]/div[1]/div/span/div[2]/dl[3]/dd");
        waitForElementLocated(by_xpath);
        return driver.findElement(by_xpath);
    }

     private WebElement get_txt_vehicle_color() {
        By by_xpath = By.xpath("//*[@id='m']/div[2]/div[5]/div[1]/div/span/div[2]/dl[4]/dd");
        waitForElementLocated(by_xpath);
        return driver.findElement(by_xpath);
    }

     private WebElement get_txt_vehicle_year() {
        By by_xpath = By.xpath("//*[@id='m']/div[2]/div[5]/div[1]/div/span/div[2]/dl[5]/dd");
        waitForElementLocated(by_xpath);
        return driver.findElement(by_xpath);
    }

    private WebElement btn_check_another_vehicle() {
        By by_xpath = By.xpath("//*[@id='m']/div[2]/div[14]/div/div/a");
        waitForElementLocated(by_xpath);
        return driver.findElement(by_xpath);
    }

    public String getTxt_vehicle_registration() {
        return get_txt_vehicle_registration().getText();
    }

    public String getTxt_vehicle_make() {
        return get_txt_vehicle_make().getText();
    }

    public String getTxt_vehicle_model() {
        return get_txt_vehicle_model().getText();
    }

    public String getTxt_vehicle_color() {
        return get_txt_vehicle_color().getText();
    }

    public String getTxt_vehicle_year() {
        return get_txt_vehicle_year().getText();
    }

    public void click_btn_check_another_vehicle() {
        btn_check_another_vehicle().click();
    }

    public CarDTO get_vehicle_id_details() {
        return new CarDTO(
            getTxt_vehicle_registration(),
            getTxt_vehicle_make(),
            getTxt_vehicle_model(),
            getTxt_vehicle_color(),
            getTxt_vehicle_year()
        );
    }
}
