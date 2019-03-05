package cn.edu.nuc.mementodemo;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText subject = null;
    private EditText body = null;
    private EditText date = null;
    private Button chooseDate = null;
    private Button add = null;
    private Button query = null;
    private ListView result = null;
    private LinearLayout title = null;
    private TextView memento_num ,memento_subject,memento_body,memento_date;
    MyDatabaseHelper mydbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subject = (EditText)findViewById(R.id.subject);
        body = (EditText)findViewById(R.id.body);
        date = (EditText)findViewById(R.id.date);
        chooseDate = (Button)findViewById(R.id.chooseDate);
        add = (Button)findViewById(R.id.add);
        query = (Button)findViewById(R.id.query);
        result = (ListView)findViewById(R.id.result);
        title = (LinearLayout)findViewById(R.id.title);
        title.setVisibility(View.INVISIBLE);

        /*memento_num = (TextView)findViewById(R.id.memento_num);
        memento_subject = (TextView)findViewById(R.id.me);
        memento_body = (TextView)findViewById(R.id.memento_body);
        memento_date = (TextView)findViewById(R.id.memento_date);*/

        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                    },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        MyOnClickListener myOnClickListener = new MyOnClickListener();
        add.setOnClickListener(myOnClickListener);
        query.setOnClickListener(myOnClickListener);

    }
    private class MyOnClickListener implements View.OnClickListener{
        public void onClick(View v){
            mydbHelper = new MyDatabaseHelper(MainActivity.this,"mementoDemo.db",null,1);
            SQLiteDatabase db = mydbHelper.getReadableDatabase();
            String subStr = subject.getText().toString();
            String bodyStr = body.getText().toString();
            String dateStr = date.getText().toString();
            switch (v.getId()){
                case R.id.add:
                    title.setVisibility(View.INVISIBLE);
                    addMemento(db,subStr,bodyStr,dateStr);
                    Toast.makeText(MainActivity.this, "添加备忘录成功！", Toast.LENGTH_LONG).show();
                    result.setAdapter(null);
                    break;
                case R.id.query:
                    title.setVisibility(View.VISIBLE);
                    Cursor cursor = queryMemento(db, subStr, bodyStr, dateStr);
                    SimpleCursorAdapter resultAdapter = new SimpleCursorAdapter(
                            MainActivity.this, R.layout.result, cursor,
                            new String[] { "_id", "subject", "body", "date" },
                            new int[] { R.id.memento_num, R.id.memento_subject,
                                    R.id.memento_body, R.id.memento_date });
                    result.setAdapter(resultAdapter);
                    break;
                default:
                    break;

            }
        }
    }

    public void addMemento(SQLiteDatabase db,String subject,String body,String date){
        db.execSQL("insert into memento_tb values(null,?,?,?)",new String[]{
                subject,body,date
        });
        this.subject.setText("");
        this.body.setText("");
        this.date.setText("");
    }

    public Cursor queryMemento(SQLiteDatabase db,String subject,String body,String date){
        Cursor cursor = db.rawQuery("select *from memento_tb where subject like ? and body like ? and date like ?",
                new String[]{"%"+subject+"%","%"+body+"%","%"+date+"%"});
        return cursor;
    }
    protected void onDestroy(){
        if(mydbHelper!=null){
            mydbHelper.close();
        }
        super.onDestroy();
    }
}