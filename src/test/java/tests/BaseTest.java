package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.ExtentManager;

public class BaseTest {

	protected ExtentReports extent;
    protected ExtentTest test;
    
    @BeforeClass
    public void setUp() throws Exception {
        extent = ExtentManager.getExtentReports();
    }

    @AfterClass
    public void tearDown() throws Exception {
        extent.flush();
    }
}
