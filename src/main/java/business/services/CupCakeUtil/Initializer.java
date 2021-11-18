package business.services.CupCakeUtil;

import business.entities.Bunde;
import business.entities.Cupcake;
import business.entities.Topping;
import business.persistence.DBConnector;

import java.util.List;

public class Initializer {

    private static List<Topping> toppingList = null;
    private static List<Bunde> bundeList = null;




    public static List<Topping> getToppingList() {
        DBConnector dbConnector = new DBConnector();
        try {
            if (toppingList == null) {
                toppingList = dbConnector.receiveTopping();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toppingList;
    }

    public static List<Bunde> getBundeList() {
        DBConnector dbConnector = new DBConnector();
        try {

            if (bundeList == null) {
                bundeList = dbConnector.receiveBunde();
            }
        } catch (Exception e) {
        }
        return bundeList;
    }

    public static String findToppingName(int id) {
        DBConnector dbConnector = new DBConnector();
        toppingList= dbConnector.receiveTopping();
        for (int i = 0; i < toppingList.size(); i++) {
            if (toppingList.get(i).getToppingId() == id) {
                String navn = toppingList.get(i).getNavn();

                return navn;
            }
        }
        return null;
    }
    public static String findBundeName(int id) {
        DBConnector dbConnector = new DBConnector();
        bundeList= dbConnector.receiveBunde();
        for (int i = 0; i < bundeList.size(); i++) {
            if (bundeList.get(i).getBundeId() == id) {
                String navn = bundeList.get(i).getNavn();

                return navn;
            }
        }
        return null;
    }

}

