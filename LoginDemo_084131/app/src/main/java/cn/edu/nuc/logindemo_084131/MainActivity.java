package cn.edu.nuc.logindemo_084131;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        //textView.setText("欢迎参加手机设计大赛");
        textView.setText(Html.fromHtml("欢迎参加<font color = red>"+"手机软件设计赛</font>"));
    }
}
