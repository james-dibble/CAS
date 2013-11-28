// --------------------------------------------------------------------------------------------------------------------
// <copyright file="IPersistableObject.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.Framework.Mapping;

/**
 * An object that has association with a persisted entity.
 */
public interface IPersistableObject
{
    /**
     * Gets a value indicating whether this is an object that has previously been persisted.
     *
     * @return A boolean value whether this is a new object.
     */
    boolean IsNewObject();
}
