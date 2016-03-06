package com.example.stopw;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String>{

	Context mContext;
	ArrayList<String> arrayList;
	public CustomListAdapter(Context context, 
			List<String> objects) {
		super(context, android.R.layout.simple_list_item_1, objects);
		mContext = context;
		arrayList = (ArrayList<String>) objects;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayList.size();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
		LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.customadapter,null);
		if(position % 2 == 0){
			layout.setBackgroundColor(Color.parseColor("#82CAFA"));
		}else{
			layout.setBackgroundColor(Color.parseColor("#F9966B"));
		}
		
		TextView serialNo = (TextView) layout.findViewById(R.id.serialNo);
		serialNo.setText(""+position);
		TextView lapText  = (TextView) layout.findViewById(R.id.lapText);
		lapText.setText(arrayList.get(position));
		
		
		return layout;
	}
	

}
