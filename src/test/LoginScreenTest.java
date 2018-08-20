package test;

import com.buildinglink.mainapp.additionalClasses.RandomValueGenerator;
import com.buildinglink.mainapp.debug.qa.LoginScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Year;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginScreenTest {
    private static AppiumDriver<MobileElement> driver;
    private LoginScreen loginScreen = new LoginScreen(driver);

    @BeforeClass
    public static void setUp() {  //set up desired capabilities
        DesiredCapabilities caps = new	DesiredCapabilities();//To	create	an	object
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "00f1edb5378094e3"); //Android-057
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0.0"); //Android-057
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        //caps.setCapability(MobileCapabilityType.DEVICE_NAME, "57daea9e9d064ab4"); //Tab 2
        //caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0.1");
        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.buildinglink.mainapp.debug.qa"); //package of the qa build
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.buildinglink.mainapp.login.view.viewcontrollers.activities.SplashActivity");//To specify the	activity which we want to launch
        try {
            driver = new AppiumDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Ignore
    public void checkLoginWithValidCreds(){
        loginScreen.loginWithCorrectCreds("tuser2","tuser2");
        //need to add Assert
    }

    @Test
    public void checkYearInCopyright(){
        int currentYear = Year.now().getValue();
        String strYear = Integer.toString(currentYear);
        assertTrue(loginScreen.getCopyrightValue().contains(strYear));
    }

    @Test
    public void checkForgotPasswordLink(){
        String expectedURL = "https://webservices-live.blkqa.com/v2/global/login/forgotpassword.aspx"; //url for qa build
        assertEquals(expectedURL, loginScreen.openForgotPasswordLink());
        driver.navigate().back();
    }

    @Test
    public void checkBuildinglinkLink(){
        String expectedURL = "https://webservices-live.blkqa.com";
        assertTrue(loginScreen.openBuildinglinkLink().contains(expectedURL));
        driver.navigate().back();
    }

    @Test
    public void openCommentsSuggestionsLink(){
        loginScreen.openCommentsSuggestions();
        driver.navigate().back();
        //need to add Assert
    }

    @Test
    public void loginWithInvalidCredentials(){
        loginScreen.loginWithInvalidCreds(RandomValueGenerator.generateRandomValue(10, "numString"), RandomValueGenerator.generateRandomValue(10, "numString"));
        String error = loginScreen.getErrorText();
        String expectedError = "Username or password is incorrect";
        Assert.assertEquals(expectedError,error);
        loginScreen.tapOnOkButton();
    }

    @Test
    public void loginAsFrontDeskStaff(){
        loginScreen.loginWithInvalidCreds("tfrontdesk1", "testtest");
        String error = loginScreen.getErrorText();
        String expectedError = "This app is restricted to residents";
        Assert.assertEquals(expectedError,error);
        loginScreen.tapOnOkButton();
    }

    @Test
    public void loginAsMaintenanceStaff(){
        loginScreen.loginWithInvalidCreds("blmaintenance", "testtest");
        String error = loginScreen.getErrorText();
        String expectedError = "This app is restricted to residents";
        Assert.assertEquals(expectedError,error);
        loginScreen.tapOnOkButton();
    }

    @Test
    public void loginAsCarValet(){
        loginScreen.loginWithInvalidCreds("tcarv1", "testtest");
        String error = loginScreen.getErrorText();
        String expectedError = "This app is restricted to residents";
        Assert.assertEquals(expectedError,error);
        loginScreen.tapOnOkButton();
    }

    @Test
    @Ignore
    public void checkRememberMeSwitchedOff() {
        loginScreen.tapRememberMeCheckbox()
                   .loginWithCorrectCreds("sotest","666f4");
        //need to add Assert
    }

    @AfterClass
    public static void close() {
        driver.closeApp();
    }

}
