package carsharing;

import java.util.List;

public interface CustomerActions {
    void getCustomerList();
    void createCustomer(Customer customer);
    void getRentedCar(Customer customer);
    void returnCar(Customer customer);
    void rentCar(Customer customer, Car car);

}
