package com.buildinglink.mainapp.debug.qa;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.Random;

public class FDITypes {
    private static AppiumDriver<MobileElement> driver;

    public FDITypes(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    private By typesList = By.id("com.buildinglink.mainapp.debug.qa:id/typeName");

    public NewInstruction selectType(){
        int countAllTypes = driver.findElements(typesList).size();
        Random random = new Random();
        int getRandomType = random.nextInt(countAllTypes);
        driver.findElements(typesList).get(getRandomType).click();
        return new NewInstruction(driver);
    }

}
