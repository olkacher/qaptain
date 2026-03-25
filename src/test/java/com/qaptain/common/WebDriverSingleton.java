package com.qaptain.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.configuration2.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * WebDriver Singleton
 * <p>
 * Example: To get access to browser use
 * <pre>{@code
 * 		WebDriverSingleton.getInstance().setWebDriver("CHROME");}</pre>
 */
public class WebDriverSingleton {

    ////////////////////////////////////////
    // WebDriverSingleton instance
    ////////////////////////////////////////

    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private static WebDriverSingleton instance = null;

    /**
     * Don't allow public constructor.
     */
    public WebDriverSingleton() {
    }

    /**
     * Create single instance for WebDriver access.
     *
     * @return instance as singleton
     */
    public static synchronized WebDriverSingleton getInstance() {
        if (instance == null) {
            instance = new WebDriverSingleton();
        }

        return instance;
    }

    ////////////////////////////////////////
    //  Browser / WebDriver access
    ////////////////////////////////////////

    /**
     * Get access to currently used WebDriver to be used in Selenium tests.
     *
     * @return WebDriver class
     */
    public synchronized WebDriver getWebDriver() {
        return webDriver.get();
    }

    /**
     * Set a new WebDriver.
     * If no valid value is given the fallback is Google Chrome.
     *
     * @param pBrowserName - examples:
     *                     <ul>
     *                     	<li>CHROME</li>
     *                     	<li>FIREFOX</li>
     *                     	<li>CHROME-REMOTE</li>
     *                     	<li>FIREFOX-REMOTE</li>
     *                     </ul>
     */
    public final synchronized void setWebDriver(String pBrowserName) {
        WebDriver aDriver;
        switch (pBrowserName.toUpperCase()) {
            case "CHROME":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions localChromeOptions = new ChromeOptions();
                localChromeOptions.addArguments("--remote-allow-origins=*");
                localChromeOptions.addArguments("--disable-search-engine-choice-screen");
                aDriver = new ChromeDriver(localChromeOptions);
                break;

            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions localFirefoxOptions = new FirefoxOptions();
                aDriver = new FirefoxDriver(localFirefoxOptions);
                break;

            case "CHROME-REMOTE":
                ChromeOptions remoteChromeOptions = new ChromeOptions();
                remoteChromeOptions.setCapability("browserName", "chrome");
                remoteChromeOptions.addArguments("--disable-dev-shm-usage");
                remoteChromeOptions.addArguments("--no-sandbox");
                remoteChromeOptions.addArguments("--disable-search-engine-choice-screen");
                aDriver = new RemoteWebDriver(getRemoteHubUrl(), remoteChromeOptions);
                break;

            case "FIREFOX-REMOTE":
                FirefoxOptions remoteFirefoxOptions = new FirefoxOptions();
                remoteFirefoxOptions.setCapability("browserName", "firefox");
                aDriver = new RemoteWebDriver(getRemoteHubUrl(), remoteFirefoxOptions);
                break;
        }

        // maximize browser window
        aDriver.manage().window().maximize();

        // set implicit wait for finding web elements
        aDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(BrowserSyncUtils.TIMEOUT_IMPLICIT_WAIT));

        // set page load timeout
        aDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(BrowserSyncUtils.TIMEOUT_PAGE_LOAD));

        // delete all cookies
        aDriver.manage().deleteAllCookies();

        // set WebDriver to local thread
        webDriver.set(aDriver);
    }

    /**
     * Destroy instance of WebDriver instance.
     */
    public synchronized void closeWebDriver() {
        if (hasWebDriver()) {
            getWebDriver().quit();
            webDriver.set(null);
        }
    }

    /**
     * Check if browser specific WebDriver has been created for used browser.
     *
     * @return TRUE if WebDriver has been created, otherwise FALSE
     */
    public synchronized boolean hasWebDriver() {
        return (getWebDriver() != null);
    }

    /**
     * Return the URL of Selenium Hub.
     *
     * @return URL of Selenium Hub
     */
    private URL getRemoteHubUrl() {
        try {
            Configuration aRemoteConfig = FileIO.loadConfig(GlobalConstants.EXECUTION_PROPERTIES_FILE);
            String aGivenURL = aRemoteConfig.getString("selenium.hub.url");
            return new URL(aGivenURL);
        } catch (MalformedURLException ex) {
            System.out.println(ex);
        }
        return null;
    }
}