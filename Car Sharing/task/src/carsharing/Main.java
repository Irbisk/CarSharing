package carsharing;

public class Main {
    public static String dbName;

    public static void main(String[] args) {
        dbName = "default";
        if (args[0].equals("-databaseFileName")) {
            dbName = args[1];
        }
        Programm programm = new Programm();
        DBHandler.createDB();
        programm.run();
    }
}