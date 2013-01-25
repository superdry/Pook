package jp.pook.android.public_timeline;

import java.io.IOException;

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

import android.net.Uri;
import android.os.AsyncTask;

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
		HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
		HttpConnectionParams.setSoTimeout(httpParams, 5000);

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
		fragment.setAdapter(json);
	}
}
