//Numberformat.txt、Rdatafile.txt、Ndatafile.txtのファイルの中身を全て表示する画面
/*
 * 「NUMBERFORMAT」ボタンを押すと、Numberformat.txtの中身全てを確認できる
 * 
 * 「RDATAFILE」ボタンを押すと、Rdatafile.txtの中身全てを確認できる
 * 
 * 「NDATAFILE」ボタンを押すと、Ndatafile.txtの中身全てを確認できる
 * 
 * 「RESET」ボタンを押すと、Numberformat.txt、Rdatafile.txt、Ndatafile.txtがすべて削除される
 */
package com.example.helloapp;

import android.support.v7.app.ActionBarActivity;

import java.io.*;
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
	
	
	void showfile(int id,String pass) throws IOException{
		TextView edit1 = (TextView)this.findViewById(id);
		FileInputStream input = null;
		input = this.openFileInput(pass);
		byte[] buffer = new byte[1000];
		input.read(buffer);
		String s = new String(buffer).trim();
		edit1.setText(s);
		input.close();
	}
	
	public void Reservenum(View view) throws IOException{
		showfile(R.id.textView1,"Reservenum.txt");
	}
	
	public void Ndatafile(View view) throws IOException{
		showfile(R.id.textView1,"Ndatafile.txt");
	}
	
	public void Rdatafile(View view) throws IOException{
		showfile(R.id.textView1,"Rdatafile.txt");
	}
	
	void resetfile(String pass) throws IOException{
		FileOutputStream fis= this.openFileOutput(pass, 0);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fis));
		out.write("");
		out.flush();
		out.close();
	}
	
	public void Reset(View view) throws IOException{
		resetfile("Reservenum.txt");
		resetfile("Ndatafile.txt");
		resetfile("Rdatafile.txt");
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
