package com.ait.phonebook;

import com.ait.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateNewUserTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition(){
        if (!app.getUser().isLoginLinkPresent()){
            app.getUser().clickOnSignOutButton();
        }
        app.getUser().clickOnLoginLink();
    }

    @Test
    public void createExistedUserNegativeTest() {
        //Так проходит процесс регистрации:
        //    enter email => [placeholder='Email'] - css
        //    enter password => [placeholder='Password'] - css
        //    click on REGISTRATION button => registration - name
        //    assert Sign out button present - //button[contains(.,'Sign Out')] - xpath
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail("qwertyma@gmail.com")
                .setPassword("Qwertyma123$"));
        app.getUser().clickOnRegistrationButton();
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

}