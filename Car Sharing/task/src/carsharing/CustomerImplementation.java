package carsharing;

import java.util.ArrayList;
import java.util.List;

import static carsharing.CarImplementation.carList;

public class CustomerImplementation implements CustomerActions {
    public static List<Customer> customerList = new ArrayList<>();

    public CustomerImplementation() {
    }

    @Override
    public void getCustomerList() {
        DBHandler.readCustomers();
    }

    @Override
    public void createCustomer(Customer customer) {
        String sql = "INSERT INTO CUSTOMER (NAME)" +
                "VALUES ('" + customer.getName() + "')";
        DBHandler.insert(sql);
        DBHandler.readCustomers();
    }

    @Override
    public void getRentedCar(Customer customer) {
        DBHandler.readCustomers();
        DBHandler.updateAllCars();
        for (Customer c: customerList) {
            if (c.getID() == customer.getID()) {
                if (c.getRentedCarID() < 0) {
                    System.out.println("You didn't rent a car!");
                    break;
                } else {
                    String sql = "SELECT NAME, COMPANY_ID FROM CAR WHERE ID = " + c.getRentedCarID();
                    System.out.println(sql);
                    DBHandler.readRentedCar(sql);
                }
            }
        }
    }

    @Override
    public void returnCar(Customer customer) {
        DBHandler.readCustomers();
        for (Customer c: customerList) {
            if (c.getID() == customer.getID()) {
                if (c.getRentedCarID() < 0) {
                    System.out.println("You didn't rent a car!");
                    break;
                } else {
                    String sql = "UPDATE CUSTOMER " +
                            "SET RENTED_CAR_ID = NULL" +
                            " WHERE NAME = '" + customer.getName() + "'";
                    DBHandler.insert(sql);
                    System.out.println("You've returned a rented car!");
                    DBHandler.readCustomers();
                    break;
                }
            }
        }
    }

    @Override
    public void rentCar(Customer customer, Car car) {
        String sql = "UPDATE CUSTOMER " +
                "SET RENTED_CAR_ID = " + car.getID() +
                " WHERE NAME = '" + customer.getName() + "'";
        DBHandler.insert(sql);
        customer.setRentedCarID(car.getID());
        System.out.println("You rented '" + car.getName() + "'");
        DBHandler.readCustomers();

    }
}
