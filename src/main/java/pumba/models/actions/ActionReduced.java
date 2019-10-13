package pumba.models.actions;

public class ActionReduced
{
	private String actionDescription;
	private Boolean available;

	public String getActionDescription()
	{
		return actionDescription;
	}

	public void setActionDescription(String actionDescription)
	{
		this.actionDescription = actionDescription;
	}

	public Boolean getAvailable()
	{
		return available;
	}

	public void setAvailable(Boolean available)
	{
		this.available = available;
	}

	public ActionReduced(String actionDescription)
	{
		super();
	}

}
