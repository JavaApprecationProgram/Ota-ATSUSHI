package com.example.helloapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button1=(Button)findViewById(R.id.button01);
		Button button2=(Button)findViewById(R.id.button02);
		/**É{É^ÉìÇ™âüÇ≥ÇÍÇΩÇÁonClickÇ™ìÆçÏÇ∑ÇÈÇÊÇ§ê›íË**/
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
	}

	public void onClick(View v){
		int num = v.getId();
	    if(num==R.id.button01){
	    	Intent intent = new Intent(this,ReserveTable.class);
	    	this.startActivity(intent);
	    }
	    
	    else if(num==R.id.button02){
	    	Intent intent = new Intent(this,DformActivity.class);
			this.startActivity(intent);
	    }
	}
	
	public void FileConfirm(View v){
		Intent intent = new Intent(this,Confirm2.class);
		this.startActivity(intent);
	}
	
	public void Administer_side(View v){
		Intent intent = new Intent(this,Administerhome.class);
		this.startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
