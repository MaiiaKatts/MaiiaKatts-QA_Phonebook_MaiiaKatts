package com.ait.phonebook.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageHelper extends HelperBase {
    public HomePageHelper(WebDriver driver) {
        super(driver);
    }

    public boolean isHomeComponentPresent() {
        // css: div:nth-child(2)>div>div
        // name: h1[contains(.,'Home Component')]
        return driver.findElements(By.
                xpath("//div[2]/div/div"))
                .size()>0;
    }
}
