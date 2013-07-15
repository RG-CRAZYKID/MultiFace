package com.example.multiface;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class StartScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_screen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_start_screen, menu);
		return true;
	}
	
	public void logout(View view)
	{
		CurrentProfile cr=new CurrentProfile();
		cr.setLogg(0,null);
		Intent intent=new Intent(this,Homescreen.class);
		startActivity(intent);
	}
	
	protected void onStart()
	{
		super.onStart();
			Intent intent=new Intent(this,Start.class);
			startActivity(intent);		
	}
	protected void onRestart()
	{
		super.onRestart();
		finish();
	}
}
