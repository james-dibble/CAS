// --------------------------------------------------------------------------------------------------------------------
// <copyright file="Views.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.Constants;

public class View
{
    private static final String viewBase = "/WEB-INF/Views/";
        
    public static String Path(String view)
    {
        return viewBase.concat(view);
    }
}
