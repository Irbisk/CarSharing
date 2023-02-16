package carsharing;

import java.util.ArrayList;
import java.util.List;

public class CarImplementation implements CarActions{
    public static List<Car> carList = new ArrayList<>();

    public CarImplementation() {
        this.carList = new ArrayList<>();
    }

    @Override
    public void getCarsList(Company company) {
        int companyID = company.getID();
        String sql = "SELECT NAME, ID FROM CAR " +
                "WHERE COMPANY_ID = " + companyID;
        DBHandler.updateAllCars(sql);
    }

    public void getFreeCompanyCars(Company company) {
        String sql = "SELECT ID, NAME, COMPANY_ID FROM CAR " +
                "WHERE COMPANY_ID = " +  company.getID() +
                " AND ID NOT IN (SELECT RENTED_CAR_ID " +
                "FROM CUSTOMER WHERE RENTED_CAR_ID IS NOT NULL)";
        DBHandler.updateAllCars(sql);
    }

    @Override
    public void createCar(String name, Company company) {
            String sql = "INSERT INTO CAR (NAME, COMPANY_ID)" +
                    "VALUES ('" + name + "', " + company.getID() + ")";
            DBHandler.insert(sql);

    }
}

