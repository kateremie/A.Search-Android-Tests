package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.support.FindBy;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static data.Constants.*;
import static data.Variables.screensize;
import static jdk.nashorn.internal.runtime.JSType.toInteger;
import static methods.Helper.*;
import static pages.StartScreenPage.userLogin;

public class MainScreenPage {

    @FindBy (id = "ru.averia.lostnfound:id/bt_add_pet")
    public static SelenideElement AddPetButton;

    @FindBy (id = "ru.averia.lostnfound:id/tv_add_pet")
    public static SelenideElement AddAnotherPetButton;

    @FindBy (id = "ru.averia.lostnfound:id/tv_caption")
    public static SelenideElement AddPetTitle;

    @FindBy (xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup[2]/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.ImageView")
    public static SelenideElement PhotoBar;

    @FindBy (xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup[2]/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[1]")
    public static SelenideElement SubtitlePets;

    @FindBy (id = "ru.averia.lostnfound:id/rb_dog")
    public static SelenideElement RBDog;

    @FindBy (id = "ru.averia.lostnfound:id/rb_cat")
    public static SelenideElement RBCat;

    @FindBy (id = "ru.averia.lostnfound:id/actv_breed")
    public static SelenideElement Breed;

    @FindBy (id = "ru.averia.lostnfound:id/rb_male")
    public static SelenideElement SexM;

    @FindBy (id = "ru.averia.lostnfound:id/rb_female")
    public static SelenideElement SexF;

    @FindBy (id = "ru.averia.lostnfound:id/tv_breed")
    public static SelenideElement BreedandSex;

    @FindBy (id = "ru.averia.lostnfound:id/et_alias")
    public static SelenideElement Name;

    @FindBy (id = "ru.averia.lostnfound:id/et_reward")
    public static SelenideElement Reward;

    @FindBy (id = "ru.averia.lostnfound:id/tv_reward")
    public static SelenideElement RewardRub;

    @FindBy (id = "ru.averia.lostnfound:id/et_specials")
    public static SelenideElement SpecificMarks;

    @FindBy (id = "ru.averia.lostnfound:id/tv_description")
    public static SelenideElement PetDescription;

    @FindBy(id = "ru.averia.lostnfound:id/bt_submit")
    public static SelenideElement SubmitButton;

    @FindBy (id = "ru.averia.lostnfound:id/tv_personal")
    public static SelenideElement AddPet2Title;

    @FindBy (id = "ru.averia.lostnfound:id/et_name")
    public static SelenideElement UserName;

    @FindBy (id = "ru.averia.lostnfound:id/et_surname")
    public static SelenideElement UserSurname;

    @FindBy (id = "ru.averia.lostnfound:id/et_phone")
    public static SelenideElement Phone;

    @FindBy (id = "ru.averia.lostnfound:id/et_email")
    public static SelenideElement Email;

    @FindBy (id = "ru.averia.lostnfound:id/tv_pet_name")
    public static SelenideElement ActivePet;

    @FindBy (id = "ru.averia.lostnfound:id/tv_edit")
    public static SelenideElement EditAdButton;

    @FindBy (id = "ru.averia.lostnfound:id/iv_photo")
    public static SelenideElement PetPhoto;

    @FindBy (id = "ru.averia.lostnfound:id/tv_moderating_status")
    public static SelenideElement ModeratingStatus;

    @FindBy (id = "ru.averia.lostnfound:id/iv_delete")
    public static SelenideElement DeleteButton;
    ///hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.ImageView[2]

    @FindBy (id = "ru.averia.lostnfound:id/md_buttonDefaultPositive")
    public static SelenideElement YesBtn;

    @FindBy (id = "ru.averia.lostnfound:id/bt_start_ad")
    public static SelenideElement StartAdButton;

    public static MainScreenPage goMain(){

        //TODO: tap pets navbar + check
        return page (MainScreenPage.class);
    }

    public MainScreenPage choosePet (String petName){

        ActivePet.click();
        $x(String.format("//android.widget.TextView[contains(@text,'%s')]", petName)).click();
        ActivePet.shouldHave(Condition.text(petName));

        return this;
    }

    public MainScreenPage addAd(String precondition, String petName){

        switch (precondition){

            case ("FirstAd"):

                AddPetButton.shouldHave(text("Добавить питомца"));
                AddPetButton.click();

                break;

            case ("NextAd"):

                EditAdButton.should(Condition.enabled);
                ActivePet.click();
                AddAnotherPetButton.click();

                break;
        }
        //TODO: добавить проверку на steps (progress bar)

        AddPetTitle.shouldHave(text("Добавить питомца"));
        PhotoBar.click();
        TakePhoto();

        scrollUp(0.95);
        RBDog.click();
        Breed.setValue(breed).click();
        SexM.click();

        scrollUp(0.9);
        Name.setValue(petName);
        Reward.setValue(reward);
        SpecificMarks.setValue(specific_marks);
        SubmitButton.click();

        switch (precondition) {

            case ("FirstAd"):

                AddPet2Title.shouldHave(text("Персональная информация"));
                UserName.setValue(userName);
                UserSurname.setValue(userSurname);
                Phone.setValue(phoneNumber);
                Email.shouldHave(Condition.text(userLogin));

                scrollUp(0.9);
                SubmitButton.click();
                sleep(10000);
                SubmitButton.shouldHave(text("Готово")).click();

                break;

            case ("NextAd"):

                break;

        }

        ActivePet.shouldHave(text(petName));

    return this;
    }

    public MainScreenPage checkAd(String petName){

        ActivePet.shouldHave(text(petName));
        PetPhoto.shouldBe(Condition.visible);
        BreedandSex.should(text(breed + ", кобель"));
        RewardRub.text().contains(reward);
        PetDescription.should(text(specific_marks));
        ModeratingStatus.should(text("Объявление на модерации"));
        //TODO: check pop up moderating view
        return this;
    }

    public MainScreenPage adsStatus(){

        ActivePet.click();
        //TODO: 1 - pet icon

        for (int i=1; i<3; i++)
            //for (int i=0; i<4; i++) ---> AS-83
           $x("//android.view.ViewGroup[" + i + "]/android.view.ViewGroup[@resource-id ='ru.averia.lostnfound:id/container_status']")
                   .shouldBe(Condition.exist);

        ActivePet.click();

        //TODO: 2 - choose pet&check moderation status
        /*choosePet("Approuved"); ---> AS-83
        StartAdButton.shouldBe(Condition.exist);*/

        choosePet("Denied");
        ModeratingStatus.shouldHave(Condition.text("Ошибка модерации"));

        choosePet("Moderating");
        ModeratingStatus.shouldHave(Condition.text("Объявление на модерации"));

        /*choosePet("Vk moderating"); ---> AS-83
        ModeratingStatus.shouldHave(Condition.text("Объявление на модерации"));*/

        return this;
    }

    public MainScreenPage editAd(){
        //public MainScreenPage EditAd(String petName){

        //TODO: for each device specific pet in approuved status :: blocked by AS-83 (case with changing status)
        EditAdButton.click();
        scrollUp(0.95);
        RBCat.click();
        Breed.setValue("Мейн кун").click();
        SexF.click();
        scrollUp(0.9);
        Name.setValue("EditedAd");
        Reward.setValue("0100500");
        SpecificMarks.setValue("Ad will be deleted");
        SubmitButton.click();

        ActivePet.shouldHave(text("EditedAd"));
        BreedandSex.should(text("Мейн кун, кошка"));
        RewardRub.text().contains("100 500 \u20BD");
        PetDescription.should(text("Ad will be deleted"));

        //TODO: 9 - check: status (approuved >> moderating) :: blocked by AS-83

    return this;
    }

    public MainScreenPage deleteAd (String petName){

        EditAdButton.click();
        DeleteButton.click();
        YesBtn.click();
        if(ActivePet.is(Condition.exist)) {
            ActivePet.click();
            //TODO: add variable petName
            $x("//android.widget.TextView[contains(@text,'"+petName+"')]").shouldNotBe(Condition.exist);
            System.out.println("Ad was deleted successfully");
        }
       else {

           if (AddPetButton.is(Condition.exist)){
                System.out.println("Single ad was deleted successfully");

            }
            else  {
                System.out.println("Ad is'nt deleted");
            }
        }
            sleep(3000);

            TouchAction action = new TouchAction((PerformsTouchActions) getWebDriver());
            action.tap(PointOption.point(toInteger(screensize.getWidth()*0.5), toInteger(screensize.getHeight()*0.2))).perform();

        return this;
    }
}
