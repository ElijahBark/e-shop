package ua.danit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.danit.model.Cart;

public class CartDAO extends AbstractDAO<Cart>
{

    public List<Cart> getAllByLogin(String login) {
    	List<Cart> carts = new ArrayList<>();
		String sql = "SELECT DISTINCT cart.id, cart.time, cart.confirmed FROM cart JOIN public.order AS ord ON cart.id = ord.cart_id WHERE ord.client_id = '"+ login+"'";
    	try ( Connection connection = ConnectionToDB.getConnection();
			  PreparedStatement statement = connection.prepareStatement(sql);
			  ResultSet rSet = statement.executeQuery(); )
		{
			while(rSet.next()){
				Cart cart =new Cart();
				cart.setCartID(rSet.getInt("id"));
				cart.setCartTime(rSet.getLong("time"));
				cart.setCartConfirmed(rSet.getBoolean("confirmed"));
				carts.add(cart);
			}
			return carts;

		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		return null;

}

    @Override public void save(Cart cart)
	{
		String sql = "INSERT INTO cart(id, time, confirmed) VALUES(?,?,?)";

		try ( Connection connection = ConnectionToDB.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql); )
		{
			statement.setInt(1, cart.getCartID());
			statement.setLong(2, cart.getCartTime());
			statement.setBoolean(3,cart.isCartConfirmed());
			statement.executeUpdate();
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
	}

	@Override public void update(Cart cart)
	{
		String sql = "UPDATE cart SET confirmed=? WHERE id=?";
		try ( Connection connection = ConnectionToDB.getConnection();
			  PreparedStatement statement = connection.prepareStatement(sql); )
		{

			statement.setBoolean(1,cart.isCartConfirmed());
			statement.setInt(2,cart.getCartID());
			statement.executeUpdate();
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}

	}

	@Override public Cart get(Object cartID)
	{
		String sql = "SELECT * FROM cart WHERE id="+cartID+"";
		Cart cart = new Cart();

		try ( Connection connection = ConnectionToDB.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rSet = statement.executeQuery(); )
		{
			while(rSet.next()){
				cart.setCartID(rSet.getInt("id"));
				cart.setCartTime(rSet.getLong("time"));
				cart.setCartConfirmed(rSet.getBoolean("confirmed"));
				return cart;
			}
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override public void delete(Object pk)
	{
		String sql = "DELETE FROM cart WHERE id="+pk;

		try ( Connection connection = ConnectionToDB.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql); )
		{
			statement.executeUpdate();
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
	}
}
