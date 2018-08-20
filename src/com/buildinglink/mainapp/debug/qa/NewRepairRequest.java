package com.buildinglink.mainapp.debug.qa;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class NewRepairRequest {
    protected static AppiumDriver<MobileElement> driver;
    public NewRepairRequest(){};
    public NewRepairRequest(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    protected By backButton = By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]");
    protected By saveButton = By.id("com.buildinglink.mainapp.debug.qa:id/menu_save");
    protected By problemDescriptionField = By.id("com.buildinglink.mainapp.debug.qa:id/requestDescription");
    //protected By entryInstructionsField = By.id("com.buildinglink.mainapp.debug.qa:id/entryInstructions");
    protected By entryInstructionsField = MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.view.ViewGroup\")).scrollIntoView("
            + "new UiSelector().resourceId(\"com.buildinglink.mainapp.debug.qa:id/entryInstructions\"))");
    protected By contactPhoneField = MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.view.ViewGroup\")).scrollIntoView("
            + "new UiSelector().resourceId(\"com.buildinglink.mainapp.debug.qa:id/phone\"))");
    protected By additionalEmailField = By.id("com.buildinglink.mainapp.debug.qa:id/email");
    protected By error = By.id("android:id/message");
    protected By okButton = By.id("android:id/button1");
    protected By successMessage = By.id("com.buildinglink.mainapp.debug.qa:id/snackbar_text");
    protected By saveWaiverButton = By.id("com.buildinglink.mainapp.debug.qa:id/menu_item_submit");
    protected By waiverField = By.id("com.buildinglink.mainapp.debug.qa:id/waiverEditText");
    protected By waiverCheckbox = By.id("com.buildinglink.mainapp.debug.qa:id/waiverCheckBox");

    public void tapSaveButton(){
        driver.findElement(saveButton).click();
    }

    public String getErrorMessage(){
        String getError = driver.findElement(error).getText();
        this.tapOkButton();
        driver.navigate().back();
        return getError;
    }

    private NewRepairRequest tapOkButton(){
        driver.findElement(okButton).click();
        return this;
    }

    public NewRepairRequest typeProblemDescription(String problemDescription) {
        driver.findElement(problemDescriptionField).sendKeys(problemDescription);
        driver.navigate().back();
        return this;
    }

    public NewRepairRequest typeEntryInstructions(String entryInstructions) {
        driver.findElement(entryInstructionsField).sendKeys(entryInstructions);
        driver.navigate().back();
        return this;
    }

    public NewRepairRequest typeContactPhone(String contactPhone) {
        driver.findElement(contactPhoneField).clear();
        driver.findElement(contactPhoneField).sendKeys(contactPhone);
        driver.navigate().back();
        return this;
    }

    public NewRepairRequest typeAdditionalEmail(String additionalEmail) {
        driver.findElement(additionalEmailField).clear();
        driver.findElement(additionalEmailField).sendKeys(additionalEmail);
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
        if (this.checkIfWaiverPresents()){
            driver.findElement(waiverField).sendKeys("yes");
            this.tapSaveWaiverButton();
        }
        if (this.checkIfWaiverCheckboxPresents()){
            driver.findElement(waiverCheckbox).click();
            this.tapSaveWaiverButton();
        }
    }

}
