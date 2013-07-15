package com.example.multiface;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProfilePasswordRemove extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_password_remove);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater()
				.inflate(R.menu.activity_profile_password_remove, menu);
		return true;
	}
	public void OKClicked(View view)
	{
		DBHandler dbh=new DBHandler(this);
		
		ProfSetHelp psh=new ProfSetHelp();
		
		int id=psh.getId();
		
		Profile_frame pf=dbh.getProfile(id);
		Long pass=pf.getPass();
		String pas=pass.toString();
		
		EditText edt=(EditText)findViewById(R.id.ppredit);
		try{
			String str=edt.getText().toString();
			if(str.equals(pas))
			{
				Intent intent=new Intent(this,ProfileSettings.class);
				startActivity(intent);
				
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
			}
			
		}
		catch(Exception e)
		{
			Toast.makeText(getApplicationContext()," Enter Password ", Toast.LENGTH_SHORT).show();
		}
		
	}
	public void cancelClicked(View view)
	{
		finish();
	}
	protected void onPause()
	{
		super.onPause();
	}
	protected void onStart()
	{
		super.onStart();
	}
	protected void onRestart()
	{
		super.onRestart();
		finish();
	}
}
