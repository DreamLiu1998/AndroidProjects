package cn.edu.nuc.asynctaskdemo_084131;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn = null;
    private TextView tv = null;
    private ProgressBar pb = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.btn);
        tv = (TextView) findViewById(R.id.tv);
        pb = (ProgressBar)findViewById(R.id.pb);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownLoadTask downLoadTask = new DownLoadTask(tv,pb);
                downLoadTask.execute(100);

            }
        });

    }
}
