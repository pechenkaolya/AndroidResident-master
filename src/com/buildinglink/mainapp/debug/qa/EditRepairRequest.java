package com.buildinglink.mainapp.debug.qa;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import java.util.Random;

public class EditRepairRequest extends NewRepairRequest {

    public EditRepairRequest(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    private By statusField = By.id("com.buildinglink.mainapp.debug.qa:id/status");
    private By openStatus = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView");
    private By onHoldStatus = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView");
    private By closedStatus = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[3]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView");
    private By statusCheckedIndicator = By.id("com.buildinglink.mainapp.debug.qa:id/checkedIndicator");
    private By requestCategory = By.id(("com.buildinglink.mainapp.debug.qa:id/category"));

    private NewRepairRequest newRepairRequest = new NewRepairRequest(driver);

    public EditRepairRequest changeStatusToRandom() {
        this.getCurrentStatusInt();
        int statusIntToChange = this.getRandomStatusInt();
        driver.findElement(statusField).click();
        switch(statusIntToChange){
            case 0: driver.findElement(openStatus).click();
                break;
            case 1: driver.findElement(onHoldStatus).click();
                break;
            case 2: driver.findElement(closedStatus).click();
        }
        return this;
    }

    private int getCurrentStatusInt(){
        String currentStatus = driver.findElement(statusField).getText();
        int currentStatusInt = 0;
        switch(currentStatus){
            case "Open": currentStatusInt = 0;
                break;
            case "On Hold": currentStatusInt = 1;
                break;
            case "Closed": currentStatusInt = 2;
        }
        return currentStatusInt;
    }

    private int getRandomStatusInt(){
        Random random = new Random();
        int randomStatusInt = random.nextInt(3);
        if (getCurrentStatusInt()==randomStatusInt){
            if(getCurrentStatusInt()==0){
                randomStatusInt = 1;
            }
            if(getCurrentStatusInt()==1){
                randomStatusInt = 2;
            }
            if(getCurrentStatusInt()==2){
                randomStatusInt = 0;
            }
        }
        return randomStatusInt;
    }

    public void changeStatusToClosed(){
        driver.findElement(statusField).click();
        driver.findElement(closedStatus).click();
    }

    public void tapRequestCategoryField(){
        driver.findElement(requestCategory).click();
    }

    @Override
    public EditRepairRequest typeProblemDescription(String problemDescription)
    {
        driver.findElement(problemDescriptionField).clear();
        super.typeProblemDescription(problemDescription);
        return this;
    }

    @Override
    public EditRepairRequest typeEntryInstructions(String entryInstructions)
    {
        driver.findElement(entryInstructionsField).clear();
        super.typeEntryInstructions(entryInstructions);
        return this;
    }

    @Override
    public EditRepairRequest typeContactPhone(String contactPhone)
    {
        driver.findElement(contactPhoneField).clear();
        super.typeContactPhone(contactPhone);
        return this;
    }

    @Override
    public EditRepairRequest typeAdditionalEmail(String additionalEmail)
    {
        driver.findElement(additionalEmailField).clear();
        super.typeAdditionalEmail(additionalEmail);
        return this;
    }


}
