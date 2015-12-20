package com.dianxun.holyn.lucky.view.widget.webview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings.ZoomDensity;
import android.widget.TextView;
import android.widget.Toast;

import com.dianxun.holyn.lucky.R;

/**
 * holyn 2015-12-20
 *
 */
public class CustomWebViewActivity extends AppCompatActivity {
	public static final String KEY_URL_FULL = "key_url";//完整的url
	public static final String KEY_TITLE = "key_title";
	
	private ProgressWebView webView = null;

	private String urlFull;
	private String title;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_custom_webview);

		initWebView();

		initToolBar();
	}

	private void initWebView(){
		webView = (ProgressWebView)findViewById(R.id.webview);

		Intent intent = getIntent();
		urlFull = intent.getStringExtra(KEY_URL_FULL);
		title = intent.getStringExtra(KEY_TITLE);

		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setBuiltInZoomControls(true); // 显示放大缩小 controler
		webView.getSettings().setSupportZoom(true); // 可以缩放
		webView.getSettings().setDefaultZoom(ZoomDensity.CLOSE);// 默认缩放模式
		webView.getSettings().setUseWideViewPort(true); // 为图片添加放大缩小功能

		webView.setDownloadListener(new DownloadListener() {
			@Override
			public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
				if (url != null && url.startsWith("http://"))
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
			}
		});

		if (!TextUtils.isEmpty(urlFull)) {
			webView.loadUrl(urlFull);
		}else {
			Toast.makeText(this, "图文链接为空", Toast.LENGTH_SHORT).show();
		}
	}

	private void initToolBar() {
		Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);

		toolbar.setNavigationIcon(R.mipmap.ic_menu_back);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});

		TextView tvToolbarTitle = (TextView)findViewById(R.id.tv_toolbar_title);
		tvToolbarTitle.setText(title);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

//	@Override
//	protected void onResume() {
//		super.onResume();
//		webView.reload();
//	}

	@Override
	protected void onPause() {
		super.onPause();
		webView.onPause();
	}
}
