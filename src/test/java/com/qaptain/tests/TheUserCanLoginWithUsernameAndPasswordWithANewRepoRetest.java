package com.qaptain.tests;

import java.time.Duration;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qaptain.pageobjects.SaucedemoComPageObject;

public class TheUserCanLoginWithUsernameAndPasswordWithANewRepoRetest {

    protected WebDriver driver = new ChromeDriver();

    @Test
    public void TheUserCanLoginWithUsernameAndPasswordWithANewRepoRetest_test() {
        driver.get("https://www.saucedemo.com/");
        SaucedemoComPageObject saucedemoPO = new SaucedemoComPageObject(driver);
        saucedemoPO.fillUserName("standard_user");

        saucedemoPO.enterPassword("secret_sauce");
    }

    @AfterTest
    public void teardown() {
        driver.quit();   
    }
}