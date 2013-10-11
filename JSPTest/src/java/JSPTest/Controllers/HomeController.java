/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JSPTest.Controllers;

import JSPTest.Interfaces.DomainModel.ITest;
import JSPTest.Interfaces.ServiceLayer.ITestService;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author james
 */
@Controller
public class HomeController
{
    private final ITestService _testService;
    
    @Autowired
    public HomeController(ITestService testService)
    {
        this._testService = testService;
    }
    
    @RequestMapping("/")
    public String Index(Model model) throws SQLException
    {       
        Iterable<ITest> things = this._testService.GetAll();
        
        model.addAttribute("tests", things);
        
        return "Home/Index";
    }
    
    @RequestMapping("/contact")
    public String Contact()
    {
        return "Home/Contact";
    }
    
    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public String ProcessContact(@RequestParam("email")String email, @RequestParam("message")String message, Model model)
    {
        ContactModel viewModel = new ContactModel(email, message);
        
        model.addAttribute("contact", viewModel);
        
        return "Home/ContactResponse";
    }
}
