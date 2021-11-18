package business.services;


import business.entities.Cupcake;
import business.entities.Kunde;
import business.entities.Order;
import business.entities.OrderLinje;
import business.persistence.DBConnector;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CheckoutServlet", value = "/CheckoutServlet")
public class CheckoutServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        DBConnector con = new DBConnector();
        boolean infomaterialIsChecked = request.getParameter("info") != null;;
        Kunde registerKunde = (Kunde) request.getAttribute("nyKunde");
        Kunde loginKunde = (Kunde) session.getAttribute("loginKunde");
        String email = request.getParameter("OrderEmail");
        System.out.println(email);
        double samletPris = (double) session.getAttribute("samletPris");
        List<Cupcake> cupcakeList = (List<Cupcake>) session.getAttribute("cupcakeList");


        int cupcakeId = 0;
        int ordrerId = 0;

        for (int i = 0; i < cupcakeList.size() ; i++)
        {
            Cupcake cupcake = new Cupcake(cupcakeList.get(i).getTopping().getToppingId(),cupcakeList.get(i).getBunde().getBundeId());
            try {
                con.insert(cupcake);
                cupcakeId = con.receiveHighestId(cupcake);
                cupcake.setCupcakeId(cupcakeId);
                cupcakeList.set(i,cupcake);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        Order order = new Order(loginKunde.getKundeId(), samletPris);
        try
        {
            con.insert(order);
            ordrerId = con.receiveHighestId(order);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        for (int i = 0; i < cupcakeList.size() ; i++)
        {
         OrderLinje orderLinje = new OrderLinje(cupcakeList.get(i).getCupcakeId(), ordrerId);

            try {
                con.insert(orderLinje);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(!infomaterialIsChecked)
        {

            request.getRequestDispatcher("/WEB-INF/OrderReceived.jsp").forward(request,response);
        }
        else
        {
            System.out.println("den var chekced");
            SendEmail sendEmail = new SendEmail();
            if(registerKunde != null)
            {
                System.out.println("jeg sender til registerkunde");
                sendEmail.setTo(registerKunde.getEmail());
                sendEmail.sendmail();
            }
            if(loginKunde != null)
            {
                System.out.println("jeg sender til kunde");
                sendEmail.setTo(loginKunde.getEmail());
                sendEmail.sendmail();
            }
            else
            {
                System.out.println("jeg sender til hvad der er blevet skrevet");
                sendEmail.setTo(email);
                sendEmail.sendmail();
            }
            request.getRequestDispatcher("/WEB-INF/OrderReceived.jsp").forward(request,response);
        }
    }
}
