package com.qaptain.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qaptain.common.GlobalConstants;

public class ExtentReportsManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    public synchronized static void createInstance() {
        // property for ignore warnings
        System.setProperty("org.freemarker.loggerLibrary", "none");

        // new Spark Reporter
        ExtentSparkReporter aSparkReporter = new ExtentSparkReporter(GlobalConstants.TEST_REPORT_FILE);

        // add Reporter to extent reports and also set some configuration
        extent = new ExtentReports();
        extent.attachReporter(aSparkReporter);
        aSparkReporter.config().setReportName("Test Report");
    }
}