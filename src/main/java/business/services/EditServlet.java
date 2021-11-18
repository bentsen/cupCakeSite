package business.services;

import business.entities.Kunde;
import business.persistence.DBConnector;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditServlet", value = "/EditServlet")
public class EditServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession httpSession = request.getSession();

        Kunde kunde = (Kunde) httpSession.getAttribute("loginKunde");

        if(kunde.getIsAdmin() == 1)
        {

        }
        request.getRequestDispatcher("/WEB-INF/editKundeInfo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession httpSession = request.getSession();
        DBConnector dbConnector = new DBConnector();

        String act = request.getParameter("editAct");
        Kunde kunde = (Kunde) httpSession.getAttribute("loginKunde");
        Kunde backupKunde = (Kunde) httpSession.getAttribute("loginKunde");

        String navn = request.getParameter("editName");
        String password = request.getParameter("editPassword");
        String email = request.getParameter("editEmail");
        String adresse = request.getParameter("editAdresse");
        int postNr = Integer.parseInt(request.getParameter("editPostNr"));


        if (act.equals("DiscardKnap"))
        {
            if (kunde.getIsAdmin() == 1)
            {
                request.getRequestDispatcher("/WEB-INF/adminKundeInfo.jsp").forward(request, response);
            }
            else
            {
            request.getRequestDispatcher("/WEB-INF/kundeInfo.jsp").forward(request, response);
            }
        }
        if (act.equals("gemKnap"))
        {
            kunde.setName(navn);
            kunde.setPassword(password);
            kunde.setEmail(email);
            kunde.setPostNr(postNr);
            kunde.setAddress(adresse);
            try
            {
                dbConnector.updateKunde(kunde);
            } catch (Exception e)
            {
                // Hvis det fejler sætter den kunde data tilbage til det gamle og meddeler om at
                // email eller password allerede er ejet af en anden kunde
                kunde.setName(backupKunde.getName());
                kunde.setPassword(backupKunde.getPassword());
                kunde.setEmail(backupKunde.getEmail());
                kunde.setPostNr(backupKunde.getPostNr());
                kunde.setAddress(backupKunde.getAddress());

                String fejlBesked = "Emailen findes allerede, prøv igen";
                request.setAttribute("fejlBesked", fejlBesked);
            }
            httpSession.setAttribute("loginKunde", kunde);
            httpSession.setAttribute("kundeNavn", navn);
            if(kunde.getIsAdmin() == 1)
            {
                request.getRequestDispatcher("/WEB-INF/adminKundeInfo.jsp").forward(request, response);
            }
            else
            {
            request.getRequestDispatcher("/WEB-INF/kundeInfo.jsp").forward(request, response);
            }
        }

    }
}
