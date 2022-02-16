package manager;

import models.Car;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyDataProviderCar {
    @DataProvider
    public Iterator<Object[]> AddCarValidDataModel() {
        int index = (int) (System.currentTimeMillis() / 1000 % 3600);
        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{Car.builder()
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
                .carRegNumber("21-"+index+"-47")
                .price("130")
                .distanceIncluded("250")
                .features("type of")
                .about("Ne bita ne krashena")
                .build()});

        list.add(new Object[]{Car.builder()
                .address("Haifa, Israel")
                .make("Mazda")
                .model("3")
                .year("2022")
                .engine("1,8")
                .fuel("Petrol")
                .gear("AT")
                .w_d("AWD")
                .doors("4")
                .seats("5")
                .car_class("Lux")
                .fuelConsumption("3.0")
                .carRegNumber("22-"+index+"-40")
                .price("150")
                .distanceIncluded("250")
                .features("type of")
                .about("Best car for you")
                .build()});

        list.add(new Object[]{Car.builder()
                .address("Haifa, Israel")
                .make("Mazda")
                .model("3")
                .year("2022")
                .engine("1,8")
                .fuel("Petrol")
                .gear("AT")
                .w_d("AWD")
                .doors("4")
                .seats("5")
                .car_class("Lux")
                .fuelConsumption("3.0")
                .carRegNumber("23-"+index+"-42")
                .price("150")
                .distanceIncluded("250")
                .features("type of")
                .about("Best car for you").build()});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> addCarValidDataModelFromFileCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/cars1.csv")));
        String line = reader.readLine();

        while (line != null) {
            String []split = line.split(";");
            list.add(new Object[]{Car.builder()
                    .address(split[0])
                    .make(split[1])
                    .model(split[2])
                    .year(split[3])
                    .engine(split[4])
                    .fuel(split[5])
                    .gear(split[6])
                    .w_d(split[7])
                    .doors(split[8])
                    .seats(split[9])
                    .car_class(split[10])
                    .fuelConsumption(split[11])
                    .carRegNumber(split[12])
                    .price(split[13])
                    .distanceIncluded(split[14])
                    .features(split[15])
                    .about(split[16])
                    .build()});
            line = reader.readLine();
        }
        return list.iterator();
    }
}
