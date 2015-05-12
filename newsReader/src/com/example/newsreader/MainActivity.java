package com.example.newsreader;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.newsreader.fab.FloatingActionButton;
import com.google.gson.GsonBuilder;

public class MainActivity extends Activity {
	private Context context;
	private ProgressDialog progressDialog;
	private ArrayList<News> newsArrayList;
	public static int width;
	NewsSubject newsSubject;
	ListView myListView;
	ActionBar actionbar;

	private String actionBarTitle;
	List<String> title, description, imageHref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myListView = (ListView) findViewById(R.id.list);
		getActionBar().setTitle(getString(R.string.app_name));
		context = getApplicationContext();
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		width = displaymetrics.widthPixels;

		init();
		title = new ArrayList<String>();
		description = new ArrayList<String>();
		imageHref = new ArrayList<String>();
		if (isConnected()) {
			new asyncConvert().execute();
		} else {
			Toast.makeText(context,
					getResources().getString(R.string.lost_connection),
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onPause() {
		super.onPause();
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
		progressDialog = null;
	}

	private void init() {
		FloatingActionButton fabButton = new FloatingActionButton.Builder(this)
				.withDrawable(getResources().getDrawable(R.drawable.refreshme))
				.withButtonColor(getResources().getColor(R.color.whiteish))
				.withGravity(Gravity.BOTTOM | Gravity.RIGHT)
				.withMargins(0, 0, 16, 16).create();

		fabButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isConnected()) {
					refresh();
				} else {
					Toast.makeText(context,
							getResources().getString(R.string.lost_connection),
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	protected void refresh() {
		title.clear();
		description.clear();
		imageHref.clear();
		myListView.setAdapter(null);
		new asyncConvert().execute();
	}

	public class asyncConvert extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(MainActivity.this);
			progressDialog.setCancelable(false);
			progressDialog.setMessage(getResources()
					.getString(R.string.loading));
			progressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... voids) {

			Reader reader = API.getData(getResources().getString(R.string.url));
			newsSubject = new GsonBuilder().create().fromJson(reader,
					NewsSubject.class);
			actionBarTitle = newsSubject.getTitle();
			newsArrayList = newsSubject.getNews();
			for (News topic : newsArrayList) {
				title.add(topic.getTitle());
				description.add(topic.getDescription());
				imageHref.add(topic.getImageHref());
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void aVoid) {
			super.onPostExecute(aVoid);
			if (progressDialog != null && progressDialog.isShowing()) {
				progressDialog.dismiss();
			}

			CustomListAdapter adapter = new CustomListAdapter(
					MainActivity.this, title, imageHref, description);

			getActionBar().setTitle(actionBarTitle);
			myListView.setAdapter(adapter);
		}
	}

	public boolean isConnected() {
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected())
			return true;
		else
			return false;
	}

}
