package com.example.mobilki2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    TimePicker timePicker;
    Button btnConfirmAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_activity);

        timePicker = findViewById(R.id.timePicker);
        btnConfirmAlarm = findViewById(R.id.btnConfirmAlarm);

        btnConfirmAlarm.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
            calendar.set(Calendar.MINUTE, timePicker.getMinute());
            calendar.set(Calendar.SECOND, 0);

            Intent intent = new Intent(AlarmActivity.this, Alarm.class);
            intent.putExtra("type", "alarm");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    AlarmActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
            );
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            if (alarmManager != null) {
                alarmManager.setExact(
                        AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(),
                        pendingIntent
                );
            }

            finish();
        });
    }
}