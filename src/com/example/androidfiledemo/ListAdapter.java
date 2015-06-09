package com.example.androidfiledemo;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<String> titleList,msgList;

	public ListAdapter() {

	}

	public ListAdapter(Context context, ArrayList<String> titleList,ArrayList<String> msgList) {
		this.context = context;
		this.titleList = titleList;
		this.msgList = msgList;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return titleList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder = null;
		if (convertView == null) {
		holder = new ViewHolder();
		LayoutInflater mInflater = LayoutInflater.from(context);
		convertView = mInflater.inflate(R.layout.item, null);
		holder.iv_file = (ImageView) convertView.findViewById(R.id.iv_file);
		holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
		holder.tv_msg = (TextView) convertView.findViewById(R.id.tv_msg);
		convertView.setTag(holder);
		} else {
		holder = (ViewHolder) convertView.getTag();
		}

		holder.iv_file.setImageResource(R.drawable.ic_launcher);
		holder.tv_title.setText(titleList.get(position));
		holder.tv_msg.setText(msgList.get(position));
		// TODO Auto-generated method stub
		return convertView;
	}
	
	class ViewHolder{
		ImageView iv_file;
		TextView tv_title;
		TextView tv_msg;
		
	}
	

}
