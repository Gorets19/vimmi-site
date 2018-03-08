package net.tvplay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;




/**
 * Created by egork on 20.02.2018.
 */

public class ExoPlayback extends FragmentActivity {
    //SimpleExoPlayer player;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(android.R.id.content,new ExoPlaybackFragment())
                    .commit();
        }

    }

}
