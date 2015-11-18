//予約情報を入力する画面
/**はじめに、onCreateメソッドでintent元から受け取った座席番号を文字列に直したものを、int型の配列に直す
 * （例: intent元から受け取った文字列"1|10|15|22"→int型の配列cnum={1,10,15,22}にする）
 * 
 * edittextに名前・人数・電話番号を入力する
 * 
 * 「決定する」ボタンを押すと、Reservenum.txtから予約番号を呼び出し、インクリメントした予約番号（①）をReservenum.txtに書き込む。
 * 次に、①+入力された名前・人数・電話番号をRdatafile.txtに書き込み、①+指定した座席番号をRdatafile.txtに書き込む。
 * その後、ReserveComplete.javaへintentして、intent先に①+入力された名前・人数・電話番号の文字列を渡す
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
	int[] cnum = new int[100];	//座席番号が格納されている
	private String path = "Reservenum.txt";
	private String path2 = "Ndatafile.txt";
	private String path3 = "Rdatafile.txt";
	String name,number,address;


	//本プログラムのxmlファイルを開くとともに、intent元から受け取った座席番号を文字列に直したものをint型の配列に直すメソッド
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
	
	//sで指定した文字列が数字であるかを確認するメソッド
	//（数字でなければエラーを表示する）
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
	
	
	//Reservenum.txtに、現在の予約番号を上書きするメソッド
	public void addRnum(int n) throws IOException{
		FileOutputStream fis1 = this.openFileOutput(path, 0);
		BufferedWriter out1 = new BufferedWriter(new OutputStreamWriter(fis1));
		out1.write(n+"");
		out1.close();
	}
	
	//予約情報が書き込まれたかを確かめるメソッド
	//（書き込まれていなければ、エラーを表示する
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
	
	//予約情報を書き込むメソッド（「予約する」ボタンを押すと実行される）
	/**「予約する」ボタンを押した時の操作
	 * まず、Reservenum.txtに入っている予約番号をインクリメントし、それをReservenum.txtに上書きする（インクリメントした予約番号を①とする）
	 * 
	 * 次に、NDdatafile.txtに①+選択した座席番号を書き込む
	 * 
	 * そして、Rdatafile.txtに①+名前+人数+電話番号を書き込む
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
	
	//1つ前の画面に戻るメソッド（「戻る」ボタンを押すと実行される）
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
