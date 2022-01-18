package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistretionTest extends TestBase {
    @Test
    public void registrationSuccess() {

        int index = (int) System.currentTimeMillis();
        System.out.println(index);

        app.getUserHelper().openRegForm();
        app.getUserHelper().fillRegistrationForm("Merlin", "Fx", "mer" + index + "@gmail.com", "Fi12345@");
        app.getUserHelper().checkPolicy();
        app.getUserHelper().submitLoginForm();


        Assert.assertTrue(app.getUserHelper().isRegistrationSuccess());
    }

    @Test
    public void registrationSuccessModel() {

        int index = (int) System.currentTimeMillis();
        System.out.println(index);

       // User user = new User().withName("Lola").withLastName("Dow").withEmail

        app.getUserHelper().openRegForm();
        app.getUserHelper().fillRegistrationForm("Jon", "Fx", "fqx" + index + "@gmail.com", "Fw12345@");
        app.getUserHelper().checkPolicy();

        app.getUserHelper().pause(2000);

        app.getUserHelper().submitLoginForm();

        app.getUserHelper().pause(2000);

        Assert.assertTrue(app.getUserHelper().isRegistrationSuccess());
    }

}
