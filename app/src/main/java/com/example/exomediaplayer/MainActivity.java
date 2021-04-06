package com.example.exomediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class MainActivity extends AppCompatActivity {

    PlayerView playerView;
    SimpleExoPlayer simpleExoPlayer;
    public static final String URL = "https://aspb16.cdn.asset.aparat.com/aparat-video/af89775216322acf292c0b4961c0ba6410272020-480p.mp4?wmsAuthSign=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbiI6ImViNGNmYjY4YWY0MWE1MDU1NzEzMmM4MGJjNTM1N2M0IiwiZXhwIj" +
            "oxNjE3NTg4NzEyLCJpc3MiOiJTYWJhIElkZWEgR1NJRyJ9.qByUHPqasxC4WWskMojmjCubbEfXIEaxLrRVCuudwfM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initExoPlayer();
    }

    private void initExoPlayer() {

        playerView = findViewById(R.id.exo_media_player);
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this);
        playerView.setPlayer(simpleExoPlayer);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, Util
                .getUserAgent(this , "appname"));
        MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(URL));
        simpleExoPlayer.prepare(mediaSource);
        simpleExoPlayer.setPlayWhenReady(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        simpleExoPlayer.setPlayWhenReady(false);
        simpleExoPlayer.release();
    }
}