package com.google.mail.automaton.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class EmailPage {

    private final WebDriver driver;
    Actions builder;


    private By buttonWrite = By.xpath("//div[@class = 'z0']/div");
    private By messageSent = By.xpath("//*[@id= 'link_vsm']");
    private By check = By.xpath("//*[@id=\":2w\"]/tbody");
    private By buttonDelete = By.xpath("//*[@id=\":4\"]/div[2]/div[1]/div/div[2]/div[3]");
    private By messDel = By.xpath("/html/body/div[7]/div[3]/div/div[1]/div[5]/div[1]/div/div[3]/div/div/div[2]");

    public By getButtonWrite() {
        return buttonWrite;
    }

    public EmailPage(WebDriver driver) {
        this.driver = driver;

    }

    public EmailPage checkSignUpSuccess() {
//        WebDriverWait wait = new WebDriverWait(driver, 20,200);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonWrite));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return this;
    }





    public EmailPage message(String toWhom, String mess,String subject){

        builder = new Actions(driver);
        builder.click(driver.findElement(buttonWrite)).sendKeys(toWhom)
                .sendKeys(Keys.ENTER).sendKeys(Keys.TAB).sendKeys(mess).sendKeys(Keys.TAB)
                .sendKeys(subject).sendKeys(Keys.chord(Keys.CONTROL,Keys.ENTER)).build().perform();
        return this;
    }

    public EmailPage waitSend(){
        WebDriverWait wait = new WebDriverWait(driver, 20,200);
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageSent));
        return this;
    }

    public By getMessageSent() {
        return messageSent;
    }

    public EmailPage delete(){

        builder = new Actions(driver);
        List<WebElement> checkbox = driver.findElements(check);
        new WebDriverWait(driver,22).until(ExpectedConditions.presenceOfElementLocated(check));
        checkbox.get(0).click();
        WebElement deleteElement = driver.findElement(buttonDelete);
        deleteElement.click();
        return this;
        }

    public By getMessDel() {
        return messDel;
    }
}





