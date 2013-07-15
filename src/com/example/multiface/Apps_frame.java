package com.example.multiface;

public class Apps_frame 
{
	
	public int _id;
	public String _app_name;
	public String packageName;
	public String icon;
	public String intent;
	
	public Apps_frame()
	{
		
	}
	public Apps_frame(int id)
	{
		this._id=id;
	}
	public Apps_frame(int id,String aname)
	{
		this._id=id;
		this._app_name=aname;
	}
	public Apps_frame(int id,String aname,String pname)
	{
		this._id=id;
		this._app_name=aname;
		this.packageName=pname;
	}
	public Apps_frame(int id,String aname,String pname,String ic)
	{
		this._id=id;
		this._app_name=aname;
		this.packageName=pname;
		this.icon=ic;
	}
	public Apps_frame(int id,String aname,String pname,String ic,String inte)
	{
		this._id=id;
		this._app_name=aname;
		this.packageName=pname;
		this.icon=ic;
		this.intent=inte;
	}
	public void setid(int id)
	{
		this._id=id;
	}
	public void setApps(String aname)
	{
		this._app_name=aname;
	}
	public void setPackage(String pname)
	{
		this.packageName=pname;
	}
	public void setIconRes(String ic)
	{
		this.icon=ic;
	}
	public void setIntentSrc(String inte)
	{
		this.intent=inte;
	}
	public int getid()
	{
		return this._id;
	}
	public String getappname()
	{
		return this._app_name;
	}
	public String getPackagesName()
	{
		return this.packageName;
	}
	public String getIconRes()
	{
		return this.icon;
	}
	public String getIntentSrc()
	{
		return this.intent;
	}
	
}
