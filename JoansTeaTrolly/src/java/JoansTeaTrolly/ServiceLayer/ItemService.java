// --------------------------------------------------------------------------------------------------------------------
// <copyright file="ItemService.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.ServiceLayer;

import JavaApplicationFramework.Mapping.*;
import JoansTeaTrolly.Interfaces.DomainModel.IItem;
import JoansTeaTrolly.Interfaces.ServiceLayer.IItemService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemService implements IItemService
{
    private final IPersistenceManager _persistence;

    public ItemService(IPersistenceManager persistence)
    {
        this._persistence = persistence;
    }
    
    @Override
    public IItem GetItem(int id)
    {
        IPersistenceSearcher<IItem> searcher = new PersistenceSearcher<IItem>(IItem.class);
        searcher.put("Id", id);
        
        IItem item = this._persistence.Find(searcher);

        return item;
    }

    @Override
    public Iterable<IItem> GetAllItems()
    {
        IPersistenceSearcher<IItem> searcher = new PersistenceSearcher<IItem>(IItem.class);        
        
        Iterable<IItem> items = this._persistence.FindCollectionOf(searcher);

        return items;
    }

    @Override
    public void ChangeItem(IItem item)
    {
        this._persistence.Change(item);
        try
        {
            this._persistence.Commit();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
