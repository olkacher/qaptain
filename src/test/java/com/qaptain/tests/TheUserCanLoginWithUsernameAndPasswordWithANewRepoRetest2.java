package com.qaptain.tests;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class TheUserCanLoginWithUsernameAndPasswordWithANewRepoRetest2 {

    protected WebDriver driver = new ChromeDriver();
    
    @Test
    public void TheUserCanLoginWithUsernameAndPasswordWithANewRepoRetest2_test(){
        driver.get("https://www.saucedemo.com/");
    }
    
    @AfterTest
    public void teardown(){
        driver.quit();   
    }
}