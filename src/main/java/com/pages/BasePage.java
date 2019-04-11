package com.pages;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static jdk.nashorn.internal.runtime.JSType.toInteger;

public class BasePage{

    @FindBy(id = "ru.averia.lostnfound:id/hint")
    public SelenideElement CamButton;
    @FindBy (id = "ru.averia.lostnfound:id/iv_confirm_crop")
    public SelenideElement ConfirmCropButton;
    @FindBy (xpath = "//android.widget.TextView[contains(@text,'Камера')]")
    public SelenideElement CamButton2;
    @FindBy (id = "ru.averia.lostnfound:id/crop_image_menu_crop")
    public SelenideElement ConfirmCropButton2;

    public String device;
    public Dimension screensize;
    public String userLogin;
    public Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage(String device, Dimension screensize, String userLogin){
        this.device = device;
        this.screensize = screensize;
        this.userLogin = userLogin;
    }

    public void phonePhoto(String from){

        switch (from) {

            case ("pet"):
                CamButton.waitUntil(enabled, 5000).click();
                break;

            case ("user"):
                CamButton2.click();
                break;
        }

        switch (device) {
            case ("lg_k10_gold"):
            case ("lg"):
                $(By.id("com.lge.camera:id/shutter_bottom_comp_type")).click();
                $(By.id("com.lge.camera:id/btn_ok")).waitUntil(visible,10000).click();

                logger.info("LG photo sequence applied");
                break;

            case ("asus_zenfone"):
                $(By.id("com.asus.camera:id/button_capture")).click();
                $(By.id("com.asus.camera:id/button_used")).waitUntil(visible,10000).click();

                logger.info("Asus Zenfone photo sequence applied");
                break;

            case ("xiomi_mi6"):
                $(By.id("com.android.camera:id/v9_shutter_button_internal")).click();
                $(By.id("com.android.camera:id/inten_done_apply")).waitUntil(visible, 5000).click();

                logger.info("Xiomi Mi6 photo sequence applied");
                break;
        }

        switch (from) {

            case ("pet"):
                ConfirmCropButton.waitUntil(visible, 5000).click();
                break;

            case ("user"):
                ConfirmCropButton2.waitUntil(visible, 5000).click();
                break;
        }
    }

    public void scrollUp(double yS){
        //double yS - percent of screen height
        sleep(3000);

        TouchAction action = new TouchAction((PerformsTouchActions) getWebDriver());
        int x = toInteger(screensize.getWidth()*0.5);
        int yStart = toInteger(screensize.getHeight()*yS);
        int yEnd = toInteger(screensize.getHeight()*0.1);
        action.longPress(PointOption.point(x,yStart)).moveTo(PointOption.point(x,yEnd)).release().perform();
    }

    public void tap(int y, int x){

        sleep(2000);

        TouchAction action = new TouchAction((PerformsTouchActions) getWebDriver());
        action.longPress(PointOption.point(x,y)).release().perform();

        logger.info("Tap on coordinates: (" + x + y + ")");
    }
}
