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
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("NewApi")
public class Administerlist extends ActionBarActivity {
	String[] rnum = new String[1000];
	String[] name = new String[1000];
	String[] pnum = new String[1000];
	int rcnt,ncnt,pcnt;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_administerlist);
	}
	
	
	public void onStart(){
		super.onStart();
		TextView rview = (TextView)this.findViewById(R.id.rnumtext);
		TextView nview = (TextView)this.findViewById(R.id.nametext);
		TextView pview = (TextView)this.findViewById(R.id.pnumtext);
		rview.setText("");
		nview.setText("");
		pview.setText("");
		try{
			FileInputStream fis = this.openFileInput("Rdatafile.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
	    	String line;
	    	while((line=in.readLine())!=null){
	    		if(line.indexOf("person") != -1){
	    			rview.append(in.readLine()+"\n");
	    			nview.append(in.readLine()+"\n");
	    			pview.append(in.readLine()+"\n");
	    			in.readLine();
	    		}
	    	}
	    	
	    	in.close();
		}
    	catch(IOException e){
    		e.printStackTrace();
    	}
	}
	
	int filedcheck(){
		try{
			int num;
			EditText edit = (EditText)this.findViewById(R.id.rnumedit);
			String s = edit.getText().toString();
			if(s.length()==0){
	    		s=null;
	    		s.toString();	//editTextに何も入力されていなければ、NullPointerExceptionを起こさせる
	    	}
			num = Integer.parseInt(s);
			return num;
		}
		
		catch(NullPointerException e){
			TextView text = (TextView)this.findViewById(R.id.textView5);
		    text.setText("予約情報を全て入力してください");
		    text.setTextColor(android.graphics.Color.RED);
		    return -1;
		}
	}
	
	
	public void UseTable(View v) throws IOException{
		int rnum;
		if((rnum=filedcheck())!=-1){

		    String linedata;
	 		String data1[] = new String[100];
	 		String data2[] = new String[100];
	 		int dcnt = 0,flag=0;
		    FileInputStream fis2 = this.openFileInput("Rdatafile.txt");
		    BufferedReader Rreader = new BufferedReader(new InputStreamReader(fis2));
			FileInputStream fis = this.openFileInput("Ndatafile.txt");
		    BufferedReader Nreader = new BufferedReader(new InputStreamReader(fis));
	 			
	 		while((linedata = Rreader.readLine()) != null) {
	 			data1[dcnt++]=linedata;
	 			System.out.println(linedata);
	 		}
	 		Rreader.close();

	 		FileOutputStream fis3= this.openFileOutput("Rdatafile.txt", 0);
	    	BufferedWriter Rwriter = new BufferedWriter(new OutputStreamWriter(fis3));
	 		for(int i=0;i<dcnt;i++){
	 			if(data1[i].indexOf("person") != -1){
	     			if(Integer.parseInt(data1[i+1])==rnum){
	     				if(i<dcnt-1){
	     					Rwriter.write("using\r\n"+-1+"\r\n");
	     					i++;
	     					flag=1;
	     				}
	     				else{
	     					Rwriter.write(data1[i]+"\r\n");
	     				}
	     			}

     				else{
     					Rwriter.write(data1[i]+"\r\n");
     				}
	 			}
	 			
	 			else{
	 				Rwriter.write(data1[i]+"\r\n");
	 			}
	 		}
	 		
	 		if(flag==0){
	 			TextView text = (TextView)this.findViewById(R.id.textView5);
				text.setText("入力された予約情報は。予約リストに存在しません");
				text.setTextColor(android.graphics.Color.RED);
				Rwriter.close();
				return;
	 		}
	 		
	 		Rwriter.close();
	 		
	 		
	 		dcnt=0;
	 		while((linedata = Nreader.readLine()) != null) {
	 			data2[dcnt++]=linedata;
	 			System.out.println(linedata);
	 		}

	     	Nreader.close();
	     	

	     	FileOutputStream fis4= this.openFileOutput("Ndatafile.txt", 0);
	    	BufferedWriter Nwriter = new BufferedWriter(new OutputStreamWriter(fis4));
	 		for(int i=0;i<dcnt;i++){
	 			if(data2[i].indexOf("person") != -1){
	     			if(Integer.parseInt(data2[i+1])==rnum){
	     				if(i<dcnt-1){
	     					Nwriter.write("using\r\n"+-1+"\r\n");
	     					i++;
	     				}
	     				else{
	     					Nwriter.write(data2[i]+"\r\n");
	     				}
	     			}

     				else{
     					Nwriter.write(data2[i]+"\r\n");
     				}
	 			}
	 			
	 			else{
	 				Nwriter.write(data2[i]+"\r\n");
	 			}
	 		}
	     	Nwriter.close();
	     	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    	builder.setTitle("指定した予約番号にある座席を、使用状態にしました");
	    	builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					onStart();
					return;
				}
			});
	    	builder.show();
		}
		else{
			return;
		}
	}
	
	
	public void Update(View v){
		onStart();
	}
	
	
	public void ReturnToPrev(View v){
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.administerlist, menu);
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
