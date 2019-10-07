package pumba.models.users;

public class User 
{

	public static final long NOT_IN_A_ROOM = 0;

	private String username;

	private String password;

	private long roomId;


	public User()
	{
		super();
	}


	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
	}


	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public long getRoomId()
	{
		return roomId;
	}

	public void setRoomId(long roomId)
	{
		this.roomId = roomId;
	}

	@Override
	public int hashCode()
	{
		return 0;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (password == null)
		{
			if (other.password != null)
				return false;
		}
		else if (!password.equals(other.password))
			return false;
		if (roomId != other.roomId)
			return false;
		if (username == null)
		{
			if (other.username != null)
				return false;
		}
		else if (!username.equals(other.username))
			return false;
		return true;
	}

}
