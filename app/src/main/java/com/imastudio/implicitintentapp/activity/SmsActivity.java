package com.imastudio.implicitintentapp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.imastudio.implicitintentapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@RequiresApi(api = Build.VERSION_CODES.M)
public class SmsActivity extends AppCompatActivity {

    @BindView(R.id.edtnohp)
    EditText edtnohp;
    @BindView(R.id.edtisipesan)
    EditText edtisipesan;
    @BindView(R.id.btnsmsintent)
    Button btnsmsintent;
    @BindView(R.id.btnsms)
    Button btnsms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        ButterKnife.bind(this);
        cekPermission();
    }

    private void cekPermission() {
        if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                        new String[]{
                                Manifest.permission.SEND_SMS,

                        }, 100
                );
            }
            return;
        }
    }

    @OnClick({R.id.edtnohp, R.id.btnsmsintent, R.id.btnsms})
    public void onViewClicked(View view) {
        String isipesan =edtisipesan.getText().toString();
        String nohp =edtnohp.getText().toString();
        switch (view.getId()) {
            case R.id.edtnohp:
                Intent i  = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(i, 1);

                break;
            case R.id.btnsmsintent:
                Intent sms = new Intent(Intent.ACTION_VIEW);
                sms.setType("vnd.android-dir/mms-sms");
                sms.putExtra("address", nohp);
                sms.putExtra("sms_body", isipesan);
                startActivity(sms);
                break;
            case R.id.btnsms:
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.SEND_SMS)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.SEND_SMS)) {
                    } else {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.SEND_SMS},
                                2);
                    }
                }else{
                    try {
                        SmsManager manager = SmsManager.getDefault();
                        manager.sendTextMessage(nohp,null,isipesan,null,null);
                        Toast.makeText(this, "pesan terkirim", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(this, "pesan tidak terkirim"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==RESULT_OK){
            Cursor c = null;
            try {
                Uri lokasi = data.getData();
                c = getContentResolver().query(lokasi, new String[]{
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                }, null, null);
                if (c!=null &&c.moveToNext()){
                    String phone = c.getString(0);
                    edtnohp.setText(phone);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
