package com.qpedido.android.fragment;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.qpedido.android.R;
import com.qpedido.android.model.Item;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment {

    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            TextView orderId = getView().findViewById(R.id.textViewOrdersOrderId);
            LinearLayout orderItems = getView().findViewById(R.id.linearLayoutOrdersItems);
            TextView orderItemsPrice = getView().findViewById(R.id.textViewOrdersItemsPrice);
            TextView totalPrice = getView().findViewById(R.id.textViewOrdersTotalPrice);

            try {
                JSONObject orders = new JSONObject(getArguments().getString("orders"));

                JSONArray jOrderItems = orders.getJSONArray("order_items");
                for (int i = 0; i < jOrderItems.length(); i++) {
                    TextView textView = new TextView(this.getContext());
                    textView.setText("1x " + jOrderItems.getJSONObject(i).getString("name") + " - " + new DecimalFormat("R$ #,##0.00").format(jOrderItems.getJSONObject(i).getDouble("price")));
                    orderItems.addView(textView);
                }

                orderId.setText("Pedido #" + getArguments().getString("order_id"));
                orderItemsPrice.setText("Itens: " + new DecimalFormat("R$ #,##0.00").format(getArguments().getDouble("order_items_price")));
                totalPrice.setText("Total do pedido: " + new DecimalFormat("R$ #,##0.00").format(getArguments().getDouble("order_total_price")));
            } catch (Exception e) {
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
            }
        }

    }
}