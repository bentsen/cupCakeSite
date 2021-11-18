package web.filter;

import business.entities.Kunde;
import business.persistence.DBConnector;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ControllerServlet", value = "/ControllerServlet")
public class ControllerServlet extends HttpServlet
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
        ServletContext context = request.getServletContext();


        String act = request.getParameter("act");
        Kunde kunde = (Kunde) httpSession.getAttribute("loginKunde");


        // TODO: SOM KUNDE -
        // Kan du se navn, adresse, post nr, whatever

        //TODO: Som admin -
        // Se alle kunder.


        if (kunde != null) //Login checker
        {
            if (act.equals("ordreKnap"))
            {
                if(kunde.getIsAdmin() == 1) //admin checker
                {
                    request.getRequestDispatcher("/WEB-INF/adminOrdre.jsp").forward(request, response);
                }
                else
                {
                request.getRequestDispatcher("/WEB-INF/ordre.jsp").forward(request, response);
                }
            }
            if (act.equals("kundeKnap"))
            {
                if(kunde.getIsAdmin() == 1) //admin checker
                {
                    List<Kunde> kundeList = dbConnector.receiveAllKunder();
                    httpSession.setAttribute("kundeList", kundeList);
                    String adminEditAct = request.getParameter("adminEditAct");
                    request.getRequestDispatcher("/WEB-INF/adminKundeInfo.jsp").forward(request, response);
                }
                else
                {
                String editAct = request.getParameter("editAct");
//                if(editKnapTrykkes gør det her)
                request.getRequestDispatcher("/WEB-INF/kundeInfo.jsp").forward(request, response);
                }
            }
        } else
        {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }



    }
}
