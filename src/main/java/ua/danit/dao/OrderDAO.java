package ua.danit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ua.danit.model.Order;

public class OrderDAO extends AbstractDAO<Order>
{
	@Override
	public void save(Order order)
	{
		String sql = "INSERT INTO public.order(client_id, item_id, amount, cart_id) VALUES(?,?,?,?)";

		try ( Connection connection = ConnectionToDB.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql); )
		{
			statement.setString(1, order.getClientId());
			statement.setInt(2, order.getItemId());
			statement.setInt(3, order.getAmount());
			statement.setInt(4, order.getCartId());

			statement.executeUpdate();
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
	}

	@Override public void update(Order order)
	{
		String sql = "UPDATE public.order SET client_id=?, item_id=?, amount=?, cart_id=? WHERE order_id=?";

		try ( Connection connection = ConnectionToDB.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); )
		{
			statement.setString(1, order.getClientId());
			statement.setInt(2, order.getItemId());
			statement.setInt(3, order.getAmount());
			statement.setInt(4, order.getCartId());
			statement.setInt(5, order.getOrderId());

			statement.executeUpdate();
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
	}

	@Override
	public Order get(Object orderId)
	{
		Order order = new Order();

		String sql = "SELECT * FROM public.order WHERE order_id='" + orderId + "'";

		try (
			Connection        connection  = ConnectionToDB.getConnection();
			PreparedStatement statement  = connection.prepareStatement(sql);
			ResultSet rSet = statement.executeQuery();
			)
		{
			while ( rSet.next() )
			{
				order.setOrderId(rSet.getInt("order_id"));
				order.setClientId(rSet.getString("client_id"));
				order.setItemId(rSet.getInt("item_id"));
				order.setAmount(rSet.getInt("amount"));
				order.setCartId(rSet.getInt("cart_id"));

				return order;
			}
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		return null;
	}

	public List<Order> getByClient(String clientId)
	{
		List<Order> orders = new ArrayList<>();

		String sql = "SELECT * FROM public.order WHERE client_id='" + clientId + "'";

		try (
			Connection        connection  = ConnectionToDB.getConnection();
			PreparedStatement statement  = connection.prepareStatement(sql);
			ResultSet rSet = statement.executeQuery();
		)
		{
			while ( rSet.next() )
			{
				Order order = new Order();

				order.setOrderId(rSet.getInt("order_id"));
				order.setClientId(rSet.getString("client_id"));
				order.setItemId(rSet.getInt("item_id"));
				order.setAmount(rSet.getInt("amount"));
				order.setCartId(rSet.getInt("cart_id"));

				orders.add(order);
			}
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		return orders;
	}

	public List<Order> getByCart(Integer cartId)
	{
		List<Order> orders = new ArrayList<>();

		String sql = "SELECT * FROM public.order WHERE cart_id=" + cartId;

		try (
			Connection        connection  = ConnectionToDB.getConnection();
			PreparedStatement statement  = connection.prepareStatement(sql);
			ResultSet rSet = statement.executeQuery();
		)
		{
			while ( rSet.next() )
			{
				Order order = new Order();

				order.setOrderId(rSet.getInt("order_id"));
				order.setClientId(rSet.getString("client_id"));
				order.setItemId(rSet.getInt("item_id"));
				order.setAmount(rSet.getInt("amount"));
				order.setCartId(rSet.getInt("cart_id"));

				orders.add(order);
			}
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		return orders;
	}

	public List<Order> getByTimeAndClient(Long startTime, Long endTime, String clientId)
	{
		List<Order> orders = new ArrayList<>();

		String sql = "SELECT * FROM public.order AS orders "
			+ "INNER JOIN cart ON orders.cart_id = cart.id "
			+ "WHERE cart.cart_time > "+startTime+" AND cart.cart_time < "+endTime+" AND orders.client_id = '" + clientId + "'";

		try (
			Connection        connection  = ConnectionToDB.getConnection();
			PreparedStatement statement  = connection.prepareStatement(sql);
			ResultSet rSet = statement.executeQuery();
		)
		{
			while ( rSet.next() )
			{
				Order order = new Order();

				order.setOrderId(rSet.getInt("order_id"));
				order.setClientId(rSet.getString("client_id"));
				order.setItemId(rSet.getInt("item_id"));
				order.setAmount(rSet.getInt("amount"));
				order.setCartId(rSet.getInt("cart_id"));

				orders.add(order);
			}
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		return orders;
	}


	@Override
	public void delete(Object orderId)
	{
		String sql = "DELETE FROM public.order WHERE order_id=?";

		try (
			Connection connection = ConnectionToDB.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			)
		{
			statement.setInt(1, (Integer) orderId);
			statement.executeUpdate();
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
	}
}
