package JoansTeaTrolly.Controllers;

import JavaApplicationFramework.Servlet.*;
import JavaApplicationFramework.Servlet.ActionAttribute.HttpMethod;
import JoansTeaTrolly.Constants.View;
import JoansTeaTrolly.Interfaces.DomainModel.IClient;
import JoansTeaTrolly.Interfaces.ServiceLayer.IClientService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientsController extends Controller
{
    @InjectAttribute
    private IClientService _clientService;
    
    @Override
    protected String ControllerPath()
    {
        return "/clients";
    }
    
    @ActionAttribute(Path = "", Method = HttpMethod.GET)
    public IActionResult Index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        Iterable<IClient> clients = this._clientService.GetAllClients();
        
        return new ViewResult(View.Path("Clients/Index.jsp"), clients);
    }
    
    @ActionAttribute(Path = "/getallclients", Method = HttpMethod.GET)
    public IActionResult GetAllClients(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        return new JsonResult(this._clientService.GetAllClients());
    }
    
    @ActionAttribute(Path = "/delete", Method = HttpMethod.POST)
    public IActionResult DeleteClient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        int clientId = GetRequestParam(request, "clientId");
        
        IClient client = this._clientService.GetClient(clientId);
        
        this._clientService.DeleteClient(client);
        
        return new RedirectToAction("/clients");
    }
    
    @ActionAttribute(Method = HttpMethod.POST, Path = "/addclient")
    public IActionResult AddClient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String clientName = request.getParameter("name");
        
        this._clientService.CreateClient(clientName);
        
        return new RedirectToAction("/clients");
    }
}
