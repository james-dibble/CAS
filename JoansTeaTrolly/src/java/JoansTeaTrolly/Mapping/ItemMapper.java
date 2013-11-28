// --------------------------------------------------------------------------------------------------------------------
// <copyright file="ItemMapper.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.Mapping;

import JoansTeaTrolly.Framework.Mapping.IPersistenceSearcher;
import JoansTeaTrolly.Framework.Mapping.Mapper;
import JoansTeaTrolly.DomainModel.Item;
import JoansTeaTrolly.Interfaces.DomainModel.IItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemMapper extends Mapper<IItem>
{
    @Override
    public Class GetMappedType()
    {
        return IItem.class;
    }

    @Override
    public String GetFindQuery(IPersistenceSearcher<IItem> searcher)
    {
        final String query = "SELECT `id`, `name`, `price` FROM `items`";

        if (searcher.HasArgument("Id"))
        {
            return query + " WHERE `id` = " + searcher.GetArgument("Id");
        }

        return query;
    }

    @Override
    public Iterable<String> GetObjectCreateQueries(IItem objectToSave)
    {
        final String insertQueryTemplate = "INSERT INTO `items` (`name`, `price`) VALUES ('%s', %s)";

        String insert = String.format(
                insertQueryTemplate,
                objectToSave.getName(),
                objectToSave.getPrice());

        ArrayList<String> queries = new ArrayList();
        queries.add(insert);

        return queries;
    }

    @Override
    public Iterable<String> GetObjectSaveQueries(IItem objectToSave)
    {
        final String insertQueryTemplate = "UPDATE `items` SET `name` = '%s', `price` = %s WHERE `id` = %s";

        String insert = String.format(
                insertQueryTemplate,
                objectToSave.getName(),
                objectToSave.getPrice(),
                objectToSave.GetId());

        ArrayList<String> queries = new ArrayList();
        queries.add(insert);

        return queries;
    }

    @Override
    public Iterable<String> GetObjectDeleteQueries(IItem objectToDelete) {
        throw new UnsupportedOperationException("Items cannot be deleted.");
    }
    
    @Override
    protected IItem MapFromResultSet(ResultSet results)
    {
        try
        {
            int id = results.getInt("id");
            String name = results.getString("name");
            int price = results.getInt("price");

            IItem mappedObject = new Item(false, id, name, price);

            return mappedObject;
        }
        catch (SQLException ex)
        {
            return null;
        }
    }
}
