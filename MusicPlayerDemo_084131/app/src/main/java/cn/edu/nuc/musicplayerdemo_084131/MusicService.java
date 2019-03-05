package cn.edu.nuc.musicplayerdemo_084131;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;

public class MusicService extends Service {

    ServiceReceiver serviceReceiver;
    AssetManager am;
    String[] musics = new String[]{"longtimenosee.mp3","qitiandasheng.mp3","tashuo.mp3"
    };
    MediaPlayer mediaPlayer;
    int status =0x11;
    int current=0;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        am = getAssets();
        serviceReceiver = new ServiceReceiver();
        IntentFilter filter = new IntentFilter(MainActivity.CONTROL);
        registerReceiver(serviceReceiver,filter);
        mediaPlayer = new MediaPlayer();
        super.onCreate();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                current++;
                if(current>=3){
                    current=0;
                }
                Intent sendIntent = new Intent(MainActivity.UPDATE);
                sendIntent.putExtra("current",current);
                sendBroadcast(sendIntent);
                prepareAndPlay(musics[current]);
            }
        });
    }
    private void prepareAndPlay(String music){
        try {
            AssetFileDescriptor assetFileDescriptor = am.openFd(music);
            mediaPlayer.reset();
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public class ServiceReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            int control = intent.getIntExtra("control",-1);
            switch (control){
                case 1:
                    if(status==0x11){
                        prepareAndPlay(musics[current]);
                        status=0x12;
                    }
                    else if(status==0x12){
                        mediaPlayer.pause();
                        status=0x13;
                    }
                    else if(status==0x13){
                        mediaPlayer.start();
                        status=0x12;
                    }
                    break;
                case 2:
                    if(status==0x12||status==0x13){
                        mediaPlayer.stop();
                        status=0x11;
                    }
            }
            Intent sendIntent = new Intent(MainActivity.UPDATE);
            sendIntent.putExtra("update",status);
            sendIntent.putExtra("current",current);
            sendBroadcast(sendIntent);
        }
    }
}

