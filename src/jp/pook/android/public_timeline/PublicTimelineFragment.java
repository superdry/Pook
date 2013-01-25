package jp.pook.android.public_timeline;

import jp.pook.android.R;
import jp.pook.android.R.layout;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PublicTimelineFragment extends ListFragment {
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		String path = "/api/v1/quotes/public_timeline.json";

		GetQuoteTask task = new GetQuoteTask(this);
		task.execute(path);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.quote_list, container, false);
		return view;
	}

	@Override
	public void setEmptyText(CharSequence text) {
		TextView tv = (TextView) getListView().getEmptyView();
		tv.setText(text);
	}

}
