package JoansTeaTrolly.Controllers;

import JavaApplicationFramework.Servlet.*;
import JoansTeaTrolly.Constants.View;
import JoansTeaTrolly.DomainModel.OrdersCollection;
import JoansTeaTrolly.Interfaces.DomainModel.*;
import JoansTeaTrolly.Interfaces.ServiceLayer.*;
import javax.servlet.http.HttpServletRequest;

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

    public IActionResult Index(HttpServletRequest request)
    {
        OrdersCollection orders = this._orderService.GetAllOrders();

        return new ViewResult(View.Path("Orders/Index.jsp"), orders);
    }

    public IActionResult Create(HttpServletRequest request)
    {
        new OrdersSessionManager(request, this._orderService).SyncronizeItemPrice();
        
        return new ViewResult(View.Path("Orders/Create.jsp"));
    }

    @ActionAttribute(Path = "/addtoorder", Method = ActionAttribute.HttpMethod.POST)
    public IActionResult AddToOrder(HttpServletRequest request)
    {
        int clientId = Controller.GetRequestParam(request, "clientId");
        int itemId = Controller.GetRequestParam(request, "itemId");
        int quantity = Controller.GetRequestParam(request, "quantity");

        IClient client = this._clientService.GetClient(clientId);
        IItem item = this._itemService.GetItem(itemId);

        IOrder order = this._orderService.CreateOrder(item, client, quantity);

        OrdersSessionManager sessionManager = new OrdersSessionManager(request, this._orderService);

        sessionManager.GetOrders().AddOrder(order);
        sessionManager.CommitChanges();

        return new RedirectToAction("/orders/create");
    }

    @ActionAttribute(Path = "/saveorders", Method = ActionAttribute.HttpMethod.POST)
    public IActionResult SaveOrders(HttpServletRequest request)
    {
        OrdersSessionManager sessionManager = new OrdersSessionManager(request, this._orderService);

        this._orderService.SaveOrders(sessionManager.GetOrders().GetAllOrders());

        sessionManager.ClearOrders();
        sessionManager.CommitChanges();

        return new RedirectToAction("/orders/create");
    }

    @ActionAttribute(Path = "/removeordersforclient", Method = ActionAttribute.HttpMethod.POST)
    public IActionResult RemoveOrdersForClient(HttpServletRequest request)
    {
        OrdersSessionManager sessionManager = new OrdersSessionManager(request, this._orderService);

        int clientId = Controller.GetRequestParam(request, "clientId");
        IClient client = this._clientService.GetClient(clientId);

        sessionManager.RemoveOrdersForClient(client);
        sessionManager.CommitChanges();

        return new RedirectToAction("/orders/create");
    }

    @ActionAttribute(Path = "/removeorder", Method = ActionAttribute.HttpMethod.POST)
    public IActionResult RemoveOrder(HttpServletRequest request)
    {
        OrdersSessionManager sessionManager = new OrdersSessionManager(request, this._orderService);

        int clientId = Controller.GetRequestParam(request, "clientId");
        int itemId = Controller.GetRequestParam(request, "itemId");
        int quantity = Controller.GetRequestParam(request, "quantity");

        IClient client = this._clientService.GetClient(clientId);
        IItem item = this._itemService.GetItem(itemId);

        IOrder order = this._orderService.CreateOrder(item, client, quantity);

        sessionManager.RemoveOrder(order);
        sessionManager.CommitChanges();

        return new RedirectToAction("/orders/create");
    }
}