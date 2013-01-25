package jp.pook.android.public_timeline;

import jp.pook.android.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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
		SimpleAdapter adapter = new SimpleAdapter(this.getActivity(),
				super.parseJson(json), android.R.layout.simple_list_item_2,
				new String[] { "content", "book_name" }, new int[] {
						android.R.id.text1, android.R.id.text2 });
		// アダプタを設定
		ListView listView = (ListView) this.getActivity().findViewById(
				R.id.listView);
		listView.setAdapter(adapter);

	}
}
