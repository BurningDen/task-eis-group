package com.google.mail.automaton.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {

    private final WebDriver driver;


    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    private static final By USER_NAME_LOCATOR = By.cssSelector("input[type='email']");
    private By buttonToFurther = By.xpath("//*[text()='Далее']");
    private By password = By.cssSelector("input[type = 'password']");


    public SignInPage login(String user) {
        driver.findElement(USER_NAME_LOCATOR).sendKeys(user);
        driver.findElement(buttonToFurther).click();
        return this;
    }

    public SignInPage password(String pass) {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(password));
        passwordElement.click();
        passwordElement.clear();
        passwordElement.sendKeys(pass);
        return this;
    }

    public EmailPage authorization() {
        driver.findElement(buttonToFurther).click();
        return new EmailPage(driver);
    }
}



