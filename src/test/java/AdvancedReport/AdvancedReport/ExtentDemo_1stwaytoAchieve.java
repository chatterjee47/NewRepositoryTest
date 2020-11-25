package AdvancedReport.AdvancedReport;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentDemo_1stwaytoAchieve {
	static ExtentTest test;
	static ExtentReports report;
	static WebDriver driver;
	

	@BeforeClass
	public static void startTest() {
		report = new ExtentReports(System.getProperty("user.dir") + "\\ExtentReportResults.html");
		test = report.startTest("ExtentDemo");
	}

	@Test
	public void extentReportsDemo() throws IOException {
		String driverPath = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver",driverPath);
		driver = new ChromeDriver();
		driver.get("https://www.google.co.in");
		if (driver.getTitle().equals("Google1")) {
			test.log(LogStatus.PASS, "Navigated to the specified URL");
		} else {
		//	test.log(LogStatus.FAIL, "Test Failed");
			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "Test Failed");
		}
	}

	@AfterClass
	public static void endTest() throws IOException {
		report.endTest(test);
		report.flush();
		driver.close();
	}
	
	
	public static String capture(WebDriver driver) throws IOException {
	File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	File Dest = new File("src/../screenshot/" + System.currentTimeMillis()+ ".png");
	String errflpath = Dest.getAbsolutePath();
	FileUtils.copyFile(scrFile, Dest);
	return errflpath;
	}
}
