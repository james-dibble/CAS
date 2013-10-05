/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JSPTest.Mapping;

import JSPTest.DomainModel.Test;
import JSPTest.Interfaces.DomainModel.ITest;
import JavaApplicationFramework.Mapping.IPersistenceSearcher;
import JavaApplicationFramework.Mapping.Mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author james
 */
public class TestMapper extends Mapper<ITest>
{
    @Override
    public Class GetMappedType() {
        return ITest.class;
    }

    @Override
    public String GetFindQuery(IPersistenceSearcher<ITest> searcher) {
        final String baseSelect = "SELECT `Id`,`Name`,`Age` FROM `cas`.`test`";

        if (searcher.HasArgument("Id")) {
            String id = searcher.GetArgument("Id").toString();

            String whereClause = " WHERE `id` = " + id;

            return baseSelect + whereClause;
        }

        return baseSelect;
    }

    @Override
    public Iterable<String> GetObjectCreateQueries(ITest objectToSave) {
        final String insertQueryTemplate = "INSERT INTO `cas`.`test` (`Name`, `Age`) VALUES ('%s', %s)";
        
        String insert = String.format(insertQueryTemplate, objectToSave.getName(), objectToSave.getAge());
        
        ArrayList<String> queries = new ArrayList();
        queries.add(insert);
        
        return queries;
    }

    @Override
    public Iterable<String> GetObjectSaveQueries(ITest objectToSave) {
        final String updateQueryTemplate = "UPDATE `cas`.`test` SET `Name` = '%s', Age = %s WHERE `Id` = %s";
        
        String update = String.format(updateQueryTemplate, objectToSave.getName(), objectToSave.getAge(), objectToSave.GetId());
        
        ArrayList<String> queries = new ArrayList();
        queries.add(update);
        
        return queries;
    }

    @Override
    protected ITest MapFromResultSet(ResultSet results) {
        try {
            int id = results.getInt("Id");
            String name = results.getString("Name");
            int age = results.getInt("Age");

            ITest mappedObject = new Test(false, id, name, age);

            return mappedObject;
        } catch (SQLException ex){
            return null;
        }
    }   
}