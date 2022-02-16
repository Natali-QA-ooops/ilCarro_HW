package tests;

import manager.MyDataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistretionTest extends TestBase {
    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
              if (app.getUserHelper().isLogOutPresent()) {
            app.getUserHelper().logout();
        }
    }

    @Test (groups = {"web"})
    public void registrationSuccess() {
        int index = (int) (System.currentTimeMillis() / 1000 % 3600);
        System.out.println(index);
        app.getUserHelper().openRegForm();
        app.getUserHelper().fillRegistrationForm("Lola", "Dow", "dow" + index + "@gmail.com", "Jj12345$");
        app.getUserHelper().pause(3000);
        app.getUserHelper().checkPolicyByXY();
        app.getUserHelper().submitForm();

        Assert.assertTrue(app.getUserHelper().isRegistrationSuccess());

    }


    @Test
    public void registrationSuccessModel() {

        int index = (int) (System.currentTimeMillis() / 1000 % 3600);

        User user = new User().withName("Lola").withLastName("Dow").withEmail("dow" + index + "@gmail.com").withPassword("Jj12345$");

        app.getUserHelper().openRegForm();
        app.getUserHelper().fillRegistrationForm(user);
        app.getUserHelper().pause(5000);
        app.getUserHelper().checkPolicy();
        app.getUserHelper().pause(5000);
        app.getUserHelper().submitForm();
        app.getUserHelper().pause(5000);

        Assert.assertTrue(app.getUserHelper().isRegistrationSuccess());

    }
//rabochii
    @Test (dataProvider = "registrationValidData", dataProviderClass = MyDataProviderUser.class)
    public void registrationSuccessDP(String name, String lastName, String email, String password) {

        app.getUserHelper().openRegForm();
        app.getUserHelper().pause(3000);
        app.getUserHelper().fillRegistrationForm(name,lastName,email,password);
        app.getUserHelper().pause(3000);
        app.getUserHelper().checkPolicyByXY();
        app.getUserHelper().pause(3000);
        app.getUserHelper().submitForm();

        Assert.assertTrue(app.getUserHelper().isRegistrationSuccess());

    }

    @AfterMethod(alwaysRun = true)
    public void postCondition() {
        app.getUserHelper().clickOkButton();

    }



}
