package com.qpedido.android.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.qpedido.android.R;

import static com.qpedido.android.constant.Constant.ATTENDANCE_CATEGORIES;
import static com.qpedido.android.constant.Constant.ATTENDANCE_PRIORITY;

public class AttendanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        loadCategories(findViewById(R.id.spinnerCategory));
        loadPriority(findViewById(R.id.spinnerPriority));
    }

    public void loadCategories(Spinner spinner) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, ATTENDANCE_CATEGORIES);
        spinner.setAdapter(adapter);
    }

    public void loadPriority(Spinner spinner) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, ATTENDANCE_PRIORITY);
        spinner.setAdapter(adapter);
    }

    public void onClickReturn(View view) {
        finish();
    }

    public void onClickRequestAttendance(View view) {
        Intent intent = new Intent(this, AttendanceRequestedActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}