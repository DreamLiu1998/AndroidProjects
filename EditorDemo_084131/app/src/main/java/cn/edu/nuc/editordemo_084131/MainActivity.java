package cn.edu.nuc.editordemo_084131;

import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.text.MessagePattern;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText testText = null;
    private Button red = null;
    private Button green = null;
    private Button blue = null;
    private Button bigger = null;
    private Button smaller = null;
    private Button bold = null;
    private Button italic = null;
    private Button normal = null;
    private EditText content = null;
    private EditText editText = null;
    //标志变量：0表示无样式，1表示斜体，2表示粗体，3表示粗斜体。
    private int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testText = (EditText)findViewById(R.id.testText);
        red = (Button)findViewById(R.id.red);
        green = (Button)findViewById(R.id.green);
        blue = (Button)findViewById(R.id.blue);
        bigger = (Button)findViewById(R.id.bigger);
        smaller = (Button)findViewById(R.id.smaller);
        bold = (Button)findViewById(R.id.bold);
        italic = (Button)findViewById(R.id.italic);
        normal = (Button)findViewById(R.id.normal);
        content = (EditText)findViewById(R.id.content);
        editText = (EditText)findViewById(R.id.content);


        ColorListener colorListener = new ColorListener();

        red.setOnClickListener(colorListener);
        green.setOnClickListener(colorListener);
        blue.setOnClickListener(colorListener);

        SizeListener sizeListener = new SizeListener(testText);

        bigger.setOnClickListener(sizeListener);
        smaller.setOnClickListener(sizeListener);

        bold.setOnClickListener(this);
        italic.setOnClickListener(this);
        normal.setOnClickListener(this);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                testText.setText(editText.getText().toString());
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        Typeface typeface = testText.getTypeface();//获取当前文本框字体样式.
        switch (v.getId()){
            case R.id.bold:
                if(flag == 1 || flag == 3) {
                    testText.setTypeface(Typeface.MONOSPACE, Typeface.BOLD_ITALIC);
                    flag = 3;
                }else{
                    testText.setTypeface(Typeface.MONOSPACE,Typeface.BOLD);
                    flag = 2;
                }
                break;
            case R.id.italic:
                if(flag == 2 || flag ==3) {
                    testText.setTypeface(Typeface.MONOSPACE, Typeface.BOLD_ITALIC);
                    flag = 3;
                }
                else{
                    testText.setTypeface(Typeface.MONOSPACE,Typeface.ITALIC);
                    flag = 1;
                }
                break;
            case R.id.normal:
                testText.setTypeface(Typeface.MONOSPACE,Typeface.NORMAL);
                flag = 0;
                break;

                default:break;
        }
    }

    private class ColorListener implements View.OnClickListener{

        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.red:
                    testText.setTextColor(Color.RED);
                    break;
                case R.id.green:
                    testText.setTextColor(Color.GREEN);
                    break;
                case R.id.blue:
                    testText.setTextColor(Color.BLUE);
                    break;
                    default:break;
            }
        }
    }
}
