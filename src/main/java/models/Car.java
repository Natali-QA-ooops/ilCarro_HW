package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Car {
    String address;
    String make;
    String model;
    String year;
    String engine;
    String fuel;
    String gear;
    String w_d;
    String doors;
    String seats;
    String car_class;
    String fuelConsumption;
    String carRegNumber;
    String price;
    String distanceIncluded;
    String features;
    String about;
}
