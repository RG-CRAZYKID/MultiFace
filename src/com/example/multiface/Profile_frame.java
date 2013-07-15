package com.example.multiface;

public class Profile_frame {

	public int _id;
	public String _profile_name;
	public long _password;
	public String _app_table;
	
	public Profile_frame()
	{
		
	}
	
	public Profile_frame(int id)
	{
		this._id=id;
	}
	public Profile_frame(int id,String pname)
	{
		this._id=id;
		this._profile_name=pname;
	}
	public Profile_frame(int id,String pname,long passwd)
	{
		this._id=id;
		this._profile_name=pname;
		this._password=passwd;
	}
	
	public Profile_frame(int id,String pname,long passwd,String aname)
	{
		this._id=id;
		this._profile_name=pname;
		this._password=passwd;
		this._app_table=aname;
	}
	
	public void setid(int id)
	{
		this._id=id;
	}
	public void setname(String pname)
	{
		this._profile_name=pname;
	}
	
	public void setpass(long pass)
	{
		this._password=pass;
	}
	public void setapp(String appt)
	{
		this._app_table=appt;
	}
	
	public int getid()
	{
		return this._id;
	}
	
	public String getProfName()
	{
		return this._profile_name;
	}
	
	public long getPass()
	{
		return this._password;
	}
	public String getAppTab()
	{
		return this._app_table;
	}
}
