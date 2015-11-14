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
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("NewApi")
public class DformActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dform);
	}
	
	
	public int numcheck(String s,String text, int redId){
		try{
	    	return Integer.parseInt(s);
	    }
	    
	    catch(NumberFormatException e){
	    	TextView text4 = (TextView)this.findViewById(R.id.textView3);
	    	text4.setText(text);
	    	text4.setTextColor(android.graphics.Color.RED);
		    TextView text2 = (TextView)this.findViewById(redId);
			text2.setTextColor(android.graphics.Color.RED);
	    	return -1;
	    }
	}
	
	public String editfilledcheck(int editID){
		try{
			EditText edit = (EditText)this.findViewById(editID);
			String s = edit.getText().toString();
			if(s.length()==0){
	    		s=null;
	    	}
	    	TextView text2 = (TextView)this.findViewById(R.id.textView1);
	    	text2.setTextColor(android.graphics.Color.BLACK);
	    	TextView text3 = (TextView)this.findViewById(R.id.textView2);
	    	text3.setTextColor(android.graphics.Color.BLACK);
			return s.toString();
		}
		
		catch(NullPointerException e){
			TextView text = (TextView)this.findViewById(R.id.textView3);
		    text.setText("—\–ñî•ñ‚ğ‘S‚Ä“ü—Í‚µ‚Ä‚­‚¾‚³‚¢");
		    text.setTextColor(android.graphics.Color.RED);
		    return null;
		}
	}
	
	
	public void DeleteReserve(View view) throws IOException{
 		//ˆês‚¸‚Â“Ç‚İ‚ñ‚Å‚¢‚­
 		String linedata;
 		String data1[] = new String[100];
 		String data2[] = new String[100];
 		int dcnt = 0,flag=0,rnum;
 		
	    String number = editfilledcheck(R.id.editText1);
	    String rnametext = editfilledcheck(R.id.editText2);
	    if(number==null||rnametext==null){
	        return;
	    }
	        
	    rnum = numcheck(number,"”š‚Å—\–ñ”Ô†‚ğ“ü—Í‚µ‚Ä‚­‚¾‚³‚¢", R.id.textView1);
	    if(rnum==-1){
	        return;
	    }
	    //“Ç‚İ‚İ
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
    	//–¼‘O‚ª“¯‚¶‚Å‚àA—\–ñ”Ô†‚ªˆá‚¤ê‡‚ÍƒGƒ‰[‚ğ‹N‚±‚·‚æ‚¤‚É‚·‚é
 		for(int i=0;i<dcnt;i++){
 			if(data1[i].indexOf("person") != -1 && Integer.parseInt(data1[i+1])==rnum && data1[i+2].indexOf(rnametext) != -1){
 				i+=3;
 				flag = 1;
     			while(data1[i].indexOf("person") == -1 && data1[i].indexOf("using") == -1){
     				if(i<dcnt-1){
     					i++;
     				}
     				else{
     					break;
     				}
     			}
     			if(i<dcnt-1){
     				Rwriter.write(data1[i]+"\r\n");
 				}
 			}
 			
 			else{
 				Rwriter.write(data1[i]+"\r\n");
 			}
 		}
 		
 		if(flag==0){
 			TextView text = (TextView)this.findViewById(R.id.textView3);
			text.setText("—\–ñî•ñ‚ªˆê’v‚µ‚Ü‚¹‚ñ");
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
 			if(data2[i].indexOf("person") != -1 && Integer.parseInt(data2[i+1])==rnum){
 				i+=2;
     			while(data2[i].indexOf("person") == -1&&data2[i].indexOf("using") == -1){
     				if(i<dcnt-1){
     					i++;
     				}
     				else{
     					break;
     				}
 				}
     			if(i<dcnt-1){
     				Nwriter.write(data2[i]+"\r\n");
 				}
 			}
 			
 			else{
 				Nwriter.write(data2[i]+"\r\n");
 			}
 		}
     	Nwriter.close();
     	
     	Intent i = new Intent(this,DeleteComplete.class);
     	this.startActivity(i);
	}
	
	public void ReturnToHome(View view){
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rform, menu);
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
