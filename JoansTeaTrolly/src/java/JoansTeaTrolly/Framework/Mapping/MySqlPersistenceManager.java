// --------------------------------------------------------------------------------------------------------------------
// <copyright file="MySqlPersistenceManager.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.Framework.Mapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

/**
 * A context to manage interaction to a MySQL persistence source.
 */
public final class MySqlPersistenceManager implements IPersistenceManager
{
    private final IConnectionFactory _connectionFactory;
    private final MapperDictionary _mappers;
    private Connection _connection;
    private List<String> _statementsToCommit;

    /**
     * Initialises a new instance of the MySqlPersistenceManager class.
     *
     * @param connectionFactory A JDBC connection to the MySQL source.
     * @param mappers A collection of know type mappers.
     */
    public MySqlPersistenceManager(IConnectionFactory connectionFactory, MapperDictionary mappers)
    {
        this._connectionFactory = connectionFactory;
        this._mappers = mappers;
        this._statementsToCommit = new Stack<String>();
    }

    @Override
    public <T extends IPersistableObject> T Find(IPersistenceSearcher<T> searcher)
    {
        IMapper mapper = this.GetMapperForType(searcher.Type());

        String query = mapper.GetFindQuery(searcher);

        T result = null;

        try
        {
            Statement statement = this.GetConnection().createStatement();

            ResultSet results = statement.executeQuery(query);

            result = (T) mapper.FindSingle(results);
        }
        finally
        {
            return result;
        }
    }

    @Override
    public <T extends IPersistableObject> Iterable<T> FindCollectionOf(IPersistenceSearcher<T> searcher)
    {
        IMapper mapper = this.GetMapperForType(searcher.Type());

        String query = mapper.GetFindQuery(searcher);

        Iterable<T> result = new ArrayList<T>();

        try
        {
            Statement statement = this.GetConnection().createStatement();

            ResultSet results = statement.executeQuery(query);

            result = mapper.FindCollectionOf(results);
        }
        finally
        {
            return result;
        }
    }

    @Override
    public void Add(IPersistableObject objectToSave)
    {
        if (!objectToSave.IsNewObject())
        {
            this.Change(objectToSave);
        }

        IMapper mapper = this.GetMapperForType(objectToSave.getClass());

        Iterable<String> queries = mapper.GetObjectCreateQueries(objectToSave);

        this._statementsToCommit.addAll((Collection<? extends String>) queries);
    }

    @Override
    public void Change(IPersistableObject objectToSave)
    {
        if (objectToSave.IsNewObject())
        {
            this.Add(objectToSave);
        }

        IMapper mapper = this.GetMapperForType(objectToSave.getClass());

        Iterable<String> queries = mapper.GetObjectSaveQueries(objectToSave);

        this._statementsToCommit.addAll((Collection<? extends String>) queries);
    }

    @Override
    public void Delete(IPersistableObject objectToDelete) {
        IMapper mapper = this.GetMapperForType(objectToDelete.getClass());
        
        Iterable<String> queries = mapper.GetObjectDeleteQueries(objectToDelete);

        this._statementsToCommit.addAll((Collection<? extends String>) queries);
    }

    @Override
    public void Commit() throws SQLException
    {
        try
        {
            this.GetConnection().setAutoCommit(false);
            
            for (String statement : this._statementsToCommit)
            {
                Statement sqlStatement = this.GetConnection().createStatement();
                sqlStatement.executeUpdate(statement);
            }
            
            this.GetConnection().commit();
        }
        finally
        {
            this.GetConnection().setAutoCommit(true);
            this._statementsToCommit.clear();
        }
    }

    @Override
    public void Dispose() throws SQLException
    {
        this.GetConnection().close();
    }

    private Connection GetConnection() throws SQLException
    {
        if(this._connection == null)
        {
            this._connection = this._connectionFactory.CreateConnection();
        }
        
        return this._connection;
    }
    
    private IMapper GetMapperForType(Class type)
    {
        IMapper mapper = this._mappers.get(type);

        if (mapper == null)
        {
            String message = String.format("A mapper has not been registered for type %s", type.toString());
            throw new UnsupportedOperationException(message);
        }

        return mapper;
    }
}
