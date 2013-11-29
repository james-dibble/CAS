package JoansTeaTrolly.Listeners;

import JoansTeaTrolly.Framework.Mapping.*;
import JoansTeaTrolly.Framework.Servlet.ServletBootstrapper;
import JoansTeaTrolly.Constants.ContextParameters;
import JoansTeaTrolly.Interfaces.ServiceLayer.*;
import JoansTeaTrolly.Mapping.*;
import JoansTeaTrolly.ServiceLayer.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

@WebListener()
public class Bootstrapper extends ServletBootstrapper
{
    @Override
    protected void InitContext(ServletContextEvent sce) throws Exception
    {
        ServletContext context = sce.getServletContext();

        IConnectionFactory persistenceConnection =
                new ConnectionFactory(
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
}
