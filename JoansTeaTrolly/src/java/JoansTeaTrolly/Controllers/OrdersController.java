package JoansTeaTrolly.Controllers;

import JavaApplicationFramework.Servlet.ActionAttribute;
import JavaApplicationFramework.Servlet.Controller;
import JavaApplicationFramework.Servlet.InjectAttribute;
import JoansTeaTrolly.Constants.Views;
import JoansTeaTrolly.DomainModel.OrdersCollection;
import JoansTeaTrolly.Interfaces.DomainModel.*;
import JoansTeaTrolly.Interfaces.ServiceLayer.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    public void Index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("orders", this._orderService.GetAllOrders());
        request.getRequestDispatcher(Views.ViewBase.Path().concat("Orders/Index.jsp")).forward(request, response);
    }

    @ActionAttribute(Path = "/create", Method = ActionAttribute.HttpMethod.GET)
    public void Create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getRequestDispatcher(Views.ViewBase.Path().concat("Orders/Create.jsp")).forward(request, response);
    }

    @ActionAttribute(Path = "/addtoorder", Method = ActionAttribute.HttpMethod.POST)
    public void AddToOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int clientId = GetRequestParam(request, "clientId");
        int itemId = GetRequestParam(request, "itemId");
        int quantity = GetRequestParam(request, "quantity");

        IClient client = this._clientService.GetClient(clientId);
        IItem item = this._itemService.GetItem(itemId);

        IOrder order = this._orderService.CreateOrder(item, client, quantity);

        HttpSession session = request.getSession(true);
        
        OrdersCollection orders = GetSessionAttribute(session, "orders");   

        if(orders == null)
        {
            orders = new OrdersCollection();
        }
        
        orders.add(order);

        session.setAttribute("orders", orders);

        response.sendRedirect(request.getContextPath().concat("/orders/create"));
    }

    @ActionAttribute(Path = "/saveorders", Method = ActionAttribute.HttpMethod.POST)
    public void SaveOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(true);

        OrdersCollection orders;
        
        if ((orders = GetSessionAttribute(session, "orders")) != null)
        {
            this._orderService.SaveOrders(orders);

            session.setAttribute("orders", null);
        }

        response.sendRedirect(request.getContextPath().concat("/orders/create"));
    }

    @ActionAttribute(Path = "/removeordersforclient", Method = ActionAttribute.HttpMethod.POST)
    public void RemoveOrdersForClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(true);

        OrdersCollection orders;
        
        if ((orders = GetSessionAttribute(session, "orders")) != null)
        {
            int clientId = GetRequestParam(request, "clientId");

            orders = orders.RemoveOrdersForClient(this._clientService.GetClient(clientId));

            session.setAttribute("orders", orders);
        }

        response.sendRedirect(request.getContextPath().concat("/orders/create"));
    }

    @ActionAttribute(Path = "/removeorder", Method = ActionAttribute.HttpMethod.POST)
    public void RemoveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(true);

        OrdersCollection orders;
        
        if ((orders = GetSessionAttribute(session, "orders")) != null)
        {
            int clientId = GetRequestParam(request, "clientId");
            int itemId = GetRequestParam(request, "itemId");
            int quantity = GetRequestParam(request, "quantity");

            IClient client = this._clientService.GetClient(clientId);
            IItem item = this._itemService.GetItem(itemId);

            IOrder order = this._orderService.CreateOrder(item, client, quantity);

            orders = orders.RemoveOrder(order);

            session.setAttribute("orders", orders);
        }

        response.sendRedirect(request.getContextPath().concat("/orders/create"));
    }
}
