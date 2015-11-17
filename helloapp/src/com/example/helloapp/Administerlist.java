//Rdatafile.txt����\�����ǂݍ��݁A�e�\��҂̗\��ԍ��E�����O�E�l�������X�g�`���ŕ\������
/*
 * �e�L�X�g�G�f�B�^�urnumedit�v�ɗ\��ԍ�����͂���
 * �u�\��Ҏg�p�v�{�^����������rnamedit�œ��͂����ԍ��ɑΉ�����\��ԍ��i�@�j�̗\��҂�"using�i�g�p���j"�ɂ���
 * �iRdatafile.txt�ENdatafile.txt�ɂ���@�ɑΉ�����\��ҏ���"person"��"using"�ɕς���j
 * onStart()�����s����ARdatafile.txt����\������ēǂݍ��݂��A�e�\��҂̗\��ԍ��E�����O�E�l�������X�g�`���ŕ\������
 * 
 * �u�X�V�v�{�^����������onStart()�����s�����
 * 
 * 
 */

package com.example.helloapp;

import android.support.v7.app.ActionBarActivity;

import java.io.*;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("NewApi")
public class Administerlist extends ActionBarActivity {

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
	    		s.toString();	//editText�ɉ������͂���Ă��Ȃ���΁ANullPointerException���N��������
	    	}
			num = Integer.parseInt(s);
			return num;
		}
		
		catch(NullPointerException e){
			TextView text = (TextView)this.findViewById(R.id.textView5);
		    text.setText("�\�����S�ē��͂��Ă�������");
		    text.setTextColor(android.graphics.Color.RED);
		    return -1;
		}
	}
	
	int fileupdate(String path,int rn,int Rdataflag) throws IOException{
		String linedata;
		String[] data = new String[1000];
 		int dcnt = 0,flag=0;
	    FileInputStream fis = this.openFileInput(path);
	    BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
 		while((linedata = reader.readLine()) != null) {
 			data[dcnt++]=linedata;
 			System.out.println(linedata);
 		}
 		reader.close();

 		FileOutputStream fis2= this.openFileOutput(path, 0);
    	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fis2));
 		for(int i=0;i<dcnt;i++){
 			if(data[i].indexOf("person") != -1){
     			if(Integer.parseInt(data[i+1])==rn){
     				if(i<dcnt-1){
     					if(Rdataflag==1){
     						i+=4;
     					}
     					else{
     						writer.write("using\r\n"+-1+"\r\n");
     						i++;
     					}
 						flag=1;
     				}
     				else{
     					writer.write(data[i]+"\r\n");
     				}
     			}

 				else{
 					writer.write(data[i]+"\r\n");
 				}
 			}
 			
 			else{
 				writer.write(data[i]+"\r\n");
 			}
 		}
 		writer.close();
 		
		return flag;
	}
	
	
	public void UseTable(View v) throws IOException{
		int num;
		if((num=filedcheck())!=-1){
			if(fileupdate("Rdatafile.txt",num,1)==0){
				TextView text = (TextView)this.findViewById(R.id.textView5);
				text.setText("���͂��ꂽ�\����́A�\�񃊃X�g�ɑ��݂��܂���");
				text.setTextColor(android.graphics.Color.RED);
				return;
			}
			fileupdate("Ndatafile.txt",num,0);

		    
	 		
	     	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    	builder.setTitle("�w�肵���\��ԍ��ɂ�����Ȃ��A�g�p��Ԃɂ��܂���");
	    	builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					onStart();
					return;
				}
			});
	    	builder.show();
		}
		
		return;
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
