package cn.edu.nuc.dailandsendmessdemo_084131;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SendActivity extends AppCompatActivity {

    private EditText number = null;
    private EditText Mess = null;
    private Button btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        number = (EditText)findViewById(R.id.number);
        Mess = (EditText)findViewById(R.id.Mess);
        btn = (Button)findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = number.getText().toString();
                String content = Mess.getText().toString();
                Intent intent = new Intent();
                intent.setData(Uri.parse("smsto:"+mobile));
                intent.putExtra("sms_body",content);
                startActivity(intent);
            }
        });
    }
}
