package com.example.zad2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;
import static android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION;


public class AddActivity extends AppCompatActivity {

    EditText tittle, subtittle;
    Button audio, photo, add;
    TextView adress, adress_photo;
    String audioPath = " ";
    String photoPath = " ";

    private static int RESULT_LOAD_IMAGE = 1;
    private static int LOAD_TYPE = 1;

    boolean isSelectedAudio=false,isSelectedPhoto=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        tittle = findViewById(R.id.add_tittle);
        subtittle = findViewById(R.id.add_subtittle);
        audio = findViewById(R.id.add_select_audio);
        photo = findViewById(R.id.add_select_photo);
        add = findViewById(R.id.add_selected_photo);
        adress = findViewById(R.id.adres_audio);
        adress_photo = findViewById(R.id.adres_photo);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelectedAudio&&isSelectedPhoto) {
                    BazaObrazkow.tittle.add(tittle.getText().toString());
                    BazaObrazkow.subtittle.add(subtittle.getText().toString());
                    BazaObrazkow.path.add(audioPath);
                    BazaObrazkow.path_photo.add(photoPath);
                    Toast.makeText(AddActivity.this, "Dodany", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }else
                {
                    Toast.makeText(AddActivity.this, "Musisz wybrac zdjecie i audio", Toast.LENGTH_SHORT).show();

                }
            }
        });

        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //stare wyszukiwanie obrazkow
                //Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                //startActivityForResult(i, RESULT_LOAD_IMAGE);
                LOAD_TYPE = 1;
                adress.setText(audioPath);
                Intent intent_upload = new Intent();
                intent_upload.setType("audio/*");
                intent_upload.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent_upload,1);
            }
        });

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //stare wyszukiwanie obrazkow
                //Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                //startActivityForResult(i, RESULT_LOAD_IMAGE);
                LOAD_TYPE = 2;
                adress_photo.setText(photoPath);
                Intent intent_upload = new Intent();
                intent_upload.setType("image/*");
                intent_upload.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent_upload,1);

                //    mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
                //    mFileName += "/audiorecordtest.3gp";

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(LOAD_TYPE==1) {
            if (requestCode == 1) {
                if (resultCode == RESULT_OK) {
                    //the selected audio.
                    Uri uri = data.getData();
                    BazaObrazkow.uri.add(uri);
                    audioPath = uri.toString();
                    Log.d("Uri", uri.toString());
                    isSelectedAudio=true;
                }
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
        else if(LOAD_TYPE==2){
            if (requestCode == 1) {
                if (resultCode == RESULT_OK) {
                    //the selected photo.
                    Uri uri = data.getData();
                    BazaObrazkow.uri_photo.add(uri);
                    photoPath = uri.toString();
                    Log.d("Uri", uri.toString());
                    isSelectedPhoto=true;
                }
            }
        super.onActivityResult(requestCode, resultCode, data);
        }
    }
  /*  @Override   //stare wyszukiwanie obrazkow
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            BazaObrazkow.uri.add(selectedImage);


            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            adress.setText(cursor.getString(columnIndex));
            photoPath = cursor.getString(columnIndex);


            cursor.close();
        }
    }*/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
