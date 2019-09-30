package com.imastudio.implicitintentapp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.imastudio.implicitintentapp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CameraActivity extends AppCompatActivity {

    @BindView(R.id.btntakepicture)
    Button btntakepicture;
    @BindView(R.id.btnshowpicture)
    Button btnshowpicture;
    @BindView(R.id.ivpicture)
    ImageView ivpicture;
    @BindView(R.id.btnsharepicture)
    Button btnsharepicture;
    private Uri lokasiFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        ButterKnife.bind(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED

        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                        new String[]{
                                Manifest.permission.CAMERA,
                                Manifest.permission.READ_EXTERNAL_STORAGE,

                        }, 100
                );
            }
            return;
        }
    }

    @OnClick({R.id.btntakepicture, R.id.btnshowpicture,R.id.btnsharepicture})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btntakepicture:
                String folder = "gambar";
                File f = new File(Environment.getExternalStorageDirectory(), folder);
                if (!f.exists()) {
                    f.mkdir();
                }
                File fileGambar = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                        + "/" + folder + "/PIC" + currentDate() + ".jpg");
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                lokasiFile = Uri.fromFile(fileGambar);
                i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                i.putExtra(MediaStore.EXTRA_OUTPUT, lokasiFile);
                startActivityForResult(i, 2);
                break;
            case R.id.btnshowpicture:
                Intent show = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(show, 3);
                break;
                }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK) {
            Toast.makeText(this, "berhasil mengambil gambar" +lokasiFile.toString(), Toast.LENGTH_SHORT).show();
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ivpicture.setImageBitmap(photo);

        } else if (requestCode == 3 && resultCode == RESULT_OK) {
            lokasiFile = data.getData();

            InputStream is = null;
            try {
                is = getContentResolver().openInputStream(lokasiFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            ivpicture.setImageBitmap(bitmap);
        }
    }

    public String currentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Date date = new Date();
        return dateFormat.format(date);

    }

}
