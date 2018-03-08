package net.tvplay;


import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

/**
 * Created by egork on 20.02.2018.
 */

public class ExoPlaybackFragment extends android.support.v4.app.Fragment {
    private SimpleExoPlayer player;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Handler mainHandler = new Handler();
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);
        DataSource.Factory defaultDataSourceFactory = new DefaultDataSourceFactory(this.getActivity().getBaseContext(), Util.getUserAgent(getActivity().getApplication(), "mediaPlayerSample"));

// 2. Create the player
        player = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector);
        MediaSource mediaSource = new HlsMediaSource(Uri.parse("http://82.80.192.13/plph_PelReshet1Repeat/_definst_/PelReshet1_1.stream/playlist.m3u8?token=e4106944ec1d7c10075a8cb01ec4677e&ttl=1519228817&cdn_token=c990f143bedbe82de4779855b52592ea"),
                defaultDataSourceFactory, mainHandler,null);
        player.prepare(mediaSource);
        player.setPlayWhenReady(true);
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.playback_layout,null);
        SimpleExoPlayerView simpleExoPlayerView = (SimpleExoPlayerView) view.findViewById(R.id.simple_exo_view);
        simpleExoPlayerView.setPlayer(player);
        return view;
    }
}
