package com.example.multiface;

import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class Start extends Activity {
	GridView gmain;
	List<Apps_frame> af;
	PackageManager pm;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		pm=this.getPackageManager();
		DBHandler dbh=new DBHandler(this);
		CurrentProfile cpf=new CurrentProfile();
		Profile_frame pf=dbh.getProfile(cpf.getId());
		Log.d(" name ",pf.getProfName());
		af=dbh.getAllApps(pf);
		gmain = (GridView)findViewById(R.id.GridView01);  
		gmain.setAdapter(new ImageAdapter(this)); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_start, menu);
		return true;
	}
	
	
	  public class ImageAdapter extends BaseAdapter implements OnClickListener{  
	         Context mContext;  
			          public static final int ACTIVITY_CREATE = 10;  
			          	public ImageAdapter(Context c){  
			             mContext = c;  
			         }
			          
			          @Override 
			          public int getCount() {    
			          return af.size();
			          } 
			          @Override
			         public View getView(int position, View convertView, ViewGroup parent) {  
			              View v; 
			             
			              Apps_frame ai=af.get(position);
			              
			              if(convertView==null) 
			            		  //&& !ai.getappname().equals("MultiFace"))
			            	{  
			            	  LayoutInflater li = getLayoutInflater();  
			                 v = li.inflate(R.layout.icon, null);  
			                 try
				             {

			                   Button b=(Button)findViewById(R.id.iconbutton);
			                   b.setTextSize(10);
			                   b.setText(ai.getappname());
			                   b.setCompoundDrawablesWithIntrinsicBounds(null,pm.getApplicationIcon(ai.getPackagesName()), null , null);
			      	           b.setId(position);
			      	           b.setBackgroundColor(Color.rgb(255,255,255));
			      	           b.setWidth(200);
			      	           b.setHeight(200);
			      	           b.setOnClickListener(this);
				             }
				             catch(Exception e)
				             { }    
				          }  
			              else 
			              {  
			            	  v = convertView;  
			               }
			              
			                return v;  
			           }
					@Override
					public Object getItem(int arg0) {
						return null;
					}
					@Override
					public long getItemId(int arg0) {
						return 0;
					}
					@Override
					public void onClick(View v) 
					{
						Button b=(Button)v;
						Toast.makeText(getApplicationContext(), " Clicked "+b.getText(), Toast.LENGTH_SHORT).show();
					
						for(Apps_frame aff: af)
						{
						if(b.getText().equals(aff.getappname()))
						{
							startActivity(pm.getLaunchIntentForPackage(aff.getPackagesName()));
						}
						}
					}  
	  }  
	  public void loggingOut(View view)
	  {
		  CurrentProfile cr=new CurrentProfile();
			cr.setLogg(0,null);
			finish();
	  }
}
