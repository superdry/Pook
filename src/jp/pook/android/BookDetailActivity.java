package jp.pook.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class BookDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_detail);

		String isbn = getIntent().getExtras().getString("SCAN_RESULT");

		TextView tv = (TextView) findViewById(R.id.book_isbn);
		tv.setText(isbn);
	}
}
