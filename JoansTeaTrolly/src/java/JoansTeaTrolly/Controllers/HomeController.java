package JoansTeaTrolly.Controllers;

import JavaApplicationFramework.Servlet.ActionAttribute;
import JavaApplicationFramework.Servlet.Controller;
import JoansTeaTrolly.Constants.Views;
import JavaApplicationFramework.Servlet.ActionAttribute.HttpMethod;
import JavaApplicationFramework.Servlet.InjectAttribute;
import JoansTeaTrolly.Interfaces.ServiceLayer.IClientService;
import JoansTeaTrolly.Interfaces.ServiceLayer.IItemService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController extends Controller
{
    @InjectAttribute
    private IClientService _clientService;
    @InjectAttribute
    private IItemService _itemService;
    
    @Override
    protected String ControllerPath()
    {
        return "/";
    }
    
    @ActionAttribute(Path = "", Method = HttpMethod.GET)
    public void Index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setAttribute("items", this._itemService.GetAllItems());
        request.setAttribute("clients", this._clientService.GetAllClients());
        request.getRequestDispatcher(Views.ViewBase.Path().concat("Home/Index.jsp")).forward(request, response);
    }
}
