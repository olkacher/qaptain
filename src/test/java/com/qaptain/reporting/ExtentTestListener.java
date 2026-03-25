package com.qaptain.reporting;

import com.aventstack.extentreports.Status;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestListener implements ITestListener {

    @Override
    public synchronized void onStart(ITestContext pContext) {
    }

    @Override
    public synchronized void onFinish(ITestContext pContext) {
        ExtentTestManager.endTest();
        ExtentReportsManager.getInstance().flush();
    }

    @Override
    public synchronized void onTestStart(ITestResult pResult) {
        ExtentTestManager.createReportNode(pResult.getMethod().getDescription(), pResult.getMethod().getMethodName());
    }

    @Override
    public synchronized void onTestSuccess(ITestResult pResult) {
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public synchronized void onTestFailure(ITestResult pResult) {
        // on test failure add viewport screenshot to the report
        ExtentTestManager.getTest().log(Status.FAIL, ExceptionUtils.getStackTrace(pResult.getThrowable()));
        ReportHelper.addViewPortScreenshotToReport();
    }

    @Override
    public synchronized void onTestSkipped(ITestResult pResult) {
        ExtentTestManager.createReportNode(pResult.getMethod().getDescription(), pResult.getMethod().getMethodName());
        ExtentTestManager.getTest().log(Status.SKIP, "Test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult pResult) {
    }
}
