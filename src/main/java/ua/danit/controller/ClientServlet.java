package ua.danit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.danit.dao.CartDAO;
import ua.danit.dao.ClientDAO;
import ua.danit.dao.OrderDAO;
import ua.danit.model.Cart;
import ua.danit.model.Client;
import ua.danit.model.Order;
import ua.danit.utils.HtmlUtil;

@WebServlet("/client")
public class ClientServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
/*		PrintWriter writer = resp.getWriter();
		writer.print("Hello!");*/
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
        String action = req.getParameter("action");
		/*if(action == null || action.isEmpty()){
			action = req.getHeader("action");
		}*/
		ClientDAO clientDAO = new ClientDAO();
		if(action.equals("create"))
		{
			String    login     = req.getParameter("login");
			String    pass      = req.getParameter("pass");
			String    checkPass     = req.getParameter("checkPass");
			String    firstName      = req.getParameter("firstName");
			String    secondName      = req.getParameter("secondName");

			if(!pass.equals(checkPass)){
				PrintWriter writer = resp.getWriter();
				String outText = HtmlUtil.readPage("new-user.html");
                outText = String.format(outText, "Passwords are not the same!");
				writer.print(outText);
			} else {
				Client clientFromDB = clientDAO.get((String)login);
				if(clientFromDB != null){
					PrintWriter writer = resp.getWriter();
					String outText = HtmlUtil.readPage("new-user.html");
					outText = String.format(outText, "This login already exists");
					writer.print(outText);
				} else {
					Client client = new Client();
					client.setLogin(login);
					client.setPassword(pass);
					client.setFirstName(firstName);
					client.setSecondName(secondName);
					clientDAO.save(client);

                    PrintWriter writer = resp.getWriter();
                    String outText = HtmlUtil.readPage("item-list.html");
                    outText = String.format(outText, client.getFirstName() + " " + client.getSecondName(),client.getLogin(), HtmlUtil.getItems(login));
                    writer.print(outText);
				}
			}
		}
		else if(action.equals("checkLogin"))
		{
			String    login     = req.getParameter("login");
			String    pass      = req.getParameter("pass");
			Client    client    = clientDAO.get((String) login);
			if ( client != null && pass.equals(client.getPassword()) )
			{
                PrintWriter writer = resp.getWriter();
                String outText = HtmlUtil.readPage("item-list.html");
                outText = String.format(outText, client.getFirstName() + " " + client.getSecondName(),client.getLogin(), HtmlUtil.getItems(login));
                writer.print(outText);
			} else if (client != null && !pass.equals(client.getPassword())) {
				PrintWriter writer = resp.getWriter();
				String outText = HtmlUtil.readPage("index.html");
				outText = String.format(outText, "Password is wrong");
				writer.print(outText);
			} else	{
				resp.sendRedirect("/shop-servlet");
			}
		}

        else if(action.equals("profile"))
        {
            String login = req.getParameter("login");
            Client client = clientDAO.get(login);
            PrintWriter writer = resp.getWriter();
            String outText = HtmlUtil.readPage("user-cabinet.html");
            outText = String.format(outText,
                    client.getFirstName() + " " + client.getSecondName(),
                    client.getLogin(),
                    client.getLogin(),
                    HtmlUtil.getCarts(login));
            writer.print(outText);
        }

		else if(action.equals("goToEdit"))
		{
            PrintWriter writer = resp.getWriter();
            String outText = HtmlUtil.readPage("edit-user.html");
            String login = req.getParameter("login");
            Client client = clientDAO.get(login);
            outText = String.format(outText, "Renew your information", login,client.getFirstName(), client.getSecondName());
            writer.write(outText);

		} else if(action.equals("edit")) {
			String    login     = req.getParameter("login");
			String    oldPass      = req.getParameter("oldPass");

			clientDAO = new ClientDAO();
			Client client = clientDAO.get(login);

			if(!oldPass.equals(client.getPassword())){
				resp.addHeader("action", "edit");
				resp.sendRedirect("/client");
			}
			String password = "";
			if (req.getParameter("checkPass").equals(req.getParameter("pass")) && (req.getParameter("pass").length() >=1)) {
			    password = req.getParameter("pass");
            } else {
			    password = oldPass;
            }

			String    firstName      = req.getParameter("firstName");
			if (firstName.equals("")) {
				firstName = client.getFirstName();
			}
			String    secondName      = req.getParameter("secondName");
			if (secondName.equals("")) {
				secondName = client.getSecondName();
			}
			client.setLogin(login);
			client.setPassword(password);
			client.setFirstName(firstName);
			client.setSecondName(secondName);
			clientDAO.update(client);

			Client clientFromDB = clientDAO.get((String)client.getLogin());
            if (client.equals(clientFromDB)){
				PrintWriter writer = resp.getWriter();
				String outText = HtmlUtil.readPage("item-list.html");
				outText = String.format(outText, firstName+" "+ secondName, login, HtmlUtil.getItems(login));
				writer.print(outText);
			}
		}
		else if(action.equals("delete"))
        {
            String    login     = req.getParameter("login");
            CartDAO cartDAO = new CartDAO();
			OrderDAO orderDAO = new OrderDAO();
            List<Cart> carts = cartDAO.getAllByLogin(login);
            for (Cart cart: carts) {
				List<Order> orders = orderDAO.getByCart(cart.getCartID());
					for( Order order: orders) {
						orderDAO.delete(order.getOrderId());
					}
					cartDAO.delete(cart.getCartID());
			}
            clientDAO.delete(login);
            PrintWriter writer = resp.getWriter();
            writer.print("<h1>Delete was successful!</h1>");
        }
	}
}
