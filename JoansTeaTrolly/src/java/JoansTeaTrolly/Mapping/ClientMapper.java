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
import java.util.ArrayList;

public class ClientMapper extends Mapper<IClient> {

    @Override
    public Class GetMappedType() {
        return IClient.class;
    }

    @Override
    public String GetFindQuery(IPersistenceSearcher<IClient> searcher) {
        final String query = "SELECT `id`, `name` FROM `clients`";

        if (searcher.HasArgument("Id")) {
            return query + " WHERE `id` = " + searcher.GetArgument("Id");
        }

        return query;
    }

    @Override
    public Iterable<String> GetObjectCreateQueries(IClient objectToSave) {
        final String insertQueryTemplate = "INSERT INTO `clients` (`name`) VALUES ('%s')";

        String insert = String.format(
                insertQueryTemplate,
                objectToSave.getName());

        ArrayList<String> queries = new ArrayList();
        queries.add(insert);

        return queries;
    }

    @Override
    public Iterable<String> GetObjectSaveQueries(IClient objectToSave) {
        final String insertQueryTemplate = "UPDATE `clients` SET `name` = '%s' WHERE `id` = %s";

        String insert = String.format(
                insertQueryTemplate,
                objectToSave.getName(),
                objectToSave.GetId());

        ArrayList<String> queries = new ArrayList();
        queries.add(insert);

        return queries;
    }

    @Override
    public Iterable<String> GetObjectDeleteQueries(IClient objectToDelete) {
        final String deleteOrdersTemplate = "DELETE FROM `orders` WHERE `clientId` = %s";
        final String deleteClientTemplate = "DELETE FROM `clients` WHERE `id` = %s";
        
        String deleteClientOrders = String.format(
                deleteOrdersTemplate,
                objectToDelete.GetId());
        
        String deleteClient = String.format(
                deleteClientTemplate,
                objectToDelete.GetId());
        
        ArrayList<String> queries = new ArrayList();
        queries.add(deleteClientOrders);
        queries.add(deleteClient);

        return queries;
    }

    @Override
    protected IClient MapFromResultSet(ResultSet results) {
        try {
            int id = results.getInt("id");
            String name = results.getString("name");

            IClient mappedObject = new Client(false, id, name);

            return mappedObject;
        } catch (SQLException ex) {
            return null;
        }
    }
}
