/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JSPTest.Interfaces.DomainModel;

import JavaApplicationFramework.Mapping.IUniqueObject;

/**
 *
 * @author james
 */
public interface ITest extends IUniqueObject<Integer>
{
    String getName();
    
    void setName(String name);
    
    int getAge();
}
