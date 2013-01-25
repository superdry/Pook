package jp.pook.android.public_timeline;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import jp.pook.android.common.Const;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;

public abstract class PublicTimelineFragment extends Fragment {
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.d(Const.TAG,"onActivityCreated");

		String path = "/api/v1/quotes/public_timeline.json";

		GetQuoteTask task = new GetQuoteTask(this);
		task.execute(path);
	}
	
    abstract void setAdapter(String json);
	
	protected ArrayList<HashMap<String, String>> parseJson(String json) {

		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();

		try {
			// オブジェクトの生成
			JSONArray rootArray = new JSONArray(json);
			int length = rootArray.length();

			for (int i = 0; i < length; i++) {
				JSONObject quotes = rootArray.getJSONObject(i);
				JSONObject quote = quotes.getJSONObject("quote");
				String content = quote.getString("content");
				JSONObject book_user = quote.getJSONObject("book_user");
				String book_name = book_user.getString("name");
				String image_url = book_user.getString("image_url");
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("content", content);
				map.put("book_name", book_name);
				map.put("image_url", image_url);
				result.add(map);
			}

		} catch (JSONException e) {
			e.printStackTrace();
			// 例外処理
		} catch (NullPointerException e) {
			Log.d(Const.TAG, "Json is null.");
		}
		return result;

	}
}
