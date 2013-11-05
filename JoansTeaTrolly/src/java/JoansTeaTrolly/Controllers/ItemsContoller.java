package JoansTeaTrolly.Controllers;

import JavaApplicationFramework.Servlet.*;
import JoansTeaTrolly.Constants.View;
import JoansTeaTrolly.DomainModel.Item;
import JoansTeaTrolly.Interfaces.DomainModel.IItem;
import JoansTeaTrolly.Interfaces.ServiceLayer.IItemService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ItemsContoller extends Controller
{
    @InjectAttribute
    private IItemService _itemService;

    @Override
    protected String ControllerPath()
    {
        return "/items";
    }

    @ActionAttribute(Path = "", Method = ActionAttribute.HttpMethod.GET)
    public IActionResult Index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        Iterable<IItem> items = this._itemService.GetAllItems();
        
        return new ViewResult(View.Path("Items/Index.jsp"), items);
    }

    @ActionAttribute(Path = "/getallitems", Method = ActionAttribute.HttpMethod.GET)
    public IActionResult GetAllItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Iterable<IItem> items = this._itemService.GetAllItems();
        
        return new JsonResult(items);
    }
    
    @ActionAttribute(Path = "/edit", Method = ActionAttribute.HttpMethod.GET)
    public IActionResult Edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int itemId = Integer.parseInt(request.getPathInfo().replace("/", ""));
        
        IItem item = this._itemService.GetItem(itemId);
        
        return new ViewResult(View.Path("Items/Edit.jsp"), item);
    }
    
    @ActionAttribute(Path = "/edit", Method = ActionAttribute.HttpMethod.POST)
    public IActionResult Save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int itemId = Integer.parseInt(request.getPathInfo().replace("/", ""));
        int itemPrice = GetRequestParam(request, "price");
        String itemName = request.getParameter("name");
        
        IItem editedItem = new Item(false, itemId, itemName, itemPrice);
        
        this._itemService.ChangeItem(editedItem);
                        
        return new RedirectToAction("/items/edit/" + itemId);
    }
}
