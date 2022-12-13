package com.qpedido.android.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.qpedido.android.R;
import com.qpedido.android.constant.Constant;
import com.qpedido.android.fragment.ItemFragment;
import com.qpedido.android.model.Item;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;


public class ItemsActivity extends AppCompatActivity {

    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        loadIteams(findViewById(R.id.linearLayoutItems));
    }

    public void loadIteams(LinearLayout itemsLayout) {
        Intent intent = getIntent();
        category = intent.getExtras().getString("category");

        for (Item item:getItemList(category)) {
            Bundle bundle = new Bundle();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            bundle.putString("name", item.getName());
            bundle.putString("description", item.getDescription());
            bundle.putInt("timesOrdened", item.getTimesOrdened());
            bundle.putDouble("price", item.getPrice());
            Fragment fragment = new ItemFragment();
            fragment.setArguments(bundle);
            ft.add(R.id.linearLayoutItems, fragment, UUID.randomUUID().toString());
            ft.commit();
        }
    }

    public void onClickOrderConfirmation(View view) {
        Intent intent = new Intent(this, OrderConfirmationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onClickReturn(View view) {
        finish();
    }

    public Item[] getItemList(String category) {
        switch (category) {
            case "starter": {
                return Constant.ITEMS_STARTER;
            }
            case "plate": {
                return Constant.ITEMS_PLATE;
            }
            case "pizza": {
                return Constant.ITEMS_PIZZA;
            }
            case "sandwich": {
                return Constant.ITEMS_SANDWICH;
            }
            case "dessert": {
                return Constant.ITEMS_DESSERT;
            }
            case "drink": {
                return Constant.ITEMS_DRINK;
            }
        }
        return null;
    }

    public void onClickItem(View view) throws JSONException {
        SharedPreferences sharedPreferences = getSharedPreferences("session", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        TextView name = view.findViewById(R.id.textViewName);
        Item item = null;
        for (Item i : getItemList(category)) {
            if (i.getName().contentEquals(name.getText())) {
                item = i;
                break;
            }
        }
        if (item != null) {
            if (!sharedPreferences.contains("user_cart")) {
                JSONObject jsonObject = new JSONObject();
                JSONArray items = new JSONArray();
                items.put(new JSONObject().put("name", item.getName()).put("description", item.getDescription()).put("price", item.getPrice()).put("timesOrdened", item.getTimesOrdened()));
                jsonObject.put("items", items);
                editor.putString("user_cart", jsonObject.toString());
                editor.apply();
            } else {
                JSONObject jsonObject = new JSONObject(sharedPreferences.getString("user_cart", ""));
                JSONArray jArray = jsonObject.getJSONArray("items");
                jArray.put(new JSONObject().put("name", item.getName()).put("description", item.getDescription()).put("price", item.getPrice()).put("timesOrdened", item.getTimesOrdened()));
                jsonObject.put("items", jArray);
                editor.putString("user_cart", jsonObject.toString());
                editor.apply();
            }
        } else {
            Toast.makeText(this, R.string.error_add_item_cart, Toast.LENGTH_LONG).show();
        }
    }
}