// --------------------------------------------------------------------------------------------------------------------
// <copyright file="IUniqueObject.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.Framework.Mapping;

/**
 * Implementing classes represent an object that is uniquely identifiable by a
 * single key.
 *
 * @param <TKey> The type of the key.
 */
public interface IUniqueObject<TKey> extends IPersistableObject
{
    /**
     * Gets the value to the unique key of this object.
     *
     * @return
     */
    TKey GetId();
    
    /**
     * A JSLT friendly version of GetId.
     * 
     * @return 
     */
    TKey getId();
}
