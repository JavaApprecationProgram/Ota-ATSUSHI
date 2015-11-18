//�\�������͂�����
/**�͂��߂ɁAonCreate���\�b�h��intent������󂯎�������Ȕԍ��𕶎���ɒ��������̂��Aint�^�̔z��ɒ���
 * �i��: intent������󂯎����������"1|10|15|22"��int�^�̔z��cnum={1,10,15,22}�ɂ���j
 * 
 * edittext�ɖ��O�E�l���E�d�b�ԍ�����͂���
 * 
 * �u���肷��v�{�^���������ƁAReservenum.txt����\��ԍ����Ăяo���A�C���N�������g�����\��ԍ��i�@�j��Reservenum.txt�ɏ������ށB
 * ���ɁA�@+���͂��ꂽ���O�E�l���E�d�b�ԍ���Rdatafile.txt�ɏ������݁A�@+�w�肵�����Ȕԍ���Rdatafile.txt�ɏ������ށB
 * ���̌�AReserveComplete.java��intent���āAintent��ɇ@+���͂��ꂽ���O�E�l���E�d�b�ԍ��̕������n��
 * 
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
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("NewApi")
public class Reserveform extends ActionBarActivity {

	int cnt=0;
	int[] cnum = new int[100];	//���Ȕԍ����i�[����Ă���
	private String path = "Reservenum.txt";
	private String path2 = "Ndatafile.txt";
	private String path3 = "Rdatafile.txt";
	String name,number,address;


	//�{�v���O������xml�t�@�C�����J���ƂƂ��ɁAintent������󂯎�������Ȕԍ��𕶎���ɒ��������̂�int�^�̔z��ɒ������\�b�h
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
	
	//s�Ŏw�肵�������񂪐����ł��邩���m�F���郁�\�b�h
	//�i�����łȂ���΃G���[��\������j
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
	
	
	//Reservenum.txt�ɁA���݂̗\��ԍ����㏑�����郁�\�b�h
	public void addRnum(int n) throws IOException{
		FileOutputStream fis1 = this.openFileOutput(path, 0);
		BufferedWriter out1 = new BufferedWriter(new OutputStreamWriter(fis1));
		out1.write(n+"");
		out1.close();
	}
	
	//�\���񂪏������܂ꂽ�����m���߂郁�\�b�h
	//�i�������܂�Ă��Ȃ���΁A�G���[��\������
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
		    text.setText("�\�����S�ē��͂��Ă�������");
		    text.setTextColor(android.graphics.Color.RED);
		    return null;
		}
	}
	
	//�\������������ރ��\�b�h�i�u�\�񂷂�v�{�^���������Ǝ��s�����j
	/**�u�\�񂷂�v�{�^�������������̑���
	 * �܂��AReservenum.txt�ɓ����Ă���\��ԍ����C���N�������g���A�����Reservenum.txt�ɏ㏑������i�C���N�������g�����\��ԍ����@�Ƃ���j
	 * 
	 * ���ɁANDdatafile.txt�ɇ@+�I���������Ȕԍ�����������
	 * 
	 * �����āARdatafile.txt�ɇ@+���O+�l��+�d�b�ԍ�����������
	 * 
	 */
	public void doAction(View v) throws IOException{
    	int num = 0;
    	name=editfilledcheck(R.id.rnumedit);
    	number=editfilledcheck(R.id.editText2);
    	address=editfilledcheck(R.id.editText3);
    	
    	if(name==null||number==null||address==null){
    		return;
    	}
	    
	    if(numcheck(number,"�����Ől������͂��Ă�������", R.id.textView2)==-1 || numcheck(address,"�����i�n�C�t�������j�œd�b�ԍ�����͂��Ă�������", R.id.textView3)==-1){
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
	    
	    
	    catch (NumberFormatException e2){
			num=1;
    		addRnum(num);
	    }
	    
	    finally{
	    	// �t�@�C���ւ̏�������
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
	    	intent.putExtra("Data","�\��ԍ�" + num+ "\n\n�����O: " + name + "\n\n�l��: " + number + "\n\n�d�b�ԍ�" + address);
	    	this.startActivity(intent);
	    }
        
	}
	
	//1�O�̉�ʂɖ߂郁�\�b�h�i�u�߂�v�{�^���������Ǝ��s�����j
	public void ReturntoPrev(View v){
		finish();
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
