package jp.pook.android.public_timeline;

import java.util.List;

import jp.pook.android.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class PublicTimelineListFragment extends PublicTimelineFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.publictimeline_list, container,
				false);
		return view;
	}

	public void setAdapter(String json) {

		// アダプタを作成
		List<QuoteData> quotes = parseJson(json);
		QuoteAdapter adapter = new QuoteAdapter(this.getActivity(),
				R.layout.list, quotes);

		// アダプタを設定
		ListView listView = (ListView) this.getActivity().findViewById(
				R.id.listView);
		listView.setAdapter(adapter);

	}
}
