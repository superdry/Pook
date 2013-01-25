package jp.pook.android;

import jp.pook.android.public_timeline.PublicTimelineGridFragment;
import jp.pook.android.public_timeline.PublicTimelineListFragment;
import jp.pook.android.public_timeline.TabListener;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

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
						new TabListener<PublicTimelineListFragment>(this, "tabA",
								PublicTimelineListFragment.class)));
		actionBar.addTab(actionBar
				.newTab()
				.setText(R.string.tab_check)
				.setTabListener(
						new TabListener<PublicTimelineGridFragment>(this, "tagB",
								PublicTimelineGridFragment.class)));

	}
}
