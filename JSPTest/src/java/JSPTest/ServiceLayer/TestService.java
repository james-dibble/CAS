/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JSPTest.ServiceLayer;

import JSPTest.Interfaces.DomainModel.ITest;
import JSPTest.Interfaces.ServiceLayer.ITestService;
import JavaApplicationFramework.Mapping.IPersistenceManager;
import JavaApplicationFramework.Mapping.IPersistenceSearcher;
import JavaApplicationFramework.Mapping.PersistenceSearcher;

/**
 *
 * @author james
 */
public class TestService implements ITestService
{
    private final IPersistenceManager _persistence;
    
    public TestService(IPersistenceManager persistence)
    {
        this._persistence = persistence;
    }
    
    @Override
    public ITest GetById(int id)
    {
        IPersistenceSearcher<ITest> searcher = new PersistenceSearcher<ITest>(ITest.class);
        
        searcher.put("Id", 3);
        
        ITest thing = this._persistence.Find(searcher);
        
        return thing;
    }

    @Override
    public Iterable<ITest> GetAll()
    {
        IPersistenceSearcher<ITest> searcher = new PersistenceSearcher<ITest>(ITest.class);
        
        Iterable<ITest> things = this._persistence.FindCollectionOf(searcher);
        
        return things;
    }
}