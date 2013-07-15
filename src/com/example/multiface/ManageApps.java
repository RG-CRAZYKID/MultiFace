package com.example.multiface;


import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.CursorIndexOutOfBoundsException;
import android.graphics.Color;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
//import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ManageApps extends Activity implements OnTouchListener,OnDragListener {

	public static int i=0;
	public static int cpx=0;
	public static int cpxb=0;
	
	List<Apps_frame> AList = new ArrayList<Apps_frame>(); 
	
	List<Apps_frame> BList = new ArrayList<Apps_frame>(); 
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_apps);

		findViewById(R.id.ListALayout).setOnDragListener(this);
		findViewById(R.id.ListBLayout).setOnDragListener(this);
 
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_manage_apps, menu);
		return true;
	}

	protected void onStart()
	{
		super.onStart();
		Log.d(" on start of------ ","---------------------- manage apps -----------------");
		/*TextView edt=(TextView)findViewById(R.id.titletextview);
		ProfSetHelp psh=new ProfSetHelp();
		String name=psh.getName();
		edt.setText(name);*/
		cpx=0;
		cpxb=0;
		i=0;
		LinearLayout alistl=(LinearLayout)findViewById(R.id.ListALayout);
        LinearLayout blistl=(LinearLayout)findViewById(R.id.ListBLayout);
		DBHandler dbh=new DBHandler(this);
		alistl.removeAllViews();
		blistl.removeAllViews();
		ProfSetHelp psh=new ProfSetHelp();
		Profile_frame pf=dbh.getProfile(psh.getId());
		final PackageManager pm = getPackageManager();
		List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
		try
		{
			BList=dbh.getAllApps(pf);
		for (ApplicationInfo packageInfo : packages) 
		{			
		 if(pm.getLaunchIntentForPackage(packageInfo.packageName)!= null &&   
		                   !pm.getLaunchIntentForPackage(packageInfo.packageName).equals(""))
		{	 
			 if(!((pm.getApplicationLabel(packageInfo)).equals("MultiFace")))
			 {
			 Apps_frame af=new Apps_frame(i,(String)pm.getApplicationLabel(packageInfo),packageInfo.packageName,pm.getApplicationIcon(packageInfo.packageName).toString(), pm.getApplicationIcon(packageInfo.packageName).toString());
			 AList.add(af);
			 i=i+1;
			 }
		}
		}
	//	compare(AList,BList);
		}
		catch(CursorIndexOutOfBoundsException e)
		{
			Log.d(" bloody "," cursor");
		}
		catch(Exception e)
		{
			 Log.d(" stupid "," Exception");
		}	
		
		if(!BList.isEmpty())
		{
			List<Apps_frame> clist=new ArrayList<Apps_frame>();
			for(Apps_frame a:BList)
			{
			clist.add(a);	
			}
		for(Apps_frame b:clist)
		{
			int flag=0;
			List<Apps_frame> dlist=new ArrayList<Apps_frame>();
			
			for(Apps_frame a:AList)
			{
				dlist.add(a);
			}
			
			for(Apps_frame a:dlist)
			{
				if(b.getappname().equals(a.getappname()) && b.getPackagesName().equals(a.getPackagesName()))
				{
					flag=1;
					
				}
			}
			if(flag==0)
			{
				Log.d(" Removing App from A",b.getappname());
				BList.remove(b);
			}
		}
		
		
		List<Apps_frame> elist=new ArrayList<Apps_frame>();
		for(Apps_frame a:AList)
		{
		elist.add(a);	
		}
		
		
		for(Apps_frame b:elist)
		{
			int flag=0;
			List<Apps_frame> dlist=new ArrayList<Apps_frame>();
			for(Apps_frame a:BList)
			{
			dlist.add(a);	
			}
			
			for(Apps_frame a:dlist)
			{
				if(b.getappname().equals(a.getappname()) && b.getPackagesName().equals(a.getPackagesName()))
				{
					flag=1;
					
				}
			}
			if(flag==1)
			{
				Log.d(" Removing App from A",b.getappname());
				AList.remove(b);
			}
		}
		Log.d(" Blist "," Printing");
		int idb=0;
		for(Apps_frame b:BList)
		{
			System.out.println(" id is " + (Integer)b.getid());
			System.out.println(" name " + b.getappname());
			//System.out.println(" icon " + b.getIconRes());
			//System.out.println(" intent "+ b.getIntentSrc());
			//System.out.println(" package name" +b.getPackagesName());
		}
		for(Apps_frame b:BList)
		{
			b.setid(idb);
			cpxb = cpxb+30;
	        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
	        		LinearLayout.LayoutParams.WRAP_CONTENT,//width
	                LinearLayout.LayoutParams.WRAP_CONTENT//height
	                );
		    
	        
	        TextView t=new TextView(this);
		    t.setId(b.getid());
		    t.setText(b.getappname());
		    t.setWidth(250);
		    t.setTextColor(Color.rgb(255,255,255));
	        t.setHeight(30);
	        t.setTextIsSelectable(true);
	        t.setOnTouchListener(this);
	        idb=idb+1;
	        blistl.addView(t, p);
	  }
	  }
	  else
	  {
		  Log.d(" BList "," is Empty");
	  }
		Log.d(" Alist "," Printing");
		int ida=0;
		if(!AList.isEmpty())
		{
			for(Apps_frame b:AList)
			{
				System.out.println(" id is " + (Integer)b.getid());
				System.out.println(" name " + b.getappname());
				//System.out.println(" icon " + b.getIconRes());
				//System.out.println(" intent "+ b.getIntentSrc());
				//System.out.println(" package name" +b.getPackagesName());
			}
		for(Apps_frame b:AList)
		{
			b.setid(ida);
			cpx = cpx+30;
	        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
	        		LinearLayout.LayoutParams.WRAP_CONTENT,//width
	                LinearLayout.LayoutParams.WRAP_CONTENT//height
	                );
		    
	        
	        TextView t=new TextView(this);
		    t.setId(b.getid());
		    t.setText(b.getappname());
		    t.setWidth(250);
		    t.setTextColor(Color.rgb(255,255,255));
	        t.setHeight(30);
	        t.setTextIsSelectable(true);
	        t.setOnTouchListener(this);
	        ida=ida+1;
	        alistl.addView(t, p);	
		}
		}
		else
		{
			Log.d("AList "," empty ");
		}
	}	
	
	public void delAllApps()
	{
		DBHandler dbh=new DBHandler(this);
		ProfSetHelp psh=new ProfSetHelp();
		Profile_frame pf=dbh.getProfile(psh.getId());
		//String aname=pf.getAppTab();
		List<Apps_frame> aflis=dbh.getAllApps(pf);
		for(Apps_frame af:aflis)
		dbh.deleteApp(af, pf);
	}
	
	@Override
	public boolean onDrag(View layoutview, DragEvent dragevent) {

		String LOGCAT=null;
		int action = dragevent.getAction();

		int ind=0;
	      
	      switch (action) {
	      case DragEvent.ACTION_DRAG_STARTED:
	          Log.d(LOGCAT, "Drag event started");
	    	break;
	      case DragEvent.ACTION_DRAG_ENTERED:
	    	  Log.d(LOGCAT, "Drag event entered into "+layoutview.toString());
	    	break;
	      case DragEvent.ACTION_DRAG_EXITED:
	    	  Log.d(LOGCAT, "Drag event exited from "+layoutview.toString());
	    	break;
	      case DragEvent.ACTION_DROP:
	    	Log.d(LOGCAT, "Dropped");
	    	View view = (View) dragevent.getLocalState();
	        ViewGroup owner = (ViewGroup) view.getParent();
	        Integer io=owner.getId();
	        Log.d(" IO is",io.toString());
	        owner.removeView(view);
	        //owner.addView(new TextView(this).setText(""));
	        LinearLayout container = (LinearLayout) layoutview;
	        view.setVisibility(View.VISIBLE);
	        container.addView(view);
	        
	        Integer aint=view.getId();
	        TextView atext=(TextView) view;
	        String atex=atext.getText().toString();
	        Log.d(" aint is",aint.toString());
	        Log.d("atex",atex);
	        Log.d("size ",((Integer)AList.size()).toString());
	        
	        
	        if(io==findViewById(R.id.ListALayout).getId())
	        {
	        	if(aint>=AList.size())
	        	{
	        		for(Apps_frame aa:AList)
	        		if(atex.equals(aa.getappname()))
	        		{
	        			aint=aa.getid();
	        		}
	        	}
	        	
	        	Apps_frame aff=AList.get(aint);
	      
	        	Log.d("afteraff "," created");
	        	Integer loc=AList.indexOf(aff);
	        	Log.d(" loc ",loc.toString());
	        	
	        	Integer size=AList.size();
	        	Log.d(" size is",size.toString());
	        	Log.d("name",aff.getappname());
	        	Log.d("package",aff.getPackagesName());
	        	Log.d(" intent",aff.getIntentSrc());
	        	AList.remove(aff);
	        	List<Apps_frame> chkl=new ArrayList<Apps_frame>();
	        	for(Apps_frame b:AList)
				{
				chkl.add(b);
				}
	        	AList.removeAll(chkl);
	        	for(Apps_frame a:chkl)
	    		{	
	    			a.setid(ind);
	    			AList.add(a);
	    			ind=ind+1;
	    		Log.d(" inside "," for loop");
	    		}
	        	if(BList.isEmpty())
	        	{
	        		aff.setid(0);
	        	}
	        	else
	        	{
	        		Integer id = null;
	        		for(Apps_frame ls:BList)
	        		{
	        			id=ls.getid();
	        		}
	        		aff.setid(id+1);
	        	}
	        	BList.add(aff);
	        	Log.d(" BList "," printing .............");
	        	for(Apps_frame b:BList)
				{
					System.out.println(" id is " + (Integer)b.getid());
					System.out.println(" name " + b.getappname());
					//System.out.println(" icon " + b.getIconRes());
					//System.out.println(" intent "+ b.getIntentSrc());
					//System.out.println(" package name" +b.getPackagesName());
				}
	        }
	        else if(io==findViewById(R.id.ListBLayout).getId())
	        {
	        	if(view.getId()>=BList.size())
	        	{
	        		Log.d(" atex ",atex);	
	        		for(Apps_frame aa:BList)
	        		if(atex.equals(aa.getappname()))
	        		{
	        			Log.d(" name" ,aa.getappname());
	        			aint=aa.getid();
	        		}
	        	}
	        	Log.d(" B to A -------- BList"," printing-------------------");
	        	for(Apps_frame b:BList)
				{
					System.out.println(" id is " + (Integer)b.getid());
					System.out.println(" name " + b.getappname());
					//System.out.println(" icon " + b.getIconRes());
					//System.out.println(" intent "+ b.getIntentSrc());
					//System.out.println(" package name" +b.getPackagesName());
				}
	        	Apps_frame aff=BList.get(aint);
	        	BList.remove(aff);
	        	List<Apps_frame> chkl=new ArrayList<Apps_frame>();
	        	for(Apps_frame bb:BList)
	        	{
	        		chkl.add(bb);
	        	}
	        	BList.removeAll(chkl);
	    		for(Apps_frame a:chkl)
	    		{	
	    			a.setid(ind);
	    			BList.add(a);
	    			ind=ind+1;
	    		}
	    		if(AList.isEmpty())
	        	{
	        		aff.setid(0);
	        	}
	        	else
	        	{
	        		//Apps_frame last=BList.
	        		Integer id = null;
	        		for(Apps_frame ls:AList)
	        		{
	        			id=ls.getid();
	        		}
	        		aff.setid(id+1);	
	        	}
	    		AList.add(aff);    	
	        }
	        view.setVisibility(View.VISIBLE);
	        break;
	      case DragEvent.ACTION_DRAG_ENDED:
	    		  Log.d(LOGCAT, "Drag ended");
		      break;
	      default:
	        break;
	      }
	      return true;

	}


	@Override
	public boolean onTouch(View view, MotionEvent motionEvent) {
		if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
		      DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
			  view.startDrag(null, shadowBuilder, view, 0);
			  //view.setVisibility(View.INVISIBLE);
			  return true;
		    }
		    else {
		    	return false;
		    }
	}
	public void cancelClicked(View view)
	{
		
		finish();
	}
	
	public void okClicked(View view)
	{
		DBHandler dbh=new DBHandler(this);
		ProfSetHelp psh=new ProfSetHelp();
		Profile_frame pf=dbh.getProfile(psh.getId());
		List<Apps_frame> clist=dbh.getAllApps(pf);
		
		if(!clist.isEmpty())
		for(Apps_frame a : clist)
		{
			dbh.deleteApp(a, pf);
		}
		if(!BList.isEmpty())
		for(Apps_frame b: BList)
		{
			dbh.insertApp(pf, b);
		}
		finish();
	}
	
	public void addAll(View view)
	{
		
		cpx=0;
		
		LinearLayout alistl=(LinearLayout)findViewById(R.id.ListALayout);
        LinearLayout blistl=(LinearLayout)findViewById(R.id.ListBLayout);

        List<Apps_frame> al=new ArrayList<Apps_frame>();
		
        for(Apps_frame af:AList)
        {
        	al.add(af);
        }
        
		for(Apps_frame af: al)
		{
			BList.add(af);
			
			cpxb = cpxb+30;
	        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
	        		LinearLayout.LayoutParams.WRAP_CONTENT,//width
	                LinearLayout.LayoutParams.WRAP_CONTENT//height
	                );
		    
	        
	        TextView t=new TextView(this);
		    t.setId(i);
		    t.setText(af.getappname());
		    t.setWidth(250);
		    t.setTextColor(Color.rgb(255,255,255));
	        t.setHeight(30);
	        t.setTextIsSelectable(true);
	        t.setOnTouchListener(this);
	        
	        blistl.addView(t, p);
			
			AList.remove(af);
		}
		alistl.removeAllViews();
	}
	public void remAll(View view)
	{
		
		cpx=0;
		
		LinearLayout alistl=(LinearLayout)findViewById(R.id.ListALayout);
        LinearLayout blistl=(LinearLayout)findViewById(R.id.ListBLayout);
        
        List<Apps_frame> al=new ArrayList<Apps_frame>();
		
        for(Apps_frame af:BList)
        {
        	al.add(af);
        }

        
		for(Apps_frame af: al)
		{
			AList.add(af);
			
			cpx = cpx+30;
	        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
	        		LinearLayout.LayoutParams.WRAP_CONTENT,//width
	                LinearLayout.LayoutParams.WRAP_CONTENT//height
	                );
		    
	        
	        TextView t=new TextView(this);
		    t.setId(i);
		    t.setText(af.getappname());
		    t.setWidth(250);
		    t.setTextColor(Color.rgb(255,255,255));
	        t.setHeight(30);
	        t.setTextIsSelectable(true);
	        t.setOnTouchListener(this);
	        
	        alistl.addView(t, p);
			
	        BList.remove(af);
		
	       // blistl.re
		
		}
		blistl.removeAllViews();
	}	
}
