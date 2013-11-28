// --------------------------------------------------------------------------------------------------------------------
// <copyright file="OrderService.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.ServiceLayer;

import JoansTeaTrolly.Framework.Mapping.*;
import JoansTeaTrolly.DomainModel.Order;
import JoansTeaTrolly.DomainModel.OrdersCollection;
import JoansTeaTrolly.Interfaces.DomainModel.*;
import JoansTeaTrolly.Interfaces.ServiceLayer.IItemService;
import JoansTeaTrolly.Interfaces.ServiceLayer.IOrderService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderService implements IOrderService
{
    private final IPersistenceManager _persistence;
    private final IItemService _itemService;
    
    public OrderService(IPersistenceManager persistence, IItemService itemService)
    {
        this._persistence = persistence;
        this._itemService = itemService;
    }

    @Override
    public IOrder GetOrder(int id)
    {
        IPersistenceSearcher<IOrder> searcher = new PersistenceSearcher<IOrder>(IOrder.class);
        searcher.put("Id", id);
        
        IOrder order = this._persistence.Find(searcher);

        return order;
    }

    @Override
    public OrdersCollection GetAllOrders()
    {
        IPersistenceSearcher<IOrder> searcher = new PersistenceSearcher<IOrder>(IOrder.class);   
        
        searcher.put("orderbyname", null);
        
        Iterable<IOrder> orders = this._persistence.FindCollectionOf(searcher);
        
        OrdersCollection orderCollection = new OrdersCollection();
        
        for(IOrder order : orders)
        {
            orderCollection.AddOrder(order);
        }

        return orderCollection;
    }

    @Override
    public IOrder CreateOrder(IItem item, IClient client, int quantity)
    {
        IOrder order = new Order(client, item, quantity);
        
        return order;
    }

    @Override
    public void SaveOrders(Iterable<IOrder> orders)
    {
        for(IOrder order : orders)
        {
            this._persistence.Add(order);
        }
        
        try
        {
            this._persistence.Commit();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void SaveOrders(IOrder... orders)
    {
        this.SaveOrders(orders);
    }

    @Override
    public OrdersCollection UpdateItemPrices(OrdersCollection orders)
    {
        OrdersCollection updatedOrders = new OrdersCollection();
        
        for(IOrder order : orders.GetAllOrders())
        {
            updatedOrders.AddOrder(
                    new Order(
                        order.getClient(), 
                        this._itemService.GetItem(order.getItem().GetId()),
                        order.getQuantity()));
        }
        
        return updatedOrders;
    }
}
