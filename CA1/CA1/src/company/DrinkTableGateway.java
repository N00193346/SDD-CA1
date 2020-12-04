package company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DrinkTableGateway {
    private static final String TABLE_NAME = "drink";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_SIZE = "size";
    private static final String COLUMN_ALCOHOLIC = "alcoholic";
    private static final String COLUMN_STOCK = "stock";

    private Connection mConnection;


    public DrinkTableGateway(Connection connection) {
        mConnection = connection;
    }

    //Insert drink object into database
    public boolean insertDrink(Drink d) {

        String query;
        PreparedStatement stmt;
        int numRowsAffected;

        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_NAME + ", " +
                COLUMN_PRICE + ", " +
                COLUMN_SIZE + ", " +
                COLUMN_ALCOHOLIC + ", " +
                COLUMN_STOCK +
                ") VALUES (?, ?, ?, ?, ?)";

        try {
            stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, d.getName());
            stmt.setDouble(2, d.getPrice());
            stmt.setDouble(3, d.getSize());

            stmt.setInt(4, d.getAlcoholic());
            stmt.setInt(5, d.getStock());

            numRowsAffected = stmt.executeUpdate();

            if (numRowsAffected == 1) {

                return true;
            }

        } catch (SQLException e) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in DrinkTableGateway : insertDrink(), Check the SQL you have created to see where your error is", e);
        }

        return false;
    }

   //Get drinks function, return results in drinks array
    public List<Drink> getDrinks() {
        String query;
        List<Drink> drinks = new ArrayList<Drink>();

        //Variables
        int id;
        String name;
        double price, size;
        int alcoholic, stock;

        //Drink object
        Drink d;

        //Sql query
        query = "SELECT * FROM " + TABLE_NAME;

        try {
            Statement stmt;
            ResultSet rs;

            stmt = this.mConnection.createStatement();

            rs = stmt.executeQuery(query);

            //Loop through data from database
            while (rs.next()) {
                id = rs.getInt(COLUMN_ID);
                name = rs.getString(COLUMN_NAME);
                price = rs.getDouble(COLUMN_PRICE);
                size = rs.getDouble(COLUMN_SIZE);

                alcoholic = rs.getInt(COLUMN_ALCOHOLIC);
                stock = rs.getInt(COLUMN_STOCK);

                //Create new drink object
                d = new Drink(id, name, price, size, alcoholic, stock);
                //Add to array
                drinks.add(d);
            }
          //If there is a problem with the SQL statement
        } catch (SQLException e) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in DrinkTableGateway : getDrinks(), Check the SQL you have created to see where your error is", e);
        }

        // Return the array
        return drinks;
    }

    //Delete Drink function
    public boolean deleteDrink(int id) {
        int numRowsAffected;

        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "= ?";
        try {
            PreparedStatement stmt;
            stmt = mConnection.prepareStatement(query);
            stmt.setInt(1, id);


            numRowsAffected = stmt.executeUpdate();

            if (numRowsAffected == 1) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in DrinkTableGateway : deleteDrink(), Check the SQL you have created to see where your error is", e);
        }
        return false;
    }

    //    Update Function
    public boolean updateDrink(int id,String name, double price, double size, int alcoholic, int stock)  {

        String query;
        PreparedStatement stmt;
        int numRowsAffected;

        query = "UPDATE drink SET name = (?), price = (?),size = (?),alcoholic = (?),stock = (?) WHERE id = ?";
        try {

            stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setDouble(3, size);
            stmt.setInt(4, alcoholic);
            stmt.setInt(5, stock);
            stmt.setInt(6, id);

            numRowsAffected = stmt.executeUpdate();

            if (numRowsAffected == 1) {

                return true;
            }

        }
        catch (SQLException e)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in DrinkTableGateway : updateDrink(), Check the SQL you have created to see where your error is", e);
        }
        return false;
    }

    //    Update Drink Stock Function
    public boolean updateDrinkStock(String name,int stock)  {

        String query;
        PreparedStatement stmt;
        int numRowsAffected;

        //SQL Query
        query = "UPDATE drink SET stock = (?) WHERE name = ?";
        try {
            stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, stock);
            stmt.setString(2, name);

            numRowsAffected = stmt.executeUpdate();

            if (numRowsAffected == 1) {
                return true;
            }
        }
        catch (SQLException e)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in DrinkTableGateway : updateDrinkStock(), Check the SQL you have created to see where your error is", e);
        }

        return false;
    }

    //    Update Drink Stock Function
    public boolean updateDrinkPrice(String name,double price)  {

        String query;
        PreparedStatement stmt;
        int numRowsAffected;

        query = "UPDATE drink SET price = (?) WHERE name = ?";
        try {

            stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, price);
            stmt.setString(2, name);

            numRowsAffected = stmt.executeUpdate();

            if (numRowsAffected == 1) {
                return true;
            }
        }
        catch (SQLException e)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in DrinkTableGateway : updateDrinkPrice(), Check the SQL you have created to see where your error is", e);
        }

        return false;
    }

    //Get drink by Name
    public List<Drink> getDrinkByName(String name) {
        List<Drink> oneDrink = new ArrayList<Drink>();
        String query = "SELECT * FROM drink WHERE name= ?";

        int id;
        double price, size;
        int alcoholic, stock;


        try {

            PreparedStatement stmt = this.mConnection.prepareStatement(query);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {

                id = rs.getInt("id");
                name = rs.getString("name");
                price = rs.getDouble("price");
                size = rs.getDouble("size");
                alcoholic = rs.getInt("alcoholic");
                stock = rs.getInt("stock");

                Drink d = new Drink(id, name, price, size, alcoholic, stock);
                oneDrink.add(d);
            }
            return oneDrink;
        }
        catch (SQLException e)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in DrinkTableGateway : updateDrinkStock(), Check the SQL you have created to see where your error is", e);
        }

        return oneDrink;
    }

    //Get Drink by Id
    public List<Drink> getDrinkById(int id) {
        List<Drink> drinkName = new ArrayList<Drink>();
        String query = "SELECT * FROM drink WHERE id= ?";

       String name;
        double price, size;
        int alcoholic, stock;

        try {
            PreparedStatement stmt = this.mConnection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {

                id = rs.getInt("id");
                name = rs.getString("name");
                price = rs.getDouble("price");
                size = rs.getDouble("size");
                alcoholic = rs.getInt("alcoholic");
                stock = rs.getInt("stock");

                Drink d = new Drink(id, name, price, size, alcoholic, stock);
                drinkName.add(d);
            }
            return drinkName;
        }
        catch (SQLException e)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in DrinkTableGateway : updateDrinkStock(), Check the SQL you have created to see where your error is", e);
        }

        return drinkName;
    }

}

