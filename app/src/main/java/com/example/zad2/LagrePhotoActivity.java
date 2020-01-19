package com.example.zad2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;
import android.util.TimeUtils;

import java.io.File;
import java.io.IOException;

public class LagrePhotoActivity extends AppCompatActivity {

    Button play,pause, stop;
    int id;
    TextView text;
    MediaPlayer mediaPlayer = new MediaPlayer();
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lagre_photo);

        id = getIntent().getIntExtra("idPhoto", 0);

        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);
        stop = findViewById(R.id.stop);
        progress = findViewById(R.id.progressBar1);


        ImageView image = findViewById(R.id.photo);
        text = findViewById(R.id.text);
        image.setImageURI(BazaObrazkow.uri_photo.get(id));

        int currentPosition = mediaPlayer.getCurrentPosition();
        int x = (int) Math.ceil(currentPosition / 1000);

        text.setText(BazaObrazkow.tittle.get(id)+"  "+BazaObrazkow.subtittle.get(id)+" - "+x);

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(getApplicationContext(), BazaObrazkow.uri.get(id));
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        progress.setMax(mediaPlayer.getDuration());
        final CountDownTimer cDT = new CountDownTimer(mediaPlayer.getDuration(), 250) {
            public void onTick(long millisUntilFinished) {
                progress.setProgress(progress.getProgress() + 250);
            }
            public void onFinish() {}
        };



        play.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        mediaPlayer.start();
        cDT.start();
    }
});
        stop.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        mediaPlayer.stop();
        cDT.cancel();
    }
});
        pause.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        mediaPlayer.pause();
        cDT.cancel();
    }
});

    }
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

}
