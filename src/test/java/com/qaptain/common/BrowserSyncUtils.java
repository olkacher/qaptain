package com.qaptain.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserSyncUtils {

    // default timeouts in seconds
    public static final int TIMEOUT_IMPLICIT_WAIT = 20;
    public static final int TIMEOUT_PAGE_LOAD = 30;
    public static final int TIMEOUT_WAITFORCLICKABLE = 30;
    public static final int TIMEOUT_WAITFORVISIBILITY = 30;
    public static final int TIMEOUT_WAITFORINVISIBILITY = 20;
    public static final int CHECK_VISIBILITY_DURATION = 2;

    // ===================================================================================
    // useful synchronization methods for different technologies'
    // explanation: https://www.swtestacademy.com/selenium-wait-javascript-angular-ajax/
    // ===================================================================================

    public static void waitForJavaScript() {
        WebDriver aDriver = WebDriverSingleton.getInstance().getWebDriver();
        WebDriverWait wait = new WebDriverWait(aDriver, Duration.ofSeconds(TIMEOUT_PAGE_LOAD));
        ExpectedCondition<Boolean> jsLoad = driver1 -> ((JavascriptExecutor) driver1).executeScript("return document.readyState").toString()
                .equals("complete");
        wait.until(jsLoad);
    }

    public static void waitForJQuery() {
        WebDriver aDriver = WebDriverSingleton.getInstance().getWebDriver();
        WebDriverWait wait = new WebDriverWait(aDriver, Duration.ofSeconds(TIMEOUT_PAGE_LOAD));
        ExpectedCondition<Boolean> jQeryLoad = driver1 -> {
            JavascriptExecutor js = (JavascriptExecutor) driver1;
            return (Boolean) js.executeScript("return !!window.jQuery && window.jQuery.active == 0");
        };
        wait.until(jQeryLoad);
    }

    public static void waitForAngularJs() {
        WebDriver aDriver = WebDriverSingleton.getInstance().getWebDriver();
        WebDriverWait wait = new WebDriverWait(aDriver, Duration.ofSeconds(TIMEOUT_PAGE_LOAD));
        ExpectedCondition<Boolean> angularLoad = pDriver1 -> Boolean.valueOf(((JavascriptExecutor) pDriver1).executeScript(
                        "return (window.angular !== undefined) && " +
                                "(angular.element(document).injector() !== undefined) && " +
                                "(angular.element(document).injector().get('$http').pendingRequests.length === 0)")
                .toString());
        wait.until(angularLoad);
    }

    // ====================================================================================================================================
    // other useful methods for web elements
    // explanation: https://stackoverflow.com/questions/44912203/selenium-web-driver-java-element-is-not-clickable-at-point-x-y-other-elem
    // ====================================================================================================================================

    /**
     * Solution for: Element is present in the DOM but not click able.
     */
    public static void waitForClickable(WebElement pWebElement) {
        WebDriver aDriver = WebDriverSingleton.getInstance().getWebDriver();
        WebDriverWait wait = new WebDriverWait(aDriver, Duration.ofSeconds(TIMEOUT_WAITFORCLICKABLE));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(pWebElement)));
    }

    /**
     * Solution for: Element is present but having temporary Overlay.
     *
     * @param pWebElement - WebElement
     */
    public static void waitForVisibility(WebElement pWebElement) {
        WebDriver aDriver = WebDriverSingleton.getInstance().getWebDriver();
        WebDriverWait wait = new WebDriverWait(aDriver, Duration.ofSeconds(TIMEOUT_WAITFORVISIBILITY));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(pWebElement)));
    }

    /**
     * Waits until the WebElement is no longer visible.
     * Catches the StaleElementReferenceException and NoSuchElementException.
     *
     * @param pLocator - By Locator des WebElements
     */
    public static void waitForInvisibility(By pLocator) {
        WebDriver aDriver = WebDriverSingleton.getInstance().getWebDriver();
        WebDriverWait wait = new WebDriverWait(aDriver, Duration.ofSeconds(TIMEOUT_WAITFORINVISIBILITY));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOfElementLocated(pLocator)));
    }

    /**
     * Checks whether a web element is displayed or not using the .isDisplayed() method.
     * Catches the StaleElementReferenceException, NoSuchElementException and NoSuchWindowException.
     * TimeOut 2 Seconds.
     *
     * @param pXpath - xpath from WebElement
     * @return true or false
     */
    public static boolean isElementVisible(String pXpath) {
        // reduce timeout
        WebDriver aDriver = WebDriverSingleton.getInstance().getWebDriver();
        aDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(CHECK_VISIBILITY_DURATION));
        boolean aIsVisible;

        try {
            aIsVisible = aDriver.findElement(By.xpath(pXpath)).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException
                 | org.openqa.selenium.NoSuchWindowException
                 | org.openqa.selenium.StaleElementReferenceException exp) {
            aIsVisible = false;
        }

        // rollback timeout
        aDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT_IMPLICIT_WAIT));
        return aIsVisible;
    }

    /**
     * Stop the current thread. In some cases, this method can contribute to stability or be helpful for your own synchronisation methods.
     * But be careful: frequent use is not the best practice.
     * <p>
     * See also: <a href="https://www.browserstack.com/guide/thread-sleep-in-selenium">thread-sleep-in-selenium</a>
     *
     * @param pSleepInMilliseconds - long
     */
    public static void threadSleeper(long pSleepInMilliseconds) {
        try {
            Thread.sleep(pSleepInMilliseconds);
        } catch (InterruptedException exp) {
            System.out.println(exp);
        }
    }
}