package main.java.pumba.log;

public class Log
{

	private static Boolean log = true;
	
	public static void debug(String msg)
	{
		if (log)
		{
			System.out.println(msg);
		}
	}

	public static void debugLine()
	{
		if (log)
		{
			System.out.println("----------------------------------------");
		}
	}
}
