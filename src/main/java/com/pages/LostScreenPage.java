package com.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LostScreenPage extends BasePage {

    @FindBy(id="ru.averia.lostnfound:id/tv_caption")
    public static SelenideElement Location;
    @FindBy (id = "ru.averia.lostnfound:id/iv_filter")
    public static SelenideElement Filter;
    @FindBy (id = "ru.averia.lostnfound:id/cb_dog")
    public static SelenideElement CBDog;
    @FindBy (id = "ru.averia.lostnfound:id/actv_city")
    //@FindBy (id = "ru.averia.lostnfound:id/til_city")
    public static SelenideElement CityFilter;
    @FindBy (id = "ru.averia.lostnfound:id/actv_breed")
    public static SelenideElement BreedFilter;
    @FindBy (id = "ru.averia.lostnfound:id/tv_pet_name")
    public static SelenideElement FilteredPet;
    @FindBy (id = "ru.averia.lostnfound:id/iv_switch")
    public static SelenideElement SwitchView;
    @FindBy (id = "ru.averia.lostnfound:id/cb_cat")
    public static SelenideElement CBCat;
    @FindBy (xpath = "//android.view.View[@content-desc=\"titile. snippet.\"]")
    public static SelenideElement PetMark;
    @FindBy (id = "ru.averia.lostnfound:id/container_selected_pet")
    public static SelenideElement InfoBlock;
    @FindBy (id = "ru.averia.lostnfound:id/action_lost")
    public static SelenideElement LostScreen;

    Logger logger;

    public LostScreenPage(String device, Dimension screensize, String userLogin) {
        super(device, screensize, userLogin);
        logger = LogManager.getLogger("LostScreen");
    }

    public LostScreenPage goLost(){

        return  page(this);
    }

    public LostScreenPage checkLostPetList() {
        //method depends on specific lost pets

        LostScreen.click();
        Location.shouldHave(Condition.text("Волгоград"));

        scrollUp(0.8);
        $x("//android.widget.TextView[contains(@text,'Чарли')]")
                .shouldBe(Condition.visible);

        Filter.click();
        CBDog.waitUntil(visible, 5000).click();

        //TODO: 5 - filter by city
        // bug! ru.averia.lostnfound:id/actv_city --> AS-86
        //CityFilter.sendKeys ("")

        BreedFilter.click();
        BreedFilter.sendKeys("Шотландская");

        tap(BreedFilter.getLocation().x + 50, BreedFilter.getLocation().y + 60);
        Filter.waitUntil(visible, 5000).click();
        FilteredPet.shouldHave(Condition.text("Тася"));

        return  page(this);
    }

    public LostScreenPage checkLostPetMap(){

        //starts after checkLostPetList method >> one filtered pet displayed
        //in the case of non filtered list should check different PetMarks

        SwitchView.click();

        //should check active_city blocked by AS-86
        Location.shouldHave(Condition.text("Волгоград"));

        Filter.click();
        CBCat.shouldBe(Condition.visible);
        PetMark.click();

        FilteredPet.waitUntil(visible, 5000).shouldHave(Condition.text("Тася"));
        InfoBlock.click();

        return  page(this);
    }

    public LostScreenPage checkLostPetAd() {

        //starts after checkLostPetMap method >> ad screen

        //TODO: add check "list or map view" + choose pet
        //TODO: move to MainScreen

        $x("//android.widget.TextView[contains(@text,'Тася')]")
                .shouldBe(Condition.visible);
        $(By.id("ru.averia.lostnfound:id/iv_photo"))
                .shouldBe(Condition.visible);
        $(By.id("ru.averia.lostnfound:id/tv_breed"))
                .shouldHave(Condition.text("Шотландская, кошка"));
        $(By.id("ru.averia.lostnfound:id/tv_description"))
                .shouldHave(Condition.text("Окрас - \"вискас\""));
        scrollUp(0.6);
        $(By.id("ru.averia.lostnfound:id/tv_location"))
                .shouldHave(Condition.text("Волгоград, Кировский район, остановка Авангард"));
        $(By.id("ru.averia.lostnfound:id/tv_owner_name"))
                .shouldHave(Condition.text("Светлана"));
        $(By.id("ru.averia.lostnfound:id/tv_owner_phone"))
                .shouldHave(Condition.text("+7 904 425-87-85"));
        $x("//android.widget.ImageButton").click();

        return  page(this);
    }
}
