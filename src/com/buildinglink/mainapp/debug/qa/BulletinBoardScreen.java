package com.buildinglink.mainapp.debug.qa;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class BulletinBoardScreen {
    private static AppiumDriver<MobileElement> driver;

    public BulletinBoardScreen(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }
}
