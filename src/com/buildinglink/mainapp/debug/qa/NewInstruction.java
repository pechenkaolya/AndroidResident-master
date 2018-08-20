package com.buildinglink.mainapp.debug.qa;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.util.Date;
import java.util.List;

public class NewInstruction {
    private static AppiumDriver<MobileElement> driver;

    public NewInstruction(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    private By backButton = By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]");
    private By saveButton = By.id("com.buildinglink.mainapp.debug.qa:id/menu_item_save");
    private By instructionsField = By.id("com.buildinglink.mainapp.debug.qa:id/instruction");
    private By startDateField = By.id("com.buildinglink.mainapp.debug.qa:id/startDate");
    private By expiresField = By.id("com.buildinglink.mainapp.debug.qa:id/expires");
    private By error = By.id("android:id/message");
    private By okButton = By.id("android:id/button1");
    private By successMessage = By.id("com.buildinglink.mainapp.debug.qa:id/snackbar_text");
    private By saveWaiverButton = By.id("com.buildinglink.mainapp.debug.qa:id/menu_item_submit");
    private By waiverField = By.id("com.buildinglink.mainapp.debug.qa:id/waiverEditText");
    private By waiverCheckbox = By.id("com.buildinglink.mainapp.debug.qa:id/waiverCheckBox");

    public void tapSaveButton(){
        driver.findElement(saveButton).click();
    }

    public String getErrorMessage(){
        String getError = driver.findElement(error).getText();
        this.tapOkButton();
        driver.navigate().back();
        return getError;
    }

    public NewInstruction tapOkButton(){
        driver.findElement(okButton).click();
        return this;
    }

    public NewInstruction typeInstructions(String instructions){
        driver.findElement(instructionsField).sendKeys(instructions);
        driver.navigate().back();
        return this;
    }

    public String getSuccessMessage(){
        return driver.findElement(successMessage).getText();
    }

    private void tapSaveWaiverButton(){
        driver.findElement(saveWaiverButton).click();
    }

    private boolean checkIfWaiverPresents(){
        try{
            return driver.findElement(waiverField).isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    private boolean checkIfWaiverCheckboxPresents(){
        try{
            return driver.findElement(waiverCheckbox).isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public void acceptLiabilityWaiver(){
        if (this.checkIfWaiverPresents()== true){
            driver.findElement(waiverField).sendKeys("yes");
            this.tapSaveWaiverButton();
        }
        if (this.checkIfWaiverCheckboxPresents()== true){
            driver.findElement(waiverCheckbox).click();
            this.tapSaveWaiverButton();
        }
    }

}
