package com.qaptain.reporting;

import com.qaptain.common.WebDriverSingleton;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.Base64;

public class CaptureScreen {

    /**
     * Take a Screenshot of current view port and return the Screenshot as a Base64 String
     *
     * @return Screenshot as Base 64 String
     */
    public static String getViewPortScreenshotAsBase64() {
        WebDriver aDriver = WebDriverSingleton.getInstance().getWebDriver();
        byte[] aSourceScreen = ((TakesScreenshot) aDriver).getScreenshotAs(OutputType.BYTES);
        return Base64.getEncoder().encodeToString(aSourceScreen);
    }
}
