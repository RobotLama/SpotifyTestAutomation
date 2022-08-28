package com.spotify.test.pages;

import com.spotify.test.driver.Driver;
import com.spotify.test.methods.Methods;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class GuestPage extends Driver {

    Methods methods;

    By loginButtonLocator = By.xpath("//button[@data-testid = 'login-button']");

    public GuestPage(){

        methods = new Methods();
    }

    // Spotify Homepage
    public void navSite(){
        driver.get("https://open.spotify.com/");
    }

    // Login button clicked
    public void loginButtonClicked(){

        Assertions.assertTrue(methods.isElementClickable(loginButtonLocator,20));
        methods.clickElement(loginButtonLocator);
    }

}
