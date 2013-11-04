/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JoansTeaTrolly.Controllers;

import JavaApplicationFramework.Servlet.ActionAttribute;
import JavaApplicationFramework.Servlet.Controller;
import JoansTeaTrolly.Constants.Services;
import JoansTeaTrolly.Constants.Views;
import JoansTeaTrolly.DomainModel.OrdersCollection;
import JoansTeaTrolly.Interfaces.DomainModel.*;
import JoansTeaTrolly.Interfaces.ServiceLayer.*;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author james
 */
public class OrdersController extends Controller
{
    private IClientService _clientService;
    private IItemService _itemService;
    private IOrderService _orderService;

    @Override
    public void init()
    {
        ServletContext context = this.getServletContext();

        this._clientService = (IClientService) context.getAttribute(Services.ClientService.Id());
        this._itemService = (IItemService) context.getAttribute(Services.ItemService.Id());
        this._orderService = (IOrderService) context.getAttribute(Services.OrderService.Id());
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

        OrdersCollection orders;

        if (session.getAttribute("orders") != null)
        {
            orders = (OrdersCollection) session.getAttribute("orders");
        }
        else
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

        if (session.getAttribute("orders") != null)
        {
            OrdersCollection orders = (OrdersCollection) session.getAttribute("orders");

            this._orderService.SaveOrders(orders);

            session.setAttribute("orders", null);
        }

        response.sendRedirect(request.getContextPath().concat("/orders/create"));
    }

    @ActionAttribute(Path = "/removeordersforclient", Method = ActionAttribute.HttpMethod.POST)
    public void RemoveOrdersForClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(true);

        if (session.getAttribute("orders") != null)
        {
            int clientId = GetRequestParam(request, "clientId");

            OrdersCollection orders = (OrdersCollection) session.getAttribute("orders");

            orders = orders.RemoveOrdersForClient(this._clientService.GetClient(clientId));

            session.setAttribute("orders", orders);
        }

        response.sendRedirect(request.getContextPath().concat("/orders/create"));
    }

    @ActionAttribute(Path = "/removeorder", Method = ActionAttribute.HttpMethod.POST)
    public void RemoveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(true);

        if (session.getAttribute("orders") != null)
        {
            int clientId = GetRequestParam(request, "clientId");
            int itemId = GetRequestParam(request, "itemId");
            int quantity = GetRequestParam(request, "quantity");

            OrdersCollection orders = (OrdersCollection) session.getAttribute("orders");

            IClient client = this._clientService.GetClient(clientId);
            IItem item = this._itemService.GetItem(itemId);

            IOrder order = this._orderService.CreateOrder(item, client, quantity);

            orders = orders.RemoveOrder(order);

            session.setAttribute("orders", orders);
        }

        response.sendRedirect(request.getContextPath().concat("/orders/create"));
    }

    @Override
    protected String GetBasePath()
    {
        return "/orders";
    }
}
