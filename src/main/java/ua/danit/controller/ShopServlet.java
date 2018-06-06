package ua.danit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.danit.utils.HtmlUtil;

@WebServlet("/shop-servlet")
public class ShopServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		PrintWriter writer = resp.getWriter();
		writer.print(HtmlUtil.readPage("index.html"));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String action = req.getParameter("action");
		if(action.equals("regNew")){
			PrintWriter writer = resp.getWriter();
			String outText = HtmlUtil.readPage("new-user.html");
			outText = String.format(outText, "Input Your credentials");
			writer.print(outText);

		} else {
			doGet(req, resp);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{

	}
}
