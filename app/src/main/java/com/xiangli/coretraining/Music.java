package com.xiangli.coretraining;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Music extends AppCompatActivity {
    private ListView listPlay;
    private List<Map<String, String>> data;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        } else {
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            init();
        } else {
            finish();
        }
    }

    private void init() {
        listPlay = findViewById(R.id.listplay);

        data = new LinkedList<>();
        readMusicList();
        String[] title = {"title"};
        int[] res = {R.id.item_title};
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.listitem, title, res);
        listPlay.setAdapter(adapter);

        listPlay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                playMusic(position);
            }
        });

        Intent intent = new Intent(this, PlayMusicService.class);
        intent.putExtra("state", "init");
        startService(intent);
    }

    private void playMusic(int index) {
        String musicFile = data.get(index).get("filename");

        Intent intent = new Intent(this, PlayMusicService.class);
        intent.putExtra("state", "play");
        intent.putExtra("playmusic", musicFile);
        startService(intent);
    }

    public void restartPlay(View view) {
        Intent intent = new Intent(this, PlayMusicService.class);
        intent.putExtra("state", "restart");
        startService(intent);
    }

    public void stopPlay(View view) {
        Intent intent = new Intent(this, PlayMusicService.class);
        intent.putExtra("state", "stop");
        startService(intent);
    }

    private void readMusicList() {
        File musicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        File[] files = musicDir.listFiles();
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        for (File musicFile : files) {
            if (musicFile.isFile()) {
                mmr.setDataSource(musicFile.toString());

                String titleName = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
                String artistName = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);

                HashMap<String, String> music = new HashMap<>();
                music.put("title", titleName + "-" + artistName);
                music.put("filename", musicFile.toString());
                data.add(music);
            }
        }
    }
}
