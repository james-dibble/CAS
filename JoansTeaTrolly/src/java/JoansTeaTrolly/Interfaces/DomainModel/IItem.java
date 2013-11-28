// --------------------------------------------------------------------------------------------------------------------
// <copyright file="IItem.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.Interfaces.DomainModel;

import JoansTeaTrolly.Framework.Mapping.IUniqueObject;

public interface IItem extends IUniqueObject<Integer>
{
    String getName();
    
    int getPrice();
}
