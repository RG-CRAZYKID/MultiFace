package com.example.multiface;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Profile extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_profile, menu);
		return true;
	}
	public void onProfileClick(View view)
	{
		Intent intent=new Intent(this,ProfileList.class);
		startActivity(intent);
	}
	public void onBack(View view)
	{
		finish();
	}
	public void finish(View view)
	{
		
	}
	

}
