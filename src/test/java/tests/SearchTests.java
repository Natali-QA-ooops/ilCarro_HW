package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SearchTests extends TestBase {

    @Test
    public void searchPeriodCurrentMonth() {
        //poisk perioda v tekyshem mes
        //2/7/2022 - 2/11/2022
        app.getSearchHelper().fillSearchFormCurrentMonth("Tel Aviv, Israel", "2/7/2022", "2/11/2022");
        app.getUserHelper().submitForm();
        Assert.assertTrue(app.getSearchHelper().isListOfCarsAppeared());
    }
    @Test
    public void searchPeriodCurrentMonth2() {
        //poisk perioda v tekyshem mes
        //2/7/2022 - 2/11/2022
        app.getSearchHelper().fillSearchFormCurrentMonth("Rehovot, Israel", "2/11/2022", "2/15/2022");
        app.getUserHelper().submitForm();
        Assert.assertTrue(app.getSearchHelper().isListOfCarsAppeared());
    }
    @Test
    public void searchPeriodAnyDataInFuture() {
        //"3/30/2022","6/25/2022"
        //"3/30/2022","1/31/2023"
        //"1/10/2023" "1/31/2023"
        app.getSearchHelper().fillSearchFormInFuture("Tel Aviv, Israel", "01/10/2023", "01/31/2023");
        app.getCarHelper().pause(7000);
        app.getUserHelper().submitForm();
        Assert.assertTrue(app.getSearchHelper().isListOfCarsAppeared());
    }
    @Test(enabled = false)
    public void typePeriodInPast() {
        //1/30/2022 - 1/31/2022

        //fill form search: addressTel Aviv, Israel---data 02/04/2022 - 02/06/2022
        //submitForm
        //assert massage "You can't pick date before today"
        app.getSearchHelper().fillSearchFormInPast("Tel Aviv, Israel","02/04/2022","02/06/2022");
        app.getCarHelper().pause(7000);

        Assert.assertTrue(app.getSearchHelper().isDataInPast());
        Assert.assertTrue(app.getSearchHelper().isYallaButtonInactive());
    }
    @AfterMethod
    public void posCondition() {
        app.getSearchHelper().returnToHome();
    }


}
