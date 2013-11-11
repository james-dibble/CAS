package JoansTeaTrolly.Listeners;

import JavaApplicationFramework.Mapping.*;
import JoansTeaTrolly.Constants.ContextParameters;
import JoansTeaTrolly.DomainModel.*;
import JoansTeaTrolly.Interfaces.ServiceLayer.*;
import JoansTeaTrolly.Mapping.*;
import JoansTeaTrolly.ServiceLayer.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class Bootstrapper implements ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        try
        {
            Connection persistenceConnection = 
                    new ConnectionFactory().CreateConnection(
                        sce.getServletContext().getInitParameter(ContextParameters.DatabaseUri.Key()),
                        sce.getServletContext().getInitParameter(ContextParameters.DatabaseUser.Key()), 
                        sce.getServletContext().getInitParameter(ContextParameters.DatabasePassword.Key()));
            
            IMapper itemMapper = new ItemMapper();
            IMapper clientMapper = new ClientMapper();
            IMapper orderMapper = new OrderMapper();
            
            MapperDictionary mappers = new MapperDictionary(itemMapper, clientMapper, orderMapper);
            mappers.put(Order.class, orderMapper);
            mappers.put(Client.class, clientMapper);
            mappers.put(Item.class, itemMapper);
            
            IPersistenceManager persistence = new MySqlPersistenceManager(persistenceConnection, mappers);
            
            sce.getServletContext().setAttribute(IClientService.class.getName(), new ClientService(persistence));
            sce.getServletContext().setAttribute(IItemService.class.getName(), new ItemService(persistence));
            sce.getServletContext().setAttribute(
                    IOrderService.class.getName(), 
                    new OrderService(persistence, (IItemService)sce.getServletContext().getAttribute(IItemService.class.getName())));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Bootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
    }
}
