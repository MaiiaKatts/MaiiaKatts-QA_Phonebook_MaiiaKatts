package com.ait.phonebook;

import com.ait.phonebook.framework.DataProviders;
import com.ait.phonebook.models.Contact;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class AddNewContactTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
        app.getUser().clickOnLoginLink();
        app.getUser().login();
        app.getUser().pause(1000);
        app.getContact().clickOnAddLink();
    }

    @Test
    public void addNewContactPositiveTest() {
        app.getContact().fillContactForm(new Contact()
                .setName("Anna")
                .setSurname("Frodo")
                .setPhone("0987654321")
                .setEmail("franna@gmail.com")
                .setAddress("Berlin,Hauptstrasse 160")
                .setDesc("manager")
        );
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactAdded("Anna"));
    }
    @AfterMethod
    public void postCondition() {
        app.getContact().removeContact();
    }

    @Test(dataProvider = "addNewContact",
            dataProviderClass = DataProviders.class)
    public void addNewContactPositiveFromDataProviderTest(
            String name, String surname,
            String phone, String email,
            String address, String desc) {
        app.getContact().fillContactForm(new Contact()
                .setName(name)
                .setSurname(surname)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setDesc(desc)
        );
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactAdded(name));
    }

    @Test(dataProvider = "addNewContactFromCSV",
            dataProviderClass = DataProviders.class)
    public void addNewContactPositiveFromDataProviderWithCSVTest(
            Contact contact) {
        app.getContact().fillContactForm(contact);

        app.getContact().clickOnSaveButton();
        Assert.assertEquals(Integer.toString(app.getContact().sizeOfContacts()),"3");
    }
}
