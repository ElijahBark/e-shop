package ua.danit.utils;

import ua.danit.dao.CartDAO;
import ua.danit.dao.ClientDAO;
import ua.danit.dao.ItemDAO;
import ua.danit.dao.OrderDAO;
import ua.danit.model.Cart;
import ua.danit.model.Client;
import ua.danit.model.Item;
import ua.danit.model.Order;

import java.util.Date;
import java.util.List;

public class test {
    public static void main(String[] args) {
//        Item item = new Item();
//        item.setName("book1");
//        item.setPrice(1990);
//        ItemDAO itemDAO = new ItemDAO();
//        itemDAO.save(item);

//        Client client = new Client();
//        client.setFirstName("Alex");
//        client.setSecondName("Ignatenko");
//        client.setLogin("Alex123");
//        client.setPassword("123");
//
//        ClientDAO clientDAO = new ClientDAO();
//        clientDAO.save(client);
//
//        Cart cart = new Cart();
//        CartDAO cartDAO = new CartDAO();
//        cartDAO.save(cart);
//
//        Order order = new Order();
//        order.setAmount(1);
//        order.setClientId("Alex123");
//        order.setCartId(cart.getCartID());
//        order.setItemId(898110824);
//        OrderDAO orderDAO = new OrderDAO();
//        orderDAO.save(order);
        String login = "Barkov";
        CartDAO cartDAO = new CartDAO();
        Cart cart = cartDAO.get(429785528);
        OrderDAO orderDAO = new OrderDAO();
        Order order = new Order();
        order.setItemId(898110824);
        order.setCartId(cart.getCartID());
        order.setClientId(login);

        List<Order> orders = orderDAO.getByCart(cart.getCartID());
        if (!orders.isEmpty()) {
            for (Order orderCurrent: orders) {
                if (orderCurrent.getItemId().equals(order.getItemId())) {
                    order.setAmount(orderCurrent.getAmount()+1);
                    order.setOrderId(orderCurrent.getOrderId());
                    orderDAO.update(order);
                    break;
                }
            }
        }

        if (order.getAmount() == null)  {
            order.setAmount(1);
            orderDAO.save(order);
        }

    }
}
