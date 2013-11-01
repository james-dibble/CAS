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
        final String query = 
"SELECT \n" +
"  `o`.`id`, \n" +
"  `o`.`quantity`,\n" +
"  `c`.`id` AS `ClientId`,\n" +
"  `c`.`name` AS `ClientName`,\n" +
"  `i`.`id` AS `ItemId`,\n" +
"  `i`.`name` AS `ItemName`,\n" +
"  `i`.`price` AS `ItemPrice`\n" +
"FROM \n" +
"  `orders` `o`\n" +
"INNER JOIN\n" +
"  `items` `i`\n" +
"  ON \n" +
"    `i`.`id` = `o`.`itemId`\n" +
"INNER JOIN \n" +
"  `clients` `c`\n" +
"  ON \n" +
"    `c`.`id` = `o`.`clientId`";
        
        if(searcher.HasArgument("id"))
        {
            return query + " WHERE `id` = " + searcher.GetArgument("id");
        }
        
        return query;
    }

    @Override
    public Iterable<String> GetObjectCreateQueries(IOrder objectToSave)
    {
        final String insertQueryTemplate = "INSERT INTO `order` (`clientId`, `itemId`, `quantity`) VALUES (%s, %s, %s)";
        
        String insert = String.format(
                insertQueryTemplate, 
                objectToSave.GetClient().GetId(), 
                objectToSave.GetItem().GetId(), 
                objectToSave.GetQuantity());
        
        ArrayList<String> queries = new ArrayList();
        queries.add(insert);
        
        return queries;
    }

    @Override
    public Iterable<String> GetObjectSaveQueries(IOrder t)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
