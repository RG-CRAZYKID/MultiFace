package com.example.multiface;

public class Empass {
	int _id;
	int password;

	public Empass()
	{}

	public Empass(int id,int pass)
	{
	this._id=id;
	this.password=pass;
	}

	public void setPass(int empass)
	{
	this.password=empass;
	}

	public int getPass()
	{
	return this.password;
	}
    public int getid()
    {
    return this._id;	
    }
    public void setid(int id)
    {
    	this._id=id;
    }
}
