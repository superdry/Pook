package jp.pook.android.public_timeline;

import java.util.List;

import com.loopj.android.image.SmartImageView;

import jp.pook.android.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class QuoteAdapter extends ArrayAdapter<QuoteData> {
	private LayoutInflater mLayoutInflater;
	private int resourceId;

	public QuoteAdapter(Context context, int textViewResourceId,
			List<QuoteData> objects) {
		super(context, textViewResourceId, objects);
		this.resourceId = textViewResourceId;
		mLayoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 特定の行(position)のデータを得る
		QuoteData item = (QuoteData) getItem(position);

		// convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
		if (null == convertView) {
			convertView = mLayoutInflater.inflate(resourceId, null);
		}


		// CustomDataのデータをViewの各Widgetにセットする
		SmartImageView imageView;
		imageView = (SmartImageView) convertView.findViewById(R.id.bookImage);
		if (null != imageView) {
			imageView.setImageUrl(item.getImageUrl());
		}

		TextView textView;
		textView = (TextView) convertView.findViewById(R.id.bookTitle);
		if (null != textView) {
			textView.setText(item.getBookName());
		}

		textView = (TextView) convertView.findViewById(R.id.quote);
		if (null != textView) {
			textView.setText(item.getQuote());
		}

		return convertView;
	}
}
