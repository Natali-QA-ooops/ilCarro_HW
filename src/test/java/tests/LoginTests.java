package tests;

import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @Test
    public void loginSuccess(){
        app.getUserHelper().openLoginForm();
        app.getUserHelper().fillLoginForm("jack123@gmail.com", "Js12345@");
        app.getUserHelper().submitLoginForm();

    }

}
