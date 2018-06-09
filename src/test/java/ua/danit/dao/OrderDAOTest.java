package ua.danit.dao;

import java.util.List;
import java.util.UUID;
import org.junit.*;
import ua.danit.model.Cart;
import ua.danit.model.Client;
import ua.danit.model.Item;
import ua.danit.model.Order;
import static org.junit.Assert.*;

public class OrderDAOTest
{

//	@BeforeClass
//	public static void setUp() throws Exception
//	{
//		Item item = new Item();
//
//		item.setName("testName");
//
//		Client client = new Client();
//		client.setLogin("testLogin12345");
//		client.setPassword("12345");
//		client.setFirstName("Alex");
//
//		Cart cart = new Cart();
//		cart.setCartID(1);
//		cart.setCartTime(System.currentTimeMillis());
//
//		ItemDAO itemDAO = new ItemDAO();
//		itemDAO.save(item);
//
//		ClientDAO clientDAO = new ClientDAO();
//		clientDAO.save(client);
//
//		CartDAO cartDAO = new CartDAO();
//		cartDAO.save(cart);
//
//		Order order = new Order();
//		order.setItemId(item.getArticleId());
//		order.setAmount(2);
//		order.setClientId(client.getLogin());
//		order.setCartId(cart.getCartID());
//
//		OrderDAO orderDAO = new OrderDAO();
//		orderDAO.save(order);
//
//	}
//
//	@AfterClass
//	public static void tearDown() throws Exception
//	{
//		ItemDAO itemDAO = new ItemDAO();
//		itemDAO.delete("testItem");
//
//		ClientDAO clientDAO = new ClientDAO();
//		clientDAO.delete("testLogin12345");
//
//		CartDAO cartDAO = new CartDAO();
//		cartDAO.delete(1);
//
//
//	}
//
//	@Test
//	public void getByClient()
//	{
//		OrderDAO orderDAO = new OrderDAO();
//
//		List<Order> orders = orderDAO.getByClient("testLogin12345");
//		assertTrue(!orders.isEmpty());
//	}
//
//	@Test
//	public void getByCart()
//	{
//	}
//
//	@Test
//	public void getByTimeAndClient()
//	{
//	}
}