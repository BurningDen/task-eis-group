package com.google.mail.automaton.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {


    private final WebDriver driver;

    private By buttonSignIn = By.cssSelector("a.gmail-nav__nav-link.gmail-nav__nav-link__sign-in");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public SignInPage signIn(){
        WebElement signInButton = driver.findElement(buttonSignIn);
        signInButton.click();
        return new SignInPage(driver);
    }
}





