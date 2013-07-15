package com.example.multiface;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends Activity {

	public int flag=0;
	public Long passw;
	public int ida;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log_in);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_log_in, menu);
		return true;
	}
	public void cancelClicked(View view)
	{
		finish();
	}
	public void OKClicked(View view)
	{
		DBHandler dbh=new DBHandler(this);
		List<Profile_frame> prof=dbh.getAllProfiles();
		
		EditText lpr=(EditText)findViewById(R.id.LogInProf);
		EditText lpa=(EditText)findViewById(R.id.LogInPass);
		
		String lpro=lpr.getText().toString();
		String lpas=lpa.getText().toString();
		
		Long pas=Long.parseLong(lpas);
	
		
		if(!prof.isEmpty())
		{
		for(Profile_frame p: prof)
		{
			if(lpro.equals(p.getProfName()))
			{
				flag=1;
				passw=p.getPass();
				ida=p.getid();
			}
		}
		if(flag==1)
		{
			if(pas.equals(passw))
			{
				CurrentProfile cp=new CurrentProfile();
				cp.setLogg(1,lpro);
				cp.setId(ida);
				Intent intent=new Intent(this,StartScreen.class );
				startActivity(intent);
			}
			else
			{
				Toast.makeText(getApplicationContext(),"Incorrect Password ", Toast.LENGTH_SHORT).show();
			}
		
		}
		else
		{
			Toast.makeText(getApplicationContext(), " No such Profile Exists ", Toast.LENGTH_SHORT).show();
			Toast.makeText(getApplicationContext(), " Please Enter a correct profile ", Toast.LENGTH_SHORT).show();
		}
		}
		else
		{
			Toast.makeText(getApplicationContext(), " Please create a profile first ", Toast.LENGTH_SHORT).show();
			Intent intent=new Intent(this,ProfileList.class);
			startActivity(intent);
		}	
	}
	protected void onRestart()
	{
		super.onRestart();
		finish();
	}
}
