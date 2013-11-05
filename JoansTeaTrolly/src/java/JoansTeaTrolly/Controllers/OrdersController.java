package JoansTeaTrolly.Controllers;

import JavaApplicationFramework.Servlet.*;
import JoansTeaTrolly.Constants.View;
import JoansTeaTrolly.Interfaces.DomainModel.*;
import JoansTeaTrolly.Interfaces.ServiceLayer.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrdersController extends Controller
{
    @InjectAttribute
    private IClientService _clientService;
    @InjectAttribute
    private IItemService _itemService;
    @InjectAttribute
    private IOrderService _orderService;

    @Override
    protected String ControllerPath()
    {
        return "/orders";
    }

    @ActionAttribute(Path = "", Method = ActionAttribute.HttpMethod.GET)
    public IActionResult Index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Iterable<IOrder> orders = this._orderService.GetAllOrders();

        return new ViewResult(View.Path("Orders/Index.jsp"), orders);
    }

    @ActionAttribute(Path = "/create", Method = ActionAttribute.HttpMethod.GET)
    public IActionResult Create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        return new ViewResult(View.Path("Orders/Create.jsp"));
    }

    @ActionAttribute(Path = "/addtoorder", Method = ActionAttribute.HttpMethod.POST)
    public IActionResult AddToOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int clientId = GetRequestParam(request, "clientId");
        int itemId = GetRequestParam(request, "itemId");
        int quantity = GetRequestParam(request, "quantity");

        IClient client = this._clientService.GetClient(clientId);
        IItem item = this._itemService.GetItem(itemId);

        IOrder order = this._orderService.CreateOrder(item, client, quantity);

        OrdersSessionManager sessionManager = new OrdersSessionManager(request);

        sessionManager.GetOrders().add(order);
        sessionManager.CommitChanges();

        return new RedirectToAction("/orders/create");
    }

    @ActionAttribute(Path = "/saveorders", Method = ActionAttribute.HttpMethod.POST)
    public IActionResult SaveOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        OrdersSessionManager sessionManager = new OrdersSessionManager(request);

        this._orderService.SaveOrders(sessionManager.GetOrders());

        sessionManager.ClearOrders();
        sessionManager.CommitChanges();

        return new RedirectToAction("/orders/create");
    }

    @ActionAttribute(Path = "/removeordersforclient", Method = ActionAttribute.HttpMethod.POST)
    public IActionResult RemoveOrdersForClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        OrdersSessionManager sessionManager = new OrdersSessionManager(request);

        int clientId = GetRequestParam(request, "clientId");
        IClient client = this._clientService.GetClient(clientId);

        sessionManager.RemoveOrdersForClient(client);
        sessionManager.CommitChanges();

        return new RedirectToAction("/orders/create");
    }

    @ActionAttribute(Path = "/removeorder", Method = ActionAttribute.HttpMethod.POST)
    public IActionResult RemoveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        OrdersSessionManager sessionManager = new OrdersSessionManager(request);

        int clientId = GetRequestParam(request, "clientId");
        int itemId = GetRequestParam(request, "itemId");
        int quantity = GetRequestParam(request, "quantity");

        IClient client = this._clientService.GetClient(clientId);
        IItem item = this._itemService.GetItem(itemId);

        IOrder order = this._orderService.CreateOrder(item, client, quantity);

        sessionManager.RemoveOrder(order);
        sessionManager.CommitChanges();

        return new RedirectToAction("/orders/create");
    }
}