//�z�[�����
/*
 * �͂��߂ɁAReservenum.txt�ENdatafile.txt�ERdatafile.txt�̎O�̃t�@�C�������ׂđ��݂��邩���m�F����
 * �i���݂��Ȃ��ꍇ�́A�t�@�C�����쐬����j
 * 
 * �u�\�񂷂�v�{�^����Reservetable.java��
 * 
 * �u�\����������v�{�^����Deleteform.java��
 * 
 * �u�t�@�C���m�F�v�{�^����Confirm2.java��
 * 
 * �u�Ǘ��҃y�[�W�v�{�^����Administerhome.java��
 */
package com.example.helloapp;

import java.io.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity implements OnClickListener {
	

	//�t�@�C�����쐬���郁�\�b�h
	public void mkfile(String p) throws IOException{
		String new_file_path = "/data/data/"+this.getPackageName()+"/files/"+p;
	    File newfile = new File(new_file_path);
	    newfile.createNewFile();
	}
	
	//�{�v���O������xml�t�@�C�����J���Ɠ����ɁA3�̃e�L�X�g�t�@�C�������ׂđ��݂��邩���m�F���郁�\�b�h
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.Relativelayout);
		layout.setBackgroundResource(R.drawable.wall);
		try{
			FileInputStream input = this.openFileInput("Reservenum.txt");
			FileInputStream input2 = this.openFileInput("Ndatafile.txt");
			FileInputStream input3 = this.openFileInput("Rdatafile.txt");
			input.close();
			input2.close();
			input3.close();
		}
		
		catch(FileNotFoundException e){
			try {
	            mkfile("Ndatafile.txt");
	            mkfile("Rdatafile.txt");
				mkfile("Reservenum.txt");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Reservetable.java�ֈڂ郁�\�b�h�i�u�\�񂷂�v�{�^���������Ǝ��s�����j
	public void ReserveTable(View v){
		Intent intent = new Intent(this,ReserveTable.class);
    	this.startActivity(intent);
	}
	
	//DformActivity.java�ֈڂ郁�\�b�h�i�u�\����������v�{�^���������Ǝ��s�����j
	public void DeleteForm(View v){
		Intent intent = new Intent(this,DformActivity.class);
		this.startActivity(intent);
	}
	
	//Confirm2.java�ֈڂ郁�\�b�h�i�u�t�@�C���m�F�v�{�^���������Ǝ��s�����j
	public void FileConfirm(View v){
		Intent intent = new Intent(this,Confirm2.class);
		this.startActivity(intent);
	}
	
	//Admministerhome�Ɉڂ郁�\�b�h�i�u�Ǘ��҃y�[�W�v�{�^���������Ǝ��s�����j
	public void Administer_side(View v){
		Intent intent = new Intent(this,Administerhome.class);
		this.startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		
	}
}
