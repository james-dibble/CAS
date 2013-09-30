package mysqltest;

// --------------------------------------------------------------------------------------------------------------------
// <copyright file="MySqlTest.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
import DataLayer.TestMapper;
import DomainModel.Test;
import JavaApplicationFramework.Mapping.ConnectionFactory;
import JavaApplicationFramework.Mapping.IConnectionFactory;
import JavaApplicationFramework.Mapping.IMapper;
import JavaApplicationFramework.Mapping.IPersistenceManager;
import JavaApplicationFramework.Mapping.MapperDictionary;
import JavaApplicationFramework.Mapping.MySqlPersistenceManager;
import JavaApplicationFramework.Mapping.PersistenceSearcher;
import java.sql.Connection;
import java.sql.SQLException;

public class MySqlTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MapperDictionary dictionary = new MapperDictionary();
                
        IConnectionFactory fact = new ConnectionFactory();
        
        Connection conn = fact.CreateConnection("jdbc:mysql://192.168.1.20/cas", "james", "3l3m3ntal!");
        
        IMapper<Test> mapper = new TestMapper();
        dictionary.put(Test.class, mapper);
        
        IPersistenceManager persistence = new MySqlPersistenceManager(conn, dictionary);
        
        PersistenceSearcher<Test> searcher = new PersistenceSearcher<>(Test.class);
        
        searcher.put("Id", 3);
        
        Test thing = persistence.Find(searcher);
        
        PrintTest(thing);
        
        searcher = new PersistenceSearcher<>(Test.class);
        
        Iterable<Test> things = persistence.FindCollectionOf(searcher);
        
        for(Test obj : things){
            PrintTest(obj);
        }
        
        Test newThink = new Test("James", 54);
        
        persistence.Add(newThink);
        persistence.Commit();
        
        things = persistence.FindCollectionOf(searcher);
        
        for(Test obj : things){
            PrintTest(obj);
        }
        
        thing.SetName("Something something else");
        
        persistence.Change(thing);
        persistence.Commit();
        
        things = persistence.FindCollectionOf(searcher);
        
        for(Test obj : things){
            PrintTest(obj);
        }
    }
    
    private static void PrintTest(Test obj){
        System.out.println(String.format("%s %s %s", obj.GetId(), obj.GetName(), obj.GetAge()));
    }
}
