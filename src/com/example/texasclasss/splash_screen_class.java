package com.example.texasclasss;


import com.example.texasclass.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class splash_screen_class extends Activity {
	ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("TO RUN", "TRQ || getting into splashScreen ");

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);

		img = (ImageView) findViewById(R.id.splashScreen);

		Thread timer = new Thread() {
			private ActivityOptions options;

			public void run() {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {

					Intent startRegisterClasses = new Intent(
							"com.example.texasclazz.REGISTERCLASSES");
					startActivity(startRegisterClasses);
				}

			}

		};
		timer.start();
	}

}

				
