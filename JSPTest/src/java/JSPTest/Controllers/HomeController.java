/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JSPTest.Controllers;

import JSPTest.Interfaces.DomainModel.ITest;
import JSPTest.Interfaces.ServiceLayer.ITestService;
import JSPTest.Mapping.TestMapper;
import JSPTest.ServiceLayer.TestService;
import JavaApplicationFramework.Mapping.ConnectionFactory;
import JavaApplicationFramework.Mapping.IConnectionFactory;
import JavaApplicationFramework.Mapping.IMapper;
import JavaApplicationFramework.Mapping.IPersistenceManager;
import JavaApplicationFramework.Mapping.MapperDictionary;
import JavaApplicationFramework.Mapping.MySqlPersistenceManager;
import java.sql.Connection;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

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
}
