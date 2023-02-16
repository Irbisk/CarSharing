package carsharing;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private int ID = 0;
    private String name;
    private List<Car> carList;

    public Company(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public Company(String name) {
        this.name = name;
        carList = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}
