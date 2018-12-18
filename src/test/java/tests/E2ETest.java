package tests;

import org.testng.annotations.Test;
import static data.Constants.*;
import static pages.MainScreenPage.*;
import static pages.ProfileScreenPage.goProfile;
import static pages.StartScreenPage.*;

public class E2ETest extends TestBase {

    @Test
    void Register(){
        start()
                .splashScreen()
                .register();
}

    @Test(dependsOnMethods = "Register")
    void AddPet(){
        goMain()
                .addAd("FirstAd",firstPet);
}

    @Test(dependsOnMethods = "AddPet")
    void CheckAd(){
        goMain()
                .checkAd(firstPet);
    }

    @Test (dependsOnMethods = "AddPet")
    void EditAd(){
        goMain()
                .editAd();

    }

    @Test(dependsOnMethods = "EditAd")
    void DeleteAd(){
        goMain()
                .deleteAd(firstPet);
    }

    @Test (dependsOnMethods = "DeleteAd")
    void CheckProfile(){

        goProfile()
                .checkProfile();
    }

    @Test(dependsOnMethods = "CheckProfile")
    void EditProfile(){
        goProfile()
                .editProfile();
    }

    @Test(dependsOnMethods = "CheckProfile")
    void Exit(){
        goProfile()
                .logOut();
    }

    @Test (dependsOnMethods = "Exit")
    void Login(){
        start()
                .login(testUser, superPass);
    }

    @Test (dependsOnMethods = "Login")
    void CheckAdsStatus(){
        goMain()
                .adsStatus();
    }

}
