package Appium.WeatherChannelAutomation;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import pageUiObjects.HourlyForecastUI;
import pageUiObjects.LaunchPageUI;
import utilities.DriverFactory;
import utilities.UtilityFunctions;
import org.openqa.selenium.*;
import io.appium.java_client.*;
import io.appium.java_client.android.*;

public class runnerClass extends DriverFactory {
	protected  static AndroidDriver driver;
	public static LaunchPageUI launchPage;
	public static WebDriverWait wait;
	public static Properties prop;
	@SuppressWarnings("deprecation")
	@BeforeSuite
	public static void setUp_Mobile(String deviceName, String platformVersion, String platformName, String appPackage, String appActivity) throws IOException
	{
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.BROWSER_NAME, "");
		cap.setCapability("deviceName", deviceName);
		cap.setCapability("platformVersion", platformVersion);
		cap.setCapability("platformName", platformName);
		cap.setCapability("appPackage", appPackage);
		cap.setCapability("appActivity", appActivity);
		
		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), cap);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 driver=driverInitiation("watherChannel.apk");
		try {
			launchPage=new LaunchPageUI(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@BeforeSuite
	public Properties getPropValue() throws Exception{
	
			FileReader reader = new FileReader(System.getProperty("user.dir")+"config.properties");
			prop = new Properties();
			prop.load(reader);
			return prop;
			
		
	}
	
	public static  void clickOperation(String value) throws Exception {
		 System.out.println("Enter the channel App");
		 try {
			wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.weather.Weather:id/search_icon")));
		Thread.sleep(3000);
		driver.findElement(By.id("")).click();
		
		
	}
	
	public static void sendOperation(String value) {
		try {
			launchPage.searchText.sendKeys(value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void takeScreenshot(String fileName) throws IOException {
		String path = System.getProperty("user.dir")+ fileName+".bmp";
		Process process = Runtime.getRuntime().exec(String.format(path + " " +System.getProperty("user.dir")+"\\src\\Screenshots"));
	}

	
	}
	

