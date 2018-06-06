package ua.danit.model;

public class Client
{
	private String login;
	private String password;
	private String firstName;
	private String secondName;

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getSecondName()
	{
		return secondName;
	}

	public void setSecondName(String secondName)
	{
		this.secondName = secondName;
	}

	public boolean equals(Client client) {
		if (secondName.equals(client.getSecondName())
				&& firstName.equals(client.getFirstName())
				&& login.equals(client.getLogin())
				&& password.equals(client.getPassword())) {
			return true;
		}
		return false;
	}
}
