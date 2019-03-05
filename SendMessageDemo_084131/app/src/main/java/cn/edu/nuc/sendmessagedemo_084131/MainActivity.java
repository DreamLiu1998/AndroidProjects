package cn.edu.nuc.sendmessagedemo_084131;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText number = null;
    private EditText message = null;
    private Button sendMessage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = (EditText)findViewById(R.id.number);
        message = (EditText)findViewById(R.id.message);
        sendMessage = (Button)findViewById(R.id.sendMessage);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = number.getText().toString().trim();
                String content = message.getText().toString().trim();
                SmsManager smsManager = SmsManager.getDefault();
                List<String> msgs = smsManager.divideMessage(content);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,new Intent(),0);
                for(String msg:msgs){
                    smsManager.sendTextMessage(mobile,null,msg,pendingIntent,null);
                }
            }
        });
    }
}
