package com.example.stopw;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class StopWatchActivity extends Activity {

	
	Button startButton;
	View view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stop_watch);

		view = this.getWindow().getDecorView();
	    view.setBackgroundColor(Color.BLACK);
		startButton = (Button) findViewById(R.id.startStopWatch);
		startButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				createDialogToSetTime();
				
				
				new CountDownTimer(30000, 1000) {

				     public void onTick(long millisUntilFinished) {
				         //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
				    	 	Log.e("Seconds Remaining", ""+ millisUntilFinished / 1000);
				    	 	
				     }

				     public void onFinish() {
				         //mTextField.setText("done!");
				    	 	Log.e("onFinish","Done");
				     }
				  }.start();
			}
		});
	}

	void createDialogToSetTime(){
		Dialog dialog = new Dialog(StopWatchActivity.this);
		//dialog.setContentView(layoutResID);
		
		int red = 255; //i.e. FF
	 	int green = 0;
	 	int stepSize = 1;
	 	while(green < 255)
	 	{
	 	    green += stepSize;
	 	    if(green > 255) { green = 255; }
	 	    //output(red, green, 0); //assume output is function that takes RGB
	 	   Log.e("-----","-------");
	 	  view.setBackgroundColor(Color.rgb(red, green, 0));
	 	}
	 	while(red > 0)
	 	{
	 	    red -= stepSize;
	 	    if(red < 0) { red = 0; }
	 	    //output(red, green, 0); //assume output is function that takes RGB
	 	   Log.e("-----","-------");
	 	   view.setBackgroundColor(Color.rgb(red, green, 0));
	 	}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stop_watch, menu);
		return true;
	}

}
