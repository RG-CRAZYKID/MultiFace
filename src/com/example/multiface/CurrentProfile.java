package com.example.multiface;

public class CurrentProfile {
	
	public static int loggedin;
	public static String prof;
	public static int id;
	public CurrentProfile()
	{}
	public CurrentProfile(int lg)
	{
		this.loggedin=lg;
	}
	public CurrentProfile(int lg,String pr)
	{
		this.loggedin=lg;
		this.prof=pr;
	}
	public CurrentProfile(int lg,String pr,int ida)
	{
		this.loggedin=lg;
		this.prof=pr;
		this.id=ida;
	}
	public void setLogg(int lg,String pr)
	{
		this.loggedin=lg;
		this.prof=pr;
	}
	public void setId(int ida)
	{
		this.id=ida;
	}
	public int getLogg()
	{
		return this.loggedin;
	}
	public String getPro()
	{
		return this.prof;
	}
	public int getId()
	{
		return this.id;
	}
}
