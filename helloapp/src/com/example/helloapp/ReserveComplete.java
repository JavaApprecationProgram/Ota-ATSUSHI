//�\�񂪊��������Ƃ��ɏo�͂������
/**
 * Reserveform.java����A�\��ԍ�+edittext�ɓ��͂��ꂽ���O�E�l���E�d�b�ԍ���textview1���o�͂���
 */
package com.example.helloapp;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

@SuppressLint("NewApi")
public class ReserveComplete extends ActionBarActivity {
	
	//�{�v���O������xml�t�@�C�����J������Aintent�����當������󂯎���āA�����textView1�ɕ\�����郁�\�b�h
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reserve_complete);
		TextView label = (TextView)findViewById(R.id.textView1); 
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        label.setText(extras.getString("Data"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reserve_complete, menu);
		return true;
	}
	
	//�z�[���ɖ߂郁�\�b�h�i�u�z�[���ɖ߂�v�{�^���������Ǝ��s�����j
	public void ReturnHome(View v){
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
