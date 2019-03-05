package cn.edu.nuc.registerdemo_084131;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView resultName = null;
    private TextView resultPsd = null;
    private TextView resultGender = null;
    private TextView resultCity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultName = (TextView)findViewById(R.id.resultName);
        resultPsd = (TextView)findViewById(R.id.resultPsd);
        resultGender = (TextView)findViewById(R.id.resultGender);
        resultCity = (TextView)findViewById(R.id.resultCity);

        Intent intent = getIntent();//获取到从Mainactivity传过来的intent

        resultName.setText(intent.getStringExtra("name"));
        resultPsd.setText(intent.getStringExtra("psd"));
        resultGender.setText(intent.getStringExtra("gender"));
        resultCity.setText(intent.getStringExtra("city"));
    }
}
