package com.example.texasclasss;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;

import com.example.texasclass.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Type_of_classes extends Activity {
	// TOC = Type Of Class
	TextView[] TOC_parent;
	TextView[] TOC_child;

	// Bundle extras = getIntent().getExtras();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.type_of_classes);
		Intent intent = getIntent();
		String degID = intent.getStringExtra("DegreeId");
		Log.i("Hello 1", "To check if its moved to TOC class");
		TextView tv_EstimatedDeg = (TextView) findViewById(R.id.Estimated_deg_text);

		RelativeLayout main_layout = (RelativeLayout) findViewById(R.id.main_layout);
		initTypeface(tv_EstimatedDeg);

		ArrayList<String> TOC_parent_list = new ArrayList<String>();
		ArrayList<String> TOC_child_list = new ArrayList<String>();

		try {
			TOC_child_list = getTypeOfClass(degID);
		} catch (IOException e) {
			e.printStackTrace();
		}
		TOC_parent_list = countDifferentTypeOfClass(TOC_child_list);

		Log.i("Hello 1",
				"To check TOC_child_list :" + TOC_child_list.toString());
		TOC_parent = new TextView[TOC_parent_list.size()];
		TOC_child = new TextView[TOC_child_list.size() / 2];

		RelativeLayout.LayoutParams params1_forTOCParent1 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		RelativeLayout.LayoutParams params2_forTOCChildren = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		int indexFor_List_TypeOfClassParent = 0;

		for (int i = 0; i < TOC_parent.length; i++) {
			Log.i("Hello 3",
					"xixixi To check if its get IN from toc_parent for loop");
			// 1.Setting the text for TOC_parent
			// 2.Put all of the TOC_parent into the main_layout

			String TOCpID = "111" + i;
			TOC_parent[i] = new TextView(getApplicationContext());
			TOC_parent[i].setId(Integer.parseInt(TOCpID));
			TOC_parent[i].setText(TOC_parent_list.get(i));

			if (i == 0)
				main_layout.addView(TOC_parent[i]);
			else {
				params1_forTOCParent1.addRule(RelativeLayout.BELOW,
						TOC_parent[i - 1].getId());
				main_layout.addView(TOC_parent[i], params1_forTOCParent1);
			}

		}

		Log.i("Hello 2", "To check if its get out from toc_parent for loop");

		int k = 0;
		for (int i = 1; i < TOC_child.length; i += 2) {
			Log.i("Hello 4",
					"xexexe To check if its get IN from toc_child for loop");
			// 1.Setting the text for TOC_child
			TOC_child[k] = new TextView(getApplicationContext());
			TOC_child[k].setText(TOC_child_list.get(i));
			k++;
		}
		Log.i("Hello 3", "To check if its get out from toc_child for loop");
		k = 0;
		for (int i = 0; i < TOC_child.length; i++) {
			// 1.Gives unique Id to child that is part to unique parent
			for (int j = 1; j < TOC_child_list.size(); j += 2) {
				// Log.i("Hello 8",
				// "xsxs To check if tis getting into toc_child id AND toc_parent_list "
				// +
				// "size :" + TOC_parent_list.size());
				for (int innerJ = 0; innerJ < TOC_parent_list.size(); innerJ++) {

					Log.i("Hello 7",
							"xsxs To check TOC_child_list :"
									+ TOC_child_list.get(j - 1)
									+ " || TOC_parent_list :"
									+ TOC_parent_list.get(innerJ));
					if (TOC_child_list.get(j - 1).equals(
							TOC_parent_list.get(innerJ))) {

						Log.i("Hello 6", "xsxs To check inner toc_child2 loop ");
						int TOCcIDX = j;
						String TOCcIDS = "" + TOCcIDX + 8 + j;
						Log.i("Hello 9", "xsxs To check for TOCcIDS  :"
								+ TOCcIDS);
						TOC_child[j].setId(Integer.parseInt(TOCcIDS));
					}
				}

			}
			Log.i("Hello 7", "xsxs  ");

		}
		Log.i("Hello 4", "To check if its get out from toc_child2 for loop");

		for (int i = 0; i < TOC_child.length; i++) {
			// 1. add the child to the corresponding parent based on theid id

			Log.i("Hello 5", "To check for PRE PRE tempIdCheckSub :"
					+ TOC_child[i].getId());
			String tempIdCheck = "" + TOC_child[i].getId();
			Log.i("Hello 5", "To check for PRE tempIdCheckSub :" + tempIdCheck);
			String tempIdCheckSub = tempIdCheck.substring(0, 1);
			Log.i("Hello 6", "To check for tempIdCheckSub :" + tempIdCheckSub);
			int tempIdCheck2 = Integer.parseInt(tempIdCheckSub);
			if (tempIdCheck2 < TOC_parent.length) {
				params2_forTOCChildren.addRule(RelativeLayout.BELOW,
						TOC_parent[tempIdCheck2].getId());
				params2_forTOCChildren.addRule(RelativeLayout.ABOVE,
						TOC_parent[tempIdCheck2 + 1].getId());
				main_layout.addView(TOC_child[i], params2_forTOCChildren);
				// params2_forTOCChildren.removeRule(0); requires higher API
				// params2_forTOCChildren.removeRule(1); requires higher API
			} else {
				params2_forTOCChildren.addRule(RelativeLayout.BELOW,
						TOC_parent[tempIdCheck2].getId());
				main_layout.addView(TOC_child[i], params2_forTOCChildren);
			}

		}
		Log.i("Hello 5", "To check if its get out from toc_child3 for loop");

	}

	private void initTypeface(TextView t) {

		Typeface jesaya = Typeface.createFromAsset(getAssets(),
				"fonts/jesaya_free.ttf");

		t.setTypeface(jesaya);
	}

	private ArrayList<String> getTypeOfClass(String DegID) throws IOException {
		File path = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File input = new File(path, "100000617719.txt");

		// File input = new File(
		// "C:/Users/Fron/Documents/An Education/Coursera/Android Development U Illnois/TexasClazz/assets/results_ida2.htm");
		org.jsoup.nodes.Document doc = Jsoup.parse(input, "UTF-8", "");
		org.jsoup.select.Elements e = doc.getAllElements();
		ArrayList<String> type_of_classes = new ArrayList<String>();
		ArrayList<String> type_of_classes_filtered = new ArrayList<String>();
		String html = "";

		html = e.text();
		Log.i("Hello 2", "To check for e.text: " + e.text());

		Log.i("Hello 4", "To check for html: " + html);
		org.jsoup.nodes.Document doc2 = Jsoup.parse(html);
		org.jsoup.select.Elements tds = doc2.select("td.status");
		int l = 0;
		String type = "";
		type_of_classes.add("initial");
		for (org.jsoup.nodes.Element td : tds) {
			if (!td.parent().parent().previousElementSibling().text()
					.contains("GPA")) {
				type = td.parent().parent().previousElementSibling().children()
						.select("a.alternate-color-link").text();
				Log.i("Hello 3", "To check for type: " + type);
				if (type.equals("")) {
					type = td.parent().parent().previousElementSibling()
							.children().select("th.section_title").text();
				}
			}

			if (type != type_of_classes.get(l)) {
				if (l == 0)
					type_of_classes.remove(0);
				type_of_classes.add(type);
			}
			type_of_classes.add(td.nextElementSibling().text());
			l++;
		}
		Log.i("Hello 5", "To check if its get out from for loopxx");
		l = 0;
		int j = 0;
		while (!type_of_classes.get(j).contains("GPA")) {
			type_of_classes_filtered.add(type_of_classes.get(j));
			j++;
		}
		Log.i("Hello 7", "To check for TOC_FILTERED :"
				+ type_of_classes_filtered.toString());
		Log.i("Hello 6", "To check if its get out from for whileloopxx");
		type_of_classes_filtered.remove(type_of_classes_filtered.size() - 1);

		Log.i("Hello 8",
				"To check type o c :" + type_of_classes_filtered.toString());
		return type_of_classes_filtered;
	}

	private ArrayList<String> countDifferentTypeOfClass(
			ArrayList<String> TOC_child_list) {
		ArrayList<String> differentType = new ArrayList<String>();

		Log.i("Hello 0", "To check for T0C_child_list in countDiff :"
				+ TOC_child_list.toString());
		int k = 0;
		for (int i = 0; i < TOC_child_list.size(); i += 2) {
			if (k == 0) {
				differentType.add(TOC_child_list.get(i));
				k++;
			} else {
				if (!differentType.get(k - 1).equals(TOC_child_list.get(i))) {
					differentType.add(TOC_child_list.get(i));
					k++;
				}

			}
		}
		Log.i("Hello 1",
				"To check if its getting out from the for loop CODEZZZX");
		Log.i("Hello 2",
				"To check differentType CODEZZZX " + differentType.toString());
		return differentType;
	}

}
