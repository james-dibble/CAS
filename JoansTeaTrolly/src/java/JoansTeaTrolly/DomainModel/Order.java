// --------------------------------------------------------------------------------------------------------------------
// <copyright file="IOrder.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.DomainModel;

import JoansTeaTrolly.Framework.Mapping.UniqueObject;
import JoansTeaTrolly.Interfaces.DomainModel.IClient;
import JoansTeaTrolly.Interfaces.DomainModel.IItem;
import JoansTeaTrolly.Interfaces.DomainModel.IOrder;

public class Order extends UniqueObject<Integer> implements IOrder
{
    private final IClient _client;
    private final IItem _item;
    private final int _quantity;
    
    public Order(boolean isNewObject, int id, IClient client, IItem item, int quantity)
    {
        super(isNewObject, id);
        this._client = client;
        this._item = item;
        this._quantity = quantity;
    }
    
    public Order(IClient client, IItem item, int quantity)
    {
        super(true, -1);
        this._client = client;
        this._item = item;
        this._quantity = quantity;
    }
    
    @Override
    public IClient getClient()
    {
        return this._client;
    }

    @Override
    public IItem getItem()
    {
        return this._item;
    }

    @Override
    public int getQuantity()
    {
        return this._quantity;
    }
    
}
