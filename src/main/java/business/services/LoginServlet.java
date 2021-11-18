package business.services;

import business.entities.Kunde;
import business.persistence.DBConnector;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession httpSession = request.getSession();
        DBConnector dbConnector = new DBConnector();
        Kunde registerKunde = (Kunde) request.getAttribute("nyKunde");
        String navn = "";

        //Login via login siden
        if(registerKunde == null)
        {
            String email = request.getParameter("loginEmail");
            String password = request.getParameter("loginPassword");

            Kunde loginKunde = dbConnector.receiveKunde(email, password);

            try
            {
                navn = loginKunde.getName();
            } catch (NullPointerException npE)
            {
                String fejlBesked = "Forkert Mail eller Password, prøv igen";
                request.setAttribute("fejlBesked", fejlBesked);
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }


//            if (navn.length() > 9)
//            {
//                navn = navn.substring(0, 9);
//            }

            httpSession.setAttribute("kundeNavn", navn); //SKAL BRUGES
            httpSession.setAttribute("loginKunde", loginKunde);
            request.getRequestDispatcher("/index.jsp").forward(request, response);

        }
        else //Login via registrering
        {
            String email = registerKunde.getEmail();
            String password = registerKunde.getPassword();

            try
            {
                navn = registerKunde.getName();
            } catch (NullPointerException npE)
            {
                String fejlBesked = "Forkert Mail eller Password, prøv igen";
                request.setAttribute("fejlBesked", fejlBesked);
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }


            if (navn.length() > 9)
            {
                navn = navn.substring(0, 9);
            }

            httpSession.setAttribute("kundeNavn", navn); //SKAL BRUGES

            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
