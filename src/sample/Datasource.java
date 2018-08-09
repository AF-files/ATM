package sample;

import java.sql.*;

public class Datasource {
    public static final String DB_NAME = "BankCustomers.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\F3ZZA\\Desktop\\Ecollege course work\\ATM builds\\" + DB_NAME;

    private Connection conn;

    public static final String CHECK_PIN = "SELECT account_pin FROM BankAccountCustomer WHERE BankAccountCustomer.account_id = ?";
    private PreparedStatement checkPin;
    public static final String GET_CUSTOMER = "SELECT * FROM BankAccountCustomer WHERE BankAccountCustomer.account_id = ?";
    private PreparedStatement getCustomer;
    public static final String UPDATE_CUSTOMER = "UPDATE BankAccountCustomer SET balance = ? WHERE BankAccountCustomer.account_id = ?";
    private PreparedStatement updateCustomer;

    //singleton
    private static Datasource instance = new Datasource();

    private Datasource() {

    }

    public static Datasource getInstance() {
        return instance;
    }

    public boolean open(){
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
            checkPin = conn.prepareStatement(CHECK_PIN);
            getCustomer = conn.prepareStatement(GET_CUSTOMER);
            updateCustomer =conn.prepareStatement(UPDATE_CUSTOMER);
            return true;
        }catch (SQLException e){
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close(){
        try{
            if(updateCustomer != null){
                updateCustomer.close();
            }
            if(getCustomer != null){
                getCustomer.close();
            }
            if(checkPin != null){
                checkPin.close();
            }
            if (conn != null) {
                conn.close();
            }
        }catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public boolean checkCustomerPin(int accNum, int pin){
        try{
            checkPin.setInt(1,accNum);
            ResultSet result = checkPin.executeQuery();
            int realPin = result.getInt(1);
            if(realPin != pin || result == null) {
                return false;
            } else{
                return true;
            }
        }catch (SQLException | NullPointerException e){
            System.out.println("Error: "+e.getMessage());
            return false;
        }
    }

    public void getCustomer(int id){
        try{
            getCustomer.setInt(1,id);
            ResultSet result = getCustomer.executeQuery();
            int accNum = result.getInt(1);
            double balance = result.getDouble(5);
            Bank temp = new Bank("Halifax","luton",234567);
            BankAccount.accNumber = accNum;
            BankAccount.balance = balance;
            BankAccount.bank = temp;
        }catch (SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    public boolean updateCustomer(){
        try {
            double updatedBal = BankAccount.balance;
            int accNum = BankAccount.accNumber;
            updateCustomer.setDouble(1, updatedBal);
            updateCustomer.setInt(2,accNum);
            int update = updateCustomer.executeUpdate();

            return update == 1;
        }catch (SQLException e){
            System.out.println("Error: "+e.getMessage());
            return false;
        }
    }
}
