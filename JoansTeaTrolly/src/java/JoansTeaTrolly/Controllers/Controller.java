// --------------------------------------------------------------------------------------------------------------------
// <copyright file="Controller.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.Controllers;

import JoansTeaTrolly.Controllers.ActionAttribute.HttpMethod;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Controller extends HttpServlet
{
    protected abstract String GetBasePath();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            processRequest(request, response, HttpMethod.GET);
        }
        catch (IllegalAccessException ex)
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalArgumentException ex)
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InvocationTargetException ex)
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            processRequest(request, response, HttpMethod.POST);
        }
        catch (IllegalAccessException ex)
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalArgumentException ex)
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InvocationTargetException ex)
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response, HttpMethod httpMethod)
            throws ServletException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        String path = request.getServletPath().replace(this.GetBasePath(), "");

        Method[] methods = this.getClass().getMethods();

        for (Method method : methods)
        {
            if (method.isAnnotationPresent(ActionAttribute.class))
            {
                ActionAttribute attr = GetAction(method);
                
                if(attr.Method() == httpMethod && attr.Path().toLowerCase().equals(path.toLowerCase()))
                {
                    method.invoke(this, request, response);
                    break;
                }
            }
        }
    }
    
    private static ActionAttribute GetAction(Method method)
    {
        for(Annotation attr : method.getDeclaredAnnotations())
        {
            if(attr.annotationType() == ActionAttribute.class)
            {
                return (ActionAttribute)attr;
            }
        }
        
        throw new IllegalStateException("This method has no action attribute.");
    }
}
