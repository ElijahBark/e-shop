package ua.danit.utils;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ua.danit.controller.ClientServlet;
import ua.danit.controller.ItemServlet;
import ua.danit.controller.ShopServlet;

public class LocalServerJetty
{
	public static void start(){
		Server server = new Server(8080);
		ServletContextHandler handler = new ServletContextHandler();

		ServletHolder holder = new ServletHolder(new ShopServlet());
		handler.addServlet(holder, "/shop-servlet");

		ServletHolder clientHolder = new ServletHolder(new ClientServlet());
		handler.addServlet(clientHolder, "/client");

		ServletHolder itemHolder = new ServletHolder(new ItemServlet());
		handler.addServlet(itemHolder, "/item");

		server.setHandler(handler);
		try
		{
			server.start();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		try
		{
			server.join();
		}
		catch ( InterruptedException e )
		{
			e.printStackTrace();
		}
	}
}
