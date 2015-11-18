//ホーム画面
/*
 * はじめに、Reservenum.txt・Ndatafile.txt・Rdatafile.txtの三つのファイルがすべて存在するかを確認する
 * （存在しない場合は、ファイルを作成する）
 * 
 * 「予約する」ボタン→Reservetable.javaへ
 * 
 * 「予約を取り消す」ボタン→Deleteform.javaへ
 * 
 * 「ファイル確認」ボタン→Confirm2.javaへ
 * 
 * 「管理者ページ」ボタン→Administerhome.javaへ
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
	

	//ファイルを作成するメソッド
	public void mkfile(String p) throws IOException{
		String new_file_path = "/data/data/"+this.getPackageName()+"/files/"+p;
	    File newfile = new File(new_file_path);
	    newfile.createNewFile();
	}
	
	//本プログラムのxmlファイルを開くと同時に、3つのテキストファイルがすべて存在するかを確認するメソッド
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
	
	//Reservetable.javaへ移るメソッド（「予約する」ボタンを押すと実行される）
	public void ReserveTable(View v){
		Intent intent = new Intent(this,ReserveTable.class);
    	this.startActivity(intent);
	}
	
	//DformActivity.javaへ移るメソッド（「予約を取り消す」ボタンを押すと実行される）
	public void DeleteForm(View v){
		Intent intent = new Intent(this,DformActivity.class);
		this.startActivity(intent);
	}
	
	//Confirm2.javaへ移るメソッド（「ファイル確認」ボタンを押すと実行される）
	public void FileConfirm(View v){
		Intent intent = new Intent(this,Confirm2.class);
		this.startActivity(intent);
	}
	
	//Admministerhomeに移るメソッド（「管理者ページ」ボタンを押すと実行される）
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
