package com.alpha.whizwish.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alpha.whizwish.Camera.VideoActivity;
import com.alpha.whizwish.R;
import com.brkckr.circularprogressbar.CircularProgressBar;
import com.melnykov.fab.FloatingActionButton;
import com.otaliastudios.cameraview.VideoCodec;

import java.io.File;
import java.io.IOException;

public class AudioRecorderActivity extends AppCompatActivity {

    private ImageView recordVideo;
    private CircularProgressBar circularProgressBar;
    private CountDownTimer cTimer = null;
    private boolean isRecordButtonLongPressed = false;
    MediaRecorder myAudioRecorder;
    File file;
    Dialog playBackDialog;
    private boolean playing;
    private FloatingActionButton playAudio;
    private Button post, cancel;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_recorder);


        circularProgressBar = (CircularProgressBar) findViewById(R.id.circularProgressBar);
        playBackDialog = new Dialog(this);
        playBackDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        playBackDialog.setCancelable(false);
        playBackDialog.setContentView(R.layout.audio_playback_dialog);
        playAudio = (FloatingActionButton) playBackDialog.findViewById(R.id.fab);
        post = (Button) playBackDialog.findViewById(R.id.post);
        cancel = (Button) playBackDialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playBackDialog.dismiss();
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playBackDialog.dismiss();
                Toast.makeText(AudioRecorderActivity.this, "Posted Successfully !", Toast.LENGTH_SHORT).show();
            }
        });

        playAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!playing){
                     mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(String.valueOf(file));
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        Toast.makeText(getApplicationContext(), "Playing Audio", Toast.LENGTH_LONG).show();
                       // playAudio.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_media_pause));
                        playAudio.setBackgroundResource(R.drawable.ic_media_pause);
                    } catch (Exception e) {
                        // make something
                    }
                } else {
                    mediaPlayer.stop();
                    playAudio.setBackgroundResource(R.drawable.ic_media_play);
                    //playAudio.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_media_play));
                }
            }
        });

        recordVideo = (ImageView) findViewById(R.id.recordAudio);
        recordVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AudioRecorderActivity.this, "hold to record", Toast.LENGTH_SHORT).show();
            }
        });
        recordVideo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                startRecord();
                isRecordButtonLongPressed = true;
                Toast.makeText(AudioRecorderActivity.this, "pressed", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        recordVideo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    // We're only interested in anything if our speak button is currently pressed.
                    if (isRecordButtonLongPressed) {
                        // Do something when the button is released.
                        Toast.makeText(AudioRecorderActivity.this, "released", Toast.LENGTH_SHORT).show();
                        stopRecord();
                        isRecordButtonLongPressed = false;
                    }
                }

                return false;
            }
        });




    }

    private void stopRecord() {
        circularProgressBar.setProgressValue(0);
        circularProgressBar.setVisibility(View.GONE);
        cTimer.cancel();
        myAudioRecorder.stop();
        myAudioRecorder.release();
        myAudioRecorder = null;
        recordVideo.setImageDrawable(getResources().getDrawable(R.drawable.record_btn));
        playBackDialog.show();
        playBackDialog.setCancelable(false);
        playBackDialog.setCanceledOnTouchOutside(false);
    }

    private void startRecord() {
        File folder = new File(Environment.getExternalStorageDirectory() + "/WhizWish/Audio");
        file = new File(folder.getAbsolutePath(), "wc" + System.currentTimeMillis() + ".3gp");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(String.valueOf(file));
        try {
            myAudioRecorder.prepare();
            myAudioRecorder.start();
            circularProgressBar.setProgressValue(0);
            startCount();
            recordVideo.setImageDrawable(getResources().getDrawable(R.drawable.stop_recording));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void startCount(){

        cTimer = new CountDownTimer(32000, 1000) {
            public void onTick(long millisUntilFinished) {
                double times = ((30000 - (double) millisUntilFinished)/ 30000) * 100;
                float progress = (float) times;
                circularProgressBar.setVisibility(View.VISIBLE);
                circularProgressBar.setProgressValueWithAnimation(progress);
            }
            public void onFinish() {
                circularProgressBar.setProgressValue(0);
                circularProgressBar.setVisibility(View.GONE);
                myAudioRecorder.stop();
                myAudioRecorder.release();
                myAudioRecorder = null;
                recordVideo.setImageDrawable(getResources().getDrawable(R.drawable.record_btn));
                playBackDialog.show();
                playBackDialog.setCancelable(false);
                playBackDialog.setCanceledOnTouchOutside(false);
            }
        };
        cTimer.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent i = new Intent(AudioRecorderActivity.this, GenieActivity.class);
        startActivity(i);
    }
}
