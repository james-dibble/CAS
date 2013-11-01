// --------------------------------------------------------------------------------------------------------------------
// <copyright file="OrderService.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.ServiceLayer;

import JavaApplicationFramework.Mapping.*;
import JoansTeaTrolly.DomainModel.Order;
import JoansTeaTrolly.Interfaces.DomainModel.*;
import JoansTeaTrolly.Interfaces.ServiceLayer.IOrderService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderService implements IOrderService
{
    private final IPersistenceManager _persistence;

    public OrderService(IPersistenceManager persistence)
    {
        this._persistence = persistence;
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
    public Iterable<IOrder> GetAllOrders()
    {
        IPersistenceSearcher<IOrder> searcher = new PersistenceSearcher<IOrder>(IOrder.class);        
        
        Iterable<IOrder> orders = this._persistence.FindCollectionOf(searcher);

        return orders;
    }

    @Override
    public void CreateOrder(IItem item, IClient client, int quantity)
    {
        IOrder order = new Order(client, item, quantity);
        
        this._persistence.Add(order);
        
        try
        {
            this._persistence.Commit();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
