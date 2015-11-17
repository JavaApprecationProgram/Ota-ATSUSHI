//Ndatafile.txtから予約番号・使用中番号を読み込み、予約済の番号（"person"以下にある番号）に対応する座席を橙色に、
//使用中の番号（"using"以下にある番号）に対応する座席を赤色にする
/*
 * 橙色の座席は選択不可・赤色の座席は選択可
 * 
 * 選択した座席は黄色になる(複数選択可)
 * 
 * 「使用する」ボタンを押すと、"using"+黄色になった座席の番号をNdatafile.txtに書き込む
 * 
 * 「空きにする」ボタンを押すと、選択した赤色の座席の番号を、Rdatafile.txtから削除する（using+-1の二行しかない箇所は、Rdatafile.txtから削除する）
 */
package com.example.helloapp;

import android.support.v7.app.ActionBarActivity;

import java.io.*;
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
import android.widget.TextView;

@SuppressLint("NewApi")
public class AdministerTable extends ActionBarActivity implements OnClickListener{
	static final int[] bid = {0,R.id.Button1,R.id.Button2,R.id.Button3,R.id.Button4,R.id.Button5,R.id.Button6,R.id.Button7,R.id.Button8,R.id.Button9,R.id.Button10,
			R.id.Button11,R.id.Button12,R.id.Button13,R.id.Button14,R.id.Button15,R.id.Button16,R.id.Button17,R.id.Button18,R.id.Button19,R.id.Button20,
			R.id.Button21,R.id.Button22,R.id.Button23,R.id.Button24,R.id.Button25,R.id.Button26,R.id.Button27,R.id.Button28,R.id.Button29,R.id.Button30,
			R.id.Button31,R.id.Button32,R.id.Button33,R.id.Button34,R.id.Button35,R.id.Button36,R.id.Button37,R.id.Button38,R.id.Button39,R.id.Button40};

	int checked[] = new int[bid.length];
	int orangeflag[] = new int[bid.length];
	int redflag[] = new int[bid.length];
	int cnt=0,num,colorturn=0;

