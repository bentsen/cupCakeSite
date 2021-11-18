package business.services;

import business.entities.Bunde;
import business.entities.Cupcake;
import business.entities.Kunde;
import business.entities.Topping;
import business.exceptions.UserException;
import business.persistence.DBConnector;
import business.services.CupCakeUtil.Initializer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddToBasket", value = "/AddToBasket")
public class AddToBasketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBConnector con = new DBConnector();

        List<Cupcake> cupcakeList = Cupcake.cupcakeList;

        String act = request.getParameter("orderAct");

        if (act.equals("addToBasket")) {

            int antal = Integer.parseInt(request.getParameter("Antal"));

            int toppingId = Integer.parseInt(request.getParameter("chooseTopping"));

            int bundId = Integer.parseInt(request.getParameter("chooseBund"));

            Kunde registerKunde = (Kunde) request.getAttribute("nyKunde");

            Kunde loginKunde = (Kunde) session.getAttribute("loginKunde");


            // bønnes metode skal være her.
            Bunde bund = con.receiveSpecificBunde(bundId);
            Topping topping = con.receiveSpecificTopping(toppingId);


            Cupcake cupcake = new Cupcake(topping, bund, antal);


            if (cupcakeList != null) {
                cupcakeList.add(cupcake);
                session.setAttribute("cupcakeList", cupcakeList);
            }
            if (cupcakeList == null) {
                cupcakeList = Cupcake.cupcakeList;
                cupcakeList.add(cupcake);
                session.setAttribute("cupcakeList", cupcakeList);
            }

            double samletPris = 0;
            for (int i = 0; i < cupcakeList.size(); i++) {
                samletPris += cupcakeList.get(i).getPris();

            }

            session.setAttribute("samletPris", samletPris);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }
}







