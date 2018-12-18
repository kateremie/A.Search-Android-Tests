package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import methods.Helper;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;
import static data.Constants.*;

public class StartScreenPage{

    @FindBy (xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.TextView")
    public static SelenideElement Walkthrough1Text;

    @FindBy (id = "ru.averia.lostnfound:id/bt_next")
    public static SelenideElement NextButton;

    @FindBy (id = "ru.averia.lostnfound:id/tv_main")
    public static SelenideElement Walkthrough2_3_4Text;

    @FindBy (id = "ru.averia.lostnfound:id/tv_skip")
    public static SelenideElement SkipButton;

    @FindBy (id = "ru.averia.lostnfound:id/iv_figure")
    public static SelenideElement Logo;

    @FindBy (id = "ru.averia.lostnfound:id/bt_submit")
    public static SelenideElement SubmitButton;

    @FindBy (id = "ru.averia.lostnfound:id/bt_switch_register")
    public static SelenideElement SwitchSubmitButton;

    @FindBy (id = "ru.averia.lostnfound:id/et_email")
    public static SelenideElement Email;

    @FindBy (id = "ru.averia.lostnfound:id/et_password")
    public static SelenideElement Password;

    @FindBy (id = "ru.averia.lostnfound:id/cb_agreement")
    public static SelenideElement CheckBox;

    @FindBy (id = "ru.averia.lostnfound:id/tv1")
    public static SelenideElement MyPetEmptyInfo;

    public static String userLogin;

    public static StartScreenPage start() {

       return page(StartScreenPage.class);
    }

    public static StartScreenPage splashScreen(){

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

        return page(StartScreenPage.class);
    }

    public StartScreenPage skipSplashScreen(){

        Walkthrough1Text.shouldHave(Condition.text
                ("Поможем быстро найти потерявшегося питомца"));
        SkipButton.click();

        Logo.should(Condition.enabled);

        return this;
    }

    public StartScreenPage register(){

        //TODO: добавить проверку, какой текст на кнопке. Если неверный - переключение между экранами регистрации/авторизации

        SubmitButton.shouldHave(Condition.text("Регистрация"));

        userLogin = Helper.generateRandomUserLogin();
        Email.setValue(userLogin);
        Password.setValue(superPass);

        CheckBox.click();
        SubmitButton.click();

        MyPetEmptyInfo.shouldHave(Condition.text
                ("Вы пока не добавили ни одного питомца"));

        return this;
    }

    public MainScreenPage login(String email, String password){

        if (Walkthrough1Text.exists())
            skipSplashScreen();
        else
            {
            if (SubmitButton.getText().equals("Войти")) {}
            else {
                SwitchSubmitButton.click();
                //sleep(3000);
                SubmitButton.shouldBe(Condition.enabled);
                SubmitButton.shouldHave(Condition.text("Войти"));
            }
        }
        Email.setValue(email);
        Password.setValue(password);
        SubmitButton.click();

        return page(MainScreenPage.class);
    }

}
