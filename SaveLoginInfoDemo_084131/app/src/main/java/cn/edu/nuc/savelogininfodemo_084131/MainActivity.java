package cn.edu.nuc.savelogininfodemo_084131;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private EditText name = null;
    private EditText psd = null;
    private CheckBox rememberPsd = null;
    private CheckBox autoLogin = null;
    private Button login = null;
    private TextView userInfo = null;

    SharedPreferences loginPreferences,accessPreferences;//保存登录信息和访问次数
    SharedPreferences.Editor loginEditor,accessEditor;//对应的编辑器
    String userName;
    String userPsd;
    boolean isSavePsd,isAutoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        accessPreferences = getSharedPreferences("accsee",Context.MODE_WORLD_READABLE);
        int count = accessPreferences.getInt("count",1);
        Toast.makeText(MainActivity.this,"欢迎您，这是第"+ count +"次访问！",Toast.LENGTH_LONG).show();
        loginEditor = loginPreferences.edit();
        accessEditor = accessPreferences.edit();
        accessEditor.putInt("count",++count);
        accessEditor.commit();
        userName = loginPreferences.getString("name",null);
        userPsd = loginPreferences.getString("psd",null);
        isSavePsd = loginPreferences.getBoolean("isSavePsd",false);
        isAutoLogin = loginPreferences.getBoolean("isAutoLogin",false);
        System.out.println("userName="+ userName +",userPsd="+userPsd);
        if(isAutoLogin){
            this.setContentView(R.layout.activity_welcome);
            userInfo = (TextView)findViewById(R.id.userInfo);
            userInfo.setText("欢迎您："+ userName +",登录成功！");
        }else {
            loadActivity();
        }
    }
    public void loadActivity(){
        this.setContentView(R.layout.activity_main);
        login = (Button)findViewById(R.id.login);
        rememberPsd = (CheckBox)findViewById(R.id.rememberPsd);
        autoLogin = (CheckBox)findViewById(R.id.autoLogin);
        name = (EditText)findViewById(R.id.name);
        psd = (EditText)findViewById(R.id.psd);
        if(isSavePsd){
            psd.setText(userPsd);
            name.setText(userName);
            rememberPsd.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginEditor.putString("name",name.getText().toString());
                loginEditor.putString("psd",psd.getText().toString());
                loginEditor.putBoolean("isSavePsd",rememberPsd.isChecked());
                loginEditor.putBoolean("isAutoLogin",autoLogin.isChecked());
                loginEditor.commit();
                MainActivity.this.setContentView(R.layout.activity_welcome);
                userInfo = (TextView)findViewById(R.id.userInfo);
                userInfo.setText("欢迎您："+ name.getText().toString()+ ",登录成功！");
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_settings:
                loginEditor.putBoolean("isAutoLogin",false);
                loginEditor.commit();
                loadActivity();
                break;
            case R.id.exit:
                this.finish();
                break;
            default:break;
        }
        return true;
    }
}
