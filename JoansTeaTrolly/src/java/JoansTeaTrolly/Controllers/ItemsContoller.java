/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JoansTeaTrolly.Controllers;

import JoansTeaTrolly.Constants.Services;
import JoansTeaTrolly.Constants.Views;
import JoansTeaTrolly.DomainModel.Item;
import JoansTeaTrolly.Interfaces.DomainModel.IItem;
import JoansTeaTrolly.Interfaces.ServiceLayer.IItemService;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ItemsContoller extends Controller
{
    private IItemService _itemService;

    @Override
    public void init()
    {
        ServletContext context = this.getServletContext();

        this._itemService = (IItemService) context.getAttribute(Services.ItemService.Id());
    }

    @ActionAttribute(Path = "", Method = ActionAttribute.HttpMethod.GET)
    public void Index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setAttribute("items", this._itemService.GetAllItems());
        request.getRequestDispatcher(Views.ViewBase.Path().concat("Items/Index.jsp")).forward(request, response);
    }

    @ActionAttribute(Path = "/getallitems", Method = ActionAttribute.HttpMethod.GET)
    public void GetAllItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        JsonResult(request, response, this._itemService.GetAllItems());
    }
    
    @ActionAttribute(Path = "/edit", Method = ActionAttribute.HttpMethod.GET)
    public void Edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int itemId = Integer.parseInt(request.getPathInfo().replace("/", ""));
        
        request.setAttribute("item", this._itemService.GetItem(itemId));
        
        request.getRequestDispatcher(Views.ViewBase.Path().concat("Items/Edit.jsp")).forward(request, response);
    }
    
    @ActionAttribute(Path = "/edit", Method = ActionAttribute.HttpMethod.POST)
    public void Save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int itemId = Integer.parseInt(request.getPathInfo().replace("/", ""));
        int itemPrice = GetRequestParam(request, "price");
        String itemName = request.getParameter("name");
        
        IItem editedItem = new Item(false, itemId, itemName, itemPrice);
        
        this._itemService.ChangeItem(editedItem);
                
        response.sendRedirect(request.getContextPath().concat("/items/edit/" + itemId));
    }

    @Override
    protected String GetBasePath()
    {
        return "/items";
    }
}
