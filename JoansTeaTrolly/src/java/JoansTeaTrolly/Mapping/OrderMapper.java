// --------------------------------------------------------------------------------------------------------------------
// <copyright file="OrderMapper.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.Mapping;

import JavaApplicationFramework.Mapping.IPersistenceSearcher;
import JavaApplicationFramework.Mapping.Mapper;
import JoansTeaTrolly.DomainModel.Client;
import JoansTeaTrolly.DomainModel.Item;
import JoansTeaTrolly.DomainModel.Order;
import JoansTeaTrolly.Interfaces.DomainModel.IClient;
import JoansTeaTrolly.Interfaces.DomainModel.IItem;
import JoansTeaTrolly.Interfaces.DomainModel.IOrder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderMapper extends Mapper<IOrder>
{
    @Override
    public Class GetMappedType()
    {
        return IOrder.class;
    }

    @Override
    public String GetFindQuery(IPersistenceSearcher<IOrder> searcher)
    {
        final String queryTemplate = 
"SELECT `o`.`id`, `o`.`quantity`,`c`.`id` AS `ClientId`,`c`.`name` AS `ClientName`,`i`.`id` AS `ItemId`,`i`.`name` AS `ItemName`,`i`.`price` AS `ItemPrice`\n" +
"FROM `orders` `o` INNER JOIN `items` `i` ON  `i`.`id` = `o`.`itemId` INNER JOIN  `clients` `c` ON  `c`.`id` = `o`.`clientId`";
        
        String query = queryTemplate;
        
        if(searcher.HasArgument("Id"))
        {
            query = query.concat(" WHERE `id` = " + searcher.GetArgument("Id"));
        }
        
        if(searcher.HasArgument("orderbyname"))
        {
            query = query.concat(" ORDER BY `ClientName` ASC");
        }
        
        return query;
    }

    @Override
    public Iterable<String> GetObjectCreateQueries(IOrder objectToSave)
    {
        final String insertQueryTemplate = "INSERT INTO `orders` (`clientId`, `itemId`, `quantity`) VALUES (%s, %s, %s)";
        
        String insert = String.format(
                insertQueryTemplate, 
                objectToSave.getClient().GetId(), 
                objectToSave.getItem().GetId(), 
                objectToSave.getQuantity());
        
        ArrayList<String> queries = new ArrayList();
        queries.add(insert);
        
        return queries;
    }

    @Override
    public Iterable<String> GetObjectSaveQueries(IOrder objectToSave)
    {
        final String insertQueryTemplate = "UPDATE `orders` SET `clientId` = %s, `itemId` = %s, `quantity` = %s WHERE `id` = %s";
        
        String insert = String.format(
                insertQueryTemplate, 
                objectToSave.getClient().GetId(), 
                objectToSave.getItem().GetId(), 
                objectToSave.getQuantity(),
                objectToSave.GetId());
        
        ArrayList<String> queries = new ArrayList();
        queries.add(insert);
        
        return queries;
    }
    
    @Override
    public Iterable<String> GetObjectDeleteQueries(IOrder objectToDelete) {
        throw new UnsupportedOperationException("Orders cannot be deleted.");
    }

    @Override
    protected IOrder MapFromResultSet(ResultSet results)
    {
        try {
            int clientId = results.getInt("ClientId");
            String clientName = results.getString("ClientName");

            IClient orderClient = new Client(false, clientId, clientName);

            int itemId = results.getInt("ItemId");
            String itemName = results.getString("ItemName");
            int itemPrice = results.getInt("ItemPrice");

            IItem orderItem = new Item(false, itemId, itemName, itemPrice);
            
            int id = results.getInt("id");
            int quantity = results.getInt("quantity");
            
            IOrder mappedObject = new Order(false, id, orderClient, orderItem, quantity);
            
            return mappedObject;
        } catch (SQLException ex){
            return null;
        }
    }

}
