package Scripts;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Appium.WeatherChannelAutomation.runnerClass;
import io.appium.java_client.AppiumBy;
import pageUiObjects.HourlyForecastUI;
import utilities.UtilityFunctions;

public class lauchApp extends runnerClass{
	static HourlyForecastUI hourly;
	@Test(priority=1)
	public static  void validate_user_LookUp_Location_For_Weather_CheckUp()throws Exception {
		
		clickOperation(prop.getProperty("dom"));
		sendOperation(prop.getProperty("val"));
		takeScreenshot("santaCruiseArea");
		
		List<WebElement> resultSet=launchPage.searchResultSet;
		try {
			for(int i=0;i<resultSet.size();i++) {
				if(resultSet.get(i).getText().equalsIgnoreCase(prop.getProperty("val1"))) {
				

					launchPage.searchResultSet.get(i).click();
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
		@Test(priority=2)
		public void checkHourlyForecast() throws Exception {
		
		hourly=new HourlyForecastUI(driver);
		
		
		System.out.println(hourly.hourlyForecastContainer.isDisplayed());
		//System.out.println(resultSet.get(0).getText());com.weather.Weather:id/hourly_forecast_adapter_column
		List<WebElement> hourlyList=driver.findElements(By.id("com.weather.Weather:id/hourly_forecast_adapter_column"));
		Assert.assertEquals(hourlyList.size(),5);
		Thread.sleep(2000);
		UtilityFunctions utils=new UtilityFunctions(driver);
		WebElement detailsButton=utils.scrollToParticularText("See Details");
				//driver.findElement(AppiumBy.androidUIAutomator ("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"See Details\"));"));
		Thread.sleep(2000);
		System.out.println("See details button displayed ,"+driver.findElement(By.xpath("//android.widget.Button[@text='See Details']")).isDisplayed());
		WebElement Today_Details=utils.scrollToParticularText("UV Index");
		takeScreenshot("ButtonDetails");
				//driver.findElement(AppiumBy.androidUIAutomator ("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Today's Details\"));"));

		List<WebElement> todaysDetails=driver.findElements(By.className("android.widget.TextView"));
		System.out.println(todaysDetails.size());

		String name=driver.findElement(By.xpath("//android.widget.TextView[@text='Wind']")).getAttribute("text");

		System.out.println("Name "+name);

		WebElement wind=driver.findElement(By.id("com.weather.Weather:id/wind_desc"));
		if(wind.getText().length()>1){
			System.out.println("Wind details  are displayed "+wind.getText());
		}
		WebElement moreDetails=driver.findElement(AppiumBy.androidUIAutomator ("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"More Details\"));"));
		//WebElement moreDetails=driver.findElement(By.xpath("//android.widget.Button[@text='More Details']"));
		takeScreenshot("MoreDetails");
		moreDetails.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='Feels like']")).click();
		//driver.switchTo().frame(driver.findElement(By.className("android.widget.RelativeLayout")));
		List<WebElement> temp_text=driver.findElements(By.id("com.weather.Weather:id/temp_text"));
		System.out.println(temp_text.size());
		System.out.println(verifyTrend(temp_text));

	}

		@Test(priority=3)
	private static boolean verifyTrend(List<WebElement> list) {
		// TODO Auto-generated method stub
		if(list.size()>=6) {
			System.out.println(list.size());
			for(int i=0;i<list.size();i++) {
				String unit=list.get(i).getText();
				System.out.println(unit);
				if(unit.length()<=1) {
					//System.out.println("inside the loop");
					return false;

				}
			}
		}
		return true;
	}

}
