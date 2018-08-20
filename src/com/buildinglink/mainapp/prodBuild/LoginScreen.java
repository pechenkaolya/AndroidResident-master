package com.buildinglink.mainapp.prodBuild;
import java.net.URL;
import java.time.Year;
import java.util.concurrent.TimeUnit;

import com.buildinglink.mainapp.additionalClasses.RandomValueGenerator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginScreen {
	private static AppiumDriver<MobileElement> driver;
	WebDriverWait wait = new WebDriverWait(driver,120);

	@BeforeClass public static void setUp() {  //set up desired capabilities
		DesiredCapabilities	caps = new	DesiredCapabilities();//To	create	an	object
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "00f1edb5378094e3"); //Android-057
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0.0"); //Android-057
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		//caps.setCapability(MobileCapabilityType.DEVICE_NAME, "QLF7N16128012393"); //Huawei
		//caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.buildinglink.mainapp");//To specify the android app package
		caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.buildinglink.mainapp.login.view.viewcontrollers.activities.SplashActivity");//To specify the	activity which we want to launch
		try {
			driver = new AppiumDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void openForgotPasswordLink() {
		driver.findElement(By.id("com.buildinglink.mainapp:id/forgotLogin")).click();
		String expectedURL = "https://buildinglink.com/v2/global/login/forgotpassword.aspx";
		String getOpenedLink = driver.findElementById("com.android.chrome:id/url_bar").getText();
		assertEquals(expectedURL, getOpenedLink);
		driver.navigate().back();
	}

	@Test
	public void openBuildingLinkPage() {
		MobileElement buildinglinkLink = driver.findElementById("com.buildinglink.mainapp:id/aboutButton");
		if (!buildinglinkLink.isDisplayed()){
			driver.launchApp();
		}

		buildinglinkLink.click();
		String expectedURL = "https://buildinglink.com";
		String getOpenedLink = driver.findElementById("com.android.chrome:id/url_bar").getText();
		assertTrue(getOpenedLink.contains(expectedURL));
		driver.navigate().back();
	}
	// do not work properly
	@Test
	public void openCommentsSuggestions() {
		driver.findElement(By.id("com.buildinglink.mainapp:id/commentsButton")).click();
		driver.navigate().back();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.navigate().back();
		boolean checkIfLoginScreenDisplayed = driver.findElementById("com.buildinglink.mainapp:id/userNameView").isEnabled();
		System.out.println(checkIfLoginScreenDisplayed);
		if (!checkIfLoginScreenDisplayed){
			driver.launchApp();
		}
	}

	@Test
	public void checkCurrentYear() {
		MobileElement copyrightValue = driver.findElement(By.id("com.buildinglink.mainapp:id/copyright"));// to get ©2018 BuildingLink.com
		int year = Year.now().getValue();
		String strYear = Integer.toString(year);
		assertTrue(copyrightValue.getText().contains(strYear));//check if current year is in copyrightValue string
	}

	@Test
	public void checkRememberMeSwitchedOff() {
		driver.findElement(By.id("com.buildinglink.mainapp:id/rememberMeCheckbox")).click();
		loginWithValidCredentials();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/alertTitle")));
		driver.closeApp();
		driver.launchApp();
	}

	@Test
	public void checkRememberMeSwitchedOn() {
		loginWithValidCredentials();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/alertTitle")));
		driver.closeApp();
		driver.launchApp();
	}

	@Test
	public void loginWithInvalidCredentials() {
		String username = RandomValueGenerator.generateRandomValue(10, "numString");
		String password = RandomValueGenerator.generateRandomValue(8, "numString");

		driver.findElementById("com.buildinglink.mainapp:id/userNameView").sendKeys(username);
		driver.findElementById("com.buildinglink.mainapp:id/passwordView").sendKeys(password);
		driver.navigate().back();
		driver.findElementById("com.buildinglink.mainapp:id/loginButton").click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
		driver.findElement(By.xpath("//*[@text = 'Username or password is incorrect']"));
		driver.findElementById("android:id/button1").click();
	}

	@Test
	public void loginAsFrontDeskStaff()
	{
		driver.findElementById("com.buildinglink.mainapp:id/userNameView").sendKeys("tfrontdesk1");
		driver.findElementById("com.buildinglink.mainapp:id/passwordView").sendKeys("testtest");
		driver.navigate().back();
		driver.findElementById("com.buildinglink.mainapp:id/loginButton").click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
		driver.findElement(By.xpath("//*[@text = 'This app is restricted to residents']"));
		driver.findElementById("android:id/button1").click();
	}

	@Test
	public void loginAsMaintenanceStaff()
	{
		driver.findElementById("com.buildinglink.mainapp:id/userNameView").sendKeys("blmaintenance");
		driver.findElementById("com.buildinglink.mainapp:id/passwordView").sendKeys("976ye");
		driver.navigate().back();
		driver.findElementById("com.buildinglink.mainapp:id/loginButton").click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
		driver.findElement(By.xpath("//*[@text = 'This app is restricted to residents']"));
		driver.findElementById("android:id/button1").click();
	}

	@Test
	public void loginAsCarValet()
	{
		driver.findElementById("com.buildinglink.mainapp:id/userNameView").sendKeys("tcarv1");
		driver.findElementById("com.buildinglink.mainapp:id/passwordView").sendKeys("testtest");
		driver.navigate().back();
		driver.findElementById("com.buildinglink.mainapp:id/loginButton").click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
		driver.findElement(By.xpath("//*[@text = 'This app is restricted to residents']"));
		driver.findElementById("android:id/button1").click();
	}

	public static void loginWithValidCredentials() {
		driver.findElementById("com.buildinglink.mainapp:id/userNameView").sendKeys("tuser2");
		driver.findElementById("com.buildinglink.mainapp:id/passwordView").sendKeys("tuser2");
		driver.navigate().back();
		driver.findElementById("com.buildinglink.mainapp:id/loginButton").click();
	}

	@AfterClass
	public static void close(){
		driver.closeApp();
	}

}
