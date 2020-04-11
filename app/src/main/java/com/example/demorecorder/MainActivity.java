package com.example.demorecorder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static int VIDEO_REQUEST=101;
    private Uri videoUri=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void captureVideo(View view) {
        Intent videointent= new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        if (videointent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(videointent,VIDEO_REQUEST);
        }
    }

    public void playVideo(View view) {
        try {
            Intent playIntent= new Intent(this,VideoPlayScreen.class);
            playIntent.putExtra("videoUri",videoUri.toString());
            startActivity(playIntent);
        }catch (Exception e){
            Toast.makeText(this, "Record Video First", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == VIDEO_REQUEST && resultCode == RESULT_OK) {
            videoUri = data.getData();
        }

    }
}
