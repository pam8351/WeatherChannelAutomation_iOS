package utilities;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class UtilityFunctions {
	AndroidDriver driver;
	
	public UtilityFunctions(AndroidDriver driver) {
		this.driver=driver;
		
	}
	public WebElement scrollToParticularText(String text) {
		WebElement element=driver.findElement(AppiumBy.androidUIAutomator ("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\""+text+"\"));"));
		return element;
	}

}
