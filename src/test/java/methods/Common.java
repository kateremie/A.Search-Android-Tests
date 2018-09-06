package methods;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.touch.TouchActions;

import java.net.MalformedURLException;
import java.util.Random;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.getWebDriverLogs;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Common {

    public static String GenerateRandomUserLogin(){

        Random login = new Random();
        String alphabet = "1234567890";
        String userlogin = "";
        for (int i = 0; i < 8; i++) userlogin += login.nextInt(alphabet.length());
        userlogin = userlogin + "@test.com";
        return userlogin;
    }

    public static void TakePhoto(){

        $(By.id("ru.averia.lostnfound:id/hint")).click();
        $(By.id("com.lge.camera:id/shutter_bottom_comp_type")).click();
        sleep(5000);
        $(By.id("com.lge.camera:id/btn_ok")).click();
        $(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[3]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button[2]")).click();
        /* TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(648,1228));*/
        /*TouchAction action = new TouchAction((PerformsTouchActions) getWebDriver());
        action.tap(PointOption.point(648,1180)).perform();*/

        //$(By.id("ru.averia.lostnfound:id/iv_confirm_crop")).click();
    }

    /*public void phonePhoto() {

        switch (devicename) {

            case (sony_xperia):
                //fkn experia does not want to focus an image
                //logger.info("Sony Xperia X Perfomance photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]").click();
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout").click();

                break;

            case (asuszenpad):
               // logger.info("Asus Zenpad photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.widget.ListView/android.widget.LinearLayout[1]").click();
                driver.findElement(By.id("com.asus.camera:id/button_capture")).click();
                driver.findElement(By.id("com.asus.camera:id/button_used")).click();

                break;
            case (htc):
                List<MobileElement> choiseslist_htc = driver.findElements(By.className("android.widget.LinearLayout"));
              //  logger.info("List: " + choiseslist_htc);
                choiseslist_htc.get(1).click();
                driver.findElement(By.id("com.asus.camera:id/button_capture")).click();
                driver.findElement(By.id("com.asus.camera:id/button_used")).click();

                break;
            case (samsung_j1):
               // logger.info("Samsung J1 photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/com.android.internal.widget.ResolverDrawerLayout/android.widget.GridView/android.widget.LinearLayout[1]/android.widget.ImageView").click();
                driver.findElementByAccessibilityId("MENUID_SHUTTER").click();
                driver.findElementById("com.sec.android.app.camera:id/okay").click();

                break;
            case (samsung_edge):
               // logger.info("Samsung Edge photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.ScrollView/android.widget.LinearLayout/com.android.internal.widget.ViewPager/android.widget.LinearLayout/android.widget.GridView/android.widget.LinearLayout[1]").click();
               // sleep(5);
                driver.findElementByXPath("(//GLButton[@content-desc=\"NONE\"])[3]").click();
                driver.findElementById("com.sec.android.app.camera:id/okay").click();

                break;
            case (lg):
            case (lg_gold):
                //logger.info("LG photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout/android.widget.LinearLayout[2]").click();
                //sleep(2);
                (new TouchAction(driver)).tap(109, 203).perform();
                //sleep(2);
                (new TouchAction(driver)).tap(109, 203).perform();
              //  sleep(1);

                break;
            case (meizu_n5):
                List<MobileElement> choiseslist_meizu_n5 = driver.findElements(By.className("android.widget.LinearLayout"));
               // logger.info("List: " + choiseslist_meizu_n5);
                choiseslist_meizu_n5.get(1).click();
                driver.findElement(By.id("com.asus.camera:id/button_capture")).click();
                driver.findElement(By.id("com.asus.camera:id/button_used")).click();

                break;
            case (xiomi_x4_note):
               // logger.info("Xiomi X4 Note photo sequence applied");
                List<MobileElement> choiseslist_xiomi_x4_note = driver.findElements(By.className("android.widget.LinearLayout"));
                logger.info("List: " + choiseslist_xiomi_x4_note);
                choiseslist_xiomi_x4_note.get(1).click();
                driver.findElement(By.id("com.asus.camera:id/button_capture")).click();
                driver.findElement(By.id("com.asus.camera:id/button_used")).click();

                break;
            case (honor_c3):
               // logger.info("Honor 3C photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.view.ViewPager/android.widget.GridView/android.widget.LinearLayout[1]").click();
                driver.findElementByAccessibilityId("Сфотографировать").click();
                driver.findElementByAccessibilityId("Готово").click();

                break;
            case (nexus_5):
                //logger.info("Nexus 5 photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout").click();
                driver.findElementByAccessibilityId("Затвор").click();
                driver.findElementByAccessibilityId("Готово").click();

                break;
            default:
             //   logger.info("Default photo action applied");
             //   sleep(2);
                List<MobileElement> choiseslist = driver.findElements(By.className("android.widget.LinearLayout"));
            //    logger.info("List: " + choiseslist);
                choiseslist.get(1).click();
                driver.findElement(By.id("com.asus.camera:id/button_capture")).click();
                driver.findElement(By.id("com.asus.camera:id/button_used")).click();

                break;
        }
    }*/

}
