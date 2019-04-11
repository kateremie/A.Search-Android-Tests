package com.tests;

import org.testng.annotations.*;
import static com.data.Constants.*;

public class  E2ETest extends BaseTest {

    @Test
    void Registration()
    {
        startScreenPage
                .start()
                .splashScreen()
                .registration();
    }

    @Test(dependsOnMethods = "Registration")
    void AddPet(){
       mainScreenPage
               .goMain()
               .addPet("FirstAd",firstPet);
               //.checkPetInfo(firstPet);
    }

    @Test(dependsOnMethods = "AddPet")
    void CheckPet(){
        mainScreenPage
                .goMain()
                .checkPetInfo(firstPet);
    }

    @Test (dependsOnMethods = "AddPet", priority = 1)
    void EditPet(){
        mainScreenPage
                .goMain()
                .editPet();

    }

    //@Test(dependsOnMethods = "EditPet")
    @Test(dependsOnMethods = "AddPet", priority = 2)
    void DeletePet(){
        mainScreenPage
                .goMain()
                .deletePet(firstPet);
    }

    //@Test(dependsOnMethods = "DeletePet")
    @Test(dependsOnMethods = "AddPet", priority = 1)
    void CheckProfile(){
        profileScreenPage
                .goProfile()
                .checkProfile();
    }

    //@Test(dependsOnMethods = "CheckProfile")
    @Test(dependsOnMethods = "AddPet", priority = 1)
    void EditProfile(){
        profileScreenPage
                .goProfile()
                .editProfile();
    }

    //@Test(dependsOnMethods = "CheckProfile")
    @Test(dependsOnMethods = "Registration", priority = 2)
    void Exit(){
        profileScreenPage
                .goProfile()
                .logOut();
    }

    //@Test(dependsOnMethods = "Exit")
    @Test(priority = 3)
    void Login(){
        startScreenPage
                .start()
                .login(testUser, superPass);
    }

    @Test (dependsOnMethods = "Login")
    void CheckAdsStatus(){
        mainScreenPage
                .goMain()
                .checkPetStatus();
    }

    /*@Test (dependsOnMethods = "Login")
    void CheckLostPetsScreen(){
        lostScreenPage
                .goLost()
                .checkLostPetList()
                .checkLostPetMap();
    }

    @Test (dependsOnMethods = "Login")
    void CheckLostPetAd(){
        lostScreenPage
                .goLost()
                .checkLostPetAd();
    }*/
}