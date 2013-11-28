// --------------------------------------------------------------------------------------------------------------------
// <copyright file="Client.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.DomainModel;

import JoansTeaTrolly.Framework.Mapping.UniqueObject;
import JoansTeaTrolly.Interfaces.DomainModel.IClient;

public class Client extends UniqueObject<Integer> implements IClient
{
    private final String _name;
    
    public Client(boolean isNewObject, int id, String name)
    {
        super(isNewObject, id);
        this._name = name;
    }
    
    public Client(String name)
    {
        super(true, -1);
        this._name = name;
    }
    
    @Override
    public String getName()
    {
        return _name;
    }

}
