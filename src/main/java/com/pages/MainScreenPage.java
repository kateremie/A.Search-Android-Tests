package com.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.data.Constants.*;

public class MainScreenPage extends BasePage{

    @FindBy (id = "ru.averia.lostnfound:id/bt_add_pet")
    public SelenideElement AddPetButton;
    @FindBy (id = "ru.averia.lostnfound:id/tv_add_pet")
    public SelenideElement AddAnotherPetButton;
    @FindBy (id = "ru.averia.lostnfound:id/tv_caption")
    public SelenideElement AddPetTitle;
    @FindBy (xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup[2]/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.ImageView")
    public SelenideElement PhotoBar;
    @FindBy (xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup[2]/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[1]")
    public SelenideElement SubtitlePets;
    @FindBy (id = "ru.averia.lostnfound:id/rb_dog")
    public SelenideElement RBDog;
    @FindBy (id = "ru.averia.lostnfound:id/rb_cat")
    public SelenideElement RBCat;
    @FindBy (id = "ru.averia.lostnfound:id/actv_breed")
    public SelenideElement Breed;
    @FindBy (id = "ru.averia.lostnfound:id/rb_male")
    public SelenideElement SexM;
    @FindBy (id = "ru.averia.lostnfound:id/rb_female")
    public SelenideElement SexF;
    @FindBy (id = "ru.averia.lostnfound:id/tv_breed")
    public SelenideElement BreedandSex;
    @FindBy (id = "ru.averia.lostnfound:id/et_alias")
    public SelenideElement Name;
    @FindBy (id = "ru.averia.lostnfound:id/et_reward")
    public SelenideElement Reward;
    @FindBy (id = "ru.averia.lostnfound:id/tv_reward")
    public SelenideElement RewardRub;
    @FindBy (id = "ru.averia.lostnfound:id/et_specials")
    public SelenideElement SpecificMarks;
    @FindBy (id = "ru.averia.lostnfound:id/tv_description")
    public SelenideElement PetDescription;
    @FindBy(id = "ru.averia.lostnfound:id/bt_submit")
    public SelenideElement SubmitButton;
    @FindBy (id = "ru.averia.lostnfound:id/tv_personal")
    public SelenideElement AddPet2Title;
    @FindBy (id = "ru.averia.lostnfound:id/et_name")
    public SelenideElement UserName;
    @FindBy (id = "ru.averia.lostnfound:id/et_surname")
    public SelenideElement UserSurname;
    @FindBy (id = "ru.averia.lostnfound:id/et_phone")
    public SelenideElement Phone;
    @FindBy (id = "ru.averia.lostnfound:id/et_email")
    public SelenideElement Email;
    @FindBy (id = "ru.averia.lostnfound:id/tv_pet_name")
    public SelenideElement ActivePet;
    @FindBy (id = "ru.averia.lostnfound:id/tv_edit")
    public SelenideElement EditAdButton;
    @FindBy (id = "ru.averia.lostnfound:id/iv_photo")
    public SelenideElement PetPhoto;
    @FindBy (id = "ru.averia.lostnfound:id/tv_moderating_status")
    public SelenideElement ModeratingStatus;
    @FindBy (id = "ru.averia.lostnfound:id/iv_delete")
    public SelenideElement DeleteButton;
    @FindBy (id = "ru.averia.lostnfound:id/md_buttonDefaultPositive")
    public SelenideElement YesBtn;
    @FindBy (id = "ru.averia.lostnfound:id/bt_start_ad")
    public SelenideElement StartAdButton;
    @FindBy(id = "ru.averia.lostnfound:id/tv_contacts")
    public SelenideElement ContactInfo;
    @FindBy (id = "ru.averia.lostnfound:id/action_search")
    public SelenideElement SearchTab;

    Logger logger;
    public String login;

    public MainScreenPage(String device, Dimension screensize, String userLogin) {
        super(device, screensize, userLogin);
        logger = LogManager.getLogger("MainScreen");
    }

    public MainScreenPage goMain(){
        return  page(this);
    }

    public MainScreenPage choosePet(String petName){

        ActivePet.click();
        $x(String.format("//android.widget.TextView[contains(@text,'%s')]", petName)).click();
        ActivePet.shouldHave(Condition.text(petName));

        logger.info(device + ": Pet chosen successfully");
        return page(this);
    }

    public MainScreenPage addPet(String precondition, String petName){

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
        //TODO: add progress bar check

        AddPetTitle.shouldHave(text("Добавить питомца"));
        PhotoBar.click();
        phonePhoto("pet");

        scrollUp(0.95);
        RBDog.click();
        Breed.setValue(breed);

        if(device.matches("lg_k10_gold|lg"))
            scrollUp(0.6);

        SexM.click();

        scrollUp(0.9);
        Name.setValue(petName);
        Reward.setValue(reward);
        SpecificMarks.setValue(specific_marks);
        SubmitButton.click();

        login = userLogin;

        switch (precondition) {

            case ("FirstAd"):

                AddPet2Title.shouldHave(text("Персональная информация"));
                UserName.setValue(userName);
                UserSurname.setValue(userSurname);
                Phone.setValue(phoneNumber);
                Email.shouldHave(Condition.text(login));

                scrollUp(0.9);
                SubmitButton.click();
                ContactInfo.waitUntil(hidden,10000);
                SubmitButton.waitUntil(visible,5000).shouldHave(text("Готово")).click();

                break;

            case ("NextAd"):
                break;
        }
        ActivePet.shouldHave(text(petName));

        return  page(this);
    }

    public MainScreenPage checkPetInfo(String petName){

        //TODO: add other pet parameters in the method signature
        SearchTab.click();
        ActivePet.shouldHave(text(petName));
        PetPhoto.shouldBe(Condition.visible);

        //TODO: sex should be variable
        BreedandSex.should(text(breed + ", кобель"));
        RewardRub.should(text(reward));
        PetDescription.should(text(specific_marks));
        ModeratingStatus.should(text("Объявление на модерации"));
        //TODO: check pop up moderating view

        logger.info(device + ": Ad checked");
        return  page(this);
    }

    public MainScreenPage checkPetStatus(){

        //for testUser and fixed set of pets

        ActivePet.click();

        for (int i=1; i<3; i++)
            //for (int i=0; i<4; i++) blocked by AS-83
           $x("//android.view.ViewGroup[" + i + "]/android.view.ViewGroup[@resource-id ='ru.averia.lostnfound:id/container_status']")
                   .shouldBe(Condition.exist);
        ActivePet.click();

        //choosePet("Approuved"); blocked by AS-83
        //StartAdButton.shouldBe(Condition.exist);

        choosePet("Denied");
        ModeratingStatus.shouldHave(Condition.text("Ошибка модерации"));

        choosePet("Moderating");
        ModeratingStatus.shouldHave(Condition.text("Объявление на модерации"));

        //choosePet("Vk moderating"); blocked by AS-83
        //ModeratingStatus.shouldHave(Condition.text("Объявление на модерации"));

        return  page(this);
    }

    public MainScreenPage editPet(){

        SearchTab.click();

        //TODO: changing status case (blocked by AS-83)
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
        RewardRub.should(text("100 500 \u20BD"));
        PetDescription.should(text("Ad will be deleted"));

        //TODO: check status (approuved >> moderating) :: blocked by AS-83

        return  page(this);
    }

    public MainScreenPage deletePet(String petName){

        SearchTab.click();
        EditAdButton.click();
        DeleteButton.click();
        YesBtn.click();
        if(ActivePet.is(Condition.exist)) {
            ActivePet.click();
            //TODO: add variable petName
            $x("//android.widget.TextView[contains(@text,'"+petName+"')]").shouldNotBe(Condition.exist);
            System.out.println("Ad deleted successfully");
        }
       else {
           if (AddPetButton.is(Condition.exist))
               logger.info("Single ad deleted successfully");
            else
               logger.error("Ad is'nt deleted");
        }

        return  page(this);
    }
}
