//Ndatafile.txtから予約番号・使用中番号を読み込み、予約済の番号（"person"以下にある番号）・
//使用中の番号（"using"以下にある番号）に対応する座席を赤色にする
/**赤色の座席は選択可
 * 
 * 選択した座席は黄色になる(複数選択可)
 * 
 * 「使用する」ボタンを押すと、"using"+黄色になった座席の番号をString型にして、
 * Reserveform.javaへintentして、intent先に文字列された、選択した座席番号の文字列を渡す
 * (例: 2・11・15番の座席を選択して「使用する」ボタンを押した場合・・・文字列2|11|15|を渡す)
 * 
 */
package com.example.helloapp;

import android.support.v7.app.ActionBarActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("NewApi")
public class ReserveTable extends ActionBarActivity implements OnClickListener {
	
	static final int[] bid = {0,R.id.Button1,R.id.Button2,R.id.Button3,R.id.Button4,R.id.Button5,R.id.Button6,R.id.Button7,R.id.Button8,R.id.Button9,R.id.Button10,
			R.id.Button11,R.id.Button12,R.id.Button13,R.id.Button14,R.id.Button15,R.id.Button16,R.id.Button17,R.id.Button18,R.id.Button19,R.id.Button20,
			R.id.Button21,R.id.Button22,R.id.Button23,R.id.Button24,R.id.Button25,R.id.Button26,R.id.Button27,R.id.Button28,R.id.Button29,R.id.Button30,
			R.id.Button31,R.id.Button32,R.id.Button33,R.id.Button34,R.id.Button35,R.id.Button36,R.id.Button37,R.id.Button38,R.id.Button39,R.id.Button40};

	int checked[] = new int[bid.length-1];
	int redflag[] = new int[bid.length];
	int cnt=0,num;

	static Button[] bu = new Button[bid.length];
	//選択した座席番号から文字列を作って、それを返す再帰メソッド
	//（例: 1・4・11番の座席を指定した場合・・・文字列"1|4|11|"を返す）
	public String Sequencemake(int i){	// 3|15|11|・・・
		if(i!=cnt-1){
			return String.valueOf(checked[i]) + "|" + Sequencemake(i+1);
		}
		
		else{
			return String.valueOf(checked[i]) + "|";
		}
	}
	//本プログラムのxmlファイルを実行した後、座席をテーブル付近に設置するメソッド
		/**
		 * 各予約者情報に"person"以下に書かれている座席番号を赤にするflagを付け、その番号の座席を赤色にする
		 * 
		 * 座席番号が1-4、9-12、・・・の場合は、下向きの椅子の画像を付ける
		 * 座席番号が5-9、13-16、・・・の場合は、上向きの椅子の画像を付ける
		 * 
		 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rtable);
		
		
		for(int i=1;i<bid.length;i++){
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
			    if(line.indexOf("person") != -1 ||line.indexOf("using") != -1){
			    	in.readLine();
			    	line=in.readLine();
			    	if(line == null){
			    		break;
			    	}
			    	if(line.indexOf("person") == -1 && line.indexOf("using") == -1){
			    		num=Integer.parseInt(line);
			    		redflag[num]=1;
			    		bu[num].setBackgroundColor(Color.RED);
			    	}
			    }
			    
			    else{
			    	num=Integer.parseInt(line);
			    	redflag[num]=1;
			    	if(1<=num%8 && num%8<=4){
						bu[num].setBackgroundDrawable(getResources().getDrawable(R.drawable.redchair));
					}
					else{
						bu[num].setBackgroundDrawable(getResources().getDrawable(R.drawable.redchair_rev));
					}
				    line=in.readLine();
			    }
			}
		    in.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Reserveform.javaに文字列に直した予約番号を渡し、Reserveform.javaを実行するメソッド（「予約する」ボタンを押すと実行される）
	public void Reserve(View v){
		Intent intent = new Intent(this,Reserveform.class);
		if(cnt==0){
			TextView text = (TextView)this.findViewById(R.id.textView2);
	    	text.setText("予約したい座席を選択してください");
	    	text.setTextColor(android.graphics.Color.RED);
	    	return;
		}
	    intent.putExtra("Data",Sequencemake(0));
	    this.startActivity(intent);
	}
	
	//ホームに戻るメソッド（「ホームに戻る」ボタンを押すと実行される）
	public void ReturntoHome(View v){
		finish();
	}
	
	//選択した座席を黄色にするメソッド（座席を選択するたびに実行される）
	/**
	 *座席番号が1-4、9-12、・・・の場合は、下向きの黄色の椅子の画像を付ける
	 * 
	 * 座席番号が5-9、13-16、・・・の場合は、上向きの黄色の椅子の画像を付ける
	 */	
	public void onClick(View v){
		int ID = v.getId();
	    for(int i=1;i<bid.length;i++){
	    	if(ID == bid[i] && redflag[i]==0){
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
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.other, menu);
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
