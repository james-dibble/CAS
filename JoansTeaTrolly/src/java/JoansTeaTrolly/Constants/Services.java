// --------------------------------------------------------------------------------------------------------------------
// <copyright file="Services.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.Constants;

public enum Services
{
    ItemService("ItemService"),
    ClientService("ClientService"),
    OrderService("OrderService");
    
    private String _identifier;
    
    Services(String identifier)
    {
        this._identifier = identifier;
    }
    
    public String Id()
    {
        return this._identifier;
    }
}
