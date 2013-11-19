package JoansTeaTrolly.Controllers;

import JavaApplicationFramework.Servlet.*;
import JavaApplicationFramework.Servlet.ActionAttribute.HttpMethod;
import JoansTeaTrolly.Constants.View;
import JoansTeaTrolly.Interfaces.DomainModel.IClient;
import JoansTeaTrolly.Interfaces.ServiceLayer.IClientService;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

public class ClientsController extends Controller
{
    @InjectAttribute
    private IClientService _clientService;
    
    @Override
    protected String ControllerPath()
    {
        return "/clients";
    }
    
    public IActionResult Index(HttpServletRequest request)
    {
        Iterable<IClient> clients = this._clientService.GetAllClients();
        
        return new ViewResult(View.Path("Clients/Index.jsp"), clients);
    }
    
    public IActionResult GetAllClients(HttpServletRequest request)
    {
        return new JsonResult(this._clientService.GetAllClients());
    }
    
    @ActionAttribute(Path = "/delete", Method = HttpMethod.POST)
    public IActionResult DeleteClient(HttpServletRequest request) throws SQLException
    {
        int clientId = Controller.GetRequestParam(request, "clientId");
        
        IClient client = this._clientService.GetClient(clientId);
        
        this._clientService.DeleteClient(client);
        
        return new RedirectToAction("/clients");
    }
    
    @ActionAttribute(Method = HttpMethod.POST, Path = "/addclient")
    public IActionResult AddClient(HttpServletRequest request) throws SQLException
    {
        String clientName = request.getParameter("name");
        
        this._clientService.CreateClient(clientName);
        
        return new RedirectToAction("/clients");
    }
}
