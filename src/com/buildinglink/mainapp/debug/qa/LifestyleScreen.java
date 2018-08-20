package com.buildinglink.mainapp.debug.qa;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class LifestyleScreen {
    private static AppiumDriver<MobileElement> driver;

    public LifestyleScreen(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }
}
