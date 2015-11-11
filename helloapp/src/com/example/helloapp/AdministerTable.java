package com.example.helloapp;

import android.support.v7.app.ActionBarActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

@SuppressLint("NewApi")
public class AdministerTable extends ActionBarActivity implements OnClickListener{
	static final int[] bid = {0,R.id.Button1,R.id.Button2,R.id.Button3,R.id.Button4,R.id.Button5,R.id.Button6,R.id.Button7,R.id.Button8,R.id.Button9,R.id.Button10,
			R.id.Button11,R.id.Button12,R.id.Button13,R.id.Button14,R.id.Button15,R.id.Button16,R.id.Button17,R.id.Button18,R.id.Button19,R.id.Button20,
			R.id.Button21,R.id.Button22,R.id.Button23,R.id.Button24,R.id.Button25,R.id.Button26,R.id.Button27,R.id.Button28,R.id.Button29,R.id.Button30,
			R.id.Button31,R.id.Button32,R.id.Button33,R.id.Button34,R.id.Button35,R.id.Button36,R.id.Button37,R.id.Button38,R.id.Button39,R.id.Button40};

	int checked[] = new int[bid.length-1];
	int orangeflag[] = new int[bid.length];
	int cnt=0,num;

	static Button[] bu = new Button[bid.length];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_administer_table);
		
	}
	
	public void onStart(){
		super.onStart();
		for(int i=1;i<bid.length;i++){
			orangeflag[i]=0;
			bu[i]=(Button)findViewById(bid[i]);
			bu[i].setOnClickListener(this);
		}
		
		try {
			FileInputStream fis = this.openFileInput("Ndatafile.txt");
	        BufferedReader in = new BufferedReader(new InputStreamReader(fis));
	        String line=null;
	        while((line=in.readLine())!=null){
	        	if(line.indexOf("person") != -1){
	        		in.readLine();
	        		line=in.readLine();
	        		num=Integer.parseInt(line);
	        		bu[num].setBackgroundColor(Color.rgb(255, 192, 0));
	        		orangeflag[num]=1;
	        	}
	        	else{
	        		num=Integer.parseInt(line);
	        		bu[num].setBackgroundColor(Color.rgb(255, 192, 0));
	        		orangeflag[num]=1;
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
	public void Use(View v) throws IOException{
		FileOutputStream fis2= this.openFileOutput("Ndatafile.txt", Context.MODE_APPEND);
    	BufferedWriter out2 = new BufferedWriter(new OutputStreamWriter(fis2));
    	out2.write("person\r\n"+-1+"\r\n");
    	for(int i=0;i<cnt;i++){
    		out2.write(checked[i]+"\r\n");
    	}
    	out2.close();
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("Žg—p’†Ý’èŠ®—¹");
    	builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				onStart();
				return;
			}
		});
    	builder.show();
	}
	
	public void Free(View v){
		
	}

	public void Update(View v){
		onStart();
	}
	
	public void onClick(View v){
		int ID = v.getId();
	    for(int i=1;i<bid.length;i++){
	    	if(ID == bid[i] && orangeflag[i]==0){
	    		bu[i].setBackgroundColor(Color.YELLOW);
 				checked[cnt++]=i;
 				break;
	    	}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.administer_table, menu);
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
