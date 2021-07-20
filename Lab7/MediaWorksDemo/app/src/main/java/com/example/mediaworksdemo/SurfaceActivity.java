package com.example.mediaworksdemo;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class SurfaceActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private SeekBar sb_main_bar;
    private SurfaceView sv_main_surface;
    private boolean iffirst = false;
    private boolean isChanging=false;
    private Timer mTimer;
    private TimerTask mTimerTask;
    String mockUrl = "https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218114723HDu3hhxqIT.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface);

        sb_main_bar = (SeekBar) findViewById(R.id.sb_main_bar);
        sv_main_surface = (SurfaceView) findViewById(R.id.sv_main_surface);

        //给进度条设置滑动监听
        sb_main_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isChanging=true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isChanging = false;
                //获取当前进度条的位置
                int currentPosition = seekBar.getProgress();
                //跳转到某个位置进行播放
                mediaPlayer.seekTo(currentPosition);
            }
        });


    }
    @Override
    protected void onDestroy() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mTimerTask.cancel();
        mTimer.cancel();
        mediaPlayer.release();
        super.onDestroy();
    }
    public void changeVideoSize() {
        if (mediaPlayer == null) {

            mediaPlayer = new MediaPlayer();

            //设置音频流的类型
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            //设置音源
            try {
                mediaPlayer.setDataSource(this, Uri.parse(mockUrl));
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //将画面展示到 SurfaceView上
            mediaPlayer.setDisplay(sv_main_surface.getHolder());
            mediaPlayer.start();
        }
        int videoWidth = mediaPlayer.getVideoWidth();
        int videoHeight = mediaPlayer.getVideoHeight();

        int surfaceWidth = sv_main_surface.getWidth();
        int surfaceHeight = sv_main_surface.getHeight();

        //根据视频尺寸去计算->视频可以在sufaceView中放大的最大倍数。
        float max;
        if (getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            //竖屏模式下按视频宽度计算放大倍数值
            max = Math.max((float) videoWidth / (float) surfaceWidth, (float) videoHeight / (float) surfaceHeight);
        } else {
            //横屏模式下按视频高度计算放大倍数值
            max = Math.max(((float) videoWidth / (float) surfaceHeight), (float) videoHeight / (float) surfaceWidth);
        }

        //视频宽高分别/最大倍数值 计算出放大后的视频尺寸
        videoWidth = (int) Math.ceil((float) videoWidth / max);
        videoHeight = (int) Math.ceil((float) videoHeight / max);

        //无法直接设置视频尺寸，将计算出的视频尺寸设置到surfaceView 让视频自动填充。
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(videoWidth, videoHeight);
        params.addRule(RelativeLayout.CENTER_VERTICAL, R.id.layoutPlay);
        sv_main_surface.setLayoutParams(params);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //changeVideoSize();
            //mediaPlayer.setDisplay(sv_main_surface.getHolder());
        }   else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            //变成竖屏了
            //changeVideoSize();
            //mediaPlayer.setDisplay(sv_main_surface.getHolder());
        }
    }
    public void playOrPause(View view) {
        final ImageButton imageButton = (ImageButton) view;

        //实例化MediaPlayer
        if (mediaPlayer == null) {

            mediaPlayer = new MediaPlayer();

            //设置音频流的类型
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            //设置音源
            try {
                mediaPlayer.setDataSource(this, Uri.parse(mockUrl));
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //将画面展示到 SurfaceView上
            mediaPlayer.setDisplay(sv_main_surface.getHolder());
            mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                @Override
                public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                    changeVideoSize();
                }
            });
            mediaPlayer.start();
//                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                    @Override
//                    public void onPrepared(MediaPlayer arg0) {
//                        // 首先取得video的宽和高
//                        int vWidth = mediaPlayer.getVideoWidth();
//                        int vHeight = mediaPlayer.getVideoHeight();
//
//                        // 该LinearLayout的父容器 android:orientation="vertical" 必须
////                        int lw = sv_main_surface.getWidth();
////                        int lh = sv_main_surface.getHeight();
//                        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutPlay);
//                        int lw = linearLayout.getWidth();
//                        int lh = linearLayout.getHeight();
//                        if (vWidth > lw || vHeight > lh) {
//                            // 如果video的宽或者高超出了当前屏幕的大小，则要进行缩放
//                            float wRatio = (float) vWidth / (float) lw;
//                            float hRatio = (float) vHeight / (float) lh;
//
//                            // 选择大的一个进行缩放
//                            float ratio = Math.max(wRatio, hRatio);
//                            vWidth = (int) Math.ceil((float) vWidth / ratio);
//                            vHeight = (int) Math.ceil((float) vHeight / ratio);
//
//                            // 设置surfaceView的布局参数
//                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(vWidth, vHeight);
//                            lp.gravity = Gravity.CENTER;
//                            sv_main_surface.setLayoutParams(lp);
//
//                        }
//                        // 然后开始播放视频
//                        mediaPlayer.start();
//                    }
//                });
            //暂停图标
            imageButton.setImageResource(android.R.drawable.ic_media_pause);

            //获取音乐的播放时间
            int time = mediaPlayer.getDuration();

            //设置进度条的最大值 为  视频的播放时间
            sb_main_bar.setMax(time);

            //----------定时器记录播放进度---------//
            mTimer = new Timer();
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    if(isChanging==true) {
                        return;
                    }
                    sb_main_bar.setProgress(mediaPlayer.getCurrentPosition());
                }
            };
            mTimer.schedule(mTimerTask, 0, 10);
            iffirst=true;


        } else if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            //播放图标
            imageButton.setImageResource(android.R.drawable.ic_media_play);
        } else {
            mediaPlayer.start();
            //暂停图标
            imageButton.setImageResource(android.R.drawable.ic_media_pause);
        }
    }
}


