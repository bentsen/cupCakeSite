package business.entities;

import java.util.List;

public class Bunde
{
    private int bundeId;
    private String navn;
    private double pris;

    List<Bunde> bundeList;

    public Bunde(String navn, double pris)
    {
        this.navn = navn;
        this.pris = pris;
    }

    public Bunde(int bundeId, String navn, double pris)
    {
        this.bundeId = bundeId;
        this.navn = navn;
        this.pris = pris;
    }

    public int getBundeId() {
        return bundeId;
    }

    public String getNavn() {
        return navn;
    }

    public double getPris() {
        return pris;
    }

    public List<Bunde> getBundeList() {
        return bundeList;
    }
}