package com.google.mail.automaton.Tests;

import com.google.mail.automaton.Pages.EmailPage;
import com.google.mail.automaton.Pages.MainPage;
import com.google.mail.automaton.Pages.SignInPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


/**
 * Unit test for gmail
 */


public class GmailTest extends BasicTest {

    MainPage main;
    SignInPage signInPage;
    EmailPage email;

    @Test(groups = "Title")
    public void gmailTitleTest() {
        String titleMainPage = "Gmail – электронная почта и бесплатное хранилище от Google";
        assertTrue(driver.getTitle().equals(titleMainPage));
    }

    @Test(dataProvider = "forLogin", dataProviderClass = BasicTest.class, groups = "login")
    public void verifySignInTest(String log, String pass) {

        main = new MainPage(driver);
        signInPage = main.signIn();
        assertTrue(driver.getCurrentUrl().contains("https://accounts.google.com"));
        signInPage.login(log);
        signInPage.password(pass);
        email = signInPage.authorization();
        email.checkSignUpSuccess();
        assertTrue(driver.findElement(email.getButtonWrite()).isDisplayed());
        assertTrue(driver.getCurrentUrl().equals("https://mail.google.com/mail/#inbox"));
        assertTrue(driver.getTitle().contains("test.for.eis.group@gmail.com - Gmail"));
    }

    @Parameters({"log","pass"})
    @Test(groups = "FailureTest", enabled = false)
    public void LowCasePasswordSignInFailureTest(String log, String pass) {
        main = new MainPage(driver);
        signInPage = main.signIn();
        assertTrue(driver.getCurrentUrl().contains("https://accounts.google.com"));
        signInPage.login(log);
        signInPage.password(pass);
        email = signInPage.authorization();
        email.checkSignUpSuccess();
        assertTrue(driver.findElement(email.getButtonWrite()).isDisplayed());
        assertTrue(driver.getCurrentUrl().equals("https://mail.google.com/mail/#inbox"));
        assertTrue(driver.getTitle().contains("test.for.eis.group@gmail.com - Gmail"));
    }

    @Test(dependsOnMethods = "verifySignInTest",groups = "login")
    public void sendMessageTest(){

        email.message(" test.for.eis.group@gmlil.com","Eis group test","Please, delete me!");
        email.waitSend();
        assertTrue(driver.findElement(email.getMessageSent()).isDisplayed());
    }

    @Test(dependsOnGroups = "login", alwaysRun = true)
    public void deleteMessageTest(){
        email.delete();
        assertTrue(driver.findElement(email.getMessDel()).isDisplayed());
        takeScreenShot();
    }
}