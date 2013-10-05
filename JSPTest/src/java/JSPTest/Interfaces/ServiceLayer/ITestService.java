/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JSPTest.Interfaces.ServiceLayer;

import JSPTest.Interfaces.DomainModel.ITest;

/**
 *
 * @author james
 */
public interface ITestService
{
    ITest GetById(int id);
    
    Iterable<ITest> GetAll();
}
