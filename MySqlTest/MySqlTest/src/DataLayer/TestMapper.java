// --------------------------------------------------------------------------------------------------------------------
// <copyright file="TestMapper.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package DataLayer;

import DomainModel.Test;
import JavaApplicationFramework.Mapping.IPersistenceSearcher;
import JavaApplicationFramework.Mapping.Mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestMapper extends Mapper<Test> {

    @Override
    public Class GetMappedType() {
        return Test.class;
    }

    @Override
    public String GetFindQuery(IPersistenceSearcher<Test> searcher) {
        final String baseSelect = "SELECT `Id`,`Name`,`Age` FROM `cas`.`test`";

        if (searcher.HasArgument("Id")) {
            String id = searcher.GetArgument("Id").toString();

            String whereClause = " WHERE `id` = " + id;

            return baseSelect + whereClause;
        }

        return baseSelect;
    }

    @Override
    public Iterable<String> GetObjectCreateQueries(Test objectToSave) {
        final String insertQueryTemplate = "INSERT INTO `cas`.`test` (`Name`, `Age`) VALUES ('%s', %s)";
        
        String insert = String.format(insertQueryTemplate, objectToSave.GetName(), objectToSave.GetAge());
        
        ArrayList<String> queries = new ArrayList();
        queries.add(insert);
        
        return queries;
    }

    @Override
    public Iterable<String> GetObjectSaveQueries(Test objectToSave) {
        final String updateQueryTemplate = "UPDATE `cas`.`test` SET `Name` = '%s', Age = %s WHERE `Id` = %s";
        
        String update = String.format(updateQueryTemplate, objectToSave.GetName(), objectToSave.GetAge(), objectToSave.GetId());
        
        ArrayList<String> queries = new ArrayList();
        queries.add(update);
        
        return queries;
    }

    @Override
    protected Test MapFromResultSet(ResultSet results) {
        try {
            int id = results.getInt("Id");
            String name = results.getString("Name");
            int age = results.getInt("Age");

            Test mappedObject = new Test(false, id, name, age);

            return mappedObject;
        } catch (SQLException ex){
            return null;
        }
    }
}
