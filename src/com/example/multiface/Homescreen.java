package com.example.multiface;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Homescreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
    }
    public void finish(View view)
    {
    	
    }
    public void startClicked(View view)
    {
    	Intent intent=new Intent(this,LogIn.class);
    	startActivity(intent);
    }
    
    protected void onPause()
    {
    	super.onPause();
    }
    
    protected void onStop()
    {
    	super.onStop();
    }
    
    protected void onRestart()
    {
    	super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_homescreen, menu);
        return true;
    }
    public void aboutUs(View view)
    {
    Intent intent = new Intent(this,About.class);
    startActivity(intent);
    }
    public void onControlButtonClick(View view)
    {
    	Intent intent = new Intent(this,Profile.class);
    	startActivity(intent);
    }
    
    public void exitButtonClicked(View view)
	{
		finish();
	}

    protected void onResume()
    {
    	super.onResume();
    }
    
    protected void onStart()
    {
    	super.onStart();
    }
}
