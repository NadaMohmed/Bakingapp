package com.example.ecommerce.bakingapp;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;


public class detail_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;
    private TextView directions ;
    private boolean isFullScreen = false;
    private static final String EXO_CURRENT_POSITION = "current_position";
    private long exo_current_position = 0;
    private static final String IS_FULLSCREEN = "is_fullscreen";
    private boolean isNull;
    private static final String IS_NULL = "is_null";
    private String videoUrl;
    private ArrayList<steps> stepsArrayList ;
    private  int position ;
    private SimpleExoPlayer exoPlayer;
    private long playerStopPosition;
    private boolean playerStopped = false;

    public void setStep(steps step) {
        this.step = step;
    }

    public steps getStep() {
        return step;
    }

    private  steps step ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private OnFragmentInteractionListener mListener;

    public detail_fragment() {
        // Required empty public constructor
    }

    public static detail_fragment newInstance(ArrayList<steps> sList, int position) {

        detail_fragment fragment = new detail_fragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("steplist",sList);
        args.putInt("position", position);

        // Set true if nothing was selected,
        // needed in case in tablet mode
        if(position == -1){
            args.putBoolean(IS_NULL,true);
        } else {
            args.putBoolean(IS_NULL,false);
        }

        fragment.setArguments(args);
        return fragment;
    }

    // TODO: Rename and change types and number of parameters
  /*  public static detail_fragment newInstance(String param1, String param2) {
        detail_fragment fragment = new detail_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
*/
  /*  @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        isNull = getArguments().getBoolean(IS_NULL);
        if(!isNull) {
            stepsArrayList = getArguments().getParcelableArrayList("steplist");
            position = getArguments().getInt("position");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_detail_fragment, container, false);
        // Inflate the layout for this fragment
        // Initialize the player view.
        mPlayerView = (SimpleExoPlayerView) rootview.findViewById(R.id.playerView);

        directions = (TextView)rootview.findViewById(R.id.direction) ;

        checkFullScreen();

        if(savedInstanceState == null){
            isFullScreen = false;
        } else {
            isFullScreen = savedInstanceState.getBoolean(IS_FULLSCREEN);
            exo_current_position = savedInstanceState.getLong(EXO_CURRENT_POSITION);
        }


       stepsArrayList =getArguments().getParcelableArrayList("steplist") ;
        position = getArguments().getInt("position") ;
        step = getArguments().getParcelable("step") ;
        videoUrl = stepsArrayList.get(position).getVideoURL() ;
     if (directions!= null)
     {
         directions.setText(stepsArrayList.get(position).getDescription());

     }


           /* videoUrl = sList.get(position).getVideoURL();
            description = sList.get(position).getShortDescription();
            directionString = sList.get(position).getDescription();
            thumbnailUrl = sList.get(position).getThumbnailURL();

*/

        rootview.findViewById(R.id.exo_full).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFullScreen) {
                    isFullScreen = true;
                    getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                } else {
                    isFullScreen = false;
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }
            }
        });


        if (!videoUrl.equals("")) {
            initializePlayer(Uri.parse(videoUrl));
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

        } else {

         /*   assert mExoPlayer != null;
            mExoPlayer.setVisibility(View.GONE);


            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);*/
        }



        return rootview;

}

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_FULLSCREEN,isFullScreen);
        outState.putLong(EXO_CURRENT_POSITION,mExoPlayer.getCurrentPosition());
    }


    @Override
    public void onStart() {
        super.onStart();
        initializePlayer(Uri.parse(videoUrl));
    }


    @Override
    public void onStop() {
        super.onStop();
        if(mExoPlayer != null) {
            playerStopPosition = mExoPlayer.getCurrentPosition();
            playerStopped = true;
            releasePlayer();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releasePlayer();
        //unbinder.unbind();
    }


    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);

            // Set the ExoPlayer.EventListener to this activity.
            //mExoPlayer.addListener(this);

            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent(getContext(), "ClassicalMusicQuiz");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    /**
     * Method to release exoPlayer
     */
    private void releasePlayer(){
        if(mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }
    public void checkFullScreen(){
        Configuration newConfig = new Configuration();
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            isFullScreen = true;
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            isFullScreen = false;
        }
    }


}