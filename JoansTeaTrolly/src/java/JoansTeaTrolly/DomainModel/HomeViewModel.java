// --------------------------------------------------------------------------------------------------------------------
// <copyright file="HomeViewModel.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.DomainModel;

import JoansTeaTrolly.Interfaces.DomainModel.IClient;
import JoansTeaTrolly.Interfaces.DomainModel.IItem;

public class HomeViewModel 
{
    private final Iterable<IItem> _items;
    private final Iterable<IClient> _clients;
    
    public HomeViewModel(Iterable<IItem> items, Iterable<IClient> clients)
    {
        this._items = items;
        this._clients = clients;
    }
    
    public Iterable<IItem> getItems()
    {
        return this._items;
    }
    
    public Iterable<IClient> getClients()
    {
        return this._clients;
    }
}
