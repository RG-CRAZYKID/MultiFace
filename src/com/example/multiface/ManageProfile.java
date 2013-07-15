package com.example.multiface;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ManageProfile extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_profile);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_manage_profile, menu);
		return true;
	}
	protected void onStart()
	{
		super.onStart();
		EditText edt=(EditText)findViewById(R.id.manProfileName);
		ProfSetHelp psh=new ProfSetHelp();
		String name=psh.getName();
		edt.setText(name);
	}
	public void cancelClicked(View view)
	{
		finish();
	}
	public void OKClicked(View view)
	{
		EditText edt=(EditText)findViewById(R.id.manProfileName);
		ProfSetHelp psh=new ProfSetHelp();
		String name=psh.getName();
		if(name.equals(edt.getText().toString()))
		{
			
		}
		else
		{
			String newname=edt.getText().toString();
			int id=psh.getId();
			if(profileNotAlreadyExisting(newname))
			{
				DBHandler dbh=new DBHandler(this);
				Profile_frame pff=dbh.getProfile(id);
				Profile_frame pf=new Profile_frame(id,newname,pff.getPass(),pff.getAppTab());
				dbh.updateProfile(pf);
				Toast.makeText(getApplicationContext(), " Profile has been renamed ", Toast.LENGTH_SHORT).show();
				psh.setName(newname, id);
			}
			else
			{
				Toast.makeText(getApplicationContext(), " Profile name already exists ", Toast.LENGTH_SHORT).show();
			}
		}
		finish();
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
	public void finish(View view)
	{
	}
	
	public void manApps(View view)
	{
		Intent intent = new Intent(this,ManageApps.class);
		startActivity(intent);
	}
	
	
}
