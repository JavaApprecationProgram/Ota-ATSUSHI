//Numberformat.txt�ARdatafile.txt�ANdatafile.txt�̃t�@�C���̒��g��S�ĕ\��������
/**
 * �uNUMBERFORMAT�v�{�^���������ƁANumberformat.txt�̒��g�S�Ă��m�F�ł���
 * 
 * �uRDATAFILE�v�{�^���������ƁARdatafile.txt�̒��g�S�Ă��m�F�ł���
 * 
 * �uNDATAFILE�v�{�^���������ƁANdatafile.txt�̒��g�S�Ă��m�F�ł���
 * 
 * �uRESET�v�{�^���������ƁANumberformat.txt�ARdatafile.txt�ANdatafile.txt�����ׂč폜�����
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
	
	//String�^�̕ϐ�"pass"�Ŏw�肵���e�L�X�g�t�@�C���̒��g�S�Ă�int�^�̕ϐ�"id"�Ŏw�肵��textview�ɕ\�����郁�\�b�h
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
	
	//Reservenum.txt�̒��g��S�ĕ\�����郁�\�b�h�i�uRESERVENUM�v�{�^���������Ǝ��s�����j
	public void Reservenum(View view) throws IOException{
		showfile(R.id.textView1,"Reservenum.txt");
	}
	
	//Ndatafile.txt�̒��g��S�ĕ\�����郁�\�b�h�i�uNDATAFILE�v�{�^���������Ǝ��s�����j
	public void Ndatafile(View view) throws IOException{
		showfile(R.id.textView1,"Ndatafile.txt");
	}
	//Rdatafile.txt�̒��g��S�ĕ\�����郁�\�b�h�i�uRDATAFILE�v�{�^���������Ǝ��s�����j
	public void Rdatafile(View view) throws IOException{
		showfile(R.id.textView1,"Rdatafile.txt");
	}
	
	//String�^�̕ϐ�"pass"�Ŏw�肵���e�L�X�g�t�@�C���̒��g�S�Ă��폜���郁�\�b�h
	void resetfile(String pass) throws IOException{
		FileOutputStream fis= this.openFileOutput(pass, 0);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fis));
		out.write("");
		out.flush();
		out.close();
	}
	
	//3�ÑS�Ă̂��t�@�C���̒��g�����Z�b�g���郁�\�b�h�i�uRESER�v�{�^���������Ǝ��s�����j
	public void Reset(View view) throws IOException{
		resetfile("Reservenum.txt");
		resetfile("Ndatafile.txt");
		resetfile("Rdatafile.txt");
	}
	
	//�z�[���ɖ߂郁�\�b�h�i�u�z�[���ɖ߂�v�{�^���������Ǝ��s�����j
	public void ReturnToHome(View v){
		finish();
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
