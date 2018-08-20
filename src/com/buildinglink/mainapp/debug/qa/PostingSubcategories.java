package com.buildinglink.mainapp.debug.qa;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.Random;

public class PostingSubcategories {
    private static AppiumDriver<MobileElement> driver;

    public PostingSubcategories(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    private By subcategoriesList = By.id("com.buildinglink.mainapp.debug.qa:id/bulletinBoardCategory");

    public void selectSubcategory(){
        int countAllSubcategories = driver.findElements(subcategoriesList).size();
        Random random = new Random();
        int getRandomCategory = random.nextInt(countAllSubcategories);
        driver.findElements(subcategoriesList).get(getRandomCategory).click();
    }
}
