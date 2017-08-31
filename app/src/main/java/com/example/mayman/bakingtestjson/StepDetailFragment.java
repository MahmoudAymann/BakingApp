package com.example.mayman.bakingtestjson;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mayman.bakingtestjson.Adapters.ExoPlayerAdapter;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StepDetailFragment extends Fragment implements ExoPlayer.EventListener, ExoPlayerAdapter.onClickListener {

    private ExoPlayerAdapter adapter;
    private StepObjs stepObjs;
    private ArrayList<StepObjs> stepObjsArrayList;

    private OnFragmentInteractionListener mListener;
    private int iD;
    private Boolean tabView;
    //--------------------

    private SimpleExoPlayer player;
    private MediaSessionCompat mediaSession;
    private PlaybackStateCompat.Builder stateBuilder;

    @InjectView(R.id.imageView2) ImageView imageView;
    @InjectView(R.id.recycle) RecyclerView recyclerView;
    @InjectView(R.id.Simple_exo) SimpleExoPlayerView exoPlayerView;

    public StepDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_step_detail, container, false);

        ButterKnife.inject(this, root);

        if (savedInstanceState != null) {
            iD = savedInstanceState.getInt("iD", 0);
            stepObjs = stepObjsArrayList.get(iD);
        }
        //--------------------------------------------------------------------------
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ExoPlayerAdapter(stepObjs, getContext(), this);
        recyclerView.setAdapter(adapter);
        //------------------------------------------------------------------------
        checkSum();
        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int Activity = 0;

        try {
            if (getActivity().getClass().getSimpleName().equals(StepDetailActivity.class.getSimpleName()) || getActivity().getClass().getSimpleName().equals(MasterDetailActivity.class.getSimpleName())) {
                Activity = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Activity == 1 && getActivity().getIntent() != null) {
            stepObjs = new StepObjs();

            stepObjsArrayList = new ArrayList<>();

            stepObjsArrayList = getActivity().getIntent().getParcelableArrayListExtra("list");

            iD = getActivity().getIntent().getExtras().getInt("idx", 0);
            tabView = getActivity().getIntent().getExtras().getBoolean("tab");
            stepObjs = stepObjsArrayList.get(iD);
        } else if (getArguments() != null) {
            stepObjs = new StepObjs();
            // recipeItem =(RecipeItem)getArguments().getSerializable(getString(R.string.bundleK1));
            iD = getArguments().getInt("iD");
            tabView = getArguments().getBoolean("tab");
            stepObjsArrayList = getArguments().getParcelableArrayList("list");
            stepObjs = stepObjsArrayList.get(iD);
        }
        try {
            getActivity().setTitle(stepObjs.getShortDescription());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void checkSum() {
        if (!stepObjs.getVideoURL().equals("")) {
            imageView.setVisibility(View.INVISIBLE);
            exoPlayerView.setVisibility(View.VISIBLE);
            if (!dimension() && !tabView) {
                recyclerView.setVisibility(View.INVISIBLE);
                ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
            }
            initializeMediaSession();
            configurePlayer();
        } else {

            exoPlayerView.setVisibility(View.INVISIBLE);
            imageView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    void configurePlayer() {
        if (player == null) {
            // 1. Create a default TrackSelector
            //    BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter(); ->green screen on pause r2.0.1
            //     TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);

            TrackSelector trackSelector = new DefaultTrackSelector();//add vedio trac selection
            // 2. Create a default LoadControl
            LoadControl loadControl = new DefaultLoadControl();
            // 3. Create the player
            player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            exoPlayerView.setPlayer(player);
            exoPlayerView.setKeepScreenOn(true);
            player.addListener(this);
            player.prepare(MediaSource(stepObjs.getVideoURL()));
            player.setPlayWhenReady(true);
        }//end if

    }//end configurePlayer

    Boolean dimension() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        return height > width;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionSelectRecipe");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void OnClick(int c) {
        if (c == 0) {
            iD += 1;
            iD %= stepObjsArrayList.size();
            stepObjs = stepObjsArrayList.get(iD);
            restExoPlayer(0, false);
            checkSum();
            adapter.changeDataSet(stepObjs);
        } else {
            iD -= iD == 0 ? 0 : 1;
            restExoPlayer(0, false);
            stepObjs = stepObjsArrayList.get(iD);
            restExoPlayer(0, false);
            checkSum();
            iD %= stepObjsArrayList.size();
            adapter.changeDataSet(stepObjs);
        }

    }

    @Override
    public void change(int idx) {
        iD = idx;
        stepObjs = stepObjsArrayList.get(idx);
        restExoPlayer(0, false);
        checkSum();
        adapter.changeDataSet(stepObjs);
    }

    interface OnFragmentInteractionListener {
        void onFragmentInteraction(Object uri);
    }


    MediaSource MediaSource(String url) {

        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), "ExoPlayer"));
        // Produces Extractor instances for parsing the media data.
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        // This is the MediaSource representing the media to be played.
        return new ExtractorMediaSource(Uri.parse(url),
                dataSourceFactory, extractorsFactory, null, null);
    }

    private void initializeMediaSession() {

        mediaSession = new MediaSessionCompat(getContext(), "SingleStepPage");

        mediaSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        mediaSession.setMediaButtonReceiver(null);
        stateBuilder = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                                PlaybackStateCompat.ACTION_PAUSE |
                                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                                PlaybackStateCompat.ACTION_PLAY_PAUSE);

        mediaSession.setPlaybackState(stateBuilder.build());
        mediaSession.setCallback(new MediaSessionCompat.Callback() {
            @Override
            public void onPlay() {
                player.setPlayWhenReady(true);
            }

            @Override
            public void onPause() {
                player.setPlayWhenReady(false);
            }

            @Override
            public void onSkipToPrevious() {
                player.seekTo(0);
            }
        });
        mediaSession.setActive(true);
    }

    private void releasePlayer() {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
        if (mediaSession != null) {
            mediaSession.setActive(false);
        }
    }

    private void restExoPlayer(long position, boolean playWhenReady) {
        if (player == null) return;
        ;
        player.seekTo(position);
        player.setPlayWhenReady(playWhenReady);
        releasePlayer();
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if (playbackState == ExoPlayer.STATE_BUFFERING) {
            mListener.onFragmentInteraction(true);
            stateBuilder.setState(PlaybackStateCompat.STATE_BUFFERING, player.getCurrentPosition(), 1f);
        } else if (playbackState == ExoPlayer.STATE_READY && playWhenReady) {
            stateBuilder.setState(PlaybackStateCompat.STATE_PLAYING, player.getCurrentPosition(), 1f);
            mListener.onFragmentInteraction(true);
        } else if (playbackState == ExoPlayer.STATE_READY) {

            stateBuilder.setState(PlaybackStateCompat.STATE_PAUSED, player.getCurrentPosition(), 1f);
            mListener.onFragmentInteraction(true);
        }
        mediaSession.setPlaybackState(stateBuilder.build());
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        //  if (player!=null) player.setPlayWhenReady(false);
        releasePlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        //    if (player!=null) player.setPlayWhenReady(true);
        checkSum();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("iD", iD);
        super.onSaveInstanceState(outState);
    }

    public void solv(int idx) {
        iD = idx;
        stepObjs = stepObjsArrayList.get(idx);
        restExoPlayer(0, false);
        checkSum();
        adapter.changeDataSet(stepObjs);
    }

}//end StepDetailFragment