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
public class Reserveform extends ActionBarActivity {

	int cnt=0;
	int[] cnum = new int[100];	//座席番号が格納されている
	private String path = "Reservenum.txt";
	private String path2 = "Ndatafile.txt";
	private String path3 = "Rdatafile.txt";
	String name,number,address;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reserveform);
		Intent intent = getIntent();
        Bundle extras = intent.getExtras();
    	char[] chararray = extras.getString("Data").toCharArray();
    	for(int i=0 ; i<chararray.length;i++){
    		if(chararray[i]!='|'){
    			if(chararray[i+1]=='|'){    			
    				cnum[cnt++]= Integer.parseInt(String.valueOf(chararray[i]));
    				i++;
    			}
    			
    			else if(chararray[i+2]=='|'){
    				cnum[cnt++]= Integer.parseInt(String.valueOf(chararray[i]))*10 + Integer.parseInt(String.valueOf(chararray[i+1]));
    				i+=2;
    			}
    		}
    	}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reserveform, menu);
		return true;
	}
	
	public int numcheck(String s,String text, int redId){
		try{
	    	return Integer.parseInt(s);
	    }
	    
	    catch(NumberFormatException e){
	    	TextView text4 = (TextView)this.findViewById(R.id.textView4);
	    	text4.setText(text);
	    	text4.setTextColor(android.graphics.Color.RED);
		    TextView text2 = (TextView)this.findViewById(redId);
			text2.setTextColor(android.graphics.Color.RED);
	    	return -1;
	    }
	} 
	
	public void mkdir(String p) throws IOException{
		String new_file_path = "/data/data/"+this.getPackageName()+"/files/"+p;
        File newfile = new File(new_file_path);
        newfile.createNewFile();
	}
	
	public void addRnum(int n) throws IOException{
		FileOutputStream fis1 = this.openFileOutput(path, 0);
		BufferedWriter out1 = new BufferedWriter(new OutputStreamWriter(fis1));
		out1.write(n+"");
		out1.close();
	}
	
	public String editfilledcheck(int editID){
		try{
			EditText edit = (EditText)this.findViewById(editID);
			String s = edit.getText().toString();
			if(s.length()==0){
	    		s=null;
	    	}
	    	TextView text2 = (TextView)this.findViewById(R.id.textView2);
	    	text2.setTextColor(android.graphics.Color.BLACK);
	    	TextView text3 = (TextView)this.findViewById(R.id.textView3);
	    	text3.setTextColor(android.graphics.Color.BLACK);
			return s.toString();
		}
		
		catch(NullPointerException e){
			TextView text = (TextView)this.findViewById(R.id.textView4);
		    text.setText("予約情報を全て入力してください");
		    text.setTextColor(android.graphics.Color.RED);
		    return null;
		}
	}
	
	public void doAction(View v) throws IOException{
    	int num = 0;
    	name=editfilledcheck(R.id.editText1);
    	number=editfilledcheck(R.id.editText2);
    	address=editfilledcheck(R.id.editText3);
    	
    	if(name==null||number==null||address==null){
    		return;
    	}
	    
	    if(numcheck(number,"数字で人数を入力してください", R.id.textView2)==-1 || numcheck(address,"数字（ハイフン無し）で電話番号を入力してください", R.id.textView3)==-1){
	    	return;
	    }
	    
	    try{
			FileInputStream fis = this.openFileInput(path);
	        BufferedReader in = new BufferedReader(new InputStreamReader(fis));
	        String line=in.readLine();
	        num=Integer.parseInt(line);
			num++;
			addRnum(num);
	    }
	    
	    catch(FileNotFoundException e){
			mkdir(path);
            mkdir(path2);
            mkdir(path3);
            num=1;
    		addRnum(num);
	    }
	    
	    catch (NumberFormatException e2){
			num=1;
    		addRnum(num);
	    }
	    
	    finally{
	    	// ファイルへの書き込み
	    	FileOutputStream fis2= this.openFileOutput(path2, Context.MODE_APPEND);
	    	BufferedWriter out2 = new BufferedWriter(new OutputStreamWriter(fis2));
	    	out2.write("person\r\n"+num+"\r\n");
	    	for(int i=0;i<cnt;i++){
	    		out2.write(cnum[i]+"\r\n");
	    	}
	    	out2.close();
	    	
	    	FileOutputStream fis3= this.openFileOutput(path3, Context.MODE_APPEND);
	    	BufferedWriter out3 = new BufferedWriter(new OutputStreamWriter(fis3));
	    	out3.write("person\r\n"+num+"\r\n"+name+"\r\n"+number+"\r\n"+address+"\r\n");
	    	out3.close();
	    	
	    	Intent intent = new Intent(this,ReserveComplete.class);
	    	intent.putExtra("Data","予約番号" + num+ "\n\nお名前: " + name + "\n\n人数: " + number + "\n\n電話番号" + address);
	    	this.startActivity(intent);
	    }
        
	}
	
	public void ReturntoHome(View v){
		Intent intent = new Intent(this,MainActivity.class);
		this.startActivity(intent);
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
