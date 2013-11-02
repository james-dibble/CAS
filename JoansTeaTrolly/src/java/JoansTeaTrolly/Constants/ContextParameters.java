// --------------------------------------------------------------------------------------------------------------------
// <copyright file="ContextParameters.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------

package JoansTeaTrolly.Constants;

public enum ContextParameters 
{
    DatabaseUri("DatabaseUri"),
    DatabaseUser("DatabaseUser"),
    DatabasePassword("DatabasePassword");
    
    private final String _key;
    
    ContextParameters(String key)
    {
        this._key = key;
    }
    
    public String Key()
    {
        return this._key;
    }
}
