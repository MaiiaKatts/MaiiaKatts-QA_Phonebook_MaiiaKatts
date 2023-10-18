package com.ait.phonebook.framework;

import com.ait.phonebook.models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void clickOnAddLink() {
        click(By.cssSelector("a:nth-child(5)"));
    }

    public void clickOnSaveButton() {
        click(By.cssSelector(".add_form__2rsm2 button"));
    }

    public void fillContactForm(Contact contact) {
        type(By.cssSelector("input:nth-child(1)"), contact.getName());
        type(By.cssSelector("input:nth-child(2)"), contact.getSurname());
        type(By.cssSelector("input:nth-child(3)"), contact.getPhone());
        type(By.cssSelector("input:nth-child(4)"), contact.getEmail());
        type(By.cssSelector("input:nth-child(5)"), contact.getAddress());
        type(By.cssSelector("input:nth-child(6)"), contact.getDesc());
    }

    public boolean isContactAdded(String text) {
        List<WebElement> contacts = driver.findElements(By.cssSelector("h2"));
        for (WebElement element : contacts) {
            if (element.getText().contains(text))
                return true;
        }
        return false;
    }

    public void removeContact() {
        click(By.cssSelector(".contact-item_card__2SOIM"));
        // кликнуть на кнопку УДАЛИТЬ- button[.='Remove']
        click(By.xpath("//button[.='Remove']"));
        pause(1000);
    }

    //
    public int sizeOfContacts(){
        if (isElementPresent(By
                .cssSelector(".contact-item_card__2SOIM"))) {
            return driver.findElements(By
                            .cssSelector(".contact-item_card__2SOIM"))
                    .size();
        }
        return 0;
    }

    public void addContact() {
        fillContactForm(new Contact()
                .setName("Anna")
                .setSurname("Frodo")
                .setPhone("0987654321")
                .setEmail("frodanna@gmail.com")
                .setAddress("HuggWorld")
                .setDesc("Frodo"));
        clickOnSaveButton();
    }
}
