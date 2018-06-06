package ua.danit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.danit.dao.ClientDAO;
import ua.danit.model.Client;
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
					writer.print("<h1>Congrats! You are registered!</h1> \n...go get some sleep...");
				} else {
					Client client = new Client();
					client.setLogin(login);
					client.setPassword(pass);
					client.setFirstName(firstName);
					client.setSecondName(secondName);
					clientDAO.save(client);

                    PrintWriter writer = resp.getWriter();
                    String outText = HtmlUtil.readPage("item-list.html");
                    outText = String.format(outText, client.getFirstName() + " " + client.getSecondName(),client.getLogin(), HtmlUtil.getItems());
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
                outText = String.format(outText, client.getFirstName() + " " + client.getSecondName(),client.getLogin(), HtmlUtil.getItems());
                writer.print(outText);
			}
			else
			{
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


//            PrintWriter writer = resp.getWriter();
//            String login = req.getParameter("login");
//            clientDAO = new ClientDAO();
//            Client client = clientDAO.get(login);
//            String firstName = client.getFirstName();
//            String secondName = client.getSecondName();
//            String outText = HtmlUtil.readPage("edit-user.html");
//            outText = String.format(outText,login,login, firstName, secondName, login);
//            writer.print(outText);
        }
		else if(action.equals("edit"))
		{
            PrintWriter writer = resp.getWriter();
            writer.write(HtmlUtil.readPage("edit-user.html"));
//			String    login     = req.getParameter("login");
//			String    oldPass      = req.getParameter("oldPass");
//
//			clientDAO = new ClientDAO();
//			Client client = clientDAO.get(login);
//
//
//			if(!oldPass.equals(client.getPassword())){
//				resp.addHeader("action", "edit");
//				resp.sendRedirect("/client");
//			}
//			String password = "";
//			if (req.getParameter("checkPass").equals(req.getParameter("pass")) && req.getParameter("pass") != null) {
//			    password = req.getParameter("pass");
//            } else {
//			    password = oldPass;
//            }
//
//			String    firstName      = req.getParameter("firstName");
//			String    secondName      = req.getParameter("secondName");
//			client.setLogin(login);
//			client.setPassword(password);
//			client.setFirstName(firstName);
//			client.setSecondName(secondName);
//			clientDAO.update(client);
//
//			Client clientFromDB = clientDAO.get((String)client.getLogin());
//            if (client.equals(clientFromDB)){
//				PrintWriter writer = resp.getWriter();
//				writer.print("<h1>Congrats! change was successful!</h1> \n...go get some sleep...");
//			}
		} else if(action.equals("delete"))
        {
            String    login     = req.getParameter("login");
            clientDAO.delete(login);
            PrintWriter writer = resp.getWriter();
            writer.print("<h1>Delete was successful!</h1>");
        }
	}
}
