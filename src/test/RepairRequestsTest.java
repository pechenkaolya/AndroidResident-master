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

public class RepairRequestsTest {
    private static AppiumDriver<MobileElement> driver;
    private HomeScreen homeScreen = new HomeScreen(driver);
    private RepairRequestCategories repairRequestCategories = new RepairRequestCategories(driver);
    private NewRepairRequest newRepairRequest = new NewRepairRequest(driver);
    private RepairRequestsScreen repairRequestsScreen = new RepairRequestsScreen(driver);
    private EditRepairRequest editRepairRequest = new EditRepairRequest(driver);

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
    public void addNewRequest(){
        homeScreen.openRepairRequestsModule();
        repairRequestsScreen.tapAddButton();
        repairRequestCategories.selectCategory();
        newRepairRequest.typeProblemDescription("NewDesc" + RandomValueGenerator.generateRandomValue(10,"string"));
        newRepairRequest.typeEntryInstructions("EntryInst" + RandomValueGenerator.generateRandomValue(10,"string"));
        newRepairRequest.typeContactPhone(RandomValueGenerator.generateRandomValue(10,"numeral"));
        newRepairRequest.typeAdditionalEmail(RandomValueGenerator.generateRandomValue(10,"numString") + "@"+RandomValueGenerator.generateRandomValue(10,"numString")+".com");
        newRepairRequest.tapSaveButton();
        newRepairRequest.acceptLiabilityWaiver();
        Assert.assertEquals("Your request has been saved", newRepairRequest.getSuccessMessage());
    }

    @Test
    public void checkEmptyDescription(){
        homeScreen.openRepairRequestsModule();
        repairRequestsScreen.tapAddButton();
        repairRequestCategories.selectCategory();
        newRepairRequest.tapSaveButton();
        Assert.assertEquals("You must enter a description", newRepairRequest.getErrorMessage());
    }

    @Test
    public void editRequest(){
        homeScreen.openRepairRequestsModule();
        repairRequestsScreen.expandRequest();
        repairRequestsScreen.tapEditButton();
        editRepairRequest.changeStatusToRandom();
        editRepairRequest.tapRequestCategoryField();
        repairRequestCategories.selectCategory();
        editRepairRequest.typeProblemDescription("Updated desc" + RandomValueGenerator.generateRandomValue(5,"numString"));
        editRepairRequest.typeEntryInstructions("Updated EI" + RandomValueGenerator.generateRandomValue(10,"string"));
        editRepairRequest.typeContactPhone(RandomValueGenerator.generateRandomValue(10,"numeral"));
        editRepairRequest.typeAdditionalEmail(RandomValueGenerator.generateRandomValue(10,"numString") + "@" +RandomValueGenerator.generateRandomValue(10,"numString")+".com");
        editRepairRequest.tapSaveButton();
        editRepairRequest.acceptLiabilityWaiver();
        Assert.assertEquals("Your request has been saved", editRepairRequest.getSuccessMessage());
    }

    @Test
    public void closeRequest(){
       homeScreen.openRepairRequestsModule();
       repairRequestsScreen.expandRequest();
       repairRequestsScreen.tapEditButton();
       editRepairRequest.changeStatusToClosed();
       editRepairRequest.tapSaveButton();
       editRepairRequest.acceptLiabilityWaiver();
        Assert.assertEquals("Your request has been saved", editRepairRequest.getSuccessMessage());
    }

}
