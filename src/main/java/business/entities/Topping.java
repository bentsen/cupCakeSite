package business.entities;

import java.util.List;

public class Topping
{
    private int toppingId;
    private String navn;
    private double pris;

    List<Topping> toppingList;

    public Topping(String navn, double pris)
    {
        this.navn = navn;
        this.pris = pris;
    }

    public Topping(int toppingId, String navn, double pris)
    {
        this.toppingId = toppingId;
        this.navn = navn;
        this.pris = pris;
    }

    public int getToppingId() {
        return toppingId;
    }

    public String getNavn() {
        return navn;
    }

    public double getPris() {
        return pris;
    }

    public List<Topping> getToppingList() {
        return toppingList;
    }
}