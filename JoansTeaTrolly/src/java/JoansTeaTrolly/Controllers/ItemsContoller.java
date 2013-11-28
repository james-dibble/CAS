package JoansTeaTrolly.Controllers;

import JoansTeaTrolly.Framework.Servlet.*;
import JoansTeaTrolly.Constants.View;
import JoansTeaTrolly.DomainModel.Item;
import JoansTeaTrolly.Interfaces.DomainModel.IItem;
import JoansTeaTrolly.Interfaces.ServiceLayer.IItemService;
import javax.servlet.http.HttpServletRequest;

public class ItemsContoller extends Controller
{
    @InjectAttribute
    private IItemService _itemService;

    @Override
    protected String ControllerPath()
    {
        return "/items";
    }

    public IActionResult Index(HttpServletRequest request)
    {
        Iterable<IItem> items = this._itemService.GetAllItems();
        
        return new ViewResult(View.Path("Items/Index.jsp"), items);
    }

    public IActionResult GetAllItems(HttpServletRequest request)
    {
        Iterable<IItem> items = this._itemService.GetAllItems();
        
        return new JsonResult(items);
    }
    
    public IActionResult Edit(HttpServletRequest request)
    {
        int itemId = Integer.parseInt(request.getPathInfo().replace("/", ""));
        
        IItem item = this._itemService.GetItem(itemId);
        
        return new ViewResult(View.Path("Items/Edit.jsp"), item);
    }
    
    @ActionAttribute(Path = "/edit", Method = ActionAttribute.HttpMethod.POST)
    public IActionResult Save(HttpServletRequest request)
    {
        int itemId = Integer.parseInt(request.getPathInfo().replace("/", ""));
        int itemPrice = Controller.GetRequestParam(request, "price");
        String itemName = request.getParameter("name");
        
        IItem editedItem = new Item(false, itemId, itemName, itemPrice);
        
        this._itemService.ChangeItem(editedItem);
                        
        return new RedirectToAction("/items/edit/" + itemId);
    }
}
