package business.entities;
import java.util.ArrayList;
import java.util.List;

public class Cupcake {
    private Topping topping;
    private Bunde bunde;
    private int cupcakeId;
    private int toppingId;
    private int bundId;
    private int antal;
    double pris;
    public static List<Cupcake> cupcakeList = new ArrayList<>();

    public Cupcake(Topping topping, Bunde bunde, int antal) {

        this.pris = (topping.getPris() + bunde.getPris()) * antal;
        this.antal = antal;
        this.topping = topping;
        this.bunde = bunde;

    }


    public Cupcake(int toppingId, int bundId)
    {
        this.toppingId = toppingId;
        this.bundId = bundId;
    }


    public Cupcake(int cupcakeId, int toppingId, int bundId) {
        this.cupcakeId = cupcakeId;
        this.toppingId = toppingId;
        this.bundId = bundId;
    }


    public int getCupcakeId() {
        return cupcakeId;
    }

    public int getToppingId() {
        return toppingId;
    }

    public int getBundId() {
        return bundId;
    }

    public List<Cupcake> getCupcakeList() {
        return cupcakeList;
    }

    public Topping getTopping() {
        return topping;
    }

    public Bunde getBunde() {
        return bunde;
    }

    public int getAntal() {
        return antal;
    }

    public double getPris() {
        return pris;
    }

    public void setCupcakeId(int cupcakeId) {
        this.cupcakeId = cupcakeId;
    }
}