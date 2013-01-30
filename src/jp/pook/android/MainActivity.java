package jp.pook.android;

import jp.pook.android.public_timeline.PublicTimelineGridFragment;
import jp.pook.android.public_timeline.PublicTimelineListFragment;
import jp.pook.android.public_timeline.TabListener;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {
	
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
	    	// TODO 暫定対処
	        Toast.makeText(this, "検索（まだ未実装）", Toast.LENGTH_LONG).show();
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
}
