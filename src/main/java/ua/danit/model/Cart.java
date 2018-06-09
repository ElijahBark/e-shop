package ua.danit.model;

import ua.danit.utils.IdMaker;

public class Cart
{

	private Integer cartID;
	private Long cartTime;

	private boolean cartConfirmed;


	public Cart() {
		this.cartID = IdMaker.makeId();
		this.cartTime = System.currentTimeMillis();
		this.cartConfirmed = false;
	}


	public boolean isCartConfirmed() {
		return cartConfirmed;
	}

	public void setCartConfirmed(boolean cartConfirmed) {
		this.cartConfirmed = cartConfirmed;
	}


	public Integer getCartID()
	{
		return cartID;
	}

	public void setCartID(Integer cartID)
	{
		this.cartID = cartID;
	}

	public Long getCartTime()
	{
		return cartTime;
	}

	public void setCartTime(Long cartTime)
	{
		this.cartTime = cartTime;
	}
}
