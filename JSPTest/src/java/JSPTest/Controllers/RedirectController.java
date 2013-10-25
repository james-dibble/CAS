// --------------------------------------------------------------------------------------------------------------------
// <copyright file="RedirectController.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JSPTest.Controllers;

import JSPTest.Interfaces.ServiceLayer.IKnownSitesService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RedirectController
{
    private final IKnownSitesService _sitesService;
    
    @Autowired
    public RedirectController(IKnownSitesService sitesService)
    {
        this._sitesService = sitesService;
    }
    
    @RequestMapping("/redirect")
    public String Index() throws SQLException
    {
        return "Redirect/Index";
    }
    
    @RequestMapping(value = "/redirect", method = RequestMethod.POST)
    public String CheckRedirect(@RequestParam("siteAddress")String siteAddress)
    {      
        return this._sitesService.IsKnownSite(siteAddress) ? "redirect:http://" + siteAddress : "Redirect/Redirect";
    }
    
    @RequestMapping("/redirect/download")
    @ResponseBody
    public void DownloadSomething(HttpServletResponse response, HttpServletRequest request) throws IOException
    {   
        String path = "/WEB-INF/Content/Downloads/sprite.png";
        response.setContentType("application-xdownload");
        response.setHeader("Content-Disposition", "attachment; filename=\"sprite.png\"");
        
        ServletContext context = request.getSession().getServletContext();
        
        File resource = new File(context.getRealPath(path));
        
        response.getOutputStream().write(Files.readAllBytes(resource.toPath()));
        
        response.getOutputStream().flush();
    }
}
