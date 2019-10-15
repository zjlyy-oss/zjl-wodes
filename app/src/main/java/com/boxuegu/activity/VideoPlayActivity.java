package com.boxuegu.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.boxuegu.R;

public class VideoPlayActivity extends AppCompatActivity {
    private VideoView videoView;
    private MediaController controller;
    private String videoPath;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        //设置界面全屏显示
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView( R.layout.activity_video_play );
        //设置此界面为横屏
        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE );
        //获取播放记录界面的视频地址
        videoPath=getIntent().getStringExtra( "videoPath" );
        position=getIntent().getIntExtra( "position",0 );
        init();
    }



    //初始化UI控件
    private void init(){
        videoView=(VideoView)findViewById( R.id.videoView );
        controller=new MediaController( this );
        videoView.setMediaController( controller );
        play();
    }
    //播放视频
    private void play(){
        if (TextUtils.isEmpty( videoPath )){
            Toast.makeText( this,"本地没有视频，暂无法播放",Toast.LENGTH_SHORT ).show();
            return;
        }
        String uri="android.resource://" + getPackageName() + "/raw/" + videoPath ;
        videoView.setVideoPath( uri );

        videoView.start();
    }
    //点击后退按钮
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent data=new Intent(  );
        data.putExtra( "position",position );
        setResult( RESULT_OK,data );
        return super.onKeyDown( keyCode, event );
    }
}
