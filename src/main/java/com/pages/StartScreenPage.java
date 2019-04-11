package com.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.data.Constants.*;

public class StartScreenPage extends BasePage{

    @FindBy (xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.TextView")
    public SelenideElement Walkthrough1Text;
    @FindBy (id = "ru.averia.lostnfound:id/bt_next")
    public SelenideElement NextButton;
    @FindBy (id = "ru.averia.lostnfound:id/tv_main")
    public SelenideElement Walkthrough2_3_4Text;
    @FindBy (id = "ru.averia.lostnfound:id/tv_skip")
    public SelenideElement SkipButton;
    @FindBy (id = "ru.averia.lostnfound:id/iv_figure")
    public SelenideElement Logo;
    @FindBy (id = "ru.averia.lostnfound:id/bt_submit")
    public SelenideElement SubmitButton;
    @FindBy (id = "ru.averia.lostnfound:id/bt_switch_register")
    public SelenideElement SwitchSubmitButton;
    @FindBy (id = "ru.averia.lostnfound:id/et_email")
    public SelenideElement Email;
    @FindBy (id = "ru.averia.lostnfound:id/et_password")
    public SelenideElement Password;
    @FindBy (id = "ru.averia.lostnfound:id/cb_agreement")
    public SelenideElement CheckBox;
    @FindBy (id = "ru.averia.lostnfound:id/tv1")
    public SelenideElement MyPetEmptyInfo;

    Logger logger;

    public StartScreenPage(String device, Dimension screensize, String userLogin) {
        super(device, screensize, userLogin);
        logger = LogManager.getLogger("StartScreen");
    }

    public StartScreenPage start(){

        return page(this);
    }

    public StartScreenPage splashScreen(){

        Walkthrough1Text.shouldHave(Condition.text
                ("Поможем быстро найти потерявшегося питомца"));
        NextButton.click();

        Walkthrough2_3_4Text.shouldHave(Condition.text
                ("После регистрации мы создадим объявление на случай потери питомца"));
        NextButton.click();

        Walkthrough2_3_4Text.shouldHave(Condition.text
                ("Объявление пройдет модерацию в соцсетях и будет готово к показам в случае потери питомца"));
        NextButton.click();

        Walkthrough2_3_4Text.shouldHave(Condition.text
                ("Всего за полчаса ваше объявление увидят сотни человек в радиусе 2 км от места потери"));
        NextButton.click();

        Logo.should(Condition.visible);

        logger.info(device +": SplashScreen method finished");
        return page(this);
    }

    public StartScreenPage skipSplashScreen(){

        Walkthrough1Text.shouldHave(Condition.text
                ("Поможем быстро найти потерявшегося питомца"));
        SkipButton.click();

        Logo.should(Condition.enabled);

        logger.info(device + ": SplashScreen skipped");
        return page(this);
    }

    public StartScreenPage registration(){

        //TODO: add button check. In the case of wrong text switch login/registration screen

        SubmitButton.shouldHave(Condition.text("Регистрация"));

        logger.info(device+": "+"User Login is " + userLogin);

        Email.setValue(userLogin);
        Password.setValue(superPass);

        CheckBox.click();
        SubmitButton.click();

        MyPetEmptyInfo.shouldHave(Condition.text
                ("Вы пока не добавили ни одного питомца"));

        return page(this);
    }

    public StartScreenPage login(String email, String password){

        if (Walkthrough1Text.exists())
        {
            logger.info(device + ": App starts from Splash Screen");
            skipSplashScreen();
        }
        else
            {
            if (SubmitButton.getText().equals("Войти"))
                logger.info(device + ": App starts from Login Screen");

            else {
                logger.info(device + ": App starts from Registration Screen");

                SwitchSubmitButton.click();
                SubmitButton.waitUntil(visible, 5000);
                SubmitButton.shouldHave(Condition.text("Войти"));
            }
        }
        Email.setValue(email);
        Password.setValue(password);
        SubmitButton.click();
        //CheckBox.click(); - failed test check

        //TODO: add check!

        return page(this);
    }

}
