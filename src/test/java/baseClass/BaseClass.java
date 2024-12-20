package baseClass;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;		// Log4j
import org.apache.logging.log4j.Logger;			// Log4j

public class BaseClass {
	
	// Frequently used items, such as login, logout etc.
	
public static WebDriver driver;
public Logger logger;		// 'logger' is object, Log4j
public Properties p;
	
	@SuppressWarnings("deprecation")
	@BeforeClass(groups={"Sanity","Master","Regression","Datadriven"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException
	{
		// Loading config.properties file
		// Location: C:\Users\Shreyas\eclipse-workspace\NewSelenium\HybridFW\src\test\resources\config.properties
		FileReader file = new FileReader("./src//test//resources//config.properties");									// './' represents current project
		p = new Properties();																// Creating object of 'properties' class
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());			// This will load log4j to xml file , and together it will load into 'logger' variable.
		
		
		//SELENIUM GRID
		
		//browser
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "chrome" : driver = new ChromeDriver(); break;
			case "edge" : driver = new EdgeDriver(); break;
			case "firefox" : driver = new FirefoxDriver(); break;
			default : System.out.println("Invalid browser name..."); return;		// 'return' will skip whole test
			}
		}
		
		//os
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))		// Incase of Remote 'DesiredCapabilities' class is needed
		{
			DesiredCapabilities cap = new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WIN11);
			}
			else if (os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				cap.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("No matching OS");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome" : cap.setBrowserName("chrome"); break;
			case "edge" : cap.setBrowserName("MicrosoftEdge"); break;				// "chrome", "MicrosoftEdge" should be entered as it is.
			case "firefox": cap.setBrowserName("firefox"); break;
			default : System.out.println("Invalid browser name..."); return;		// 'return' will skip whole test
			}
			
			
			// Generated by Selenium Grid for local host: 'http://192.168.29.5:4444'
			// 'http://localhost:4444' can be used instead.
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);	// '/wd/hub' to be added to localhost URL
			
		}
		
		
		/*
		 * Without Selenium Grid
		 * 
		switch(br.toLowerCase())
		{
		case "chrome" : driver = new ChromeDriver(); break;
		case "edge" : driver = new EdgeDriver(); break;
		case "firefox" : driver = new FirefoxDriver(); break;
		default : System.out.println("Invalid browser name..."); return;		// 'return' will skip whole test
		}
		
		*/
	
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		driver.get(p.getProperty("appURL"));				// Value/URL to be fetched from config.properties file
		driver.manage().window().maximize();
	}
	
	
	@AfterClass(groups={"Sanity","Master","Datadriven"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}

}
