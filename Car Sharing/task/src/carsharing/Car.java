package carsharing;

public class Car {
    private int ID;
    private String name;
    private int companyID;

    public Car(int ID, String name, int companyID) {
        this.ID = ID;
        this.name = name;
        this.companyID = companyID;
    }

    public Car(int ID, String name) {
        this.ID = ID;
        this.name = name;
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

    public int getCompanyID() {
        return companyID;
    }
}
