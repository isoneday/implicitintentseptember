package com.imastudio.implicitintentapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.imastudio.implicitintentapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BrowserActivity extends AppCompatActivity {

    @BindView(R.id.edtalamatweb)
    EditText edtalamatweb;
    @BindView(R.id.btnopenbrows)
    Button btnopenbrows;
    @BindView(R.id.wv1)
    WebView wv1;
    String alamatweb = null;
    @BindView(R.id.btnopenweb)
    Button btnopenweb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btnopenbrows, R.id.btnopenweb})
    public void onViewClicked(View view) {
        alamatweb = edtalamatweb.getText().toString();

        switch (view.getId()) {
            case R.id.btnopenbrows:
                if (TextUtils.isEmpty(alamatweb)) {
                    edtalamatweb.setError(getText(R.string.emailwajib));
                } else {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://" + alamatweb)));
                }

                break;
            case R.id.btnopenweb:
                wv1.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return super.shouldOverrideUrlLoading(view, url);
                    }
                });
                wv1.getSettings().setJavaScriptEnabled(true);
                Log.d("alamatweb:", alamatweb);
                wv1.loadUrl("https://" + alamatweb);
                break;
        }
    }


}
