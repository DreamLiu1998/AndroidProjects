package cn.edu.nuc.asynctaskdemo_084131;

import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DownLoadTask extends AsyncTask<Integer,Integer,String> {

    private TextView tv = null;
    private ProgressBar pb = null;

    public DownLoadTask(TextView tv, ProgressBar pb) {
        this.tv = tv;
        this.pb = pb;
    }

    @Override
    protected String doInBackground(Integer... integers) {
        for(int i = 0;i<=100;i++){
            try {
                Thread.sleep(integers[0]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(i);
        }
        return "下载完成！";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        tv.setText("当前完成任务的"+values[0]+"%");
        pb.setProgress(values[0]);
        pb.setVisibility(View.VISIBLE);
        tv.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(String s) {
        tv.setText(s);
        tv.setTextColor(Color.RED);
        tv.setTextSize(20);
        pb.setVisibility(View.INVISIBLE);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
