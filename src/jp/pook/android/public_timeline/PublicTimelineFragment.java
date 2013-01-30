package jp.pook.android.public_timeline;

import java.util.ArrayList;

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
	
	protected ArrayList<QuoteData> parseJson(String json) {

		ArrayList<QuoteData> result = new ArrayList<QuoteData>();

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
				String isbn = book_user.getString("username");
				QuoteData data = new QuoteData();
				data.setBookName(book_name);
				data.setQuote(content);
				data.setImageUrl(image_url);
				data.setIsbn(isbn);
				result.add(data);
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
