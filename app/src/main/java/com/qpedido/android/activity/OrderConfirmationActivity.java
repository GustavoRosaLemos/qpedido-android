package com.qpedido.android.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.qpedido.android.R;
import com.qpedido.android.fragment.ItemFragment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.UUID;

public class OrderConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);
        loadCartItems();
    }

    public void onClickReturn(View view) {
        finish();
    }

    public void loadCartItems() {
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("session", 0);
            JSONObject jsonObject = new JSONObject(sharedPreferences.getString("user_cart", ""));
            JSONArray jArray = jsonObject.getJSONArray("items");
            LinearLayout linearLayout = findViewById(R.id.linearLayoutCartItems);
            double totalPrice = 0d;
            if (jArray.length() > 0) {
                for (int i = 0; i < jArray.length(); i++) {
                    TextView textView = new TextView(this);
                    textView.setText("1x " + jArray.getJSONObject(i).getString("name") + " - " + new DecimalFormat("R$ #,##0.00").format(jArray.getJSONObject(i).getDouble("price")));
                    textView.setTextSize(20);
                    linearLayout.addView(textView);
                    totalPrice += jArray.getJSONObject(i).getDouble("price");
                }
                TextView totalPriceTextView = findViewById(R.id.textViewConfirmationTotalPrice);
                TextView itensPriceTextView = findViewById(R.id.textViewConfirmationItensPrice);
                totalPriceTextView.setText("Total do pedido: " + new DecimalFormat("R$ #,##0.00").format(totalPrice));
                itensPriceTextView.setText("Itens: " + new DecimalFormat("R$ #,##0.00").format(totalPrice));
            }
        } catch (Exception ignored) {

        }

    }

    public void onClickConfirm(View view) {
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}