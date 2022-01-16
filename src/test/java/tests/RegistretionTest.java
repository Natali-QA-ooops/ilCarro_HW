package tests;

import org.testng.annotations.Test;

public class RegistretionTest extends TestBase {

    @Test
    public void registrationSuccess() {
app.getUserHelper().openRegForm();
app.getUserHelper().fillRegistrationForm("John","Fix","fix123@gmail.com","Ff12345@");
app.getUserHelper().checkPolicy();
app.getUserHelper().submitForm();
    }

}
