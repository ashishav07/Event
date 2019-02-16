package com.example.owner.event;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class FormActivity extends AppCompatActivity {
    private EditText et;
    private EditText dt1;
    private EditText clk;
    private Button create;
    private TextView name,date,time;
    int mYear,mMonth,mDay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        et= findViewById(R.id.event_name);

    }
    public void getSpeechInput(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        if(intent.resolveActivity(getPackageManager())!=null){
        startActivityForResult(intent,10);}
        else{
            Toast.makeText(this,"Your Device doesen't support speech to text",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case 10:
            if(resultCode==RESULT_OK && data !=null){
                ArrayList<String> result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                et.setText(result.get(0));
            }
            break;
        }
    }
    //get date method
    public void getDate(View view) {
        Calendar mcurrentDate = Calendar.getInstance();
        mYear = mcurrentDate.get(Calendar.YEAR);
        mMonth = mcurrentDate.get(Calendar.MONTH);
        mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog mDatePicker = new DatePickerDialog(FormActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                Calendar myCalendar = Calendar.getInstance();
                myCalendar.set(Calendar.YEAR, selectedyear);
                myCalendar.set(Calendar.MONTH, selectedmonth);
                myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                String myFormat = "dd/MM/yy"; //Change as you need
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
                dt1= findViewById(R.id.dt);
                dt1.setText(sdf.format(myCalendar.getTime()));
                mDay = selectedday;
                mMonth = selectedmonth;
                mYear = selectedyear;
            }
        }, mYear, mMonth, mDay);
        mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
        //mDatePicker.setTitle("Select date");
        mDatePicker.show();
    }

    public void getTime(View view) {
        clk= findViewById(R.id.clk);
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(FormActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                clk.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    create=(Button) findViewById(R.id.create);
//    name=(TextView) findViewById(R.id.name);
//    date=(TextView) findViewById(R.id.date);
//    time=(TextView) findViewById(R.id.stime);
    create.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intentform=new Intent(FormActivity.this,MainActivity.class);
            String eName = et.getText().toString();
            String eDate = dt1.getText().toString();
            String eTime = clk.getText().toString();
            intentform.putExtra("Name",eName);
            intentform.putExtra("Date",eDate);
            intentform.putExtra("Time",eTime);
            startActivity(intentform);
        }
    });
    }
}