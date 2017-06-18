package com.example.user.appfinal;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.appfinal.data.FoodContract;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.lang.Integer.parseInt;


/**
 * Created by user on 2017/6/17.
 */

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class AddFoodActivity extends AppCompatActivity {
    private static final int FOOD_NOTIFICATION_ID = 0;  //建立通知
    private NotificationManager notificationManager;

    private DatePicker mDatePicker;
    private Spinner spinner_alertTime;

    private String D;
    private String M;
    private String ch;

    //取得目前日期時間 / 格式化日期
    private Calendar schedule = Calendar.getInstance();
    private Calendar now = Calendar.getInstance();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_add);

        mDatePicker = (DatePicker) findViewById(R.id.datePicker_buy);


        spinner_alertTime = (Spinner)findViewById(R.id.spinner_alert);
        final String[] time_choice = {"3秒", "1小時", "4小時", "8小時"};
        ArrayAdapter<String> timeList = new ArrayAdapter<>(AddFoodActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                time_choice);

        spinner_alertTime.setAdapter(timeList);

        spinner_alertTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AddFoodActivity.this, "你選的是" +  time_choice[position], Toast.LENGTH_SHORT).show();
                ch = time_choice[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //呼叫getSystemService()指定NOTIFICATION_SERVICE，可取得NotificationManager物件，為了之後發送Notification
        notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onClickAddFood(View view) {
        String input = ((EditText) findViewById(R.id.et_foodinput)).getText().toString();
        if (input.length() == 0) {
            return;
        }
        String remarkinput = ((EditText) findViewById(R.id.et_foodinput)).getText().toString();

        D = String.valueOf(mDatePicker.getDayOfMonth());
        M = String.valueOf(mDatePicker.getMonth()+1);
        String fullDay = M+"/"+D;

        ContentValues contentValues = new ContentValues();
        contentValues.put(FoodContract.FoodEntry.COLUMN_FOOD_NAME, input);
        contentValues.put(FoodContract.FoodEntry.COLUMN_BUY_TIME, fullDay);
        contentValues.put(FoodContract.FoodEntry.COLUMN_ALERT_TIME, ch);
        contentValues.put(FoodContract.FoodEntry.COLUMN_REMARK,remarkinput);

        Uri uri = getContentResolver().insert(FoodContract.FoodEntry.CONTENT_URI, contentValues);

        if(ch=="3秒"){
            int time= schedule.get(Calendar.SECOND)+3;
            schedule.set(Calendar.SECOND, time);
        }
        else if(ch=="1小時"){
            int time= schedule.get(Calendar.HOUR)+1;
            schedule.set(Calendar.HOUR, time);
        }
        else if(ch=="4小時"){
            int time= schedule.get(Calendar.HOUR)+4;
            schedule.set(Calendar.HOUR, time);
        }
        else if(ch=="8小時"){
            int time= schedule.get(Calendar.HOUR)+8;
            schedule.set(Calendar.HOUR, time);
        }

//        int result = Calendar.getInstance().compareTo(schedule);
//        if(result==0){

            // 點擊通知會到AddFoodActivity介面
            PendingIntent pendingIntent = PendingIntent.getActivity(this,
                    0, new Intent(this, ChildActivity.class), 0);

            // Set the info for the views that show in the notification panel.
            Notification.Builder notification = new Notification.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher) // the status icon
                    .setWhen(System.currentTimeMillis())  // the time stamp
                    .setContentTitle(this.getText(R.string.message_remind))  //通知的標題
                    .setContentText(this.getText(R.string.message_move_move))  // 通知的內容
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);

            // 寄出通知
            notificationManager.notify(FOOD_NOTIFICATION_ID, notification.build());
//        }

        finish();

    }

}
