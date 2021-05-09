package com.vehiclecheck.managers;

import com.vehiclecheck.pageojects.CarCheckHomePage;
import com.vehiclecheck.pageojects.FreeCarCheckPage;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    private WebDriver driver;
    private CarCheckHomePage carCheckHomePage;
    private FreeCarCheckPage freeCarCheckPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public CarCheckHomePage getCarCheckHomePage() {
        return (carCheckHomePage == null) ? carCheckHomePage = new CarCheckHomePage(driver) : carCheckHomePage;
    }

    public FreeCarCheckPage getFreeCarCheckPage() {
        return (freeCarCheckPage == null) ? freeCarCheckPage = new FreeCarCheckPage(driver) : freeCarCheckPage;
    }
}
