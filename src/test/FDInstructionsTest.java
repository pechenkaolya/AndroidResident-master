package test;

import com.buildinglink.mainapp.additionalClasses.RandomValueGenerator;
import com.buildinglink.mainapp.debug.qa.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;

public class FDInstructionsTest {
    private static AppiumDriver<MobileElement> driver;
    private HomeScreen homeScreen = new HomeScreen(driver);
    private FrontDeskInstructionsScreen fdInstructionsScreen = new FrontDeskInstructionsScreen(driver);
    private FDITypes fdiTypes = new FDITypes(driver);
    private NewInstruction newInstruction = new NewInstruction(driver);

    @BeforeClass
    public static void setUp() {  //set up desired capabilities
        DesiredCapabilities caps = new	DesiredCapabilities();//To	create	an	object
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "00f1edb5378094e3"); //Android-057
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0.0"); //Android-057
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.buildinglink.mainapp.debug.qa"); //package of the qa build
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.buildinglink.mainapp.login.view.viewcontrollers.activities.SplashActivity");//To specify the	activity which we want to launch
        try {
            driver = new AppiumDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
            LoginScreen loginScreen = new LoginScreen(driver);
            loginScreen.loginWithTestUser();
            WebDriverWait wait = new WebDriverWait(driver,20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/alertTitle"))); //wait till BuildingLink Push Notifications popup appears
            HomeScreen homeScreen = new HomeScreen(driver);
            homeScreen.tapOnOkButton();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void addNewInstruction(){
        homeScreen.openFDInstructionsModule();
        fdInstructionsScreen.tapAddButton();
        fdiTypes.selectType();
        newInstruction.typeInstructions("NewInstr"+RandomValueGenerator.generateRandomValue(10, "string"));
        newInstruction.tapSaveButton();
        newInstruction.acceptLiabilityWaiver();
        Assert.assertEquals("Your instruction has been saved", newInstruction.getSuccessMessage());
    }

    @Test
    public void checkEmptyInstructionField(){
        homeScreen.openFDInstructionsModule();
        fdInstructionsScreen.tapAddButton();
        fdiTypes.selectType();
        newInstruction.getErrorMessage();
        Assert.assertEquals("You must enter a description", newInstruction.getErrorMessage());
    }




}
