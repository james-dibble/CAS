// --------------------------------------------------------------------------------------------------------------------
// <copyright file="ClientMapper.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.Mapping;

import JavaApplicationFramework.Mapping.IPersistenceSearcher;
import JavaApplicationFramework.Mapping.Mapper;
import JoansTeaTrolly.DomainModel.Client;
import JoansTeaTrolly.Interfaces.DomainModel.IClient;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper extends Mapper<IClient>
{
    @Override
    public Class GetMappedType()
    {
        return IClient.class;
    }

    @Override
    public String GetFindQuery(IPersistenceSearcher<IClient> searcher)
    {
        final String query = "SELECT `id`, `name` FROM `clients`";
        
        if(searcher.HasArgument("Id"))
        {
            return query + " WHERE `id` = " + searcher.GetArgument("Id");
        }
        
        return query;
    }

    @Override
    public Iterable<String> GetObjectCreateQueries(IClient t)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<String> GetObjectSaveQueries(IClient t)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected IClient MapFromResultSet(ResultSet results)
    {
        try {
            int id = results.getInt("id");
            String name = results.getString("name");

            IClient mappedObject = new Client(false, id, name);

            return mappedObject;
        } catch (SQLException ex){
            return null;
        }
    }

}
