package cn.edu.nuc.musicplayerdemo_084131;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
public class MainActivity extends Activity implements View.OnClickListener{
    TextView title = null;
    TextView author = null;
    ImageButton stop = null;
    ImageButton play = null;

    public static final String CONTROL="zg.ym.zhang.android.control";
    public static final String UPDATE="zg.ym.zhang.android.update";

    ActivityReceiver activityReceiver;
    int status = 0x11;  //0x11未播放  0x12正在播放   0x13暂停
    String[] titleStrs = new String[]{"好久不见","齐天大圣","她说"};
    String[] authorStrs = new String[]{"Eason","huahua","JJ"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (TextView)findViewById(R.id.title);
        author= (TextView)findViewById(R.id.author);
        stop = (ImageButton)findViewById(R.id.stop);
        play = (ImageButton)findViewById(R.id.play);


        stop.setOnClickListener(MainActivity.this);
        play.setOnClickListener(MainActivity.this);

        activityReceiver = new ActivityReceiver();
        IntentFilter filter = new IntentFilter(UPDATE);
        registerReceiver(activityReceiver,filter);
        Intent intent = new Intent(MainActivity.this,MusicService.class);
        startService(intent);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(CONTROL);
        switch (v.getId()){
            case R.id.stop:
                intent.putExtra("control",2);break;
            case R.id.play:
                intent.putExtra("control",1);break;
        }
        sendBroadcast(intent);
    }

    public class ActivityReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO: This method is called when the BroadcastReceiver is receiving
            // an Intent broadcast.
            int update = intent.getIntExtra("update",-1);
            int current = intent.getIntExtra("current",-1);

            if(current>=0){
                title.setText(titleStrs[current]);
                author.setText(authorStrs[current]);
            }
            switch (update){
                case 0x11:
                    play.setImageResource(R.drawable.play);
                    status = 0x11;
                    break;
                case 0x12:
                    play.setImageResource(R.drawable.pause);
                    status = 0x12;
                    break;
                case 0x13:
                    play.setImageResource(R.drawable.play);
                    status = 0x13;
                    break;
            }
        }
    }
}
