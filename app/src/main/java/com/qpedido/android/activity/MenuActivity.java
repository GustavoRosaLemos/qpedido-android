package com.qpedido.android.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.qpedido.android.R;
import com.qpedido.android.model.Item;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        cleanCart();
    }

    public void cleanCart() {
        SharedPreferences sharedPreferences = getSharedPreferences("session", 0);
        if (sharedPreferences.contains("user_cart")) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("user_cart");
            editor.apply();
        }
    }

    public void onClickNewOrder(View view) {
        Intent intent = new Intent(this, CategoriesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onClickAttendance(View view) {
        Intent intent = new Intent(this, AttendanceActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onClickProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}