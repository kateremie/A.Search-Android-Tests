package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.support.FindBy;

import java.net.MalformedURLException;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import static methods.Common.TakePhoto;

public class MainScreenPage {

    @FindBy (id = "ru.averia.lostnfound:id/bt_add_pet")
    public static SelenideElement AddPetButton;

    @FindBy (id = "ru.averia.lostnfound:id/tv_caption")
    public static SelenideElement AddPetHeader;

    @FindBy (xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup[2]/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.ImageView")
    public static SelenideElement PhotoBar;

    public static MainScreenPage Init(){

        return page (MainScreenPage.class);
    }

    public MainScreenPage AddAd(String precondition){

        switch (precondition){

            case ("FirstAd"):

                AddPetButton.shouldHave(Condition.text("Добавить питомца"));

                break;

            case ("NextAd"):

                //TODO

                break;
        }

        AddPetHeader.shouldHave(Condition.text("Добавить питомца"));
        PhotoBar.click();

        TakePhoto();

        /*
        MobileElement el8 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup[2]/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[1]");
        el8.clear();
        MobileElement el9 = (MobileElement) driver.findElementById("ru.averia.lostnfound:id/rb_dog");
        el9.click();
        MobileElement el10 = (MobileElement) driver.findElementById("ru.averia.lostnfound:id/actv_breed");
        el10.click();
        el10.sendKeys("барб");
        MobileElement el11 = (MobileElement) driver.findElementById("ru.averia.lostnfound:id/actv_breed");
        el11.sendKeys("барб");
        MobileElement el12 = (MobileElement) driver.findElementById("ru.averia.lostnfound:id/actv_breed");
        el12.sendKeys("ретривер");
        MobileElement el13 = (MobileElement) driver.findElementById("ru.averia.lostnfound:id/rb_male");
        el13.click();
        MobileElement el14 = (MobileElement) driver.findElementById("ru.averia.lostnfound:id/et_alias");
        el14.sendKeys("Dog");
        MobileElement el15 = (MobileElement) driver.findElementById("ru.averia.lostnfound:id/et_reward");
        el15.sendKeys("1000");
        MobileElement el16 = (MobileElement) driver.findElementById("ru.averia.lostnfound:id/et_specials");
        el16.sendKeys("Особенный пёс");
        MobileElement el17 = (MobileElement) driver.findElementById("ru.averia.lostnfound:id/et_specials");
        el17.sendKeys("Особенный пёс");
        MobileElement el18 = (MobileElement) driver.findElementById("ru.averia.lostnfound:id/bt_submit");
        el18.click();*/

    return this;
    }
}
