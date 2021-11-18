package business.entities;

import java.util.ArrayList;
import java.util.List;

public class Order
{
    private int ordrerId;
    private int kundeId;
    private double pris;

    List<Order> orderList;

    public Order(int kundeId, double pris)
    {
        this.kundeId = kundeId;
        this.pris = pris;
    }

    public Order(int ordrerId, int kundeId, double pris)
    {
        this.ordrerId = ordrerId;
        this.kundeId = kundeId;
        this.pris = pris;
    }

    public int getOrdrerId() {
        return ordrerId;
    }

    public int getKundeId() {
        return kundeId;
    }

    public double getPris() {
        return pris;
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}