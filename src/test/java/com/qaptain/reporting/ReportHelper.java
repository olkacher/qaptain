package com.qaptain.reporting;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.lang3.StringUtils;

/**
 * Helper class for the creation of the reports.
 */
public class ReportHelper {

    private ReportHelper() {
    }

    /**
     * Creates a table in the report.
     *
     * @param pInfos - Two dimensional Array
     */
    public static void createTable(String[][] pInfos) {
        if (pInfos != null && pInfos.length != 0) {
            ExtentTestManager.getTest().log(Status.INFO, MarkupHelper.createTable(pInfos));
        }
    }

    /**
     * Creates a simple Info entry in the Report.
     *
     * @param pInfoName   - Label der Info
     * @param pInfoDetail - Info
     */
    public static void createInfoLog(String pInfoName, String pInfoDetail) {
        if (!StringUtils.isEmpty(pInfoDetail)) {
            String aLogEintrag = pInfoName + " : " + pInfoDetail;
            ExtentTestManager.getTest().log(Status.INFO, aLogEintrag);
        }
    }

    /**
     * Creates a code block in the report.
     *
     * @param pBlockName     - Name of the Block
     * @param pBlockContents - Contents
     */
    public static void createCodeBlock(String pBlockName, String pBlockContents) {
        if (!StringUtils.isEmpty(pBlockContents)) {
            String aLogEintrag = "=== " + pBlockName + " ===\n\n" + pBlockContents;
            ExtentTestManager.getTest().log(Status.INFO, MarkupHelper.createCodeBlock(aLogEintrag));
        }
    }

    /**
     * Adds a screenshot of th current view port to the report.
     */
    public static void addViewPortScreenshotToReport() {
        String aViewPortScreenshot = CaptureScreen.getViewPortScreenshotAsBase64();
        addScreenshotToReport("View Port", aViewPortScreenshot);
    }

    private static void addScreenshotToReport(String pScreenshotName, String pBase64String) {
        String aHtmlImage =
                "<a href=\"data:image/png;base64," + pBase64String + "\" data-featherlight=\"image\"> " + "<img src=\"data:image/png;" +
                        "base64," + pBase64String + "\" width=\"50%\" height=\"45%\" id=\"" + pScreenshotName + "\" />" + "</a>";
        ExtentTestManager.getTest().log(Status.INFO, aHtmlImage);
    }
}
