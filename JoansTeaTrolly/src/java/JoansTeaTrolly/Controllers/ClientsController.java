/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JoansTeaTrolly.Controllers;

import JavaApplicationFramework.Servlet.ActionAttribute;
import JoansTeaTrolly.Constants.Services;
import JoansTeaTrolly.Constants.Views;
import JavaApplicationFramework.Servlet.ActionAttribute.HttpMethod;
import JavaApplicationFramework.Servlet.Controller;
import JoansTeaTrolly.Interfaces.ServiceLayer.IClientService;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author james
 */
public class ClientsController extends Controller
{
    private IClientService _clientService;

    @Override
    public void init()
    {
        ServletContext context = this.getServletContext();

        this._clientService = (IClientService) context.getAttribute(Services.ClientService.Id());
    }
    
    @ActionAttribute(Path = "", Method = HttpMethod.GET)
    public void Index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setAttribute("clients", this._clientService.GetAllClients());
        request.getRequestDispatcher(Views.ViewBase.Path().concat("Clients/Index.jsp")).forward(request, response);
    }
    
    @ActionAttribute(Path = "/getallclients", Method = HttpMethod.GET)
    public void GetAllClients(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        JsonResult(request, response, this._clientService.GetAllClients());
    }
    
    @ActionAttribute(Method = HttpMethod.POST, Path = "/addclient")
    public void AddClient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String clientName = request.getParameter("name");
        
        this._clientService.CreateClient(clientName);
        
        response.sendRedirect(request.getContextPath().concat("/clients"));
    }

    @Override
    protected String GetBasePath()
    {
        return "/clients";
    }
}
