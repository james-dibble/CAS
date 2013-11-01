// --------------------------------------------------------------------------------------------------------------------
// <copyright file="IItemService.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.Interfaces.ServiceLayer;

import JoansTeaTrolly.Interfaces.DomainModel.IItem;

public interface IItemService 
{
    IItem GetItem(int id);
    
    Iterable<IItem> GetAllItems();
    
    void ChangeItem(IItem item);
}
