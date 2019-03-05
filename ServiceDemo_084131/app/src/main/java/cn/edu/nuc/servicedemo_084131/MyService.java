package cn.edu.nuc.servicedemo_084131;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";
    private boolean quit = false;
    private int count = 0;
    private MyBinder myBinder = new MyBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"MyService onBind invoked!");
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"MyService onCreate invoked!");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!quit){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"MyService onDestroy invoked!");
        quit = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"MyService onStartCommand invoked!");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG,"MyService onRebind invoked!");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"MyService onUnbind invoked!");
        return super.onUnbind(intent);
    }

    public class MyBinder extends Binder{
        public MyBinder(){
            Log.i(TAG,"MyBinder:Constructure invoked!!");
        }
        public int getCount(){
            return count;
        }
    }

}
