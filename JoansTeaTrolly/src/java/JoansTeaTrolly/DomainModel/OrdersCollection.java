// --------------------------------------------------------------------------------------------------------------------
// <copyright file="OrdersCollection.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JoansTeaTrolly.DomainModel;

import JoansTeaTrolly.Interfaces.DomainModel.IClient;
import JoansTeaTrolly.Interfaces.DomainModel.IOrder;
import java.util.ArrayList;
import java.util.HashMap;

public class OrdersCollection extends HashMap<IClient, ArrayList<IOrder>>
{

    public OrdersCollection AddOrder(IOrder order)
    {
        ArrayList<IOrder> clientOrders = this.GetOrdersForClient(order.getClient());

        clientOrders.add(order);

        return this;
    }

    public OrdersCollection RemoveOrdersForClient(IClient client)
    {
        this.remove(client);

        return this;
    }

    public OrdersCollection RemoveOrder(IOrder orderToRemove)
    {
        ArrayList<IOrder> clientOrders = this.GetOrdersForClient(orderToRemove.getClient());
        ArrayList<IOrder> newOrderCollection = new ArrayList<IOrder>();

        boolean orderFound = false;

        for (IOrder order : clientOrders)
        {
            if (!orderFound
                    && order.getItem().getId() == orderToRemove.getItem().getId()
                    && order.getQuantity() == orderToRemove.getQuantity())
            {
                orderFound = true;
            } 
            else
            {
                newOrderCollection.add(order);
            }
        }

        this.remove(orderToRemove.getClient());

        if (!newOrderCollection.isEmpty())
        {
            this.put(orderToRemove.getClient(), newOrderCollection);
        }

        return this;
    }

    public Iterable<IOrder> GetAllOrders()
    {
        ArrayList<IOrder> orders = new ArrayList<IOrder>();

        for (ArrayList<IOrder> clientOrders : this.values())
        {
            orders.addAll(clientOrders);
        }

        return orders;
    }
    
    public int GetTotalForClient(IClient client)
    {
        Iterable<IOrder> orders = this.GetOrdersForClient(client);
        
        int total = 0;
        
        for(IOrder order : orders)
        {
            total += (order.getItem().getPrice() * order.getQuantity());
        }
        
        return total;
    }

    @Override
    public boolean containsKey(Object key)
    {
        for (IClient client : this.keySet())
        {
            if (client.GetId() == ((IClient) key).GetId())
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public ArrayList<IOrder> get(Object key)
    {
        for (IClient client : this.keySet())
        {
            if (client.getId() == ((IClient) key).GetId())
            {
                return super.get(client);
            }
        }

        return null;
    }

    @Override
    public ArrayList<IOrder> remove(Object key)
    {
        for (IClient client : this.keySet())
        {
            if (client.getId() == ((IClient) key).GetId())
            {
                return super.remove(client);
            }
        }

        return null;
    }

    private ArrayList<IOrder> GetOrdersForClient(IClient client)
    {
        if (this.containsKey(client))
        {
            return this.get(client);
        }

        ArrayList<IOrder> clientOrders = new ArrayList<IOrder>();

        this.put(client, clientOrders);

        return clientOrders;
    }
}
