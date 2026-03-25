package com.qaptain.common;

import org.apache.commons.configuration2.Configuration;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class TestBase {

    // attribute names for text context
    protected final String ATTR_EXAMPLE = "example_attribute";

    @BeforeClass
    protected void setUp() {
        // get system properties or use execution properties
        Configuration aRemoteConfig = FileIO.loadConfig(GlobalConstants.EXECUTION_PROPERTIES_FILE);
        String aGivenDriverType = aRemoteConfig.getString("driver.type");
        String aGivenEnvironment = aRemoteConfig.getString("test.environment");
        String aDriverType = System.getProperty("driverType", aGivenDriverType);
        String aEnvironment = System.getProperty("testEnvironment", aGivenEnvironment);

        // set the specific driver
        WebDriverSingleton.getInstance().setWebDriver(aDriverType);
        WebDriver aDriver = WebDriverSingleton.getInstance().getWebDriver();

        // open the AUT
        aDriver.get(aEnvironment);
        BrowserSyncUtils.waitForJavaScript();

        // << accept cookies if necessary >>

        // write test infos to console
        System.out.println();
        System.out.println("Test properties - driver: " + aDriverType + " | env: " + aEnvironment);
    }

    @BeforeMethod
    protected void beforeMethod(ITestResult pResult) {
        String aTestDescription = pResult.getMethod().getDescription();
        System.out.println();
        System.out.println("=== Test started: " + aTestDescription + " ===");
        System.out.println();
    }

    @AfterMethod
    protected void afterMethod(ITestResult pResult) {
        String aTestDescription = pResult.getMethod().getDescription();
        System.out.println("*** Test finished: " + aTestDescription + " ***");
    }

    @AfterClass(alwaysRun = true)
    protected void tearDown() {
        WebDriverSingleton.getInstance().closeWebDriver();
    }
}