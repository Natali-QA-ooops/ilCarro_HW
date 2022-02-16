package tests;

import manager.MyDataProviderCar;
import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewCar extends TestBase{
    @BeforeMethod(alwaysRun = true)
    public void preCond(){
        //if !logged---->login
if (!app.getUserHelper().isLogOutPresent()){
    User user = new User().withEmail("jack123@gmail.com").withPassword("Js12345@");
    app.getUserHelper().login(user);

    logger.info("Car was added for user"+user.toString());
}

    }
    @Test (groups = {"web"})
    public void addNewCarSuccess(){
         int index = (int)(System.currentTimeMillis()/1000)%3600;
        System.out.println(index);

        Car car = Car.builder()
                .address("Tel-Aviv, Israel")
                .make("Hunday")
                .model("Veloster")
                .year("2021")
                .engine("1,8")
                .fuel("Petrol")
                .gear("AT")
                .w_d("AWD")
                .doors("3")
                .seats("4")
                .car_class("S")
                .fuelConsumption("3.0")
                .carRegNumber("32-876-"+index)
                .price("130")
                .distanceIncluded("250")
                .features("type of")
                .about("Ne bita ne krashena")
                .build();
        logger.info(car.toString()+"car was added");
        app.getCarHelper().openCarForm();
        app.getCarHelper().fillCarForm(car);
        app.getCarHelper().attachFoto("C:/Users/Natali/Downloads/1.jpg");
        app.getCarHelper().submitForm();

        Assert.assertTrue(app.getCarHelper().isCarAdded());

    }

//rabochii
    @Test (dataProvider = "AddCarValidDataModel",dataProviderClass = MyDataProviderCar.class)
    public void addNewCarSuccessDataProvider(Car car){

        logger.info(car.toString()+"car was added");

        app.getCarHelper().openCarForm();
        app.getCarHelper().fillCarForm(car);
        app.getCarHelper().attachFoto("C:/Users/Natali/Downloads/1.jpg");
        app.getCarHelper().submitForm();

        Assert.assertTrue(app.getCarHelper().isCarAdded());

    }
//RABoCHII
    @Test (dataProvider = "addCarValidDataModelFromFileCSV",dataProviderClass = MyDataProviderCar.class,enabled = false)
    public void addNewCarSuccessReadFromFile(Car car){

        logger.info(car.toString()+"car was added");

        app.getCarHelper().openCarForm();
        app.getCarHelper().fillCarForm(car);
        app.getCarHelper().attachFoto("C:/Users/Natali/Downloads/1.jpg");
        app.getCarHelper().submitForm();

        Assert.assertTrue(app.getCarHelper().isCarAdded());

    }

    @AfterMethod(alwaysRun = true)
    public void postCond(){
        app.getCarHelper().clickSearchButton();
        app.getUserHelper().logout();
    }

}
