package com.example.multiface;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsoluteLayout;
import android.widget.Button;

@SuppressWarnings("deprecation")
public class ProfileList extends Activity implements OnClickListener {

	public int cpx=0;
	//public static final String PACKAGE_ID="com.example.multiface:R.id/";
	public static List<Integer> li=new ArrayList<Integer>();
	public static Integer a[]=new Integer[10];
	public static int i=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_list);
		li.add(100);
		a[0]=100;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_profile_list, menu);
		return true;
	}

	protected void onStart()
	{
		i=0;
		cpx=0;
		super.onStart();
		AbsoluteLayout layout = (AbsoluteLayout) findViewById(R.id.abslayout1);
		Button cb=(Button)findViewById(R.id.createbutton);
		layout.removeAllViews();
		layout.addView(cb);
		DBHandler dbh=new DBHandler(this);
		List<Profile_frame> pff=dbh.getAllProfiles();
		for(Profile_frame pf : pff)
		{
			Integer id=pf.getid();
			String ids=id.toString();
			Log.d(" the id is",ids);
			Log.d(" executing else part ","");
			cpx = cpx+50;
            AbsoluteLayout.LayoutParams p = new AbsoluteLayout.LayoutParams(

                    AbsoluteLayout.LayoutParams.WRAP_CONTENT,//width
                    AbsoluteLayout.LayoutParams.WRAP_CONTENT,//height
                    0,
                    cpx
            );
            
            //p.width=100;
            
           Button b = new Button(this);
           b.setText(pf.getProfName());
           b.setId(pf.getid());
           String str=((Integer)b.getId()).toString();
           Log.d(" button id  is ",str);
           
           //li.add(b.getId());
           a[i]=b.getId();
           Log.d(" a i is",a[i].toString());
           b.setBackgroundColor(Color.rgb(103,153,238));//67,99,225 hex is equivalent of 103,153,238 decimal
           b.setTextColor(Color.rgb(255,255,255));
           b.setWidth(250);
           b.setHeight(20);
           b.setOnClickListener(this);
           //b.setOnClickListener(OnClickListener l);
           layout.addView(b, p);

           Integer aa=(Integer)i;
	           Log.d(" i before increment ",aa.toString());
	           i=i+1;
	           Integer aaa=(Integer)i;
	           Log.d(" i after increment ",aaa.toString());
		}
	}
	
	public void createClicked(View view)
	{
		Intent intent=new Intent(this,New_Profile.class);
		startActivity(intent);
	}
	public void backClicked(View view)
	{
		finish();
	}
	protected void finish(View view)
	{
	}

	@Override
	public void onClick(View v) {
		
		ProfSetHelp psh=new ProfSetHelp();
		int id=v.getId();
		DBHandler dbh=new DBHandler(this);
		Profile_frame pff=dbh.getProfile(id);
		
		psh.setName(pff.getProfName(),pff.getid());
		
		Intent intent=new Intent(this,ProfilePasswordRemove.class);
		startActivity(intent);
	}
}
