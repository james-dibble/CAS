// --------------------------------------------------------------------------------------------------------------------
// <copyright file="Test.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------

package JSPTest.DomainModel;

import JSPTest.Interfaces.DomainModel.ITest;
import JavaApplicationFramework.Mapping.UniqueObject;

public class Test extends UniqueObject<Integer> implements ITest {

    private String _name;
    private int _age;
    
    public Test(boolean isNewObject, int id, String name, int age){
        super(isNewObject, id);
        this._name = name;
        this._age = age;
    }
    
    public Test(String name, int age){
        super(true, -1);
        this._name = name;
        this._age = age;
    }
    
    @Override
    public String getName(){
        return this._name;
    }
    
    @Override
    public void setName(String name){
        this._name = name;
    }
    
    @Override
    public int getAge(){
        return this._age;
    }
}
