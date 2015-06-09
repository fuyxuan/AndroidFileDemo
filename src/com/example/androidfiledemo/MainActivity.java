package com.example.androidfiledemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.content.Context;
import java.util.ArrayList;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ArrayList<String> titleList, msgList,readlineTitleList,readlineMsgList;
	private Button btnAdd;
	private ListAdapter listAdapter;
	private ListView listView;
	private EditText et_title,et_msg;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.context = this;
		findView();
		
		readFromSD();
		setAction();

	}

	private void findView() {
		btnAdd = (Button) findViewById(R.id.btnAddList);
		listView = (ListView) findViewById(R.id.listView);
		et_title = (EditText)findViewById(R.id.et_title);
		et_msg = (EditText)findViewById(R.id.et_msg);
	}

	



	private void setAction() {
		titleList = new ArrayList<String>();
		msgList = new ArrayList<String>();
		listAdapter = new ListAdapter(context, titleList, msgList);
		btnAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				titleList.add(et_title.getText().toString());
				msgList.add(et_msg.getText().toString());
				String str,str1 = et_title.getText().toString() , str2 =et_msg.getText().toString();
				Log.i("msg","et_title:"+str1.replaceAll(" ", "")+"   >>"+str2.replaceAll(" ", ""));
				
				//寫入
				try {
					if(!(str1.equals("") && str2.equals(""))){
						FileWriter fw = new FileWriter("/sdcard/output.txt", true);
						BufferedWriter bw = new BufferedWriter(fw); 
						bw.write(str1+" "+str2);
						bw.newLine();
						bw.close();
					}
					Log.i("msg", "success");
				} catch (IOException e) {
					Log.i("msg", "fail");
					e.printStackTrace();
				}
				
				//讀取
				readFromSD();
			}
		});

	}
	
	private void readFromSD() {
		
		try {
			readlineTitleList = new ArrayList<String>();
			readlineMsgList = new ArrayList<String>();
			FileReader fr = new FileReader("/sdcard/output.txt");
			BufferedReader br = new BufferedReader(fr);
			String readData = "";
			String temp = br.readLine(); // readLine()讀取一整行
			while (temp != null) {
				readData += temp+",";
				temp = br.readLine();
			}
				
				String[] AfterSplit = readData.split(",");
				
				for (int i = 0; i < AfterSplit.length; i++){
					String[] titleStrSplit = AfterSplit[i].split(" ");
					readlineTitleList.add((AfterSplit[i]).substring(0, AfterSplit[i].indexOf(" ")));
					readlineMsgList.add((AfterSplit[i]).substring(AfterSplit[i].indexOf(" ")+1));
				}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		listAdapter = new ListAdapter(context, readlineTitleList, readlineMsgList);
		listView.setAdapter(listAdapter);

	}
	
	private void wrireToSD() {

		
	}

}
