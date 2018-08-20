package com.buildinglink.mainapp.debug.qa;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class FrontDeskInstructionsScreen {
    private static AppiumDriver<MobileElement> driver;

    public FrontDeskInstructionsScreen(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    private By addButton = By.id("com.buildinglink.mainapp.debug.qa:id/menu_item_add");
    private By allInstructions = By.className("android.view.ViewGroup");
    private By instructionDescription = MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView("
            + "new UiSelector().resourceId(\"com.buildinglink.mainapp.debug.qa:id/description\"))");
    private By editButton = By.id("com.buildinglink.mainapp.debug.qa:id/editButtonText");
    private By expireNowButton = By.id("com.buildinglink.mainapp.debug.qa:id/expireNowText");

    public void tapAddButton(){
        driver.findElement(addButton).click();
    }

    public void expandInstruction(){
        driver.findElement(instructionDescription).click();
    }

    public void tapEditButton(){
        driver.findElement(editButton).click();
    }

    public void tapExpireNowButton(){
        driver.findElement(expireNowButton).click();
    }





}
