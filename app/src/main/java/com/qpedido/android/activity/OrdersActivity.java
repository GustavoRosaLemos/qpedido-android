package com.qpedido.android.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.qpedido.android.R;
import com.qpedido.android.fragment.ItemFragment;
import com.qpedido.android.fragment.OrderFragment;
import com.qpedido.android.model.Item;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.UUID;

public class OrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        loadOrders();
    }

    public void onClickReturn(View view) {
        finish();
    }

    public void loadOrders() {
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("session", 0);
            JSONObject userOrders = new JSONObject(sharedPreferences.getString("user_orders", ""));
            JSONArray orders = userOrders.getJSONArray("orders");
            for (int i = 0; i < orders.length(); i++) {
                double total_order = 0;
                Bundle bundle = new Bundle();
                JSONArray orderItems = orders.getJSONObject(i).getJSONArray("order_items");
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                bundle.putString("order_id", orders.getJSONObject(i).getString("order_id"));
                for (int x = 0; x < orderItems.length(); x++) {
                    total_order += orderItems.getJSONObject(x).getDouble("price");
                }
                bundle.putString("orders", orders.getJSONObject(i).toString());
                bundle.putDouble("order_items_price", total_order);
                bundle.putDouble("order_total_price", total_order);
                Fragment fragment = new OrderFragment();
                fragment.setArguments(bundle);
                ft.add(R.id.linearLayoutOrders, fragment, UUID.randomUUID().toString());
                ft.commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
    }

    public void onClickMoreOrderInfo(View view) {
        TextView orderId = view.findViewById(R.id.textViewOrdersOrderId);
        Intent intent = new Intent(this, OrderInfoActivity.class);
        intent.putExtra("order_id", orderId.getText().toString().replace("Pedido #", ""));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}