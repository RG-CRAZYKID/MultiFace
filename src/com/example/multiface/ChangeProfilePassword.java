package com.example.multiface;


import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChangeProfilePassword extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_profile_password);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater()
				.inflate(R.menu.activity_change_profile_password, menu);
		return true;
	}

	protected void finish(View view)
	{
		
	}
	public void OKClicked(View view)
	{
		ProfSetHelp psh=new ProfSetHelp();
		
		int id=psh.getId();
		
		EditText opa = (EditText)findViewById(R.id.oldpass);
		EditText npa = (EditText)findViewById(R.id.newpass);
		EditText rpa = (EditText)findViewById(R.id.repass);
		String op=opa.getText().toString();
		Log.d("opis",op);
		String np=npa.getText().toString();
		String rp=rpa.getText().toString();
		if(op.isEmpty() ||np.isEmpty()||rp.isEmpty())
		{
		if(op.isEmpty())
		{
			Toast.makeText(getApplicationContext()," please fill the old password field ",Toast.LENGTH_SHORT).show();
			Log.d("op is","null");
		}
		if(np.isEmpty())
		{
			Toast.makeText(getApplicationContext()," please fill the new password field ",Toast.LENGTH_SHORT).show();
			Log.d("np is","null");
		}
		if(rp.isEmpty())
		{
			Toast.makeText(getApplicationContext()," please fill the re enter password field ",Toast.LENGTH_SHORT).show();
			Log.d("rp is","null");
		}
		}
		else
		{
		Long oldPass=Long.parseLong(op);
		Long newPass=Long.parseLong(np);
		Long rePass=Long.parseLong(rp);
		
		Log.d("oldPass",Long.toString(oldPass));
		Log.d("newPass",Long.toString(newPass));
		Log.d("rePass",Long.toString(rePass));
		if(isOldPasswordCorrect(oldPass))
		{
			if(areBothPasswordsSame(newPass,rePass))
			{
				Log.d("inside "," if both pass same");
				DBHandler dbh=new DBHandler(this);
				Profile_frame pf=dbh.getProfile(id);
				dbh.updateProfile(pf);
				Log.d("action"," has been completed");
				Toast.makeText(getApplicationContext()," A new Password has been set ",Toast.LENGTH_SHORT).show();
				finish();
			}
			else
			{ 
				Toast.makeText(getApplicationContext()," New and Re-entered Passwords do not match ",Toast.LENGTH_SHORT).show();
				Log.d("new and reenter","not equal");
			}
		}
		else
		{
		 	Log.d("old pass","incorrect");
		 	Toast.makeText(getApplicationContext()," The Old Password is incorrect ",Toast.LENGTH_SHORT).show();
		}
		}
	}
	public boolean isOldPasswordCorrect(Long op)
	{
		
		ProfSetHelp psh=new ProfSetHelp();
		
		int id=psh.getId();
		
	Log.d("in is old pass"," inside old password");
	DBHandler dbh=new DBHandler(this);	
	Log.d("step 3"," created dbhandler");
	Profile_frame pff=dbh.getProfile(id);
	Log.d(" step 4 "," empass created ");
	Long chkpass=pff.getPass();
	Log.d(" the old password is",chkpass.toString());
	String chk=chkpass.toString();
	String opc=op.toString();
	if(chk.equals(opc))
	{
		Log.d("chk and old"," are equal");
		return true;
	}
	Log.d("chk and old"," are not equal");
	return false;
	}
	public boolean areBothPasswordsSame(Long np,Long rp)
	{
		
		String npc=np.toString();
		String rpc=rp.toString();
		
		if(npc.equals(rpc))
		{
			return true;
		}
		return false;
	}
	public void cancelClicked(View view)
	{
		finish();
	}
}
