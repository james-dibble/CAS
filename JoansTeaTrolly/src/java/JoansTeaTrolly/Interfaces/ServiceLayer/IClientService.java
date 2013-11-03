// --------------------------------------------------------------------------------------------------------------------
// <copyright file="IClientService.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.Interfaces.ServiceLayer;

import JoansTeaTrolly.Interfaces.DomainModel.IClient;

public interface IClientService 
{
    IClient GetClient(int id);
    
    Iterable<IClient> GetAllClients();
    
    void CreateClient(String name);
}
