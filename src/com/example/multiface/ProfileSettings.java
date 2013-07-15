package com.example.multiface;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class ProfileSettings extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_settings);	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_profile_settings, menu);
		return true;
	}
	
	protected void onStart()
	{
		Log.d(" on start of "," profile settings ");
		super.onStart();
		TextView txt=(TextView)findViewById(R.id.prof_heading_settings);
		ProfSetHelp psh=new ProfSetHelp();
		Log.d(" name is ",psh.getName());
		txt.setText(psh.getName());
		//txt.setBackgroundColor(Color.rgb(103,153,238));
	}

	public void finish(View view)
	{
		
	}
	
	public void backClicked(View view)
	{
	//finishFromChild(getParent());
	finish();
	}	
	//@SuppressWarnings("deprecation")
	public void onRemoveProfile(View view)
	{
		//AbsoluteLayout layout = (AbsoluteLayout) findViewById(R.id.abslayout1);	
		DBHandler dbh=new DBHandler(this);
		ProfSetHelp psh=new ProfSetHelp();
		int ids=psh.getId();
		Profile_frame pf=dbh.getProfile(ids);
		//Button but=(Button)findViewById(psh.getId());
		//layout.removeViewInLayout(but);
		dbh.deleteProfile(pf);
		//finish();
		Intent intent=new Intent(this,ProfileList.class);
		startActivity(intent);
	}
	public void changePassword(View view)
	{
	Intent intent=new Intent(this,ChangeProfilePassword.class);
	startActivity(intent);
	}
	public void manageProfile(View view)
	{
		Intent intent=new Intent(this,ManageProfile.class);
		startActivity(intent);	
	}
	public void manageApps(View view)
	{
		Intent intent =new Intent(this,ManageApps.class);
		startActivity(intent);
	}
}