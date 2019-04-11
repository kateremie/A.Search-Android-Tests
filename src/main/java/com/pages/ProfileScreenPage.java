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

public class ProfileScreenPage extends BasePage {

    @FindBy (id = "ru.averia.lostnfound:id/action_settings")
    public SelenideElement ProfileTab;
    @FindBy (id = "ru.averia.lostnfound:id/tv_rate")
    public SelenideElement RateApp;
    @FindBy (id = "ru.averia.lostnfound:id/tv_name")
    public SelenideElement Name;
    @FindBy (id = "ru.averia.lostnfound:id/et_name")
    public SelenideElement EditName;
    @FindBy (id = "ru.averia.lostnfound:id/tv_surname")
    public SelenideElement Surname;
    @FindBy (id = "ru.averia.lostnfound:id/et_surname")
    public SelenideElement EditSurname;
    @FindBy (id = "ru.averia.lostnfound:id/tv_phone")
    public SelenideElement Phone;
    @FindBy (id = "ru.averia.lostnfound:id/et_phone")
    public SelenideElement EditPhone;
    @FindBy (id = "ru.averia.lostnfound:id/tv_email")
    public SelenideElement Email;
    @FindBy (id = "ru.averia.lostnfound:id/iv_edit")
    public SelenideElement Edit;
    @FindBy (id = "ru.averia.lostnfound:id/tv_edit_photo")
    public SelenideElement EditPhoto;
    @FindBy (id = "ru.averia.lostnfound:id/iv_save")
    public SelenideElement Save;
    @FindBy (id = "ru.averia.lostnfound:id/tv_logout")
    public SelenideElement LogOut;
    @FindBy (id = "ru.averia.lostnfound:id/iv_figure")
    public SelenideElement Logo;

    Logger logger;

    public ProfileScreenPage(String device, Dimension screensize, String userLogin) {
        super(device, screensize, userLogin);
        logger = LogManager.getLogger("ProfileScreen");
    }

    public ProfileScreenPage goProfile() {

        return  page(this);
    }

    public ProfileScreenPage checkProfile(){

        ProfileTab.waitUntil(visible, 5000).click();

        Name.should(Condition.text(userName));
        Surname.should(Condition.text(userSurname));
        Phone.should(Condition.text("+7 812 765-43-21")); //q.v. Constants >> phoneNumber
        Email.should(Condition.text(userLogin));

        return  page(this);
    }

    public ProfileScreenPage editProfile() {

        ProfileTab.click();

        Edit.shouldBe(Condition.visible).click();
        EditPhoto.click();
        phonePhoto("user");
        EditName.setValue("A.Test");
        EditSurname.setValue("A.User");
        EditPhone.setValue("8444567890");
        Save.click();
        Name.should(Condition.text("A.Test"));
        Surname.should(Condition.text("A.User"));
        Phone.should(Condition.text("+7 844 456-78-90"));

        return  page(this);
    }

    public ProfileScreenPage logOut() {

        ProfileTab.click();
        scrollUp(0.8);
        LogOut.click();
        LogOut.click();
        Logo.should(Condition.visible);

        return  page(this);
    }
}
