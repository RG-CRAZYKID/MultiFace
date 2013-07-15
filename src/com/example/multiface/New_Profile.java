package com.example.multiface;

import java.util.List;

import com.example.multiface.util.SystemUiHider;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
@SuppressLint("NewApi")
public class New_Profile extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_new__profile);
	}
	
	public void cancelButtonClick(View view)
	{
		finish();
	}
	
	public void  finish(View view)
	{
		
	}
	
	@SuppressLint("NewApi")
	public void onOK(View view)
	{
		DBHandler dbh=new DBHandler(this);
		int i=dbh.getLastId();
		
		EditText name=(EditText)findViewById(R.id.editText1);
		EditText pwd=(EditText)findViewById(R.id.editText2);
		EditText rpwd=(EditText)findViewById(R.id.editText3);
		
		String nm=name.getText().toString();
		String pass=pwd.getText().toString();
		String rpass=rpwd.getText().toString();
		
		String App=nm+"_APPS";
		if(nm.isEmpty() ||  pass.isEmpty() || rpass.isEmpty() )
		{
		if(nm.isEmpty()){
				Toast.makeText(getApplicationContext(), " Please Enter the Profile Name ", Toast.LENGTH_SHORT).show();
		}
		if(pass.isEmpty()){
			Toast.makeText(getApplicationContext(), "Please Enter the Password ", Toast.LENGTH_SHORT).show();
		}
		if(rpass.isEmpty()){
			Toast.makeText(getApplicationContext(), " Please Re-enter the Password ", Toast.LENGTH_SHORT).show();
		}
		}
		else
		{
		if(profileNotAlreadyExisting(nm))
		{
		if(pass.equals(rpass))
		{
			Integer pas=Integer.parseInt(pass);
			Profile_frame pf=new Profile_frame(i+1,nm,pas,App);
			dbh.insertProfile(pf);
			Toast.makeText(getApplicationContext(), " Profile has been Created",Toast.LENGTH_SHORT ).show();
			finish();
		}
		else
		{
			Toast.makeText(getApplicationContext(), " The Passwords do not match ", Toast.LENGTH_SHORT).show();
		}
	
		List<Profile_frame> pff=dbh.getAllProfiles();
	
		for(Profile_frame pf : pff )
		{
		System.out.println(pf.getid());
		System.out.println(pf.getProfName());
		System.out.println(pf.getPass());
		System.out.println(pf.getAppTab());
		}
		}
		else
		{
			Toast.makeText(getApplicationContext(), " Profile Already Exists ", Toast.LENGTH_SHORT).show();
		}
		}
	}

public boolean profileNotAlreadyExisting(String name)
{
	int flag=0;
	
	DBHandler dbh=new DBHandler(this);
	
	List<Profile_frame> pff=dbh.getAllProfiles();
	
	for(Profile_frame pf : pff )
	{
	System.out.println(pf.getid());
	System.out.println(pf.getProfName());
	System.out.println(pf.getPass());
	System.out.println(pf.getAppTab());
	Log.d(" name is ",name);
	Log.d(" prof name ",pf.getProfName());
	if(name.equals(pf.getProfName()))
	{
		flag=1;
	}
	}
	
	if(flag==0)
	{
		return true;
	}
	else
	{
		return false;
	}
}

}
