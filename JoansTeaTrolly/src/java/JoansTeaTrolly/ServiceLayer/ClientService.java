// --------------------------------------------------------------------------------------------------------------------
// <copyright file="ClientService.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.ServiceLayer;

import JavaApplicationFramework.Mapping.*;
import JoansTeaTrolly.Interfaces.DomainModel.IClient;
import JoansTeaTrolly.Interfaces.ServiceLayer.IClientService;

public class ClientService implements IClientService
{
    private final IPersistenceManager _persistence;

    public ClientService(IPersistenceManager persistence)
    {
        this._persistence = persistence;
    }
    
    @Override
    public IClient GetClient(int id)
    {
        IPersistenceSearcher<IClient> searcher = new PersistenceSearcher<IClient>(IClient.class);
        searcher.put("Id", id);
        
        IClient client = this._persistence.Find(searcher);

        return client;
    }

    @Override
    public Iterable<IClient> GetAllClients()
    {
        IPersistenceSearcher<IClient> searcher = new PersistenceSearcher<IClient>(IClient.class);        
        
        Iterable<IClient> clients = this._persistence.FindCollectionOf(searcher);

        return clients;
    }

}
