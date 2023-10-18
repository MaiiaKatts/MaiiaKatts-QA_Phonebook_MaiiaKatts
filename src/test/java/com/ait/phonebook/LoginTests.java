package com.ait.phonebook;

import com.ait.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()){
            app.getUser().clickOnSignOutButton();
        }
        app.getUser().clickOnLoginLink();
    }
    @Test
    public void loginPositiveTest() {
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail("qwertyma@gmail.com")
                .setPassword("Qwertyma123$"));
        app.getUser().clickOnLoginButton();
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    @Test
    public void loginPositiveTestWithScreenCast() throws Exception {
        app.getUser().deleteScreenCast("record");
        app.getUser().startRecording();

        app.getUser().fillLoginRegisterFormForScreenCast(new User()
                .setEmail("qwertyma@gmail.com")
                .setPassword("Qwertyma123$"));

        app.getUser().clickOnLoginButton();
        app.getUser().pause(2000);

        app.getUser().stopRecording();
    }
}
