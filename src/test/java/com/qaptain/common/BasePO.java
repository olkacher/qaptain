package com.qaptain.common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePO {

    // WebDriver for all derived classes
    protected WebDriver driver = WebDriverSingleton.getInstance().getWebDriver();

    // === WebElements ===

    // === Constructor ===
    public BasePO() {
        BrowserSyncUtils.waitForJavaScript();
        PageFactory.initElements(driver, this);
    }

    // === Methods ===
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Executes JavaScript statement in Browser Instance.
     * Attention: This is not the same as user input.
     *
     * @param pScript     - script to be executed
     * @param pWebElement - WebElement on which the script is executed
     */
    protected void executeJavaScript(String pScript, WebElement pWebElement) {
        JavascriptExecutor aExecutor = (JavascriptExecutor) driver;
        aExecutor.executeScript(pScript, pWebElement);
    }

    /**
     * Solution for: could not be scrolled into view.
     *
     * @param pWebElement - WebElement.
     */
    protected void scrollIntoView(WebElement pWebElement) {
        BrowserSyncUtils.waitForVisibility(pWebElement);
        executeJavaScript("arguments[0].scrollIntoView(true);", pWebElement);
    }

    /**
     * Simulates a click using JavaScript
     *
     * @param pWebElement - WebElement to click on.
     */
    protected void clickElementWithJS(WebElement pWebElement) {
        scrollIntoView(pWebElement);
        executeJavaScript("arguments[0].click();", pWebElement);
    }
}
