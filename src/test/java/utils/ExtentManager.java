package utils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	
	private static ExtentReports extent;
    private static ExtentTest test;
    static String homeFolder = System.getProperty("user.dir");
    
    public static ExtentReports getExtentReports() {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm");
		DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		File destFile1 = new File(
				homeFolder + "\\src\\test\\resources\\Reports\\" + dateFormat3.format(date));
		if (!destFile1.exists()) {
			destFile1.mkdir();
		}
		
        if (extent == null) {
            extent = new ExtentReports();
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter(destFile1+"\\" + dateFormat.format(date) + ".html");
            htmlReporter.config().setDocumentTitle("Test Results");
            htmlReporter.config().setReportName("Automation Report");
            htmlReporter.config().setTheme(Theme.STANDARD);
    		extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            extent.setSystemInfo("Tester", "Kamlesh Gupta");
        }
        return extent;
    }
    
    public static ExtentTest getTest() {
        return test;
    }
    
    public static void setTest(ExtentTest extentTest) {
        test = extentTest;
    }
}
