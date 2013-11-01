// --------------------------------------------------------------------------------------------------------------------
// <copyright file="ItemService.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------

package JoansTeaTrolly.Listeners;

import JavaApplicationFramework.Mapping.*;
import JoansTeaTrolly.Mapping.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class InitialisePersistenceContext implements ServletContextListener
{

    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        IMapper clientMapper = new ClientMapper();
        IMapper orderMapper = new OrderMapper();
        IMapper itemMapper = new ItemMapper();
        
        MapperDictionary mappers = new MapperDictionary(clientMapper, orderMapper, itemMapper);
        
        sce.getServletContext().setAttribute("mappers", mappers);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        sce.getServletContext().removeAttribute("mappers");
    }
}
