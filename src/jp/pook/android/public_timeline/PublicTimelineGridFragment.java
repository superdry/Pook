package jp.pook.android.public_timeline;

import java.util.List;

import jp.pook.android.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class PublicTimelineGridFragment extends PublicTimelineFragment {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		String path = "/api/v1/requotes/public_timeline.json";

		GetQuoteTask task = new GetQuoteTask(this);
		task.execute(path);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.publictimeline_grid, container,
				false);
		return view;
	}

	public void setAdapter(String json) {

		// アダプタを作成
		List<QuoteData> quotes = parseJson(json);
		QuoteAdapter adapter = new QuoteAdapter(this.getActivity(), R.layout.grid, quotes);

		// アダプタを設定
		GridView gallery = (GridView) this.getActivity().findViewById(
				R.id.gridView);
		gallery.setAdapter(adapter);
	}
}
