package com.example.dvdlogo;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private CustomView customView;
    private boolean isPartyMode = false;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customView = new CustomView(this);
        setContentView(customView);

        // Check if Party Mode is enabled via Intent extra (equivalent of command-line flag)
        isPartyMode = getIntent().getBooleanExtra("PARTY_MODE", false);

        if (isPartyMode) {
            playAudioLoop();
            customView.setPartyMode(true); // Enable faster movement in Party Mode
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.upload_image:
                // Implement image upload
                return true;
            case R.id.upload_audio:
                // Implement audio upload
                return true;
            case R.id.toggle_fullscreen:
                // Implement fullscreen toggle
                return true;
            case R.id.toggle_party_mode:
                isPartyMode = !isPartyMode;
                customView.setPartyMode(isPartyMode);
                if (isPartyMode) {
                    playAudioLoop();
                } else {
                    stopAudio();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void playAudioLoop() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.default_party_mode); // Replace with default audio file
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
    }

    private void stopAudio() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopAudio();
    }
}
