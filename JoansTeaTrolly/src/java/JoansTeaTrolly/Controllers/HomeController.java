package JoansTeaTrolly.Controllers;

import JavaApplicationFramework.Servlet.*;
import JavaApplicationFramework.Servlet.ActionAttribute.HttpMethod;
import JavaApplicationFramework.Servlet.InjectAttribute;
import JoansTeaTrolly.Constants.View;
import JoansTeaTrolly.DomainModel.HomeViewModel;
import JoansTeaTrolly.Interfaces.ServiceLayer.IClientService;
import JoansTeaTrolly.Interfaces.ServiceLayer.IItemService;
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
    public IActionResult Index(HttpServletRequest request, HttpServletResponse response)
    {
        HomeViewModel model = new HomeViewModel(this._itemService.GetAllItems(), this._clientService.GetAllClients()); 
        
        return new ViewResult(View.Path("Home/Index.jsp"), model);
    }
}
