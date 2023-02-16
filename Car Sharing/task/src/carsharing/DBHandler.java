package carsharing;
import java.sql.*;

import static carsharing.CarImplementation.carList;
import static carsharing.CompanyImplementation.companyList;
import static carsharing.CustomerImplementation.customerList;
import static carsharing.Main.dbName;

public class DBHandler {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    //  Database credentials
    static final String PATH = "jdbc:h2:file:..\\task\\src\\carsharing\\db\\";

    public static void createDB() {

        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //STEP 2: Open a connection
            conn = DriverManager.getConnection(PATH + dbName);
            conn.setAutoCommit(true);
            //STEP 3: Execute a query
            stmt = conn.createStatement();

            String sql =  "CREATE TABLE IF NOT EXISTS COMPANY " +
                    "(ID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    " NAME VARCHAR(255) UNIQUE NOT NULL )";
            String sql2 = "CREATE TABLE IF NOT EXISTS car " +
                    "(ID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    "NAME VARCHAR(255) UNIQUE NOT NULL, " +
                    "COMPANY_ID INT NOT NULL, " +
                    "FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY (ID))";

            String sql3 = "CREATE TABLE IF NOT EXISTS CUSTOMER " +
                    "(ID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    "NAME VARCHAR(255) UNIQUE NOT NULL, " +
                    "RENTED_CAR_ID INT, " +
                    "FOREIGN KEY (RENTED_CAR_ID) REFERENCES CAR (ID))";
            updateCompanies();
            updateAllCars();
            readCustomers();
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql3);
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
    }

    public static void insert(String sql) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //STEP 2: Open a connection
            conn = DriverManager.getConnection(PATH + dbName);
            conn.setAutoCommit(true);
            //STEP 3: Execute a query
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try


    }

    public static void readCustomers() {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //STEP 2: Open a connection
            conn = DriverManager.getConnection(PATH + dbName);
            conn.setAutoCommit(true);
            //STEP 3: Execute a query
            stmt = conn.createStatement();

            String sql = "SELECT ID, NAME, RENTED_CAR_ID FROM CUSTOMER";

            ResultSet resultSet = stmt.executeQuery(sql);
            customerList.clear();
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                int id = resultSet.getInt("ID");
                int rented_car_id = resultSet.getInt("RENTED_CAR_ID");
                if (resultSet.wasNull()) {
                    rented_car_id = -1;
                }
                customerList.add(new Customer(id, name, rented_car_id));
            }
            resultSet.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try


    }

    public static void updateAllCars() {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //STEP 2: Open a connection
            conn = DriverManager.getConnection(PATH + dbName);
            conn.setAutoCommit(true);
            //STEP 3: Execute a query
            stmt = conn.createStatement();

            String sql = "SELECT ID, NAME, COMPANY_ID FROM CAR";

            ResultSet resultSet = stmt.executeQuery(sql);
            carList.clear();
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                int id = resultSet.getInt("ID");
                int companyID = resultSet.getInt("COMPANY_ID");
                carList.add(new Car(id, name, companyID));
            }
            resultSet.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try


    }


    public static void updateCompanies() {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //STEP 2: Open a connection
            conn = DriverManager.getConnection(PATH + dbName);
            conn.setAutoCommit(true);
            //STEP 3: Execute a query
            stmt = conn.createStatement();

            String sql = "SELECT ID, NAME FROM COMPANY";

            ResultSet resultSet = stmt.executeQuery(sql);
            companyList.clear();
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                int id = resultSet.getInt("ID");
                companyList.add(new Company(id, name));
            }
            resultSet.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
    }

    public static void updateAllCars(String sql) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //STEP 2: Open a connection
            conn = DriverManager.getConnection(PATH + dbName);
            conn.setAutoCommit(true);
            //STEP 3: Execute a query
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            carList.clear();
            int number = 1;
            while (resultSet.next()) {
               String name = resultSet.getString("NAME");
               int carID = resultSet.getInt("ID");
               carList.add(new Car(carID, name));
            }
            System.out.println();
            resultSet.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
    }

    public static void readRentedCar(String sql) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //STEP 2: Open a connection
            conn = DriverManager.getConnection(PATH + dbName);
            conn.setAutoCommit(true);
            //STEP 3: Execute a query
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            int companyId = 0;
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                companyId = resultSet.getInt("COMPANY_ID");
                System.out.println("Your rented car: ");
                System.out.println(name);

            }
            String sql2 = "SELECT NAME FROM COMPANY WHERE ID = " + companyId;
            resultSet = stmt.executeQuery(sql2);
            while (resultSet.next()) {
                String companyName = resultSet.getString("NAME");
                System.out.println("Company:");
                System.out.println(companyName);
            }
            System.out.println();
            resultSet.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
    }






}
