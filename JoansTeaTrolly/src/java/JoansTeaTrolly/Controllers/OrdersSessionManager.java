// --------------------------------------------------------------------------------------------------------------------
// <copyright file="OrdersSessionManager.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.Controllers;

import JoansTeaTrolly.DomainModel.OrdersCollection;
import JoansTeaTrolly.Interfaces.DomainModel.IClient;
import JoansTeaTrolly.Interfaces.DomainModel.IOrder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class OrdersSessionManager
{
    private final HttpSession _session;
    private final String sessionAttributeId = "orders";
    private OrdersCollection _orders;

    public OrdersSessionManager(HttpServletRequest request)
    {
        this._session = request.getSession(true);
    }

    public OrdersCollection GetOrders()
    {
        if (this._orders == null)
        {
            OrdersCollection orders = (OrdersCollection) this._session.getAttribute(this.sessionAttributeId);

            if (orders == null)
            {
                orders = new OrdersCollection();
            }
            
            this._orders = orders;
        }

        return this._orders;
    }
    
    public void RemoveOrder(IOrder order)
    {        
        this._orders = this.GetOrders().RemoveOrder(order);
    }
    
    public void RemoveOrdersForClient(IClient client)
    {
        this._orders = this.GetOrders().RemoveOrdersForClient(client);
    }
    
    public void ClearOrders()
    {
        this._orders = null;
    }
    
    public void CommitChanges()
    {
        this._session.setAttribute(this.sessionAttributeId, this.GetOrders());
    }
}
