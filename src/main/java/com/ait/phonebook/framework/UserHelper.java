package com.ait.phonebook.framework;

import com.ait.phonebook.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper  extends HelperBase {
    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public void clickOnLoginLink() {
        click(By.xpath("//a[contains(.,'LOGIN')]"));
    }

    public void clickOnSignOutButton() {
        click(By.xpath("//button[contains(.,'Sign Out')]"));
    }

    public boolean isLoginLinkPresent() {
        return isElementPresent(By
                .xpath("//a[contains(.,'LOGIN')]"));
    }

    public void clickOnRegistrationButton() {
        click(By.name("registration"));
    }

    public void fillLoginRegisterForm(User user) {
        type(By.cssSelector("[placeholder='Email']"), user.getEmail());
        type(By.cssSelector("[placeholder='Password']"), user.getPassword());
    }

    public void fillLoginRegisterFormForScreenCast(User user) {
        type(By.cssSelector("[placeholder='Email']"), user.getEmail());
        type(By.cssSelector("[placeholder='Password']"), user.getPassword());
        pause(1000);
    }

    public boolean isSignOutButtonPresent() {
        return isElementPresent2(By
                .xpath("//button[contains(.,'Sign Out')]"));
    }

    public void clickOnLoginButton() {
        click(By.xpath("//button[text()='Login']"));
    }

    public void login() {
        fillLoginRegisterForm(new User()
                .setEmail("qwertyma@gmail.com")
                .setPassword("Qwertyma123$")
        );
        clickOnLoginButton();
    }
}
