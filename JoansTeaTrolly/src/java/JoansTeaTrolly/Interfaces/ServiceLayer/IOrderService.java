// --------------------------------------------------------------------------------------------------------------------
// <copyright file="IOrderService.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.Interfaces.ServiceLayer;

import JoansTeaTrolly.DomainModel.OrdersCollection;
import JoansTeaTrolly.Interfaces.DomainModel.IClient;
import JoansTeaTrolly.Interfaces.DomainModel.IItem;
import JoansTeaTrolly.Interfaces.DomainModel.IOrder;

public interface IOrderService 
{
    IOrder GetOrder(int id);
    
    OrdersCollection GetAllOrders();
    
    OrdersCollection UpdateItemPrices(OrdersCollection orders);    
    
    IOrder CreateOrder(IItem item, IClient client, int quantity);
    
    void SaveOrders(Iterable<IOrder> orders);
    
    void SaveOrders(IOrder... orders);
}
