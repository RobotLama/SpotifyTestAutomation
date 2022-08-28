package com.spotify.test.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import com.spotify.test.methods.Methods;

public class LoginPage {

    Methods methods;

    By loginUsernameLocator = By.id("login-username");
    By loginPasswordLocator = By.id("login-password");
    By loginButtonLocator = By.id("login-button");

    public LoginPage(){

        methods = new Methods();

    }

    // Check the Login Page elements are visible
    public void loginPageControl(){
        Assertions.assertTrue(methods.isElementVisible(loginUsernameLocator,20));
        Assertions.assertTrue(methods.isElementVisible(loginPasswordLocator,20));
        Assertions.assertTrue(methods.isElementVisible(loginButtonLocator,20));
    }

    // Positive login process
    public void loginPositive(String username, String password){
        methods.sendKeys(loginUsernameLocator,username);
        methods.sendKeys(loginPasswordLocator,password);
        Assertions.assertTrue(methods.isElementClickable(loginButtonLocator,20));
        methods.clickElement(loginButtonLocator);
    }



}
