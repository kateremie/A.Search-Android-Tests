package tests;

import org.testng.annotations.Test;

import static data.Constants.*;
import static pages.StartScreenPage.*;

public class DraftTest extends TestBase {

    @Test
    public void draftTest(){

        Init()
               //.SplashScreen()
              // .Register()
                .Login(testUser, superPass)
                .AddAd("FirstAd");
}
}
