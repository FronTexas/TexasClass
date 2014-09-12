package com.example.texasclasss;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import com.example.texasclass.R;


import android.app.Activity;
import android.os.Bundle;
import android.renderscript.Element;
import android.util.Log;

public class TryJsoup extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("Hello 2", "Check for ENTERING Jsoup " + " codeSS");
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jsoup_test);
		Connection.Response res = null;
		try {
			res = Jsoup.connect("https://utdirect.utexas.edu/").data("LOGON","fsk226","PASSWORDS","f123456717!").method(Method.POST).execute();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Map<String,String> loginCookies = res.cookies();
		
		org.jsoup.nodes.Document doc = null;
		try {
			doc = Jsoup.connect("https://utdirect.utexas.edu/apps/degree/audits/requests/history/").cookies(loginCookies).get();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Elements elements = doc.getElementsContainingText("B S");
		
		for(org.jsoup.nodes.Element e : elements){
			Log.i("Hello 1", "Check for Jsoup " + e.text() +" codeSS");
		}
	}
}
