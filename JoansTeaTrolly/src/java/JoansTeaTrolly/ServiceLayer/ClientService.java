// --------------------------------------------------------------------------------------------------------------------
// <copyright file="ClientService.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.ServiceLayer;

import JavaApplicationFramework.Mapping.*;
import JoansTeaTrolly.DomainModel.Client;
import JoansTeaTrolly.Interfaces.DomainModel.IClient;
import JoansTeaTrolly.Interfaces.ServiceLayer.IClientService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    @Override
    public void CreateClient(String name)
    {
        IClient client = new Client(name);
        
        this._persistence.Add(client);
        
        try
        {
            this._persistence.Commit();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void DeleteClient(IClient client) {
        this._persistence.Delete(client);
        
        try
        {
            this._persistence.Commit();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
