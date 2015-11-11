package com.example.helloapp;

import android.support.v7.app.ActionBarActivity;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

@SuppressLint("NewApi")
public class Confirm2 extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirm2);
	}
	
	public void Reservenum(View view){
		TextView edit1 = (TextView)this.findViewById(R.id.textView1);
		FileInputStream input = null;
		try{
			input = this.openFileInput("Reservenum.txt");
			byte[] buffer = new byte[1000];
			input.read(buffer);
			String s = new String(buffer).trim();
			edit1.setText(s);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void Ndatafile(View view){
		TextView edit1 = (TextView)this.findViewById(R.id.textView1);
		FileInputStream input = null;
		try{
			input = this.openFileInput("Ndatafile.txt");
			byte[] buffer = new byte[1000];
			input.read(buffer);
			String s = new String(buffer).trim();
			edit1.setText(s);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void Rdatafile(View view){
		TextView edit1 = (TextView)this.findViewById(R.id.textView1);
		FileInputStream input = null;
		try{
			input = this.openFileInput("Rdatafile.txt");
			byte[] buffer = new byte[1000];
			input.read(buffer);
			String s = new String(buffer).trim();
			edit1.setText(s);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void Reset(View view){
		try{
			FileOutputStream fis= this.openFileOutput("Reservenum.txt", 0);
    		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fis));
    		out.write("");
    		out.flush();
    		out.close();
    		
    		FileOutputStream fis2= this.openFileOutput("Rdatafile.txt", 0);
    		BufferedWriter out2 = new BufferedWriter(new OutputStreamWriter(fis2));
    		out2.write("");
    		out2.flush();
    		out2.close();
    		
    		FileOutputStream fis3= this.openFileOutput("Ndatafile.txt", 0);
    		BufferedWriter out3 = new BufferedWriter(new OutputStreamWriter(fis3));
    		out3.write("");
    		out3.flush();
    		out3.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ReturnToHome(View v){
		Intent i = new Intent(this,MainActivity.class);
		this.startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.confirm2, menu);
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
