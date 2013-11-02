// --------------------------------------------------------------------------------------------------------------------
// <copyright file="IOrder.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.Interfaces.DomainModel;

import JavaApplicationFramework.Mapping.IUniqueObject;

public interface IOrder extends IUniqueObject<Integer>
{
    IClient getClient();
    
    IItem getItem();
    
    int getQuantity();
}
