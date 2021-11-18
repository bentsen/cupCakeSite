package business.entities;

import java.util.List;

public class OrderLinje
{
    private int orderLinjeId;
    private int cupcakeId;
    private int ordrerId;

    List<OrderLinje> orderLinjeList;

    public OrderLinje(int cupcakeId, int ordrerId)
    {
        this.cupcakeId = cupcakeId;
        this.ordrerId = ordrerId;
    }

    public OrderLinje(int orderLinjeId, int cupcakeId, int ordrerId)
    {
        this.orderLinjeId = orderLinjeId;
        this.cupcakeId = cupcakeId;
        this.ordrerId = ordrerId;
    }

    public int getOrderLinjeId() {
        return orderLinjeId;
    }

    public int getCupcakeId() {
        return cupcakeId;
    }

    public int getOrdrerId() {
        return ordrerId;
    }

    public List<OrderLinje> getOrderLinjeList() {
        return orderLinjeList;
    }
}