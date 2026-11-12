package swagbase_pkg;

import java.io.File;
import java.lang.reflect.Method;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import swagpage_pkg.Swag_loginpage;


public class Swag_base {
	
public static WebDriver driver;
	
	public  Swag_loginpage obj;
	
	public static ExtentSparkReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	@BeforeTest
	public void setup()
	{
		reporter = new ExtentSparkReporter("./Reports/myreport1.html"); //create a report
		reporter.config().setDocumentTitle("Automationreport"); //to set title
		reporter.config().setReportName("functional test"); //to set report name
		reporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		extent.setSystemInfo("Hostname", "localhost");
		extent.setSystemInfo("OS", "window11");
		extent.setSystemInfo("Tester Name", "Parvathy");
		extent.setSystemInfo("Browser Name", "chrome");

		ChromeOptions options = new ChromeOptions(); //--
		options.addArguments("--incognito"); //--
		driver=new ChromeDriver(options);   //-- to open site in incognito mode
		
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void mthd(Method method)
	{
		obj = new Swag_loginpage();
	}
	
	
	@AfterMethod
	public void aftmthd(ITestResult result)
	{
		if(result.getStatus() == ITestResult.FAILURE) //to retrieve results(pass or fail) from testmthd
		{
			test.log(Status.FAIL,"test case failed is " +result.getName());
			test.log(Status.FAIL,"test case failed is " +result.getThrowable());
     	}
		else if(result.getStatus() == ITestResult.SUCCESS)
        {
			test.pass("Test Passed: " + result.getName());
		}
		else if(result.getStatus() ==ITestResult.SKIP)
		{
			test.skip("Test Skipped: " + result.getName());
		}
	}
	

	public String screenshotMethod(WebDriver driver, String name) throws Exception
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir")+"./screenshot/"+name+".png";
		// String destination = "./screenshot/"+name+".png";
		FileHandler.copy(src, new File(destination));
		
		return destination;
	}

	
	@AfterTest
	public void endReport() {
		extent.flush();
		driver.quit();
	}
	
}