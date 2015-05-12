package com.example.newsreader;

import java.util.List;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String> {

	private final Activity context;
	private String[] title;
	private String[] description;
	private String[] imageURL;

	public CustomListAdapter(Activity context, List<String> title,
			List<String> imageHref, List<String> description) {
		super(context, R.layout.list_item, title);

		this.context = context;
		this.title = new String[title.size()];
		this.title = title.toArray(this.title);
		this.description = new String[description.size()];
		this.description = description.toArray(this.description);
		this.imageURL = new String[imageHref.size()];
		this.imageURL = imageHref.toArray(this.imageURL);
	}

	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.list_item, null, true);

		TextView txtTitle = (TextView) rowView.findViewById(R.id.title);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.itemImage);
		TextView extratxt = (TextView) rowView.findViewById(R.id.desc);
		

		if (TextUtils.isEmpty(title[position]) || TextUtils.isEmpty(description[position])) {
			txtTitle.setVisibility(View.GONE);
			extratxt.setVisibility(View.GONE);
			rowView.findViewById(R.id.arrow).setVisibility(View.GONE);
		} else {
			txtTitle.setText(title[position]);
			extratxt.setText(description[position]);
		}
		int w = MainActivity.width / 5;
		Picasso.with(context).load(imageURL[position]).resize(w, 0).into(imageView);

		return rowView;

	};
}