package com.buildinglink.mainapp.debug.qa;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.Random;

public class PostingCategories {
    private static AppiumDriver<MobileElement> driver;

    public PostingCategories(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    private By categoriesList = By.id("com.buildinglink.mainapp.debug.qa:id/bulletinBoardType");

    public void selectCategory(){
        int countAllCategories = driver.findElements(categoriesList).size();
        Random random = new Random();
        int getRandomCategory = random.nextInt(countAllCategories);
        driver.findElements(categoriesList).get(getRandomCategory).click();
    }
}
