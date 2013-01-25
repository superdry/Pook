package jp.pook.android.public_timeline;

import jp.pook.android.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class PublicTimelineGridFragment extends PublicTimelineFragment {

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

		View view = inflater.inflate(R.layout.publictimeline_grid, container, false);
		return view;
	}

	public void setAdapter(String json) {

		// アダプタを作成
		SimpleAdapter adapter = new SimpleAdapter(this.getActivity(),
				parseJson(json), android.R.layout.simple_list_item_2,
				new String[] { "content", "book_name" }, new int[] {
						android.R.id.text1, android.R.id.text2 });
		// アダプタを設定
		GridView gallery = (GridView) this.getActivity().findViewById(
				R.id.gridView);
		gallery.setAdapter(adapter);
	}
}
