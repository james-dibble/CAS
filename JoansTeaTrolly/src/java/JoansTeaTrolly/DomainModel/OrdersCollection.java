// --------------------------------------------------------------------------------------------------------------------
// <copyright file="OrdersCollection.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.DomainModel;

import JoansTeaTrolly.Interfaces.DomainModel.IClient;
import JoansTeaTrolly.Interfaces.DomainModel.IOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OrdersCollection extends ArrayList<IOrder>
{
    public void Sort()
    {
        Collections.sort(this, new Comparator<IOrder>()
        {
            @Override
            public int compare(IOrder o1, IOrder o2)
            {
                return o1.getClient().getName().compareTo(o2.getClient().getName());
            }
        });
    }

    @Override
    public boolean add(IOrder order)
    {
        boolean result = super.add(order);

        this.Sort();

        return result;
    }

    public OrdersCollection RemoveOrdersForClient(IClient client)
    {
        OrdersCollection orders = new OrdersCollection();

        for (IOrder order : this)
        {
            if (order.getClient().GetId() != client.GetId())
            {
                orders.add(order);
            }
        }

        return orders;
    }

    public OrdersCollection RemoveOrder(IOrder orderToRemove)
    {
        OrdersCollection orders = new OrdersCollection();

        boolean orderFound = false;

        for (IOrder order : this)
        {
            if (!orderFound
                    && order.getClient() == orderToRemove.getClient()
                    && order.getItem() == orderToRemove.getItem()
                    && order.getQuantity() == orderToRemove.getQuantity())
            {
                orderFound = true;
            }
            else
            {
                orders.add(order);
            }
        }
        
        return orders;
    }
}
