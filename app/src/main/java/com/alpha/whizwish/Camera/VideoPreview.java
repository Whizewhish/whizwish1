package com.alpha.whizwish.Camera;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.VideoView;

import com.alpha.whizwish.Activities.GenieActivity;
import com.alpha.whizwish.R;
import com.universalvideoview.UniversalVideoView;

public class VideoPreview extends AppCompatActivity {

    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_preview);
        String video_path = getIntent().getStringExtra(VideoActivity.VIDEO_PATH);
        videoView = (VideoView) findViewById(R.id.vidShow);

                videoView.setMediaController(null);
                videoView.setVideoURI(Uri.parse(video_path));
                videoView.start();
                videoView.requestFocus();
                //videoView.setZOrderOnTop(true);
        }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent i = new Intent(VideoPreview.this, VideoActivity.class);
        startActivity(i);
    }
}