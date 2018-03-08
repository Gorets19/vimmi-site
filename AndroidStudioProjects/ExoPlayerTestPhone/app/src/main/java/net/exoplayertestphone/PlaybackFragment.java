package net.exoplayertestphone;


import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;


/**
 * Created by egork on 20.02.2018.
 */

public class PlaybackFragment extends Fragment {
    private SimpleExoPlayer player;
    private SimpleExoPlayerView simpleExoPlayerView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.playba_frag,null);
      //  simpleExoPlayerView = (SimpleExoPlayerView) view.findViewById(R.id.exo_view);
        //simpleExoPlayerView.setPlayer(player);
        Log.d("MYTAG","onCreateViewF");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //getMediaSource(getString(R.string.mp4_sample_url),getString(R.string.subtitles_sample_url));
       // player.setPlayWhenReady(true);
    }



    private void getMediaSource(String videoUrl, String subtitlesUrl){
        Handler mainHandler = new Handler();
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);
// 2. Create the player
        player = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector);
        DataSource.Factory defaultDataSourceFactory = new DefaultDataSourceFactory(this.getActivity().getBaseContext(), Util.getUserAgent(getActivity().getApplication(), "mediaPlayerSample"));
        MediaSource mediaSource = new ExtractorMediaSource.Factory(defaultDataSourceFactory).createMediaSource(Uri.parse(videoUrl));
        Format subtitleFormat = Format.createTextSampleFormat("id",MimeTypes.APPLICATION_SUBRIP,C.SELECTION_FLAG_DEFAULT,null);
        MediaSource subtitleSource = new SingleSampleMediaSource.Factory(defaultDataSourceFactory).createMediaSource(Uri.parse(subtitlesUrl),subtitleFormat,
                C.TIME_UNSET);
        MergingMediaSource mergingMediaSource = new MergingMediaSource(mediaSource, subtitleSource);
        player.prepare(mergingMediaSource);
        player.setPlayWhenReady(true);
        simpleExoPlayerView.setPlayer(player);
    }




}
