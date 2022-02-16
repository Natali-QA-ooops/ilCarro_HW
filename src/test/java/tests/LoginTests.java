package tests;

import manager.MyDataProviderUser;
import models.User;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {


    @BeforeMethod(alwaysRun = true)
    public void preCond() {
        if (app.getUserHelper().isLogOutPresent()) {
            app.getUserHelper().logout();
        }
    }

    @Test(groups = {"web"})
    public void loginSuccess() {
        logger.info("Test start with email: [jack123@gmail.com] & password: [Js12345@]");

        app.getUserHelper().openLoginForm();
        app.getUserHelper().fillLoginForm("jack123@gmail.com", "Js12345@");
        app.getUserHelper().submitForm();

    }

    @Test
    public void loginSuccessModel() {
        User user = new User().withEmail("jack123@gmail.com").withPassword("Js12345@");

        logger.info("The test start with data" + user.toString());

        app.getUserHelper().openLoginForm();
        app.getUserHelper().fillLoginForm(user);
        app.getUserHelper().submitForm();


        app.getUserHelper().takeScreenShot("src/test/screenshorts/sce1.png");
    }

    //rabochii
    @Test(dataProvider = "loginValidDataModel", dataProviderClass = MyDataProviderUser.class)
    public void loginSuccessModelDataProvider(User user) {

        logger.info("The test start with data" + user.toString());

        app.getUserHelper().openLoginForm();
        app.getUserHelper().fillLoginForm(user);
        app.getUserHelper().submitForm();

        app.getUserHelper().takeScreenShot("src/test/screenshorts/sce1.png");
    }

    //rabochii
    @Test(dataProvider = "loginValidData", dataProviderClass = MyDataProviderUser.class, enabled = false)
    public void loginSuccessDataProvider(String email, String password) {

        logger.info("Test start with 'email': " + email + " && 'password': " + password);
        app.getUserHelper().pause(3000);
        app.getUserHelper().openLoginForm();
        app.getUserHelper().fillLoginForm(email, password);
        app.getUserHelper().submitForm();
        //Assert.assertTrue(app.getUserHelper().isLogged());
    }

    @Test(dataProvider = "loginValidDataCSV", dataProviderClass = MyDataProviderUser.class, enabled = false)
    public void loginSuccessTestDataProviderCSV(String email, String password) {
        logger.info("Test start with 'email': " + email + " && 'password': " + password);
        app.getUserHelper().pause(3000);
        app.getUserHelper().openLoginForm();
        app.getUserHelper().fillLoginForm(email, password);
        app.getUserHelper().submitForm();
        //Assert.assertTrue(app.getUserHelper().isLogged());
    }

    @AfterMethod(alwaysRun = true)
    public void postCond() {
        app.getUserHelper().clickOkButton();
    }


}
