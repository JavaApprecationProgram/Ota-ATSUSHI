package com.example.helloapp;

import android.support.v7.app.ActionBarActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("NewApi")
public class ReserveTable extends ActionBarActivity implements OnClickListener {
	
	static final int[] bid = {0,R.id.Button1,R.id.Button2,R.id.Button3,R.id.Button4,R.id.Button5,R.id.Button6,R.id.Button7,R.id.Button8,R.id.Button9,R.id.Button10,
			R.id.Button11,R.id.Button12,R.id.Button13,R.id.Button14,R.id.Button15,R.id.Button16,R.id.Button17,R.id.Button18,R.id.Button19,R.id.Button20,
			R.id.Button21,R.id.Button22,R.id.Button23,R.id.Button24,R.id.Button25,R.id.Button26,R.id.Button27,R.id.Button28,R.id.Button29,R.id.Button30,
			R.id.Button31,R.id.Button32,R.id.Button33,R.id.Button34,R.id.Button35,R.id.Button36,R.id.Button37,R.id.Button38,R.id.Button39,R.id.Button40};

	int checked[] = new int[bid.length-1];
	int redflag[] = new int[bid.length];
	int cnt=0,num;

	static Button[] bu = new Button[bid.length];
	
	public String Sequencemake(int i){	// 3|15|11|・・・
		if(i!=cnt-1){
			return String.valueOf(checked[i]) + "|" + Sequencemake(i+1);
		}
		
		else{
			return String.valueOf(checked[i]) + "|";
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rtable);
		
		
		for(int i=1;i<bid.length;i++){
			redflag[i]=0;
			bu[i]=(Button)findViewById(bid[i]);
			bu[i].setOnClickListener(this);
		}
		
		try {
			FileInputStream fis = this.openFileInput("Ndatafile.txt");
	        BufferedReader in = new BufferedReader(new InputStreamReader(fis));
	        String line=null;
	        while((line=in.readLine())!=null){
	        	if(line.indexOf("person") != -1||line.indexOf("using") != -1){
	        		in.readLine();
	        		line=in.readLine();
	        		num=Integer.parseInt(line);
	        		bu[num].setBackgroundColor(Color.RED);
	        		redflag[num]=1;
	        	}
	        	else{
	        		num=Integer.parseInt(line);
	        		bu[num].setBackgroundColor(Color.RED);
	        		redflag[num]=1;
	        	}
	        }
	        in.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Reserve(View v){
		Intent intent = new Intent(this,Reserveform.class);
		if(cnt==0){
			TextView text = (TextView)this.findViewById(R.id.textView2);
	    	text.setText("予約したい座席を選択してください");
	    	text.setTextColor(android.graphics.Color.RED);
	    	return;
		}
	    intent.putExtra("Data",Sequencemake(0));
	    this.startActivity(intent);
	}
	
	public void ReturntoHome(View v){
		Intent intent = new Intent(this,MainActivity.class);
		this.startActivity(intent);
	}
	
	public void onClick(View v){
		int ID = v.getId();
	    for(int i=1;i<bid.length;i++){
	    	if(ID == bid[i] && redflag[i]==0){
	    		bu[i].setBackgroundColor(Color.YELLOW);
 				checked[cnt++]=i;
 				break;
	    	}
		}
	}
	
	public void ReturnToHome(View view){
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.other, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
