/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JoansTeaTrolly.Controllers;

import JavaApplicationFramework.Servlet.ActionAttribute;
import JavaApplicationFramework.Servlet.Controller;
import JoansTeaTrolly.Constants.Services;
import JoansTeaTrolly.Constants.Views;
import JavaApplicationFramework.Servlet.ActionAttribute.HttpMethod;
import JoansTeaTrolly.Interfaces.ServiceLayer.IClientService;
import JoansTeaTrolly.Interfaces.ServiceLayer.IItemService;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author james
 */
public class HomeController extends Controller
{
    private IClientService _clientService;
    private IItemService _itemService;

    @Override
    public void init()
    {
        ServletContext context = this.getServletContext();

        this._itemService = (IItemService) context.getAttribute(Services.ItemService.Id());
        this._clientService = (IClientService) context.getAttribute(Services.ClientService.Id());
    }
    
    @ActionAttribute(Path = "", Method = HttpMethod.GET)
    public void Index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setAttribute("items", this._itemService.GetAllItems());
        request.setAttribute("clients", this._clientService.GetAllClients());
        request.getRequestDispatcher(Views.ViewBase.Path().concat("Home/Index.jsp")).forward(request, response);
    }

    @Override
    protected String GetBasePath()
    {
        return "/";
    }
}
