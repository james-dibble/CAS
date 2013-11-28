// --------------------------------------------------------------------------------------------------------------------
// <copyright file="Item.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.DomainModel;

import JoansTeaTrolly.Framework.Mapping.UniqueObject;
import JoansTeaTrolly.Interfaces.DomainModel.IItem;

public class Item extends UniqueObject<Integer> implements IItem
{
    private final String _name;
    private final int _price;
    
    public Item(boolean isNewObject, int id, String name, int price)
    {
        super(isNewObject, id);
        this._name = name;
        this._price = price;
    }
    
    public Item(String name, int price)
    {
        super(true, -1);
        this._name = name;
        this._price = price;
    }
    
    @Override
    public String getName()
    {
        return this._name;
    }

    @Override
    public int getPrice()
    {
        return this._price;
    }

}
