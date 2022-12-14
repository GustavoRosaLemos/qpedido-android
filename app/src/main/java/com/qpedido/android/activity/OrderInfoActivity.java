package com.qpedido.android.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import com.qpedido.android.R;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class OrderInfoActivity extends AppCompatActivity {

    private int status = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        loadOrderInfo();
    }

    public void onClickReturn(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void loadOrderInfo() {
        try {

            SharedPreferences sharedPreferences = getSharedPreferences("session", 0);
            JSONObject jsonObject = new JSONObject(sharedPreferences.getString("user_orders", ""));
            JSONArray jArray = jsonObject.getJSONArray("orders");
            JSONArray orderItems = null;
            for (int i =  0; i < jArray.length(); i++) {
                JSONObject order = jArray.getJSONObject(i);
                Intent intent = getIntent();
                if(order.getString("order_id").equals(intent.getStringExtra("order_id"))) {
                    orderItems = order.getJSONArray("order_items");
                    break;
                }
            }
            LinearLayout linearLayout = findViewById(R.id.linearLayoutOrderInfoItems);
            System.out.println("AQUIII " + orderItems != null && orderItems.length() > 0);
            if(orderItems != null && orderItems.length() > 0) {
                double totalPrice = 0;
                for (int i = 0; i < orderItems.length(); i++) {
                    TextView textView = new TextView(this);
                    textView.setText("1x " + orderItems.getJSONObject(i).getString("name") + " - " + new DecimalFormat("R$ #,##0.00").format(orderItems.getJSONObject(i).getDouble("price")));
                    linearLayout.addView(textView);
                    totalPrice += orderItems.getJSONObject(i).getDouble("price");
                }
                TextView textView = new TextView(this);
                textView.setText("Total: " + new DecimalFormat("R$ #,##0.00").format(totalPrice));
                textView.setTextSize(18);
                linearLayout.addView(textView);
            }
        } catch (Exception e) {
            Toast.makeText(this, R.string.error_load_order_items, Toast.LENGTH_LONG).show();
        }
    }

    public void statusChange(View view) {
        status++;
        try {
            ImageView imageView = findViewById(R.id.imageViewOrderStatus);
            int[] images = {R.drawable.order_step_1, R.drawable.order_step_2, R.drawable.order_step_3, R.drawable.order_step_4};
            imageView.setImageDrawable(ContextCompat.getDrawable(this, images[status]));
        } catch (Exception e) {
            Toast.makeText(this, R.string.status_change_error, Toast.LENGTH_LONG).show();
        }
    }
}