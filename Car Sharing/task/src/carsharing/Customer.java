package carsharing;

public class Customer {
    private int ID = 0;
    private String name;
    private int rentedCarID = -1;

    public Customer(int ID, String name, int rentedCarID) {
        this.ID = ID;
        this.name = name;
        this.rentedCarID = rentedCarID;
    }

    public Customer(String name) {
        this.name = name;
    }

    public void setRentedCarID(int rentedCarID) {
        this.rentedCarID = rentedCarID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getRentedCarID() {
        return rentedCarID;
    }
}
