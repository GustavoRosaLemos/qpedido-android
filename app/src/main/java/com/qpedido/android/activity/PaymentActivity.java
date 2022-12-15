package com.qpedido.android.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.qpedido.android.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

import static com.qpedido.android.constant.Constant.ATTENDANCE_PRIORITY;
import static com.qpedido.android.constant.Constant.PAYMENT_TYPES;

public class PaymentActivity extends AppCompatActivity {

    private String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        loadPaymentsMethods(findViewById(R.id.spinnerPaymentType));
        orderId = UUID.randomUUID().toString();
    }

    public void onClickReturn(View view) {
        finish();
    }

    public void loadPaymentsMethods(Spinner spinner) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, PAYMENT_TYPES);
        spinner.setAdapter(adapter);
    }

    public void cleanCart() {
        SharedPreferences sharedPreferences = getSharedPreferences("session", 0);
        if (sharedPreferences.contains("user_cart")) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("user_cart");
            editor.apply();
        }
    }

    public void onClickPayment(View view) throws JSONException {
        EditText cardNumber = findViewById(R.id.editTextCardNumber);
        if (!cardNumber.getText().toString().isEmpty()) {
            char lastCharCardNumber = cardNumber.getText().toString().charAt(cardNumber.getText().toString().length() - 1);
            if (lastCharCardNumber == '0' || lastCharCardNumber == '2' || lastCharCardNumber == '4' || lastCharCardNumber == '6' || lastCharCardNumber == '8') {
                //COMPRA APROVADA
                SharedPreferences sharedPreferences = getSharedPreferences("session", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                JSONObject jUserCart = new JSONObject(sharedPreferences.getString("user_cart", ""));
                if (!sharedPreferences.contains("user_orders")) {
                    JSONObject jsonObject = new JSONObject();
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(new JSONObject().put("order_id", orderId).put("order_items", jUserCart.getJSONArray("items")));
                    jsonObject.put("orders", jsonArray);
                    editor.putString("user_orders", jsonObject.toString());
                    editor.apply();
                } else {
                    JSONObject jsonObject = new JSONObject(sharedPreferences.getString("user_orders", ""));
                    JSONArray jsonArray = jsonObject.getJSONArray("orders");
                    jsonArray.put(new JSONObject().put("order_id", orderId).put("order_items", jUserCart.getJSONArray("items")));
                    jsonObject.put("orders", jsonArray);
                    editor.putString("user_orders", jsonObject.toString());
                    editor.apply();
                }
                cleanCart();
                Intent intent = new Intent(this, OrderInfoActivity.class);
                intent.putExtra("order_id", orderId);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } else {
                //COMPRA RECUSADA
                Toast.makeText(this, R.string.payment_fail, Toast.LENGTH_LONG).show();
            }
        }
    }
}