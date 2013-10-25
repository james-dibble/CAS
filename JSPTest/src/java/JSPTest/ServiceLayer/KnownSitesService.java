// --------------------------------------------------------------------------------------------------------------------
// <copyright file="KnownSitesService.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------

package JSPTest.ServiceLayer;

import JSPTest.Interfaces.ServiceLayer.IKnownSitesService;
import java.util.ArrayList;
import java.util.List;

public class KnownSitesService implements IKnownSitesService 
{
    private final List<String> _knownSites;
    
    public KnownSitesService()
    {
        this._knownSites = new ArrayList<String>();
        this._knownSites.add("google.com");
        this._knownSites.add("sighb.org");
        this._knownSites.add("well.com");
        this._knownSites.add("fish.net");
    }
    
    @Override
    public boolean IsKnownSite(String url)
    {
        return this._knownSites.contains(url);
    }
}
