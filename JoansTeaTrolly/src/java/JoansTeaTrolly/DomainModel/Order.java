// --------------------------------------------------------------------------------------------------------------------
// <copyright file="IOrder.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.DomainModel;

import JavaApplicationFramework.Mapping.UniqueObject;
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
        super(false, -1);
        this._client = client;
        this._item = item;
        this._quantity = quantity;
    }
    
    @Override
    public IClient GetClient()
    {
        return this._client;
    }

    @Override
    public IItem GetItem()
    {
        return this._item;
    }

    @Override
    public int GetQuantity()
    {
        return this._quantity;
    }
    
}
