package com.qpedido.android.activity;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.qpedido.android.R;

import java.util.UUID;

public class ProfileActivity extends AppCompatActivity {

    private EditText fullname;
    private EditText email;
    private EditText ddd;
    private EditText phone;
    private EditText birthDate;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        fullname = findViewById(R.id.editTextFullNameProfile);
        email = findViewById(R.id.editTextEmailProfile);
        ddd = findViewById(R.id.editTextDDDProfile);
        phone = findViewById(R.id.editTextPhoneProfile);
        birthDate = findViewById(R.id.editTextBirthProfile);
        password = findViewById(R.id.editTextPasswordProfile);
        loadFieldValues();
    }

    public void loadFieldValues() {
        SharedPreferences sharedPreferences = getSharedPreferences("session", 0);
        fullname.setText(sharedPreferences.getString("user_fullname", ""));
        email.setText(sharedPreferences.getString("user_email", ""));
        ddd.setText(sharedPreferences.getString("user_ddd", ""));
        phone.setText(sharedPreferences.getString("user_phone", ""));
        birthDate.setText(sharedPreferences.getString("user_birth", ""));
        password.setText(sharedPreferences.getString("user_password", ""));
    }

    public void saveChanges(View view) {
        boolean error = false;
        if(fullname.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.empty_fullname, Toast.LENGTH_LONG).show();
            error = true;
        } else if(email.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.empty_email, Toast.LENGTH_LONG).show();
            error = true;
        } else if(ddd.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.empty_ddd, Toast.LENGTH_LONG).show();
            error = true;
        } else if(phone.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.empty_phone, Toast.LENGTH_LONG).show();
            error = true;
        } else if(birthDate.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.empty_birthDate, Toast.LENGTH_LONG).show();
            error = true;
        } else if(password.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.empty_password, Toast.LENGTH_LONG).show();
            error = true;
        }
        try {
            if (!error) {
                SharedPreferences sharedPreferences = getSharedPreferences("session", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.putString("session_id", UUID.randomUUID().toString());
                editor.putString("user_fullname", fullname.getText().toString());
                editor.putString("user_email", email.getText().toString());
                editor.putString("user_ddd", ddd.getText().toString());
                editor.putString("user_phone", phone.getText().toString());
                editor.putString("user_birth", birthDate.getText().toString());
                editor.putString("user_password", password.getText().toString());
                editor.apply();
            }
        } catch (Exception e) {
            Toast.makeText(this, R.string.registration_error, Toast.LENGTH_LONG).show();
            error = true;
        }

        if (!error) {
            finish();
        }
    }

    public void onClickReturn(View view) {
        finish();
    }
}