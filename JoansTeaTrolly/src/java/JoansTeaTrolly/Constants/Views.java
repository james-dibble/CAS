// --------------------------------------------------------------------------------------------------------------------
// <copyright file="Views.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.Constants;

public enum Views
{
    ViewBase("/WEB-INF/Views/");
    
    private final String _path;

    Views(String path)
    {
        this._path = path;
    }
    
    public String Path()
    {
        return this._path;
    }
}
