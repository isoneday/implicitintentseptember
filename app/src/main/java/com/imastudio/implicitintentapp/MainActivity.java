package com.imastudio.implicitintentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.imastudio.implicitintentapp.activity.AudioManagerActivity;
import com.imastudio.implicitintentapp.activity.AudioRecorderActivity;
import com.imastudio.implicitintentapp.activity.BrowserActivity;
import com.imastudio.implicitintentapp.activity.CameraActivity;
import com.imastudio.implicitintentapp.activity.EmailActivity;
import com.imastudio.implicitintentapp.activity.PhoneActivity;
import com.imastudio.implicitintentapp.activity.SmsActivity;
import com.imastudio.implicitintentapp.activity.TTSActivity;
import com.imastudio.implicitintentapp.activity.VideoActivity;
import com.imastudio.implicitintentapp.activity.WifiActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btncamera)
    Button btncamera;
    @BindView(R.id.video)
    Button video;
    @BindView(R.id.btntts)
    Button btntts;
    @BindView(R.id.btnsms)
    Button btnsms;
    @BindView(R.id.btnemail)
    Button btnemail;
    @BindView(R.id.btnwifi)
    Button btnwifi;
    @BindView(R.id.btnphone)
    Button btnphone;
    @BindView(R.id.btnbrowser)
    Button btnbrowser;
    @BindView(R.id.btnaudiomanager)
    Button btnaudiomanager;
    @BindView(R.id.btnaudiorecorder)
    Button btnaudiorecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btncamera, R.id.video, R.id.btntts, R.id.btnsms, R.id.btnemail, R.id.btnwifi, R.id.btnphone, R.id.btnbrowser, R.id.btnaudiomanager, R.id.btnaudiorecorder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btncamera:
                startActivity(new Intent(this, CameraActivity.class));
                break;
            case R.id.video:
                startActivity(new Intent(this, VideoActivity.class));

                break;
            case R.id.btntts:
                startActivity(new Intent(this, TTSActivity.class));

                break;
            case R.id.btnsms:
                startActivity(new Intent(this, SmsActivity.class));

                break;
            case R.id.btnemail:
                startActivity(new Intent(this, EmailActivity.class));

                break;
            case R.id.btnwifi:
                startActivity(new Intent(this, WifiActivity.class));

                break;
            case R.id.btnphone:
                startActivity(new Intent(this, PhoneActivity.class));

                break;
            case R.id.btnbrowser:
                startActivity(new Intent(this, BrowserActivity.class));

                break;
            case R.id.btnaudiomanager:
                startActivity(new Intent(this, AudioManagerActivity.class));

                break;
            case R.id.btnaudiorecorder:
                startActivity(new Intent(this, AudioRecorderActivity.class));

                break;
        }
    }
}
