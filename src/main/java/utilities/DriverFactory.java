package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverFactory {
	
	public static IOSDriver driver;
	public static String currentProjectDir=System.getProperty("user.dir");

	public static AndroidDriver driverInitiation(String appName) throws IOException {
		// TODO Auto-generated method stub
		String currentProjectDir=System.getProperty("user.dir");
		FileInputStream fis=new FileInputStream(currentProjectDir+"\\RunConfig.properties");
		Properties prop=new Properties();
		prop.load(fis);
		//prop.get("appName");
		File appDir=new File("src");
		//"watherChannel.apk"
		File app=new File(appDir,(String) prop.get("appName"));
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability("noReset","true");
				apabilites.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.3");
		capabilites.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 12 Pro");
		//IOS_XCUI_Test     Apple 
		capabilites.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
		capabilites.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 500000);
		capabilites.setCapability("commandTimeouts", "12000"); 
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

		IOSDriver driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"),capabilites);
		return driver;
		
		//.app

		
		

	}
	public void getScreenshot(String testName, String passOrFail) throws IOException {
		
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile,new File(currentProjectDir+"\\Screenshots\\"+passOrFail+"\\"+testName+".png"));
	}


}
