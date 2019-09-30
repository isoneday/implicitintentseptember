package com.imastudio.implicitintentapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.imastudio.implicitintentapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmailActivity extends AppCompatActivity {

    @BindView(R.id.edtto)
    EditText edtto;
    @BindView(R.id.edtsubject)
    EditText edtsubject;
    @BindView(R.id.edtmsg)
    EditText edtmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wm_il);
        ButterKnife.bind(this);
    }

    //tampil menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //untuk aksi klik item di menu

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mn_send) {
            String to = edtto.getText().toString();
            String msg = edtmsg.getText().toString();
            String subject = edtsubject.getText().toString();
            if (TextUtils.isEmpty(to)) {
                edtto.setError("To tidak boleh kosong");

            } else if (TextUtils.isEmpty(subject)) {
                edtsubject.setError("subject tidak boleh kosong");

            } else if (TextUtils.isEmpty(msg)) {
                edtmsg.setError("message tidak boleh kosong");

            }else {
                Intent kirimData = new Intent(Intent.ACTION_SEND);
                kirimData.putExtra(Intent.EXTRA_EMAIL,   new String[]{to});
                kirimData.putExtra(Intent.EXTRA_TEXT,msg);
                kirimData.putExtra(Intent.EXTRA_SUBJECT, subject);
                kirimData.setType("message/rfc822");
                startActivity(Intent.createChooser(kirimData, "send email via :"));
            }
        }else{
            edtmsg.setText("");
            edtto.setText("");
            edtsubject.setText("");

        }
        return super.onOptionsItemSelected(item);
    }
}
