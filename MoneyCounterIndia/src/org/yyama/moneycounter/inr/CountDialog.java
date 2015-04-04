package org.yyama.moneycounter.inr;

import android.view.View;
import android.widget.TextView;

public class CountDialog implements android.view.View.OnClickListener {

	@Override
	public void onClick(View v) {
		int added = 0;
		switch (v.getId()) {
		case R.id.btnPlus1:
			added = 1;
			break;
		case R.id.btnPlus5:
			added = 5;
			break;
		case R.id.btnPlus10:
			added = 10;
			break;
		case R.id.btnPlus50:
			added = 50;
			break;
		case R.id.btnMinus1:
			added = -1;
			break;
		case R.id.btnMinus5:
			added = -5;
			break;
		case R.id.btnMinus10:
			added = -10;
			break;
		case R.id.btnMinus50:
			added = -50;
			break;
		default:
			break;
		}
		TextView tv = (TextView) ((View) v.getParent().getParent())
				.findViewById(R.id.edit);
		int now = 0;
		try {
			now = Integer.valueOf(tv.getText().toString());
		} catch (Exception e) {
		}

		tv.setText(String.valueOf(Math.max(now + added, 0)));
	}
}
