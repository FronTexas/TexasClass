package com.example.texasclasss;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.jsoup.Jsoup;

import com.example.texasclass.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Register_classes extends Activity implements View.OnClickListener {

	String[] list;
	BufferedReader input2;
	BufferedReader input;
	int counter = 0;
	TextView t1;
	TextView t2;
	int uniquedegree;
	ArrayList<Integer> dateRemove = new ArrayList<Integer>();
	TextView[] tv_degreeName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("TO RUN", "TRQ || getting into RegisterClasses ");
		AbsoluteLayout absolute_parent;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_class);
		absolute_parent = (AbsoluteLayout) findViewById(R.id.absolute_parent);
		RelativeLayout textarrow_layout = (RelativeLayout) findViewById(R.id.linear_layout_for_text_and_arrow);
		t1 = (TextView) findViewById(R.id.Choose_audit);
		initTypeface(t1);

		ArrayList<String> degreeType = new ArrayList<String>();
		ArrayList<String> auditDate = new ArrayList<String>();
		ArrayList<String> degreePercentage = new ArrayList<String>();
		ArrayList<Integer> degreeId = new ArrayList<Integer>();

		try {
			degreeType = getDegreeType();
		} catch (IOException e) {

			e.printStackTrace();
		}

		Log.i("TO RUN", "TRQ || getting out from getDegreType ");
		Log.i("TO RUN", "TRQ || degreeType = " + degreeType.toString());

		try {
			auditDate = getDate(dateRemove);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			degreeId = getDegreeId(dateRemove, degreeType);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			degreePercentage = getDegreePercentage(degreeType);
		} catch (IOException e) {
			e.printStackTrace();
		}

		tv_degreeName = new TextView[degreeType.size()];
		ImageView[] iv = new ImageView[degreeType.size()];
		TextView[] tv_date = new TextView[degreeType.size()];
		TextView[] tv_degreePercentage = new TextView[degreeType.size()];
		TextView[] tv_completed = new TextView[degreeType.size()];
		TextView[] tv_blankspace = new TextView[degreeType.size()];
		ImageView[] iv_update = new ImageView[degreeType.size()];

		String pngName = "png_next_arrow4";
		String pngUpdateName = "png_update_logo";
		int ArrowResource = getResources().getIdentifier(pngName, "drawable",
				getPackageName());
		int Update_image = getResources().getIdentifier(pngUpdateName,
				"drawable", getPackageName());

		for (int i = 0; i < tv_completed.length; i++) {
			tv_completed[i] = new TextView(getApplicationContext());
			String tv_completed_id = "888" + i;
			int int_tv_completed_id = Integer.parseInt(tv_completed_id);
			tv_completed[i].setId(int_tv_completed_id);
			tv_completed[i].setText("Completed");
			tv_completed[i].setTextColor(Color.rgb(0, 0, 0));
			initTypeface(tv_completed[i]);
		}

		for (int i = 0; i < tv_blankspace.length; i++) {
			tv_blankspace[i] = new TextView(getApplicationContext());
			String tv_blankspace_id = "9990" + i;
			int int_tv_blankspace_id = Integer.parseInt(tv_blankspace_id);
			tv_blankspace[i].setId(int_tv_blankspace_id);
			tv_blankspace[i].setText(" ");
		}

		for (int i = 0; i < iv_update.length; i++) {
			iv_update[i] = new ImageView(getApplicationContext());
			iv_update[i].setImageResource(Update_image);
			String iv_update_id = "2323" + i;
			iv_update[i].setId(Integer.parseInt(iv_update_id));
		}

		for (int i = 0; i < degreeType.size(); i++) {
			RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);

			RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);

			RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);

			RelativeLayout.LayoutParams params4_forTVDATE = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);

			RelativeLayout.LayoutParams params5_forTVDATE = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);

			RelativeLayout.LayoutParams params6_forTVPERCENTAGE = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);

			RelativeLayout.LayoutParams params7_forTVPERCENTAGE = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);

			RelativeLayout.LayoutParams params8_forTVCOMPLETED = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);

			RelativeLayout.LayoutParams params9_forTVCOMPLETED = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);

			RelativeLayout.LayoutParams params10_forTVBLANK = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);

			RelativeLayout.LayoutParams params11_forTVBLANK = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);

			RelativeLayout.LayoutParams params12_forIVUPDATE = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);

			RelativeLayout.LayoutParams params13_forIVUPDATE = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);

			tv_degreeName[i] = new TextView(getApplicationContext());
			tv_date[i] = new TextView(getApplicationContext());
			iv[i] = new ImageView(getApplicationContext());
			tv_degreePercentage[i] = new TextView(getApplicationContext());
			// String TVid = "555" + i;
			// int int_TVid = Integer.parseInt(TVid);
			tv_degreeName[i].setId(degreeId.get(i));
			tv_degreeName[i].setText(degreeType.get(i));
			tv_degreeName[i].setTextSize(25);
			tv_degreeName[i].setTextColor(Color.rgb(102, 102, 102));
			iv[i].setImageResource(ArrowResource);
			tv_date[i].setText("Last Updated: " + auditDate.get(i));
			String TVDATE_id = "666" + i;
			int int_TVDATE_id = Integer.parseInt(TVDATE_id);
			tv_date[i].setId(int_TVDATE_id);
			tv_date[i].setTextColor(Color.rgb(153, 153, 153));
			initTypeface(tv_degreeName[i]);
			initTypeface(tv_date[i]);

			String TVPERCENTAGE_id = "777" + i;
			int int_TVPERCENTAGE_id = Integer.parseInt(TVPERCENTAGE_id);
			tv_degreePercentage[i].setId(int_TVPERCENTAGE_id);
			tv_degreePercentage[i].setText(degreePercentage.get(i));
			tv_degreePercentage[i].setTextSize(25);
			tv_degreePercentage[i].setTextColor(Color.rgb(32, 185, 233));
			initTypeface(tv_degreePercentage[i]);

			if (i == 0) {
				textarrow_layout.addView(tv_degreeName[i]); // adding degree
															// type text
				params1.addRule(RelativeLayout.RIGHT_OF,
						tv_degreeName[i].getId());
				params4_forTVDATE.addRule(RelativeLayout.BELOW,
						tv_degreeName[i].getId());
				params6_forTVPERCENTAGE.addRule(RelativeLayout.BELOW,
						tv_date[i].getId());
				params8_forTVCOMPLETED.addRule(RelativeLayout.BELOW,
						tv_degreePercentage[i].getId());
				params10_forTVBLANK.addRule(RelativeLayout.BELOW,
						tv_completed[i].getId());
				params12_forIVUPDATE.addRule(RelativeLayout.RIGHT_OF,
						tv_degreePercentage[i].getId());
				params12_forIVUPDATE.addRule(RelativeLayout.BELOW,
						tv_date[i].getId());
				params12_forIVUPDATE.addRule(RelativeLayout.ABOVE,
						tv_completed[i].getId());

				textarrow_layout.addView(iv[i], params1);
				textarrow_layout.addView(tv_date[i], params4_forTVDATE);
				textarrow_layout.addView(tv_degreePercentage[i],
						params6_forTVPERCENTAGE);
				textarrow_layout.addView(tv_completed[i],
						params8_forTVCOMPLETED);
				textarrow_layout.addView(tv_blankspace[i], params10_forTVBLANK);
				textarrow_layout.addView(iv_update[i], params12_forIVUPDATE);

			} else {
				params2.addRule(RelativeLayout.BELOW,
						tv_blankspace[i - 1].getId());
				params3.addRule(RelativeLayout.RIGHT_OF,
						tv_degreeName[i].getId());
				params3.addRule(RelativeLayout.ALIGN_TOP,
						tv_degreeName[i].getId());
				params3.addRule(RelativeLayout.BELOW, iv[i - 1].getId());
				params5_forTVDATE.addRule(RelativeLayout.BELOW,
						tv_degreeName[i].getId());
				params7_forTVPERCENTAGE.addRule(RelativeLayout.BELOW,
						tv_date[i].getId());
				params9_forTVCOMPLETED.addRule(RelativeLayout.BELOW,
						tv_degreePercentage[i].getId());
				params11_forTVBLANK.addRule(RelativeLayout.BELOW,
						tv_completed[i].getId());
				params13_forIVUPDATE.addRule(RelativeLayout.RIGHT_OF,
						tv_degreePercentage[i].getId());
				params13_forIVUPDATE.addRule(RelativeLayout.BELOW,
						tv_date[i].getId());
				params13_forIVUPDATE.addRule(RelativeLayout.ABOVE,
						tv_completed[i].getId());

				textarrow_layout.addView(tv_degreeName[i], params2);
				textarrow_layout.addView(iv[i], params3);
				textarrow_layout.addView(tv_date[i], params5_forTVDATE);
				textarrow_layout.addView(tv_degreePercentage[i],
						params7_forTVPERCENTAGE);
				textarrow_layout.addView(tv_completed[i],
						params9_forTVCOMPLETED);
				textarrow_layout.addView(tv_blankspace[i], params11_forTVBLANK);
				textarrow_layout.addView(iv_update[i], params13_forIVUPDATE);

			}

		}
		// for the degreename to be clickable
		for (int i = 0; i < tv_degreeName.length; i++) {
			tv_degreeName[i].setOnClickListener(this);
		}
	}

	int removeFromDate = 0;

	private void initTypeface(TextView t) {

		Typeface jesaya = Typeface.createFromAsset(getAssets(),
				"fonts/jesaya_free.ttf");

		t.setTypeface(jesaya);
	}

	private ArrayList<String> getDegreeType() throws IOException,
			FileNotFoundException {
		Log.i("hello 10", "TAIZZZZZ");
		ArrayList<String> degreeType = new ArrayList<String>();

		String line2 = "";
		String line3 = "";

		int counter2 = 0;
		int isSameDegreeType = 0;
		int j = 0;
		int uniqueDegree = 0;
		boolean ArrayIsFilled = false;

		try {
			input2 = new BufferedReader(new InputStreamReader(getAssets().open(
					"web.txt")));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException E) {
			E.printStackTrace();
		}
		// tryJsoup();
		while (input2.readLine() != null) {
			line2 = input2.readLine();
			if (line2.length() != 0) {
				if (line2.contains("B S") || line2.contains("B B A")
						|| line2.contains("B A")) {
					Log.i("check 8", line2 + "ANJING");
					for (int i = 0; i < line2.length(); i++) {
						if (Character.isWhitespace(line2.charAt(i))) {
							counter2++;

						} else {
							break;
						}
					}

					line3 = line2.substring(counter2); // removing space
					if (ArrayIsFilled) {
						for (int i = 0; i < degreeType.size(); i++) {
							Log.i("check 7", line3 + "hihi");
							if (line3.equals(degreeType.get(i))) {
								isSameDegreeType++;
								dateRemove.add(removeFromDate);
							}
						}
					}
					Log.i("Check 6", line3 + "nyampe hehehehe");
					if (isSameDegreeType == 0) {
						degreeType.add(line3);
						Log.i("Check 9", "nyampe hehehehe");
						Log.i("Check 10", degreeType.toString()
								+ "nyampe hehehehe");
						ArrayIsFilled = true;
						uniqueDegree++;
					}

					isSameDegreeType = 0;
					counter2 = 0;
					removeFromDate++;
				}
			}
			Log.i("Check 11", degreeType.toString() + "nyampe hehehehe");
		}
		Log.i("Check 8", degreeType.toString() + " nyampe hehehehe");
		return degreeType;
	}

	private ArrayList<String> getDate(ArrayList<Integer> dateToRemove)
			throws FileNotFoundException, IOException {
		Log.i("hello 10", dateToRemove.toString() + "TAIXXXXXXXX");
		ArrayList<String> tempList = new ArrayList<String>();
		ArrayList<String> dateList = new ArrayList<String>();
		BufferedReader input = new BufferedReader(new InputStreamReader(
				getAssets().open("web.txt")));
		int counter = 0;
		while (input.readLine() != null) {
			String line2 = input.readLine();
			if (line2.contains("[degaudt-634]")) {
				while (line2.contains("/2013") == false) { // really fucking
															// fragile, need to
															// change the algo
															// later
					line2 = input.readLine();
				}
				for (int i = 0; i < line2.length(); i++) {
					if (Character.isWhitespace(line2.charAt(i))) {
						counter++;
					} else {
						break;
					}
				}
				tempList.add(line2.substring(counter));
				counter = 0;
			}
		}
		for (int i = 1; i < tempList.size(); i++) {
			dateList.add(tempList.get(i));
		}
		for (int i = 0; i < dateToRemove.size(); i++) {

			dateList.set(dateToRemove.get(i), "jerk");
		}
		for (int i = 0; i < dateList.size(); i++) {
			if (dateList.get(i).contains("jerk")) {
				dateList.remove(i);
			}
		}
		for (int i = 0; i < dateList.size(); i++) {
			if (dateList.get(i).contains("jerk")) {
				dateList.remove(i);
			}
		}
		return dateList;
	}

	private ArrayList<String> getDegreePercentage(ArrayList<String> degreeType)
			throws IOException {
		Log.i("Hello 1", "To check if its getting into this method" + " CodeYY");
		ArrayList<String> percentage = new ArrayList<String>();

		for (int i = 0; i < degreeType.size(); i++) {
			String filename = degreeType.get(i) + ".txt";
			BufferedReader webInput = new BufferedReader(new InputStreamReader(
					getAssets().open(filename)));

			String line2 = "";
			String line3 = "";

			int whiteSpace_counter = 0;
			while (webInput.readLine() != null) {
				line2 = webInput.readLine();
				if (line2.length() != 0) {
					if (line2.contains("<span style")) {
						Log.i("Hello 2", "To check for line2 = " + line2
								+ " And filename " + filename + " CodeYY");
						int i1 = 0;
						while (line2.charAt(i1) != '<') {
							whiteSpace_counter++;
							i1++;
						}
						Log.i("Hello 4", "To check for line2(again)  = "
								+ line2 + " and whitespacecounter = "
								+ whiteSpace_counter + " CodeYY");
						line3 = line2.substring(whiteSpace_counter); // removing
																		// space
						Log.i("Hello 3", "To check for line3 = " + line3
								+ " CodeYY");
						percentage.add(line3.substring(20, 23));
						Log.i("Hello 5",
								"To check for line3 substring 20-23 = "
										+ line3.substring(20, 23) + " CodeYY");
						whiteSpace_counter = 0;
					}
				}
			}
			Log.i("Hello 6", "To check is it getting out from while loop"
					+ " CodeYY");
		}
		Log.i("Hello 7", "To check percentage " + percentage.toString()
				+ " CodeYY");
		return percentage;
	}

	private ArrayList<Integer> getDegreeId(ArrayList<Integer> dateToRemove,
			ArrayList<String> degreeType) throws IOException {
		// 1.to make arraylist containing all of the Audti ID

		try {
			input2 = new BufferedReader(new InputStreamReader(getAssets().open(
					"Audit Results Listing - IDA 2.0.txt")));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException E) {
			E.printStackTrace();
		}

		String input = "";
		while (input2.read() != -1) {
			input += input2.readLine();
		}
		org.jsoup.nodes.Document doc = Jsoup.parse(input);

		org.jsoup.select.Elements e = doc.getAllElements();

		String parsed = "";

		parsed = e.text();
		org.jsoup.nodes.Document doc_parsed = Jsoup.parse(parsed);

		org.jsoup.select.Elements e_theOneOh = doc_parsed
				.getElementsContainingOwnText("100000");
		ArrayList<Integer> degreeId = new ArrayList<Integer>();
		ArrayList<Integer> degreeId_final = new ArrayList<Integer>();

		int data_int = 0;
		for (org.jsoup.nodes.Element e1 : e_theOneOh) {
			String regex = "[0-9]+";
			String data = e1.text();
			if (data.matches(regex)) {
				data_int = Integer.parseInt(data.substring(6));
				degreeId.add(data_int);
			}
		}
		for (int i = 0; i < dateToRemove.size(); i++) {

			degreeId.set(dateToRemove.get(i), 999);
		}
		for (int i = 0; i < degreeId.size(); i++) {
			if (degreeId.get(i) == 999) {
				degreeId.remove(i);
			}
		}
		for (int i = 0; i < degreeId.size(); i++) {
			if (degreeId.get(i) == 999) {
				degreeId.remove(i);
			}
		}
		for (int i = 0; i < degreeType.size(); i++) {
			degreeId_final.add(degreeId.get(i));
		}

		Log.i("Hello ", "To check dID final: " + degreeId_final);
		return degreeId_final;
	}

	@Override
	public void onClick(View view) {
		Log.i("Hello 2", "To check nyampe di onclick");
		//Intent TOC_start = new Intent(this,Type_of_classes.class);
		Log.i("Hello 1", "To check view.getId " + view.getId() + "codeIDID");
		//TOC_start.putExtra("DegreeId",view.getId());
		//startActivity(TOC_start);

		Intent myIntent = new Intent(Register_classes.this,
				Type_of_classes.class);
		myIntent.putExtra("DegreeId", "" + view.getId()); //Optional parameters
		Register_classes.this.startActivity(myIntent);
	}

	/*
	 * private void tryJsoup() throws IOException, ClientProtocolException { //
	 * This will get you the response.
	 * 
	 * DefaultHttpClient httpClient = new DefaultHttpClient(); HttpPost httpPost
	 * = new HttpPost("https://utdirect.utexas.edu/");
	 * 
	 * List<BasicNameValuePair> nameValuePairs = new
	 * ArrayList<BasicNameValuePair>(); nameValuePairs.add(new
	 * BasicNameValuePair("LOGON", "fsk226")); nameValuePairs.add(new
	 * BasicNameValuePair("PASSWORDS", "f123456717!"));
	 * 
	 * httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	 * 
	 * // Execute HTTP Post Request HttpResponse response =
	 * httpClient.execute(httpPost);
	 * 
	 * if (response.getStatusLine().getStatusCode() < 400) {
	 * 
	 * String cookie = response.getFirstHeader("Set-Cookie").getValue(); // get
	 * the contacts page HttpGet getContacts = new HttpGet(
	 * "https://utdirect.utexas.edu/apps/degree/audits/requests/history/");
	 * getContacts.setHeader("Cookie", cookie); HttpResponse response2 =
	 * httpClient.execute(getContacts);
	 * 
	 * InputStream ins = response2.getEntity().getContent(); BufferedReader in =
	 * new BufferedReader(new InputStreamReader(ins));
	 * 
	 * String inputLine; while ((inputLine = in.readLine()) != null) {
	 * Log.d("Ngehe", "asdf ngehe " + inputLine); }
	 * 
	 * in.close(); } else { Log.d("Ngehe2", "Response error: " +
	 * response.getStatusLine().getStatusCode()); }
	 * 
	 * }
	 */

}
