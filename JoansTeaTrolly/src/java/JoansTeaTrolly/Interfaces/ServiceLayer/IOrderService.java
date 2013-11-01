// --------------------------------------------------------------------------------------------------------------------
// <copyright file="IOrderService.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.Interfaces.ServiceLayer;

import JoansTeaTrolly.Interfaces.DomainModel.IClient;
import JoansTeaTrolly.Interfaces.DomainModel.IItem;
import JoansTeaTrolly.Interfaces.DomainModel.IOrder;

public interface IOrderService 
{
    IOrder GetOrder(int id);
    
    Iterable<IOrder> GetAllOrders();
    
    void CreateOrder(IItem item, IClient client, int quantity);
}
