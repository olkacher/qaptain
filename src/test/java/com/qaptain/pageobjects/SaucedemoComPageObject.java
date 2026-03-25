package com.qaptain.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SaucedemoComPageObject {
    protected WebDriver driver;

    // Objects
    @FindBy(xpath = "//*[@id='user-name']")
    protected WebElement userNameField;

    @FindBy(xpath = "//*[@id='password']")
    protected WebElement passwordField;

    public SaucedemoComPageObject(WebDriver pDriver) {
        driver = pDriver;
        PageFactory.initElements(driver, this);
    }

    // Methods
    /**
     * Enters the specified username into the user name field.
     *
     * @param value the username to enter (e.g., "standard_user")
     */
    public void fillUserName(String value) {
        userNameField.sendKeys(value);
    }

    /**
     * Enters the specified password into the password field.
     *
     * @param value the password to enter (e.g., "secret_sauce")
     */
    public void enterPassword(String value) {
        passwordField.sendKeys(value);
    }
}