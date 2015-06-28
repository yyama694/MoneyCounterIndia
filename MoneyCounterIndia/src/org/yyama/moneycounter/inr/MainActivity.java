package org.yyama.moneycounter.inr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/*
 * 
 */
public class MainActivity extends AppCompatActivity implements OnClickListener,
		OnTouchListener {
	int[] paisaValue = { 50, 100, 200, 500, 1000, 2000, 5000, 10000, 50000,
			100000, 20000 };
	Map<Integer, Integer> data = new HashMap<Integer, Integer>();
	CountDialog cDialog = new CountDialog();
	Map<Integer, Integer> idMapNum = new HashMap<Integer, Integer>();
	Map<Integer, Integer> idMapSum = new HashMap<Integer, Integer>();
	Map<Integer, Integer> idMapTxt = new HashMap<Integer, Integer>();
	CountDialog countDialog;
	int[] numIds = { R.id.Num0050ps, R.id.Num0001rs, R.id.Num0002rs,
			R.id.Num0005rs, R.id.Num0010rs, R.id.Num0020rs, R.id.Num0050rs,
			R.id.Num0100rs, R.id.Num0500rs, R.id.Num1000rs };
	int[] sumIds = { R.id.Sum0050ps, R.id.Sum0001rs, R.id.Sum0002rs,
			R.id.Sum0005rs, R.id.Sum0010rs, R.id.Sum0020rs, R.id.Sum0050rs,
			R.id.Sum0100rs, R.id.Sum0500rs, R.id.Sum1000rs };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		for (int i : paisaValue) {
			data.put(i, 0);
		}

		((Button) findViewById(R.id.btnClear)).setOnClickListener(this);

		((TextView) findViewById(R.id.Text1000rs)).setOnClickListener(this);
		((TextView) findViewById(R.id.Text0500rs)).setOnClickListener(this);
		((TextView) findViewById(R.id.Text0100rs)).setOnClickListener(this);
		((TextView) findViewById(R.id.Text0050rs)).setOnClickListener(this);
		((TextView) findViewById(R.id.Text0020rs)).setOnClickListener(this);
		((TextView) findViewById(R.id.Text0010rs)).setOnClickListener(this);
		((TextView) findViewById(R.id.Text0005rs)).setOnClickListener(this);
		((TextView) findViewById(R.id.Text0002rs)).setOnClickListener(this);
		((TextView) findViewById(R.id.Text0001rs)).setOnClickListener(this);
		((TextView) findViewById(R.id.Text0050ps)).setOnClickListener(this);

		findViewById(R.id.img1000rs).setOnTouchListener(this);
		findViewById(R.id.img0500rs).setOnTouchListener(this);
		findViewById(R.id.img0100rs).setOnTouchListener(this);
		findViewById(R.id.img0050rs).setOnTouchListener(this);
		findViewById(R.id.img0020rs).setOnTouchListener(this);
		findViewById(R.id.img0010rs).setOnTouchListener(this);
		findViewById(R.id.img0005rs).setOnTouchListener(this);
		findViewById(R.id.img0002rs).setOnTouchListener(this);
		findViewById(R.id.img0001rs).setOnTouchListener(this);
		findViewById(R.id.img0050ps).setOnTouchListener(this);

		idMapNum.put(R.id.Text0050ps, R.id.Num0050ps);
		idMapNum.put(R.id.Text0001rs, R.id.Num0001rs);
		idMapNum.put(R.id.Text0002rs, R.id.Num0002rs);
		idMapNum.put(R.id.Text0005rs, R.id.Num0005rs);
		idMapNum.put(R.id.Text0010rs, R.id.Num0010rs);
		idMapNum.put(R.id.Text0020rs, R.id.Num0020rs);
		idMapNum.put(R.id.Text0050rs, R.id.Num0050rs);
		idMapNum.put(R.id.Text0100rs, R.id.Num0100rs);
		idMapNum.put(R.id.Text0500rs, R.id.Num0500rs);
		idMapNum.put(R.id.Text1000rs, R.id.Num1000rs);

		idMapSum.put(R.id.Text0050ps, R.id.Sum0050ps);
		idMapSum.put(R.id.Text0001rs, R.id.Sum0001rs);
		idMapSum.put(R.id.Text0002rs, R.id.Sum0002rs);
		idMapSum.put(R.id.Text0005rs, R.id.Sum0005rs);
		idMapSum.put(R.id.Text0010rs, R.id.Sum0010rs);
		idMapSum.put(R.id.Text0020rs, R.id.Sum0020rs);
		idMapSum.put(R.id.Text0050rs, R.id.Sum0050rs);
		idMapSum.put(R.id.Text0100rs, R.id.Sum0100rs);
		idMapSum.put(R.id.Text0500rs, R.id.Sum0500rs);
		idMapSum.put(R.id.Text1000rs, R.id.Sum1000rs);

		int idx = 0;
		idMapTxt.put(R.id.Text0050ps, paisaValue[idx++]);
		idMapTxt.put(R.id.Text0001rs, paisaValue[idx++]);
		idMapTxt.put(R.id.Text0002rs, paisaValue[idx++]);
		idMapTxt.put(R.id.Text0005rs, paisaValue[idx++]);
		idMapTxt.put(R.id.Text0010rs, paisaValue[idx++]);
		idMapTxt.put(R.id.Text0020rs, paisaValue[idx++]);
		idMapTxt.put(R.id.Text0050rs, paisaValue[idx++]);
		idMapTxt.put(R.id.Text0100rs, paisaValue[idx++]);
		idMapTxt.put(R.id.Text0500rs, paisaValue[idx++]);
		idMapTxt.put(R.id.Text1000rs, paisaValue[idx++]);

		addSetting();
		add2Init();
		add2Setting();
		load(FILE_NAME);
		draw();
	}

	private boolean isStarted;

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (!isStarted) {
			initVew();
			isStarted = true;
		}
	}

	private void initVew() {
		int hight = (int) ((findViewById(R.id.Text1000rs).getHeight()) * 0.9);
		Log.d("yyama", "高さ" + String.valueOf(hight));
		ImageView iv;

		iv = (ImageView) findViewById(R.id.img1000rs);
		int orgH = iv.getHeight();
		Log.d("yyama", "オリジナルの高さ" + orgH);
		int orgW = iv.getWidth();
		Log.d("yyama", "オリジナルの幅" + orgW);
		double raito = (double) hight / (double) orgH;
		Log.d("yyama", "比率" + raito);
		TableRow.LayoutParams prm = new TableRow.LayoutParams(
				(int) (orgW * raito), hight);
		// TableRow.LayoutParams prm = new TableRow.LayoutParams(
		// (int) (300), hight);
		prm.rightMargin = 0;
		prm.leftMargin = 0;
		prm.weight = 0;
		prm.gravity = Gravity.CENTER;
		prm.column = 0;

		// イメージを縮小して表示
		iv = (ImageView) findViewById(R.id.img1000rs);
		iv.setLayoutParams(prm);
		Log.d("yyama", "1000rsの幅：" + iv.getWidth());
		iv = (ImageView) findViewById(R.id.img0500rs);
		iv.setLayoutParams(prm);
		iv = (ImageView) findViewById(R.id.img0100rs);
		iv.setLayoutParams(prm);
		iv = (ImageView) findViewById(R.id.img0050rs);
		iv.setLayoutParams(prm);
		iv = (ImageView) findViewById(R.id.img0020rs);
		iv.setLayoutParams(prm);
		iv = (ImageView) findViewById(R.id.img0010rs);
		iv.setLayoutParams(prm);
		iv = (ImageView) findViewById(R.id.img0005rs);
		iv.setLayoutParams(prm);
		iv = (ImageView) findViewById(R.id.img0002rs);
		iv.setLayoutParams(prm);
		iv = (ImageView) findViewById(R.id.img0001rs);
		iv.setLayoutParams(prm);
		iv = (ImageView) findViewById(R.id.img0050ps);
		iv.setLayoutParams(prm);
	}

	private AdView adView;

	private void addSetting() {
		// adView を作成する
		adView = new AdView(this);
		adView.setAdUnitId("ca-app-pub-2505812570403600/9985800973");
		adView.setAdSize(AdSize.BANNER);

		// 属性 android:id="@+id/mainLayout" が与えられているものとして
		// LinearLayout をルックアップする
		LinearLayout layout = (LinearLayout) findViewById(R.id.adSpace);

		// adView を追加する
		layout.addView(adView);

		// 一般的なリクエストを行う
		AdRequest adRequest = new AdRequest.Builder().addTestDevice(
				"F3B1B2779DEF816F9B31AA6C6DC57C3F").build();
		// AdRequest adRequest = new AdRequest.Builder().build();

		// 広告リクエストを行って adView を読み込む
		adView.loadAd(adRequest);

	}

	private InterstitialAd interstitial;

	// 全画面広告の方。
	private void add2Init() {
		// インタースティシャルを作成する。
		interstitial = new InterstitialAd(this);
		interstitial.setAdUnitId("ca-app-pub-2505812570403600/6892733771");

		// Set the AdListener.
		interstitial.setAdListener(new AdListener() {
			@Override
			public void onAdClosed() {
				add2Setting();
			}
		});
	}

	// 全画面広告の方。1回表示するたびにロードしなおす必要があるみたい。
	private void add2Setting() {
		if (!interstitial.isLoaded()) {
			Log.d("yyama", "未ロードのため、リクエストします。");
			// 広告リクエストを作成する。
			AdRequest adRequest = new AdRequest.Builder().addTestDevice(
					"F3B1B2779DEF816F9B31AA6C6DC57C3F").build();
			// インタースティシャルの読み込みを開始する。
			interstitial.loadAd(adRequest);
		}
	}

	private void draw() {
		int sum = 0;
		NumberFormat nf = NumberFormat.getInstance();
		for (int i = 0; i < numIds.length; i++) {
			// Log.d("yyama", ((TextView) findViewById(numIds[i])).getText()
			// .toString());
			((TextView) findViewById(numIds[i])).setText(" "
					+ String.format("%,d", data.get(paisaValue[i])));
			int num = 0;

			num = paisaValue[i];
			((TextView) findViewById(sumIds[i])).setText("₹ "
					+ nf.format((double) data.get(paisaValue[i]) * (double) num
							/ 100.0d));
			sum += data.get(paisaValue[i]) * num;
		}
		((TextView) findViewById(R.id.allSum)).setText("₹ "
				+ nf.format((double) sum / 100.0d));
	}

	@Override
	public void onClick(final View v) {
		add2Setting();
		if (v.getId() == R.id.btnClear) {
			Clear();
		} else {
			showCountDialog(v);
		}
	}

	private void save() {
		OutputStream out;
		try {
			out = openFileOutput(FILE_NAME, MODE_PRIVATE);
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(out,
					"UTF-8"));
			// 追記する
			for (int i = 0; i < paisaValue.length; i++) {
				writer.append(paisaValue[i] + "," + data.get(paisaValue[i])
						+ System.getProperty("line.separator"));
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public final static String FILE_NAME = "CountDialog.dat";

	private void load(String fileName) {
		InputStream in;
		String lineBuffer;

		try {
			in = openFileInput(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					in, "UTF-8"));
			while ((lineBuffer = reader.readLine()) != null) {
				String[] strs = lineBuffer.split(",");
				Log.d("yyama", lineBuffer);
				Integer kind = Integer.parseInt(strs[0]);
				data.put(kind, Integer.parseInt(strs[1]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showCountDialog(final View v) {
		LayoutInflater li = LayoutInflater.from(this);
		final View dialog = li.inflate(R.layout.dialog, null);
		int now = getNow(v.getId());
		((TextView) dialog.findViewById(R.id.edit))
				.setText(String.valueOf(now));
		AlertDialog.Builder builder = new Builder(this);
		builder.setView(dialog);
		builder.setTitle(((TextView) v).getText() + " ");
		builder.setNegativeButton(getString(R.string.cancel),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface df, int which) {
				String editStr = ((EditText) dialog.findViewById(R.id.edit))
						.getText().toString();
				int num;
				// 数値判定
				try {
					num = Integer.parseInt(editStr);
				} catch (NumberFormatException e) {
					return;
				}
				Log.d("yyama2", String.valueOf(v.getId()));
				data.put(idMapTxt.get(v.getId()), num);
				save();
				draw();
			}
		});
		((Button) dialog.findViewById(R.id.btnPlus1))
				.setOnClickListener(cDialog);
		((Button) dialog.findViewById(R.id.btnPlus5))
				.setOnClickListener(cDialog);
		((Button) dialog.findViewById(R.id.btnPlus10))
				.setOnClickListener(cDialog);
		((Button) dialog.findViewById(R.id.btnPlus50))
				.setOnClickListener(cDialog);
		((Button) dialog.findViewById(R.id.btnMinus1))
				.setOnClickListener(cDialog);
		((Button) dialog.findViewById(R.id.btnMinus5))
				.setOnClickListener(cDialog);
		((Button) dialog.findViewById(R.id.btnMinus10))
				.setOnClickListener(cDialog);
		((Button) dialog.findViewById(R.id.btnMinus50))
				.setOnClickListener(cDialog);
		builder.show();
	}

	private void Clear() {
		// 確認ダイアログの生成
		AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
		alertDlg.setTitle(getString(R.string.all_crear_confirm_title));
		alertDlg.setMessage(getString(R.string.all_crear_confirm_msg));
		alertDlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				for (int i : paisaValue) {
					data.put(i, 0);
				}
				save();
				draw();
			}
		});
		alertDlg.setNegativeButton(getString(R.string.cancel),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		alertDlg.create().show();
	}

	private int getNow(int id) {
		String str = ((TextView) findViewById(idMapNum.get(id))).getText()
				.toString().replace(",", "").replace(" ", "");
		Log.d("yyama", str);
		int now = Integer.parseInt(str);
		Log.d("yyama", String.valueOf(now));
		return now;
	}

	@Override
	public void onPause() {
		adView.pause();
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		adView.resume();
	}

	@Override
	public void onDestroy() {
		adView.destroy();
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public static final int REQUEST_CD = 100;

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.screenshot:
			Screenshot.saveScreen(this);
			if (interstitial.isLoaded()) {
				interstitial.show();
			}
			add2Setting();
			break;
		case R.id.file_save:
			// ファイル保存
			saveLocalFile();
			break;
		case R.id.file_open:
			// ファイル選択アクティビティを表示
			Intent intent = new Intent(this, LocalFileActivity.class);
			this.startActivityForResult(intent, REQUEST_CD);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void saveLocalFile() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final EditText edit = new EditText(this);
		edit.setInputType(InputType.TYPE_CLASS_TEXT);
		builder.setView(edit);
		builder.setTitle(this.getString(R.string.please_memo));

		builder.setNegativeButton(R.string.cancel,
				new AlertDialog.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		builder.setPositiveButton(R.string.saveFile,
				new AlertDialog.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String fileName = String.format(
								"MC_%1$tY%1$tm%1$td_%1$tH%1$tM%1$tS_%2$s.txt",
								Calendar.getInstance(), edit.getText());
						PrintWriter pw = null;
						try {
							pw = new PrintWriter(MainActivity.this
									.openFileOutput(fileName,
											Activity.MODE_PRIVATE));
							// 追記する
							for (int i : paisaValue) {
								pw.append(i + "," + data.get(i)
										+ System.getProperty("line.separator"));
							}
							pw.flush();
							Toast.makeText(MainActivity.this,
									R.string.has_been_saved, Toast.LENGTH_LONG)
									.show();
							if (interstitial.isLoaded()) {
								Log.d("yyama", "インターステシャルはロードされています。");
								Random rnd = new Random();
								if (rnd.nextInt(2) == 0) {
									interstitial.show();
								}
							} else {
								Log.d("yyama", "インターステシャルはロードされていません。");
							}
							add2Setting();

						} catch (Exception e) {
							Toast.makeText(MainActivity.this,
									R.string.failed_to_save, Toast.LENGTH_LONG)
									.show();
						} finally {
							if (!(pw == null)) {
								pw.close();
							}
						}
					}
				});
		final AlertDialog dialog = builder.create();
		edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					dialog.getButton(AlertDialog.BUTTON_POSITIVE)
							.performClick();
				}
				return true;
			}
		});
		dialog.setOnShowListener(new DialogInterface.OnShowListener() {
			@Override
			public void onShow(DialogInterface dialog) {
				// ソフトキーボードを出す
				InputMethodManager inputMethodManager = (InputMethodManager) MainActivity.this
						.getSystemService(Context.INPUT_METHOD_SERVICE);
				inputMethodManager.showSoftInput(edit, 0);
			}
		});
		dialog.show();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CD) {
			switch (resultCode) {
			case RESULT_OK:
				try {
					load(data.getStringExtra("file_name"));
					save();
					draw();
					Toast.makeText(
							this,
							getString(R.string.opend_file)
									+ System.getProperty("line.separator")
									+ data.getStringExtra("file_title"),
							Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(this, getString(R.string.file_open_error),
							Toast.LENGTH_LONG).show();
				}

				break;
			case RESULT_CANCELED:
				break;
			default:
				break;
			}
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent arg1) {
		switch (arg1.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			ColorFilter cf = new LightingColorFilter(Color.rgb(157, 204, 224),
					getResources().getColor(R.color.blue));
			((ImageView) v).setColorFilter(cf);
			v.setBackgroundColor(getResources().getColor(R.color.skyblue));
			break;
		}
		case MotionEvent.ACTION_UP: {
			switch (v.getId()) {
			case R.id.img1000rs: {
				findViewById(R.id.Text1000rs).performClick();
				break;
			}
			case R.id.img0500rs: {
				findViewById(R.id.Text0500rs).performClick();
				break;
			}
			case R.id.img0100rs: {
				findViewById(R.id.Text0100rs).performClick();
				break;
			}
			case R.id.img0050rs: {
				findViewById(R.id.Text0050rs).performClick();
				break;
			}
			case R.id.img0020rs: {
				findViewById(R.id.Text0020rs).performClick();
				break;
			}
			case R.id.img0010rs: {
				findViewById(R.id.Text0010rs).performClick();
				break;
			}
			case R.id.img0005rs: {
				findViewById(R.id.Text0005rs).performClick();
				break;
			}
			case R.id.img0002rs: {
				findViewById(R.id.Text0002rs).performClick();
				break;
			}
			case R.id.img0001rs: {
				findViewById(R.id.Text0001rs).performClick();
				break;
			}
			case R.id.img0050ps: {
				findViewById(R.id.Text0050ps).performClick();
				break;
			}
			}
		}
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_OUTSIDE: {
			((ImageView) v).setColorFilter(null);
			v.setBackgroundColor(Color.TRANSPARENT);
			break;
		}
		}
		return true;
	}
}
