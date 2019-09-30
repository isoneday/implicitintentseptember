package com.imastudio.implicitintentapp.activity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.imastudio.implicitintentapp.R;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TTSActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    @BindView(R.id.edtinput)
    EditText edtinput;
    @BindView(R.id.btnspeak)
    Button btnspeak;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts);
        ButterKnife.bind(this);
        tts = new TextToSpeech(this,this);
    }

    @OnClick(R.id.btnspeak)
    public void onViewClicked() {
        String text = edtinput.getText().toString();
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onInit(int i) {

        if (i== TextToSpeech.SUCCESS){
            Locale bahasa = new Locale("id","ID");
            int indo = tts.setLanguage(Locale.ENGLISH);
            if (indo ==TextToSpeech.LANG_MISSING_DATA||indo==TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this, "bahasa tidak mendukung", Toast.LENGTH_SHORT).show();
            }else{
                onViewClicked();

            }

        }else{
            Toast.makeText(this, "TTS not Supported", Toast.LENGTH_SHORT).show();
        }
    }
}
