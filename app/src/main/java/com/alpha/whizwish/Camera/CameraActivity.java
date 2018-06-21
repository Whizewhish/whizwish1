package com.alpha.whizwish.Camera;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.alpha.whizwish.Activities.GenieActivity;
import com.alpha.whizwish.R;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.Facing;
import com.otaliastudios.cameraview.Flash;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CameraActivity extends AppCompatActivity {


    public static final String IMAGE_PATH = "Image Preview Path";
    private ImageView cap, flashIcon;
    private CameraView cameraView;
    private boolean hasFlash;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_camera);

        progressDialog = new ProgressDialog(this);

        cap = (ImageView) findViewById(R.id.takePic);
        cameraView = (CameraView) findViewById(R.id.camera);
        flashIcon = (ImageView) findViewById(R.id.flashIcon);
        cameraView.addCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(byte[] jpeg) {
                super.onPictureTaken(jpeg);
                new saveCapturedPic(jpeg).execute();
            }
        });

        checkFlash();

       cap.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               cameraView.capturePicture();
           }
       });
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

    public void rotateCamera(View view) {

        if (cameraView.getFacing().toString() == "BACK"){
            cameraView.setFacing(Facing.FRONT);
            flashIcon.setVisibility(View.GONE);
        }else {
            cameraView.setFacing(Facing.BACK);
            flashIcon.setVisibility(View.VISIBLE);
        }
    }
    public void changeFlash(View view) {

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




    private static int fixOrientation(Bitmap bitmap) {
        if (bitmap.getWidth() > bitmap.getHeight()) {
            return 90;
        }
        return 0;
    }


 public static Bitmap flipIMage(Bitmap bitmap) {


        Matrix matrix = new Matrix();
        int rotation = fixOrientation(bitmap);
        matrix.postRotate(rotation);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }





    private class saveCapturedPic extends AsyncTask<Void, Void, Void>{
        private byte[] data;
        String path;
        Bitmap bitmap;;
        public saveCapturedPic(byte[] data) {
            this.data = data;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cameraView.stop();
            progressDialog.setMessage("Please wait ...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {
            //create a folder to get image
            File folder = new File(Environment.getExternalStorageDirectory() + "/WhizWish");
            if (!folder.exists()) {
                folder.mkdirs();
            }
             if (bitmap!= null){
                 bitmap.recycle();
             } else {
                 bitmap = flipIMage(BitmapFactory.decodeByteArray(data, 0, data.length));
                 File file = new File(folder.getAbsolutePath(), "wc" + System.currentTimeMillis() + ".jpg");
                 OutputStream output = null;

                 try {
                     output = new FileOutputStream(file);
                     bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
                     output.flush();
                     output.close();
                     path = file.getAbsolutePath();


                     Log.d("tag", "IMAGEPATH:" + path);
                 } catch (FileNotFoundException e) {
                     e.printStackTrace();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }

             }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            progressDialog.dismiss();
            finish();
            Intent i = new Intent(CameraActivity.this, PicturePreview.class);
            i.putExtra(IMAGE_PATH, path);
            startActivity(i);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent i = new Intent(CameraActivity.this, GenieActivity.class);
        startActivity(i);
    }
}
