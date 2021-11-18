package business.services;

import business.entities.Kunde;
import business.exceptions.UserException;
import business.persistence.DBConnector;

import javax.naming.Context;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getRequestDispatcher("/WEB-INF/registrerKunde.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        DBConnector dbConnector = new DBConnector();

        String navn = request.getParameter("kundeNavn");
        String adresse = request.getParameter("kundeAdresse");
        int postNr = Integer.parseInt(request.getParameter("kundePostNr"));
        String email = request.getParameter("kundeEmail");
        String password = request.getParameter("kundePassword");
        boolean isAdmin = false;
        Kunde nyKunde = new Kunde(navn, email, password, adresse, postNr, isAdmin);

        try
        {
            dbConnector.insert(nyKunde);
        } catch(Exception e)
        {
            String fejlBesked = "Emailen findes allerede, prøv igen";
            request.setAttribute("fejlBesked", fejlBesked);
            request.getRequestDispatcher("/WEB-INF/registrerKunde.jsp").forward(request, response);
        }

        request.setAttribute("nyKunde", nyKunde);
        request.getRequestDispatcher("LoginServlet").forward(request, response);
        //request.getRequestDispatcher("/index.jsp").forward(request, response);

    }
}
