package jp.pook.android.public_timeline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jp.pook.android.common.Const;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

public class GetQuoteTask extends AsyncTask<String, Void, String> {

	private String uri;
	private static final String SCHEME = "http";
	private static final String AUTHORITY = "pook.jp";
	private PublicTimelineFragment fragment;

	public GetQuoteTask(PublicTimelineFragment fragment) {
		this.fragment = fragment;
	}

	@Override
	protected String doInBackground(String... params) {

		Uri.Builder uriBuilder = new Uri.Builder();
		uriBuilder.scheme(SCHEME);
		uriBuilder.authority(AUTHORITY);
		uriBuilder.path(params[0]);
		uri = uriBuilder.toString();

		HttpClient httpClient = new DefaultHttpClient();
		HttpParams httpParams = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
		HttpConnectionParams.setSoTimeout(httpParams, 1000);

		HttpUriRequest httpRequest = new HttpGet(uri);

		HttpResponse httpResponse = null;

		try {
			httpResponse = httpClient.execute(httpRequest);
		} catch (ClientProtocolException e) {
			// 例外処理
		} catch (IOException e) {
			// 例外処理
		}

		String json = null;

		if (httpResponse != null
				&& httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity httpEntity = httpResponse.getEntity();
			try {
				json = EntityUtils.toString(httpEntity);
			} catch (ParseException e) {
				// 例外処理
			} catch (IOException e) {
				// 例外処理
			} finally {
				try {
					httpEntity.consumeContent();
				} catch (IOException e) {
					// 例外処理
				}
			}
		}

		httpClient.getConnectionManager().shutdown();

		return json;
	}

	@Override
	protected void onPostExecute(String json) {

		// アダプタを作成
		SimpleAdapter adapter = new SimpleAdapter(fragment.getActivity(),
				parseJson(json), android.R.layout.simple_list_item_2,
				new String[] { "content", "book_name" }, new int[] {
						android.R.id.text1, android.R.id.text2 });
		// アダプタを設定
		fragment.setListAdapter(adapter);
	}

	private ArrayList<HashMap<String, String>> parseJson(String json) {

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
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("content", content);
				map.put("book_name", book_name);
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
