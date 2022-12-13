package com.qpedido.android.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import com.qpedido.android.R;
import com.qpedido.android.fragment.LoginFragment;
import com.qpedido.android.fragment.RegisterFragment;

import java.util.UUID;

public class LoginActivity extends AppCompatActivity {

    TextView title;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        title = findViewById(R.id.textViewTitle);
        title.setText(R.string.user_login);
        loadLoginFragment();
    }

    private void loadLoginFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentLogin, new LoginFragment());
        fragmentTransaction.commit();
    }

    public void onClickReturn(View view) {
        finish();
    }

    public void onClickSwitchRegister(View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentLogin, new RegisterFragment());
        fragmentTransaction.commit();
        title.setText(R.string.user_registration);
    }

    public void onClickSwitchLogin(View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentLogin, new LoginFragment());
        fragmentTransaction.commit();
        title.setText(R.string.user_login);
    }

    public void onClickLogin(View view) {
        //TODO Adicionar fluxo de verificação de credencias.
        Intent intent = new Intent(this, QRCodeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onClickRegister(View view) {
        boolean error = false;
        EditText fullname = findViewById(R.id.editTextFullName);
        EditText email = findViewById(R.id.editTextEmail);
        EditText ddd = findViewById(R.id.editTextDDD);
        EditText phone = findViewById(R.id.editTextPhone);
        EditText birthDate = findViewById(R.id.editTextBirth);
        EditText password = findViewById(R.id.editTextPassword);
        EditText repeatPassword = findViewById(R.id.editTextRepeatPassword);

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
        } else if(repeatPassword.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.empty_repeat_password, Toast.LENGTH_LONG).show();
            error = true;
        } else if(!password.getText().toString().equals(repeatPassword.getText().toString())) {
            Toast.makeText(this, R.string.password_not_equals, Toast.LENGTH_LONG).show();
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
        if(!error) {
            Intent intent = new Intent(this, QRCodeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}