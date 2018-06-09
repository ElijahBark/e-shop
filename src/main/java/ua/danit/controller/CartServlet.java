package ua.danit.controller;

import ua.danit.dao.CartDAO;
import ua.danit.dao.ItemDAO;
import ua.danit.dao.OrderDAO;
import ua.danit.model.Cart;
import ua.danit.model.Item;
import ua.danit.model.Order;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cart")
public class CartServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		PrintWriter writer = resp.getWriter();
		writer.print("Hello!");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String action = req.getParameter("action");
		if (action.equals("addToCart")) {
			PrintWriter writer = resp.getWriter();
			String login = req.getParameter("login");

			ItemDAO itemDAO = new ItemDAO();
			String articleId = req.getParameter("article_id");
			Item item = itemDAO.get(articleId);

			CartDAO cartDAO = new CartDAO();
			Cart cart = null;
			List<Cart> carts = cartDAO.getAllByLogin(login);
			if(!carts.isEmpty()) {
				for (Cart cartCurrent : carts) {
					if (!cartCurrent.isCartConfirmed()) {
						cart = cartCurrent;
						break;
					}
				}
			}
			if (cart == null) {
				cart = new Cart();
				cartDAO.save(cart);
			}

			Order order = new Order();
			order.setItemId(item.getArticleId());
			order.setCartId(cart.getCartID());
			order.setClientId(login);

			OrderDAO orderDAO = new OrderDAO();
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


			writer.print("Success");
		}
	}

}