	static Button[] bu = new Button[bid.length];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_administer_table);
		
	}
	
	void changecolor(String line,String color){
    	if(line.indexOf("person") == -1 && line.indexOf("using") == -1){
    		num=Integer.parseInt(line);
    		if(color=="orange"){
    			orangeflag[num]=1;
    			colorturn=1;
    		}
    		else if(color == "red"){
    			redflag[num]=1;
    			colorturn=2;
    		}
    	}
		return;
	}
	
	void colorpaint(int n){
    	if(colorturn==1){
    		orangeflag[num]=1;
    		if(1<=num%8 && num%8<=4){
				bu[num].setBackgroundDrawable(getResources().getDrawable(R.drawable.orengechair));
			}
			else{
				bu[num].setBackgroundDrawable(getResources().getDrawable(R.drawable.orengechair_rev));
			}
    	}
    	else if(colorturn==2){
    		redflag[num]=1;
    		if(1<=num%8 && num%8<=4){
				bu[num].setBackgroundDrawable(getResources().getDrawable(R.drawable.redchair));
			}
			else{
				bu[num].setBackgroundDrawable(getResources().getDrawable(R.drawable.redchair_rev));
			}
    	}
	}
	
	public void onStart(){
		super.onStart();
		for(int i=0;i<cnt;i++){
			checked[i]=-1;
		}
		cnt=0;
		colorturn=0;
		for(int i=1;i<bid.length;i++){
			orangeflag[i]=0;
			redflag[i]=0;
			bu[i]=(Button)findViewById(bid[i]);
			if(1<=i%8 && i%8<=4){
				bu[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.chair));
			}
			else{
				bu[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.chair_rev));
			}
			bu[i].setOnClickListener(this);
		}

	    try {
	    	FileInputStream fis = this.openFileInput("Ndatafile.txt");
	    	BufferedReader in = new BufferedReader(new InputStreamReader(fis));
	    	String line=in.readLine();
			while(line!=null){
			    if(line.indexOf("person") != -1){
			    	in.readLine();
			    	line=in.readLine();
			    	if(line == null){
			    		break;
			    	}
			    	changecolor(line,"orange");
			    }
			    
			    else if(line.indexOf("using") != -1){
			    	in.readLine();
			    	line=in.readLine();
			    	if(line == null){
			    		break;
			    	}
			    	changecolor(line,"red");
			   	}
			    
			    else{
			    	num=Integer.parseInt(line);
			    	colorpaint(num);
				    line=in.readLine();
			    }
			}
		    in.close();
		}
	    catch (NumberFormatException e) {
			e.printStackTrace();
		}
	    catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	int selectedcheck(){
		if(cnt==0){
			TextView text = (TextView)this.findViewById(R.id.textView2);
			text.setText("座席を指定してください");
			text.setTextColor(android.graphics.Color.RED);
			return -1;
		}
		return 0;
	}
	
	public void Use(View v) throws IOException{
		if(selectedcheck()==-1){
			return;
		}
		FileOutputStream fis2= this.openFileOutput("Ndatafile.txt", Context.MODE_APPEND);
    	BufferedWriter out2 = new BufferedWriter(new OutputStreamWriter(fis2));
    	out2.write("using\r\n"+-1+"\r\n");
    	for(int i=0;i<cnt;i++){
    		out2.write(checked[i]+"\r\n");
    	}
    	out2.close();
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("使用中座席を設定しました");
    	builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				onStart();
				return;
			}
		});
    	builder.show();
	}
	
	public void Free(View v) throws IOException{
		if(selectedcheck()==-1){
			return;
		}
		FileInputStream fis = this.openFileInput("Ndatafile.txt");
	    BufferedReader Nreader = new BufferedReader(new InputStreamReader(fis));
     	
    	int dcnt=0,flag=0,cnum;
    	String linedata = null;
    	String[] data = new String[1000];
    	
    	while((linedata = Nreader.readLine()) != null) {
 			data[dcnt++]=linedata;
 		}
     	Nreader.close();
     	
     	FileOutputStream fis4= this.openFileOutput("Ndatafile.txt", 0);
    	BufferedWriter Nwriter = new BufferedWriter(new OutputStreamWriter(fis4));
 		for(int i=0;i<dcnt;i++){
 			if(data[i].indexOf("using") != -1 || data[i].indexOf("person") != -1){
 				if(data[i+2]==null){
 					break;
 				}
 				if(data[i+2].indexOf("using") == -1 && data[i+2].indexOf("person") == -1){
 					Nwriter.write(data[i]+"\r\n");
					Nwriter.write(data[i+1]+"\r\n");
 				}
				i++;
 			}
 			
 			else{
 				cnum = Integer.parseInt(data[i]);
				for(int j=0;j<checked.length;j++){
 					if(cnum==checked[j]){
 						flag = 1;
 						break;
 					}
				}
				if(flag == 1){
					flag=0;
				}
				else{
					Nwriter.write(data[i]+"\r\n");
				}
 			}
 		}
 		
 		
     	Nwriter.close();
     	TextView text = (TextView)this.findViewById(R.id.textView2);
		text.setText("");
     	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("空座席を確保しました");
    	builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				onStart();
				return;
			}
		});
    	builder.show();
	}

	public void Update(View v){
		onStart();
	}
	
	public void onClick(View v){
		int ID = v.getId();
	    for(int i=1;i<bid.length;i++){
	    	if(ID == bid[i] && orangeflag[i]==0){
	    		if(1<=i%8 && i%8<=4){
					bu[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.yellowchair));
				}
				else{
					bu[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.yellowchair_rev));
				}
 				checked[cnt++]=i;
 				break;
	    	}
		}
	}
	
	
	public void Returnprev(View v){
		finish();
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
