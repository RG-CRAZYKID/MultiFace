package com.example.multiface;

import android.util.Log;

public class ProfSetHelp {

	public static String a=" ";
	public static int b;
	public ProfSetHelp()
	{
		
	}
	public void setName(String name,int id)
	{
		//Log.d("in setName "," prof set helper");
		//Log.d(" name is ",name);
		a=name;
		b=id;
	}
	public String getName()
	{
		Log.d(" in  getname", a);
		return a;
	}
	public int getId()
	{
		return b;
	}
	
}
