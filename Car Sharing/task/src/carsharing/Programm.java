package carsharing;


import java.util.List;
import java.util.Scanner;

import static carsharing.CarImplementation.carList;
import static carsharing.CompanyImplementation.companyList;
import static carsharing.CustomerImplementation.customerList;

public class Programm {
    Scanner scanner = new Scanner(System.in);
    CompanyActions companyActions = new CompanyImplementation();
    CarActions carActions = new CarImplementation();
    CustomerActions customerActions = new CustomerImplementation();


    public void run() {
        boolean on = true;
        while (on) {
            System.out.println("1. Log in as a manager\n" +
                    "2. Log in as a customer\n" +
                    "3. Create a customer\n" +
                    "0. Exit");

            int entering = scanner.nextInt();
            scanner.nextLine();
            switch (entering) {
                case  1:
                    companyMenu();
                    break;
                case 2:
                    chooseCustomer();
                    break;
                case 3:
                    createCustomer();
                    break;
                case 0:
                    on = false;
                    break;
                default:
                    System.out.println("Wrong command");
            }
        }
    }

    private void chooseCustomer() {
        DBHandler.readCustomers();
        if (customerList.isEmpty()) {
            System.out.println("The customer list is empty!");
        } else {
            System.out.println("Customer list:");
            int number = 1;
            for (Customer customer: customerList) {
                System.out.println(number++ + ". " + customer.getName());
            }
            System.out.println("0. Back");
            int entering = scanner.nextInt();
            scanner.nextLine();
            if (entering != 0 && entering <= customerList.size()) {
                Customer customer = customerList.get(entering - 1);
                customerMenu(customer);
            }
        }
    }

    private void customerMenu(Customer customer) {
        boolean on = true;
        while (on) {
            System.out.println("1. Rent a car\n" +
                    "2. Return a rented car\n" +
                    "3. My rented car\n" +
                    "0. Back");
            int value = scanner.nextInt();
            scanner.nextLine();
            switch (value) {
                case 1:
                    rentCar(customer);
                    break;
                case 2:
                    customerActions.returnCar(customer);
                    break;
                case 3:
                    customerActions.getRentedCar(customer);
                    break;
                case 0:
                    on = false;
                default:
            }
        }

    }

    private void rentCar(Customer customer) {
        if (companyList.isEmpty()) {
            System.out.println("The company list is empty!");
        } else {
            if (customer.getRentedCarID() >= 0) {
                System.out.println("You've already rented a car!");
            } else {
                chooseCompanyForRent(customer);
            }
        }

    }

    private void chooseCompanyForRent(Customer customer) {
        int number = 1;
        System.out.println("Choose the company:");
        for (Company company: companyList) {
            System.out.println(number++ + ". " + company.getName());
        }
        System.out.println("0. Back");
        int entering = scanner.nextInt();
        scanner.nextLine();
        if (entering != 0 && entering <= companyList.size()) {
           chooseACarForRent(companyList.get(entering - 1), customer);
        } else if (entering != 0) {
            System.out.println("Wrong value! try again!");
        }
    }

    private void chooseACarForRent(Company company, Customer customer) {
        System.out.println("Choose a car:");
        int number = 1;
        carActions.getFreeCompanyCars(company);
        for (Car car: carList) {
            System.out.println(number++ + ". " + car.getName());
        }
        int entering = scanner.nextInt();
        scanner.nextLine();
        if (entering != 0 && entering <= companyList.size()) {
            customerActions.rentCar(customer, carList.get(entering - 1));
        } else if (entering != 0) {
            System.out.println("Wrong value! try again!");
        }
    }

    private void createCustomer() {
        System.out.println("Enter the customer name:");
        String name = scanner.nextLine();
        Customer customer = new Customer(name);
        customerActions.createCustomer(customer);
        System.out.println("The customer was added!");
    }



    private void carMenu(Company company) {
        boolean on = true;
        while (on) {
            System.out.println("1. Car list\n" +
                    "2. Create a car\n" +
                    "0. Back");

            int entering = scanner.nextInt();
            scanner.nextLine();

            switch (entering) {
                case 1:
                    carActions.getCarsList(company);
                    if (carList.isEmpty()) {
                        System.out.println("The car list is empty!");
                    } else {
                        int number = 1;
                        for (Car car: carList) {
                            System.out.println(number++ + ". " + car.getName());
                        }
                    }
                    break;
                case 2:
                    System.out.println("Enter the car name:");
                    String name = scanner.nextLine();
                    carActions.createCar(name, company);
                    System.out.println("The car was added!");
                    break;
                case 0 :
                    on = false;
                    break;
                default:
                    System.out.println("Wrong value! try again!");
            }
        }
    }

    private void companyMenu() {
        boolean on = true;
        while (on) {
            System.out.println("1. Company list\n" +
                    "2. Create a company\n" +
                    "0. Back");
            int entering = scanner.nextInt();
            scanner.nextLine();
            switch (entering) {
                case 1:
                    companyActions.getCompaniesList();
                    if (companyList.isEmpty()) {
                        System.out.println("The company list is empty!");
                    } else {
                        chooseCompanyMenu();
                    }
                    break;
                case 2:
                    System.out.println("Enter the company name:");
                    String name = scanner.nextLine();
                    companyActions.createCompany(new Company(name));
                    System.out.println("The company was created!\n");
                    break;
                case 0:
                    on = false;
                    run();
                    break;
                default:
                    System.out.println("Wrong command");
            }
        }
    }

    private void chooseCompanyMenu() {
        System.out.println("Choose the company:");
        int number = 1;
        for (Company company: companyList) {
            System.out.println(number++ + ". " + company.getName());
        }
        System.out.println("0. Back");
        int entering = scanner.nextInt();
        scanner.nextLine();
        if (entering != 0 && entering <= companyList.size()) {
            carMenu(companyList.get(entering - 1 ));
        } else if (entering != 0) {
            System.out.println("Wrong value! try again!");
        }
    }

}
