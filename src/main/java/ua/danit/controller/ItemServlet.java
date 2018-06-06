package ua.danit.controller;

import ua.danit.dao.ItemDAO;
import ua.danit.model.Item;
import ua.danit.utils.HtmlUtil;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/item")
public class ItemServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		//doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
        String action = req.getParameter("action");

		if (action.equals("preview")) {
			PrintWriter writer = resp.getWriter();
			String login = req.getParameter("login");
            String articleId = req.getParameter("item");
            ItemDAO itemDAO = new ItemDAO();
            Item item = itemDAO.get(articleId);
            String itemInfo = "name: "+item.getName()+"<br>"+
                    "\nid: "+ item.getArticleId() +"<br>"+
                    "\nprice " + HtmlUtil.beatyPrice(item.getPrice()) + "<br>";
			String outText =  HtmlUtil.readPage("item-preview.html");
			outText = String.format(outText, itemInfo, login);

			writer.print(outText);
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("Ups");
		}
	}


}
