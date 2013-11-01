// --------------------------------------------------------------------------------------------------------------------
// <copyright file="IClient.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.Interfaces.DomainModel;

import JavaApplicationFramework.Mapping.IUniqueObject;

public interface IClient extends IUniqueObject<Integer>
{
    String GetName();
}
