package ua.danit.model;

import ua.danit.utils.IdMaker;

public class Item
{
	private Integer articleId;
	private String name;
	private Integer price;

	public Item() {
		this.articleId = IdMaker.makeId();
	}

	public Integer getArticleId()
	{
		return articleId;
	}

	public void setArticleId(Integer articleId)
	{
		this.articleId = articleId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Integer getPrice()
	{
		return price;
	}

	public void setPrice(Integer price)
	{
		this.price = price;
	}
}
