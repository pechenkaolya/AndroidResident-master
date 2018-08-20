package com.buildinglink.mainapp.debug.qa;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;
import java.util.Random;

import static com.buildinglink.mainapp.additionalClasses.RandomValueGenerator.generateRandomValue;

public class HomeScreen {

    private static AppiumDriver<MobileElement> driver;

    public HomeScreen(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    private By allowPushNotificationsAllert = By.id("android:id/message");
    private By refreshButton = By.id("com.buildinglink.mainapp.debug.qa:id/action_refresh");
    private By okButton = By.id("android:id/button1");
    private By cancelButton = By.id("android:id/button2");
    private By allUpcomingEvents = By.id("com.buildinglink.mainapp.debug.qa:id/agendaDescriptionView");
    private By homeIcon = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup[2]/android.widget.TextView[1]");
    private By lifestyleIcon = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup[2]/android.widget.TextView[2]");
    private By greenPlusButton = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup[2]/android.widget.ImageButton");
    private By contactsIcon = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup[2]/android.widget.TextView[3]");
    private By myProfileIcon = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup[2]/android.widget.TextView[4]");
    private By repairRequestsButton = MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.buildinglink.mainapp.debug.qa:id/moduleRecyclerView\")).scrollIntoView("
            + "new UiSelector().text(\"Repair Requests\"))");
    private By fDInstructionsButton = MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.buildinglink.mainapp.debug.qa:id/moduleRecyclerView\")).scrollIntoView("
            + "new UiSelector().text(\"Front Desk Instructions\"))");
    private By submitRepairRequestButton = By.id("com.buildinglink.mainapp.debug.qa:id/submitARepairRequest");
    private By postToBulletinBoardButton = By.id("com.buildinglink.mainapp.debug.qa:id/addBulletinBoardItem");
    private By submitFDIButton = By.id("com.buildinglink.mainapp.debug.qa:id/submitFdi");

    public HomeScreen tapOnOkButton(){
        WebDriverWait wait = new WebDriverWait(driver,120);
        wait.until(ExpectedConditions.elementToBeClickable(okButton));
        driver.findElement(okButton).click();
        return this;
    }

    public HomeScreen tapOnCancelButton(){
        WebDriverWait wait = new WebDriverWait(driver,120);
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
        driver.findElement(cancelButton).click();
        return this;
    }

    public HomeScreen tapRefreshButton(){
        driver.findElement(refreshButton).click();
        return this;
    }

    public EventCalendarScreen openUpcomingEvent(){
        int countAllEvents = driver.findElements(allUpcomingEvents).size();
        Random random = new Random();
        int getRandomEvent = random.nextInt(countAllEvents);
        driver.findElements(allUpcomingEvents).get(getRandomEvent).click();
        return new EventCalendarScreen(driver);
    }

    public HomeScreen tapHomeIcon(){
        driver.findElement(homeIcon).click();
        return this;
    }

    public HomeScreen tapLifestyleIcon(){
        driver.findElement(lifestyleIcon).click();
        return this;
    }

    public HomeScreen tapContactsButton(){
        driver.findElement(contactsIcon).click();
        return this;
    }

    public HomeScreen tapMyProfileButton(){
        driver.findElement(myProfileIcon).click();
        return this;
    }

    public HomeScreen tapGreenPlusButton(){
        driver.findElement(greenPlusButton).click();
        return this;
    }

    public RepairRequestCategories tapSubmitRepairRequestButton(){
        driver.findElement(submitRepairRequestButton).click();
        return new RepairRequestCategories(driver);
    }

    public FDITypes tapSubmitFDIButton(){
        driver.findElement(submitFDIButton).click();
        return new FDITypes(driver);
    }

    public PostingCategories tapPostToBulletinBoardButton(){
        driver.findElement(postToBulletinBoardButton).click();
        return new PostingCategories(driver);
    }

    public RepairRequestsScreen openRepairRequestsModule(){
        driver.findElement(repairRequestsButton).click();
        return new RepairRequestsScreen(driver);
    }

    public FrontDeskInstructionsScreen openFDInstructionsModule(){
        driver.findElement(fDInstructionsButton).click();
        return new FrontDeskInstructionsScreen(driver);
    }

}
