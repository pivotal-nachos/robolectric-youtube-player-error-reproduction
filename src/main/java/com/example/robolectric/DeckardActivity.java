package com.example.robolectric;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import com.example.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

public class DeckardActivity extends FragmentActivity {
    private static final String API_KEY = "A_VALID_API_KEY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deckard);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                playYoutube();
            }
        }, 1000);
    }

    public void playYoutube() {
        YouTubePlayerSupportFragment fragment = new YouTubePlayerSupportFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                fragment).commit();
        fragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
                youTubePlayer.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION);

                youTubePlayer.loadVideo("AaEmCFiNqP0");  // smooth jazz nyan cat, of course
                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(DeckardActivity.this, "Can't handle nyan cat", Toast.LENGTH_LONG);
            }
        });
    }
}
