package JoansTeaTrolly.Controllers;

import JavaApplicationFramework.Servlet.*;
import JavaApplicationFramework.Servlet.InjectAttribute;
import JoansTeaTrolly.Constants.View;
import JoansTeaTrolly.DomainModel.HomeViewModel;
import JoansTeaTrolly.Interfaces.ServiceLayer.IClientService;
import JoansTeaTrolly.Interfaces.ServiceLayer.IItemService;
import javax.servlet.http.HttpServletRequest;

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
    
    public IActionResult Index(HttpServletRequest request)
    {
        HomeViewModel model = new HomeViewModel(this._itemService.GetAllItems(), this._clientService.GetAllClients()); 
        
        return new ViewResult(View.Path("Home/Index.jsp"), model);
    }
}