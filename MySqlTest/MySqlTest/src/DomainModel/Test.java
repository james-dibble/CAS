// --------------------------------------------------------------------------------------------------------------------
// <copyright file="Test.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------

package DomainModel;

import JavaApplicationFramework.Mapping.UniqueObject;

public class Test extends UniqueObject<Integer> {

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
    
    public String GetName(){
        return this._name;
    }
    
    public void SetName(String name){
        this._name = name;
    }
    
    public int GetAge(){
        return this._age;
    }
}
