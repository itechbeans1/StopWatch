package com.example.stopw;

import java.util.ArrayList;

import android.R.array;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button startButton;
	private Button pauseButton;

	private TextView timerValue;

	private long startTime = 0L;

	private Handler customHandler = new Handler();

	long timeInMilliseconds = 0L;
	long timeSwapBuff = 0L;
	long updatedTime = 0L;

	ListView listView;
	ArrayAdapter<String> adapter;
	CustomListAdapter listAdapter;
	ArrayList<String> arrayList;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		//getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.customtitle);	
		
		timerValue = (TextView) findViewById(R.id.timerValue);

		startButton = (Button) findViewById(R.id.startButton);

		startButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				startTime = SystemClock.uptimeMillis();
				customHandler.postDelayed(updateTimerThread, 0);

			}
		});

		pauseButton = (Button) findViewById(R.id.pauseButton);

		pauseButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {

				timeSwapBuff += timeInMilliseconds;
				customHandler.removeCallbacks(updateTimerThread);
				Log.e("Added in array", timerValue.getText().toString());
				arrayList.add(timerValue.getText().toString());
				listAdapter.notifyDataSetChanged();
			}
		});

		arrayList = new ArrayList<String>();
		arrayList.add("hiiiii");
		listAdapter = new CustomListAdapter(this, arrayList );
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
		listView = (ListView) findViewById(R.id.stopListView);
		listView.setAdapter(listAdapter);
		
	}

	private Runnable updateTimerThread = new Runnable() {

		public void run() {
			//Log.e("-------","------");
			timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

			updatedTime = timeSwapBuff + timeInMilliseconds;

			int secs = (int) (updatedTime / 1000);
			int mins = secs / 60;
			secs = secs % 60;
			int milliseconds = (int) (updatedTime % 1000);
			timerValue.setText("" + mins + ":"
					+ String.format("%02d", secs) + ":"
					+ String.format("%03d", milliseconds));
			customHandler.postDelayed(this, 0);
		}

	};
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
	
		if(item.getItemId() == R.id.reset){
			Toast.makeText(MainActivity.this, "You have clicked reset button", Toast.LENGTH_LONG).show();
			customHandler.removeCallbacks(updateTimerThread);
			arrayList.clear();
			listAdapter.notifyDataSetChanged();
			//customHandler.postDelayed(updateTimerThread, 0);
			startTime = SystemClock.uptimeMillis();
			Log.e("=====",""+startTime);
			//customHandler.postDelayed(updateTimerThread, 0);
			timerValue.setText("00:00:000");
		}else{
			Toast.makeText(MainActivity.this, "You have clicked Color button", Toast.LENGTH_LONG).show();
		}
		
		return false;
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

}
