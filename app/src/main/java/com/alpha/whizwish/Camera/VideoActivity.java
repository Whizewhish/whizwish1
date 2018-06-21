package com.alpha.whizwish.Camera;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alpha.whizwish.Activities.GenieActivity;
import com.alpha.whizwish.R;
import com.brkckr.circularprogressbar.CircularProgressBar;
import com.otaliastudios.cameraview.Audio;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.Facing;
import com.otaliastudios.cameraview.Flash;
import com.otaliastudios.cameraview.SessionType;
import com.otaliastudios.cameraview.VideoCodec;

import java.io.File;

public class VideoActivity extends AppCompatActivity {

    public static final String VIDEO_PATH = "video path";
    private CameraView cameraView;
    private boolean capturing = false;
    private boolean hasFlash;
    private ImageView flashIcon, recordVideo;
    private CircularProgressBar circularProgressBar;
    private CountDownTimer cTimer = null;
    private boolean isRecordButtonLongPressed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video);
        cameraView= (CameraView) findViewById(R.id.camera);
        flashIcon = (ImageView) findViewById(R.id.flashIcon);
        circularProgressBar = (CircularProgressBar) findViewById(R.id.circularProgressBar);
        recordVideo = (ImageView) findViewById(R.id.recordVideo);

        recordVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(VideoActivity.this, "hold to record", Toast.LENGTH_SHORT).show();
            }
        });
        recordVideo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                startCapture();
                isRecordButtonLongPressed = true;
                Toast.makeText(VideoActivity.this, "pressed", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(VideoActivity.this, "released", Toast.LENGTH_SHORT).show();
                        stopCapture();
                        isRecordButtonLongPressed = false;
                    }
                }

                return false;
            }
        });
        cameraView.addCameraListener(new CameraListener() {
            @Override
            public void onVideoTaken(File video) {
                super.onVideoTaken(video);
                new saveVideo(video).execute();

            }
        });
        checkFlash();


    }

    private class saveVideo extends AsyncTask<Void, Void, Void>{

        private File file;
        String path;

        public saveVideo(File file){
            this.file = file;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            path = file.getPath();
            return null;


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Toast.makeText(VideoActivity.this, path, Toast.LENGTH_SHORT).show();
            finish();

             Intent intent = new Intent(VideoActivity.this, VideoPreview.class);
            intent.putExtra(VIDEO_PATH, path);
            startActivity(intent);

        }
    }


    private void checkFlash() {

        hasFlash = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!hasFlash) {

            Toast.makeText(this, "Flash not supported on this device !", Toast.LENGTH_SHORT).show();
            flashIcon.setVisibility(View.GONE);

        } else {

            flashIcon.setVisibility(View.VISIBLE);
            if (cameraView.getFlash().toString() == "ON") {
                flashIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_flash_auto));
                cameraView.setFlash(Flash.AUTO);
            } else if (cameraView.getFlash().toString() == "AUTO") {
                flashIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_flash_off));
                cameraView.setFlash(Flash.OFF);

            } else {
                flashIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_flash_on));
                cameraView.setFlash(Flash.ON);
            }
        }

    }

    public void rotateCamera(View view) {
        if (capturing){
            Toast.makeText(this, "Can't rotate Camera during recording !", Toast.LENGTH_SHORT).show();
        } else  {

            if (cameraView.getFacing().toString() == "BACK"){
                cameraView.setFacing(Facing.FRONT);

            }else {
                cameraView.setFacing(Facing.BACK);

            }
        }

    }

    public void startCapture (){

        File folder = new File(Environment.getExternalStorageDirectory() + "/WhizWish/Videos");
        File file = new File(folder.getAbsolutePath(), "wc" + System.currentTimeMillis() + ".mp4");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        circularProgressBar.setProgressValue(0);

        cameraView.startCapturingVideo(file);
        cameraView.setVideoCodec(VideoCodec.H_264);
        startCount();
        recordVideo.setImageDrawable(getResources().getDrawable(R.drawable.stop_recording));

        capturing = true;

    }

    public void stopCapture(){
        cameraView.stopCapturingVideo();
        circularProgressBar.setVisibility(View.GONE);
        recordVideo.setImageDrawable(getResources().getDrawable(R.drawable.capture));
        cTimer.cancel();
        capturing = false;
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
                cameraView.stopCapturingVideo();
                circularProgressBar.setVisibility(View.GONE);
            }
        };
        cTimer.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraView.start();


    }


    @Override
    protected void onPause() {
        super.onPause();
        cameraView.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraView.destroy();
    }

    public void changeFlash(View view) {

        if (capturing){
            Toast.makeText(this, "Can't change flash mode during recording !", Toast.LENGTH_SHORT).show();
        } else {
            if (cameraView.getFlash().toString() == "ON") {
                flashIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_flash_auto));
                cameraView.setFlash(Flash.AUTO);
            } else if (cameraView.getFlash().toString() == "AUTO"){
                flashIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_flash_off));
                cameraView.setFlash(Flash.OFF);

            } else {
                flashIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_flash_on));
                cameraView.setFlash(Flash.ON);
            }
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent i = new Intent(VideoActivity.this, GenieActivity.class);
        startActivity(i);
    }
}
