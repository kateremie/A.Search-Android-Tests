package tests;

import com.codeborne.selenide.WebDriverRunner;
import methods.AndroidDriverProvider;

import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;

import static methods.Helper.getDeviceInfo;

public class TestBase {

   // public AndroidDriver AndroidDriver;

    @BeforeSuite
    public void initTestSuite() throws Exception, MalformedURLException {

        WebDriverRunner.setWebDriver(AndroidDriverProvider.getAndroidDriver());
        getDeviceInfo();
    }

}
