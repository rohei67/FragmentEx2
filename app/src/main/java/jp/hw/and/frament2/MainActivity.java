package jp.hw.and.frament2;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	static final String[] TAGS = {"tab1", "tab2", "tab3"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final ActionBar bar = getActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// タブを登録
		final int TAB_NUM = 3;
		for (int i = 0; i < TAB_NUM; i++) {
			TabContentFragment tabContentFragment = TabContentFragment.newInstance(getLayoutId(i));
			MyTabListener myTabListener = new MyTabListener(tabContentFragment);
			Tab tab = bar.newTab().setText(getLabel(i)).setTabListener(myTabListener);
			bar.addTab(tab);
		}
	}

	private int getLayoutId(int num) {
		int[] tabContentLayout = {R.layout.bar_tabs_content1, R.layout.bar_tabs_content2, R.layout.bar_tabs_content3};
		return tabContentLayout[num];
	}

	private String getLabel(int num) {
		int[] label = {R.string.tab1_label, R.string.tab2_label, R.string.tab3_label};
		return getResources().getString(label[num]);
	}

	public class MyTabListener implements TabListener {
		private TabContentFragment _fragment;

		public MyTabListener(TabContentFragment fragment) {
			_fragment = fragment;
		}

		@Override
		public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			if (_fragment.isAdded())
				ft.show(_fragment);
			else
				ft.add(R.id.fragment_content/*activity_main.xml*/, _fragment, TAGS[tab.getPosition()]);
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			ft.hide(_fragment);
		}
	}

	public static class TabContentFragment extends Fragment {
		public static TabContentFragment newInstance(int layoutId) {
			TabContentFragment tf = new TabContentFragment();

			Bundle args = new Bundle();
			args.putInt("layoutId", layoutId);
			tf.setArguments(args);
			return tf;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			int layoutId = getArguments().getInt("layoutId");
			View tabView = inflater.inflate(layoutId, container, false);
			return tabView;
		}
	}

	public void onBtnLog(View v) {
		// タグからFragmentのインスタンスを取得する
		TabContentFragment tf1 = (TabContentFragment) getFragmentManager().findFragmentByTag(TAGS[0]);
		if (tf1 != null) {
			TextView tv1 = (TextView) findViewById(R.id.textView1);
			EditText edt1 = (EditText) findViewById(R.id.editText1);
			Log.d("ActionBarTab", tv1.getText().toString());
			Log.d("ActionBarTab", edt1.getText().toString());
		}
		TabContentFragment tf2 = (TabContentFragment) getFragmentManager().findFragmentByTag(TAGS[1]);
		if (tf2 != null) {
			TextView tv2 = (TextView) findViewById(R.id.textView2);
			TextView tv3 = (TextView) findViewById(R.id.textView3);
			Log.d("ActionBarTab", tv2.getText().toString());
			Log.d("ActionBarTab", tv3.getText().toString());
		}
		TabContentFragment tf3 = (TabContentFragment) getFragmentManager().findFragmentByTag(TAGS[2]);
		if (tf3 != null) {
			TextView tv4 = (TextView) findViewById(R.id.textView4);
			EditText edt2 = (EditText) findViewById(R.id.editText2);
			TextView tv5 = (TextView) findViewById(R.id.textView5);
			EditText edt3 = (EditText) findViewById(R.id.editText3);
			Log.d("ActionBarTab", tv4.getText().toString());
			Log.d("ActionBarTab", edt2.getText().toString());
			Log.d("ActionBarTab", tv5.getText().toString());
			Log.d("ActionBarTab", edt3.getText().toString());
		}
	}

	@Override
	public void finish() {
		showDialog();
	}

	void showDialog() {
		DialogFragment newFragment = MyAlertDialogFragment.newInstance(R.string.alert_dialog_fin_confirm_title, R.string.alert_dialog_fin_confirm_message);
		newFragment.show(getFragmentManager(), "dialog");
	}

	void appEnd() {
		super.finish();
	}

}
