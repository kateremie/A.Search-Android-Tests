package com.tests;

import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.pages.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.data.Constants.app_path_win;
import static com.methods.Helper.generateRandomUserLogin;

public class BaseTest {

    public DesiredCapabilities capabilities = new DesiredCapabilities();
    public Logger logger= LogManager.getLogger("A_Search Logger");

    public String port;
    public String device;
    public String udid;
    public String systemPort;

    public String devicename;
    public Dimension screensize;
    public String userLogin;

    public BasePage basePage;
    public StartScreenPage startScreenPage;
    public MainScreenPage mainScreenPage;
    public ProfileScreenPage profileScreenPage;
    public LostScreenPage lostScreenPage;

    @Parameters({"server_port","device","udid", "systemPort"})
    @BeforeTest
    void BeforeTest(@Optional("4732") String port, @Optional("default") String device, @Optional("default") String udid, @Optional ("8200") String systemPort) {
        ThreadContext.put("ROUTINGKEY",device);

        logger.info("**************************************************************");
        logger.info(device+": "+"Initial Settings and App Startup");

        this.port = port;
        this.device = device;
        this.udid = udid;
        this.systemPort = systemPort;

        StartUp();
        logger.info(device+": "+"Settings Applied");

        this.devicename = device;
        this.screensize = getWebDriver().manage().window().getSize();
        this.userLogin = generateRandomUserLogin();

        basePage = new BasePage(device, screensize, userLogin);
        startScreenPage = new StartScreenPage(device, screensize, userLogin);
        mainScreenPage = new MainScreenPage(device, screensize, userLogin);
        profileScreenPage = new ProfileScreenPage(device, screensize, userLogin);
        lostScreenPage = new LostScreenPage(device, screensize, userLogin);

    }

    @AfterTest(alwaysRun = true)
    void AfterTest() {

        logger.info(device + ": Closing app\n \n");
        WebDriverRunner.closeWebDriver();
    }

    @AfterMethod(alwaysRun = true)
    void afterMethod(ITestResult result)
    {
        String testName = result.getName();

        try
        {
            if(result.getStatus() == ITestResult.SUCCESS)
            {
                logger.info(">> " + device + ": Passed " + testName);
            }
            else
                if(result.getStatus() == ITestResult.FAILURE)
            {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                Throwable cause = result.getThrowable();
                cause.printStackTrace(printWriter);
                logger.error(">>>>>> " + device + ": Failed " + testName + "\n " + stringWriter.getBuffer().toString());
            }

            else
                if(result.getStatus() == ITestResult.SKIP)
            {
                logger.error(">>>> " + device + ": Skiped " + testName);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    void StartUp(){

        logger.info(device + ": Starting app");

        capabilities.setCapability(MobileCapabilityType.VERSION, "4.4.2");
        capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability("deviceName", udid);
        capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemPort);
        //capabilities.setCapability("deviceName", "LGK43099FEKVAE");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability(MobileCapabilityType.APP, app_path_win.getAbsolutePath());
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"ru.averia.lostnfound");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "ru.averia.lostnfound.SplashActivity");
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("gpsEnabled", true);
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);

        try {
            WebDriverRunner.setWebDriver(new AndroidDriver(new URL("http://127.0.0.1:"+port+"/wd/hub"), capabilities));
            logger.info(device + ": App launched");
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error(device +": WebDriver initialized error: \n" + e.getMessage());
        }
    }
}
