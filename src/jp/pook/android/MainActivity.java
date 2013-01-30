package jp.pook.android;

import jp.pook.android.common.Const;
import jp.pook.android.public_timeline.PublicTimelineGridFragment;
import jp.pook.android.public_timeline.PublicTimelineListFragment;
import jp.pook.android.public_timeline.TabListener;
import android.app.ActionBar;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final int SEARCH_CODE = 1;
	private static final String QRCODE = "com.google.zxing.client.android.SCAN";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		actionBar.addTab(actionBar
				.newTab()
				.setText(R.string.tab_public_timeline)
				.setTabListener(
						new TabListener<PublicTimelineListFragment>(this,
								"tabA", PublicTimelineListFragment.class)));
		actionBar.addTab(actionBar
				.newTab()
				.setText(R.string.tab_requotes)
				.setTabListener(
						new TabListener<PublicTimelineGridFragment>(this,
								"tagB", PublicTimelineGridFragment.class)));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {// addしたときのIDで識別
		switch (item.getItemId()) {
		case R.id.menu_search:

			Intent intent = new Intent(QRCODE);
			try {
				startActivityForResult(intent, SEARCH_CODE);
			} catch (ActivityNotFoundException e) {
				//　次のアクティビティ情報を設定
				Uri uri = Uri.parse(Const.MARKET_LINK_BASE + Const.QRCODE_PACKAGE);
				intent = new Intent(Intent.ACTION_VIEW, uri);
				// 呼び出し
				startActivity(intent);
				Toast.makeText(this, R.string.toast_no_barcode_app,
						Toast.LENGTH_SHORT).show();
			}

			return true;

		case R.id.menu_login:
			// TODO 暫定対処
			Toast.makeText(this, "ログイン（まだ未実装）", Toast.LENGTH_LONG).show();
			return true;

		case R.id.menu_new_acount:
			// TODO 暫定対処
			Toast.makeText(this, "アカウント作成（まだ未実装）", Toast.LENGTH_LONG).show();
			return true;
		}
		return false;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case SEARCH_CODE:
			if (data == null) {
				// 何もしない
			} else {
				String isbn = data.getStringExtra("SCAN_RESULT");
				Intent intent = new Intent(this, BookDetailActivity.class);
				intent.putExtra("SCAN_RESULT", isbn);
				startActivity(intent);
			}
			
		}
	}
}
