//�Ǘ��ґ��̃z�[����ʁi���X�g��ʁE�e�[�u����ʂɈڂ�{�^��������j
package com.example.helloapp;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

@SuppressLint("NewApi")
public class Administerhome extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_administerhome);
	}
	
	//�u���X�g�\���v�{�^�����������Ƃ��Ɏ��s�����
	public void GoToList(View v){
		Intent i = new Intent(this,Administerlist.class);
		this.startActivity(i);
	}
	
	//�u�e�[�u���\���v�{�^�����������Ƃ��Ɏ��s�����
	public void GoToTable(View v){
		Intent i = new Intent(this,AdministerTable.class);
		this.startActivity(i);
	}
	
	//�u�z�[���ɖ߂�v�{�^�����������Ƃ��Ɏ��s�����
	public void ReturnToHome(View v){
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.administerhome, menu);
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
