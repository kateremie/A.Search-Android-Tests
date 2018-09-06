package methods;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static data.Constants.app_path_win;

public class AndroidDriverProvider {

    //private static String port;

    public static DesiredCapabilities getDesiredCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.VERSION, "4.4.2");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("deviceName", "LGK43099FEKVAE");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability(MobileCapabilityType.APP, app_path_win.getAbsolutePath());
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"ru.averia.lostnfound");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "ru.averia.lostnfound.SplashActivity");
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("gpsEnabled", true);
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);

        return capabilities;
    }

    public static String device;
    public static WebDriver driver;
    public static AndroidDriver getAndroidDriver() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.VERSION, "4.4.2");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("deviceName", "LGK43099FEKVAE");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability(MobileCapabilityType.APP, app_path_win.getAbsolutePath());
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"ru.averia.lostnfound");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "ru.averia.lostnfound.SplashActivity");
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("gpsEnabled", true);
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);

        return new AndroidDriver(new URL("http://127.0.0.1:4732/wd/hub"), capabilities);
    }

}
