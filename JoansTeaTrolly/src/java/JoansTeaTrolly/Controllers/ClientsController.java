/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JoansTeaTrolly.Controllers;

import JoansTeaTrolly.Constants.Services;
import JoansTeaTrolly.Constants.Views;
import JoansTeaTrolly.Interfaces.ServiceLayer.IClientService;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

/**
 *
 * @author james
 */
public class ClientsController extends HttpServlet
{
    private IClientService _clientService;

    @Override
    public void init()
    {
        ServletContext context = this.getServletContext();

        this._clientService = (IClientService) context.getAttribute(Services.ClientService.Id());
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String path = request.getServletPath().replace("/clients", "");

        if (path.equals("/") || path.equals(""))
        {
            this.Index(request, response);
        }

        if (path.equals("/getallclients"))
        {
            this.GetAllClients(request, response);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

    
    private void Index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setAttribute("clients", this._clientService.GetAllClients());
        request.getRequestDispatcher(Views.ViewBase.Path().concat("Clients/Index.jsp")).forward(request, response);
    }
    
    private void GetAllClients(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setCharacterEncoding("utf8");
        response.setContentType("application/json");

        String clientsAsJson = new Gson().toJson(this._clientService.GetAllClients());

        response.getWriter().write(clientsAsJson);
        response.getWriter().flush();
    }
}
