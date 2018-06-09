package ua.danit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.danit.model.Item;

public class ItemDAO extends AbstractDAO<Item>
{
	@Override
	public void save(Item item)
	{
		String sql = "INSERT INTO item(article_id, name, price) VALUES(?,?,?)";

		try ( Connection connection = ConnectionToDB.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); )
		{
			statement.setInt(1, item.getArticleId());
			statement.setString(2, item.getName());
			statement.setInt(3, item.getPrice());

			statement.executeUpdate();
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
	}

	@Override public void update(Item item)
	{
		String sql = "UPDATE item SET name=?, price=? WHERE article_id=?";

		try ( Connection connection = ConnectionToDB.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); )
		{
			statement.setInt(3, item.getArticleId());
			statement.setString(1, item.getName());
			statement.setInt(2, item.getPrice());

			statement.executeUpdate();
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
	}

	@Override public Item get(Object article_id)
	{
		Item item = new Item();

		String sql = "SELECT * FROM item WHERE article_id='" + article_id + "'";

		try (
			Connection        connection  = ConnectionToDB.getConnection();
			PreparedStatement statement  = connection.prepareStatement(sql);
			ResultSet rSet = statement.executeQuery();
			)
		{
			while ( rSet.next() )
			{
				item.setArticleId(rSet.getInt("article_id"));
				item.setName(rSet.getString("name"));
				item.setPrice(rSet.getInt("price"));

				return item;
			}
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override public void delete(Object itemId)
	{
		String sql = "DELETE FROM item WHERE article_id=?";

		try (
			Connection connection = ConnectionToDB.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			)
		{
			statement.setString(1, (String) itemId);
			statement.executeUpdate();
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
	}

	public List<Item> getAll()
	{
		List<Item> items = new ArrayList<>();

		String sql = "SELECT * FROM item ";

		try (
				Connection        connection  = ConnectionToDB.getConnection();
				PreparedStatement statement  = connection.prepareStatement(sql);
				ResultSet rSet = statement.executeQuery();
		)
		{
			while ( rSet.next() )
			{
				Item item = new Item();
				item.setArticleId(rSet.getInt("article_id"));
				item.setName(rSet.getString("name"));
				item.setPrice(rSet.getInt("price"));

				items.add(item);
			}
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		return items;
	}
}
