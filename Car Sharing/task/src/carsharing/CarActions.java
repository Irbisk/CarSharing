package carsharing;

import java.util.List;

public interface CarActions {
    void getCarsList(Company company);
    void createCar(String name, Company company);
    void getFreeCompanyCars(Company company);

}
