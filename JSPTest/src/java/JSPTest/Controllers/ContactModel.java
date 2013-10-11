// --------------------------------------------------------------------------------------------------------------------
// <copyright file="ContactModel.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JSPTest.Controllers;

public class ContactModel
{
    private final String _email;
    private final String _message;

    public ContactModel(String email, String message)
    {
        this._email = email;
        this._message = message;
    }

    public String getEmail()
    {
        return this._email;
    }

    public String getMessage()
    {
        return this._message;
    }
}
