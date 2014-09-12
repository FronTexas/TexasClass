package com.example.texasclasss;




import com.example.texasclass.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;




public class MainActivity extends Activity {
	Button bAdd;
	Button bSub;
	TextView text;
	int counter = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bAdd = (Button) findViewById(R.id.bAdd);
		bSub = (Button) findViewById(R.id.bSub);
		text = (TextView) findViewById(R.id.tvDisplay);
		
		bAdd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				counter++;
				text.setText("Your total is " + counter);
				
			}
		});
		
		bSub.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				counter--;
				text.setText("Your total is " + counter);
			}
		});
		

	}
}

