package cn.edu.nuc.registerdemo_084131;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    private EditText name = null;
    private EditText psd = null;
    private EditText psd2 = null;
    private RadioButton male = null;
    private RadioButton female = null;
    private Button cityBtn = null;
    private EditText city = null;
    private Button registerBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.name);
        psd = (EditText)findViewById(R.id.psd);
        psd2 = (EditText)findViewById(R.id.psd2);
        male = (RadioButton)findViewById(R.id.male);
        female = (RadioButton)findViewById(R.id.female);
        cityBtn = (Button)findViewById(R.id.cityBtn);
        city = (EditText)findViewById(R.id.city);
        registerBtn = (Button)findViewById(R.id.registerBtn);

        cityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ChooseCityActivity.class);
                startActivityForResult(intent,0);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkResult = checkInfo();
                if(checkResult!=null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this
                    );
                    builder.setTitle("出错提示！");
                    builder.setMessage("checkResult");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            psd.setText("");
                            psd2.setText("");
                        }
                    });
                    builder.create().show();
                }else{
                    Intent intent = new Intent(MainActivity.this,ResultActivity.class);
                    intent.putExtra("name",name.getText().toString().trim());
                    intent.putExtra("psd",psd.getText().toString().trim());
                    String gender = male.isChecked()?"男":"女";
                    intent.putExtra("gender",gender);
                    intent.putExtra("city",city.getText().toString().trim());
                    startActivity(intent);
                }
            }
        });
    }

    public String checkInfo(){
        if(name.getText().toString().trim()==null||name.getText().toString().trim()=="")
        {
            return "用户名不能为空！";
        }
        if(psd.getText().toString().trim().length()<6||psd.getText().toString().trim().length()>15){
            return "密码的位数不对";
        }
        if(!psd.getText().toString().trim().equals(psd2.getText().toString().trim())){
            return "两次输入的密码不一致！";
        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == 0){
            Bundle bundle = data.getExtras();
            String resultCity = bundle.getString("city");
            city.setText(resultCity);
        }
    }
}
