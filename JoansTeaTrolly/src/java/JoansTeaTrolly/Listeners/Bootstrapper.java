package JoansTeaTrolly.Listeners;

import JavaApplicationFramework.Mapping.*;
import JavaApplicationFramework.Servlet.ServletBootstrapper;
import JoansTeaTrolly.Constants.ContextParameters;
import JoansTeaTrolly.Interfaces.ServiceLayer.*;
import JoansTeaTrolly.Mapping.*;
import JoansTeaTrolly.ServiceLayer.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

@WebListener()
public class Bootstrapper extends ServletBootstrapper
{
    @Override
    protected void InitContext(ServletContextEvent sce)
    {
        ServletContext context = sce.getServletContext();
        
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
            
            IPersistenceManager persistence = new MySqlPersistenceManager(persistenceConnection, mappers);
            
            IItemService itemService = new ItemService(persistence);
            
            this.Bind(new ClientService(persistence), IClientService.class, context);
            this.Bind(itemService, IItemService.class, context);
            this.Bind(new OrderService(persistence, itemService), IOrderService.class, context);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Bootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
