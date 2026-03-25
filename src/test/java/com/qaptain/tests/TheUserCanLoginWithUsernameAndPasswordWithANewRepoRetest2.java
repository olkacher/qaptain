package com.qaptain.tests;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.qaptain.pageobjects.SaucedemoComPageObject;

public class TheUserCanLoginWithUsernameAndPasswordWithANewRepoRetest2 {

    protected WebDriver driver = new ChromeDriver();
    
    @Test
    public void TheUserCanLoginWithUsernameAndPasswordWithANewRepoRetest2_test(){
        driver.get("https://www.saucedemo.com/");
        
        SaucedemoComPageObject saucedemoPage = new SaucedemoComPageObject(driver);
        saucedemoPage.enterUsername("standard_user");
        saucedemoPage.enterPassword("secret_sauce");
    }
    
    @AfterTest
    public void teardown(){
        driver.quit();   
    }
}