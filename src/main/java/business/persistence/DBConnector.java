package business.persistence;

import business.entities.*;
import business.exceptions.UserException;

import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.List;


public class DBConnector {
    private final static String dburl = "jdbc:mysql://164.90.177.28:3306/CupcakeDB?serverTimezone=CET";
    private final static String dbuname = "mikkel";
    private final static String dbpassword = "Mikkel405!";
    private final static String dbdriver = "com.mysql.cj.jdbc.Driver";

    public void loadDriver(String dbdriver) {
        try {
            Class.forName(dbdriver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(dburl, dbuname, dbpassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    public String insert(Object object) throws Exception
    {
        loadDriver(dbdriver);
        Connection con = getConnection();
        String result = "";
        if (object instanceof Kunde) {
            result = "data entered succesfully";
            Kunde kunde = (Kunde) object;
            String sql = "insert into CupcakeDB.kunder(navn,email,password,adresse,postNr, isAdmin) values(?,?,MD5(?),?,?,?)";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, kunde.getName());
                ps.setString(2, kunde.getEmail());
                ps.setString(3, kunde.getPassword());
                ps.setString(4, kunde.getAddress());
                ps.setInt(5, kunde.getPostNr());
                ps.setInt(6, kunde.getIsAdmin());
                try
                {
                    ps.executeUpdate();
                } catch(SQLIntegrityConstraintViolationException e)
                {
                    throw new UserException("Email Already Exist");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                result = "Data not entered";
            }
        } else if (object instanceof Order) {
            result = "data entered succesfully";
            Order order = (Order) object;
            String sql = "insert into CupcakeDB.ordrer(kunde_id,pris) values(?,?)";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, order.getKundeId());
                ps.setDouble(2, order.getPris());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                result = "Data not entered";
            }
        } else if (object instanceof OrderLinje) {
            result = "data entered succesfully";
            OrderLinje orderLinje = (OrderLinje) object;
            String sql = "insert into CupcakeDB.OrdrerLinje(cupcake_id,ordrer_id) values(?,?)";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, orderLinje.getCupcakeId());
                ps.setDouble(2, orderLinje.getOrdrerId());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                result = "Data not entered";
            }
        } else if (object instanceof Cupcake) {
            result = "data entered succesfully";
            Cupcake cupcake = (Cupcake) object;
            String sql = "insert into CupcakeDB.cupcake(topping_id,bunde_id) values(?,?)";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, cupcake.getToppingId());
                ps.setInt(2, cupcake.getBundId());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                result = "Data not entered";
            }
        } else if (object instanceof Bunde) {
            result = "data entered succesfully";
            Bunde bunde = (Bunde) object;
            String sql = "insert into CupcakeDB.bunde(navn,pris) values(?,?)";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, bunde.getNavn());
                ps.setDouble(2, bunde.getPris());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                result = "Data not entered";
            }
        } else if (object instanceof Topping) {
            result = "data entered succesfully";
            Topping topping = (Topping) object;
            String sql = "insert into CupcakeDB.topping(navn,pris) values(?,?)";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, topping.getNavn());
                ps.setDouble(2, topping.getPris());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                result = "Data not entered";
            }
        }
        return result;
    }

    public String updateKunde(Kunde kunde)
    {
        loadDriver(dbdriver);
        Connection con = getConnection();
        String result = "";
        result = "data entered succesfully";
        String sql = "UPDATE CupcakeDB.kunder SET navn=?, email=?, password=MD5(?), adresse=?, postNr=?, isAdmin=? WHERE kunde_id="+kunde.getKundeId();
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, kunde.getName());
            ps.setString(2, kunde.getEmail());
            ps.setString(3, kunde.getPassword());
            ps.setString(4, kunde.getAddress());
            ps.setInt(5, kunde.getPostNr());
            ps.setInt(6,kunde.getIsAdmin());
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            result = "Data not entered";
        }
        return result;
    }

    public Kunde receiveKunde(String emailInput, String passwordInput)
    {
        loadDriver(dbdriver);
        Connection con = getConnection();

        Kunde kunde;
        String sql = " SELECT * FROM kunder WHERE email='"+emailInput+"' AND password=MD5('"+passwordInput+"')";
        try(PreparedStatement ps = con.prepareStatement(sql))
        {
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next()) {
                int kundeId = rs.getInt("kunde_id");
                String navn = rs.getString("navn");
                String adresse = rs.getString("adresse");
                int postNr = rs.getInt("postNr");
                String password = rs.getString("password");
                String email = rs.getString("email");
                int tinyIntIsAdmin = rs.getInt("isAdmin");
                boolean isAdmin;
                if (tinyIntIsAdmin == 1)
                {
                    isAdmin = true;
                }
                else
                {
                    isAdmin = false;
                }
                kunde = new Kunde(kundeId, navn, email, password, adresse, postNr, isAdmin);
                return kunde;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public Bunde receiveSpecificBunde(int id)
    {
        loadDriver(dbdriver);
        Connection con = getConnection();

        Bunde bunde;
        String sql = " SELECT * FROM bunde WHERE bunde_id='"+id+"'";
        try(PreparedStatement ps = con.prepareStatement(sql))
        {
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next()) {
                int bundeId = rs.getInt("bunde_id");
                String navn = rs.getString("navn");
                int pris = rs.getInt("pris");

                bunde = new Bunde(bundeId,navn,pris);
                return bunde;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public Topping receiveSpecificTopping(int id)
    {
        loadDriver(dbdriver);
        Connection con = getConnection();

        Topping topping;
        String sql = " SELECT * FROM topping WHERE topping_id='"+id+"'";
        try(PreparedStatement ps = con.prepareStatement(sql))
        {
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next()) {
                int toppingId = rs.getInt("topping_id");
                String navn = rs.getString("navn");
                int pris = rs.getInt("pris");

                topping = new Topping(toppingId,navn,pris);
                return topping;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public Order receiveSpecificOrderer(int id)
    {
        loadDriver(dbdriver);
        Connection con = getConnection();

        Order order;
        String sql = " SELECT * FROM ordrer WHERE kunde_id='"+id+"'";
        try(PreparedStatement ps = con.prepareStatement(sql))
        {
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next()) {
                int ordrerId = rs.getInt("topping_id");
                int kunde_id = rs.getInt("kunde_id");
                int pris = rs.getInt("pris");

                order = new Order(ordrerId,kunde_id,pris);
                return order;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<Kunde> receiveAllKunder()
    {
        loadDriver(dbdriver);
        Connection con = getConnection();
        Statement stmt = null;
        List<Kunde> kundeList = new ArrayList<>();
        Kunde kunde;
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = " SELECT * FROM CupcakeDB.kunder";
        try
        {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int kundeId = rs.getInt("kunde_id");
                String navn = rs.getString("navn");
                String adresse = rs.getString("adresse");
                int postNr = rs.getInt("postNr");
                String password = rs.getString("password");
                String email = rs.getString("email");
                boolean isAdmin;
                int tinyIntIsAdmin = rs.getInt("isAdmin");
                if (tinyIntIsAdmin == 1)
                {
                    isAdmin = true;
                }
                else
                {
                    isAdmin = false;
                }

                kunde = new Kunde(kundeId, navn, email, password, adresse, postNr, isAdmin);
                kundeList.add(kunde);

            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return kundeList;
    }

    public List receiveOrder(Kunde kunde) {
        loadDriver(dbdriver);
        Connection con = getConnection();
        Statement stmt = null;

        List<Order> orderList = new ArrayList<>();
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = " SELECT * FROM CupcakeDB.ordrer";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int ordrerId = rs.getInt("ordrer_id");
                int kundeId = rs.getInt("kunde_id");
                double pris = rs.getDouble("pris");
                if (kunde.getKundeId() == kundeId) {
                    Order order = new Order(ordrerId, kundeId, pris);
                    orderList.add(order);
                }
                else
                {
                    System.out.println("Kunde har ikke nogen ordrer!");
                    return orderList;
                }
            }
            return orderList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<OrderLinje> receiveOrderLinje(Order order) {
        loadDriver(dbdriver);
        Connection con = getConnection();
        Statement stmt = null;
        List<OrderLinje> orderLinjeList = new ArrayList<>();
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = " SELECT * FROM CupcakeDB.OrderLinje";
        try
        {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                int orderLinjeId = rs.getInt("orderLinje_id");
                int cupcakeId = rs.getInt("cupcake_id");
                int ordrerId = rs.getInt("ordrer_id");

                if(order.getOrdrerId() == ordrerId )
                {
                    OrderLinje orderLinje = new OrderLinje(orderLinjeId, cupcakeId, ordrerId);
                    orderLinjeList.add(orderLinje);
                }
            }
            return orderLinjeList;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Cupcake> receiveCupcake(OrderLinje orderLinje)
    {
        loadDriver(dbdriver);
        Connection con = getConnection();
        Statement stmt = null;
        List<Cupcake> cupcakeList = new ArrayList<>();
        try
        {
            stmt = con.createStatement();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        String sql = " SELECT * FROM CupcakeDB.cupcake";
        try
        {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                int cupcakeId = rs.getInt("cupcake_id");
                int toppingId = rs.getInt("topping_id");
                int bundId = rs.getInt("bund_id");
                if(orderLinje.getCupcakeId() == cupcakeId)
                {
                    Cupcake cupcake = new Cupcake(cupcakeId, toppingId, bundId);
                    cupcakeList.add(cupcake);
                }
                else
                {
                    System.out.println("no cupcakes");
                }
            }
            return cupcakeList;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<Bunde> receiveBunde()
    {
        loadDriver(dbdriver);
        Connection con = getConnection();
        Statement stmt = null;

        List<Bunde> bundeList = new ArrayList<>();
        try
        {
            stmt = con.createStatement();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        String sql = " SELECT * FROM CupcakeDB.bunde";
        try
        {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                int bundeId = rs.getInt("bunde_id");
                String navn = rs.getString("navn");
                double pris = rs.getDouble("pris");

                    Bunde bunde = new Bunde(bundeId, navn, pris);
                    bundeList.add(bunde);
            }
            return bundeList;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<Topping> receiveTopping()
    {
        loadDriver(dbdriver);
        Connection con = getConnection();
        Statement stmt = null;

        List<Topping> toppingList = new ArrayList<>();
        try
        {
            stmt = con.createStatement();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        String sql = " SELECT * FROM CupcakeDB.topping";
        try
        {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                int toppingId = rs.getInt("topping_id");
                String navn = rs.getString("navn");
                double pris = rs.getDouble("pris");

                    Topping topping = new Topping(toppingId, navn, pris);
                    toppingList.add(topping);

            }
            return toppingList;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public int receiveHighestId(Object object)
    {
        loadDriver(dbdriver);
        Connection con = getConnection();
        Statement stmt = null;

        int highestId;

        try
        {
            stmt = con.createStatement();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        if(object instanceof Cupcake) {
            String sql = " SELECT MAX(cupcake_id) FROM cupcake";
            try
            {
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    int cupcakeId = rs.getInt(1);
                    highestId = cupcakeId;
                    return cupcakeId;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(object instanceof Order)
        {
            String sql = " SELECT MAX(ordrer_id) FROM ordrer";
            try {
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    int ordrerId = rs.getInt(1);
                    highestId = ordrerId;
                    return ordrerId;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}