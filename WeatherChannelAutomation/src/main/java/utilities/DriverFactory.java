package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverFactory {

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
		String deviceName = (String) prop.get("device");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		//cap.setCapability(MobileCapabilityType.DEVICE_NAME, "prakashPixel4");
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

		AndroidDriver driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		return driver;

	}

}
