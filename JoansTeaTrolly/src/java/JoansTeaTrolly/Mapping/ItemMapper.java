// --------------------------------------------------------------------------------------------------------------------
// <copyright file="ItemMapper.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.Mapping;

import JavaApplicationFramework.Mapping.IPersistenceSearcher;
import JavaApplicationFramework.Mapping.Mapper;
import JoansTeaTrolly.DomainModel.Item;
import JoansTeaTrolly.Interfaces.DomainModel.IItem;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        
        if(searcher.HasArgument("id"))
        {
            return query + " WHERE `id` = " + searcher.GetArgument("id");
        }
        
        return query;
    }

    @Override
    public Iterable<String> GetObjectCreateQueries(IItem t)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<String> GetObjectSaveQueries(IItem t)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected IItem MapFromResultSet(ResultSet results)
    {
        try {
            int id = results.getInt("id");
            String name = results.getString("name");
            int price = results.getInt("price");

            IItem mappedObject = new Item(false, id, name, price);

            return mappedObject;
        } catch (SQLException ex){
            return null;
        }
    }

}
