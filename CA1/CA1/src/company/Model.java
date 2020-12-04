package company;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {

    private static Model instance = null;

    public static synchronized Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    private DrinkTableGateway gateway;


    private Model() {

        try {
            Connection conn = DBConnection.getInstance();
            this.gateway = new DrinkTableGateway(conn);


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "Problem Connecting to the Database, have you added your JDBC Driver .jar file?", ex);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }



        public List<Drink> getDrinks() { return  (gateway.getDrinks()); }

        public boolean addDrink (Drink d){
            return (gateway.insertDrink(d));
        }

         public boolean deleteDrink(int id) { return (gateway.deleteDrink(id) ); }

    public boolean updateDrink(int id,String name,Double price,Double size, int alcoholic, int stock){
        return this.gateway.updateDrink(id, name, price, size, alcoholic, stock);
    }

    public boolean updateDrinkStock(String name, int stock){
        return this.gateway.updateDrinkStock(name, stock);
    }

    public List<Drink> getDrink(String name) { return  gateway.getDrinkByName(name); }

    public List<Drink> getDrinkById(int id) { return  gateway.getDrinkById(id); }

    public boolean updateDrinkPrice(String name, double price){
        return this.gateway.updateDrinkPrice(name, price);
    }
}