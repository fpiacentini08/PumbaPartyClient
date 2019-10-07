package pumba.models.rooms;

import java.util.Set;

import pumba.models.users.User;

public class Room
{
	private long id;
	private Set<User> users;
	private String master;
	private Boolean playing;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public Set<User> getUsers()
	{
		return users;
	}

	public void setUsers(Set<User> users)
	{
		this.users = users;
	}

	public String getMaster()
	{
		return master;
	}

	public void setMaster(String master)
	{
		this.master = master;
	}

	public Boolean getPlaying()
	{
		return playing;
	}

	public void setPlaying(Boolean playing)
	{
		this.playing = playing;
	}

}
