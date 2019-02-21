package com.google.mail.automaton.Tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class BasicTest {

    WebDriver driver;


    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.google.com/intl/ru/gmail/about/");
    }

    @DataProvider(name = "forLogin")
    public Object[][]userData(){
        return new Object[][]{
                {"test.for.eis.group@gmail.com", "Test2019"}
        };
    }


    public void takeScreenShot(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat ("dd_hh_mm");
        String nameScreenShot = format.format(date)+".png";

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("\\ScreenShot\\"+nameScreenShot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @AfterClass
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}

