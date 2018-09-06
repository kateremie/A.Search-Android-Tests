package tests;

import com.appium.manager.AppiumDriverManager;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.android.AndroidDriver;
import methods.AndroidDriverProvider;

import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;

public class TestBase {

    public AndroidDriver AndroidDriver;

    @BeforeSuite
    public void initTestSuite() throws Exception, MalformedURLException {

        WebDriverRunner.setWebDriver(AndroidDriverProvider.getAndroidDriver());
    }
}
