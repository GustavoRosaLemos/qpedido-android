package com.qpedido.android.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.qpedido.android.R;
import com.qpedido.android.constant.Constant;
import com.qpedido.android.fragment.ItemFragment;
import com.qpedido.android.model.Item;


public class ItemsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        loadIteams(findViewById(R.id.linearLayoutItems));
    }

    public void loadIteams(LinearLayout itemsLayout) {
        Intent intent = getIntent();
        String category = intent.getExtras().getString("category");
        Item[] items = {};
        switch (category) {
            case "starter": {
                items = Constant.ITEMS_STARTER;
                break;
            }
            case "plate": {
                items = Constant.ITEMS_PLATE;
                break;
            }
            case "pizza": {
                items = Constant.ITEMS_PIZZA;
                break;
            }
            case "sandwich": {
                items = Constant.ITEMS_SANDWICH;
                break;
            }
            case "dessert": {
                items = Constant.ITEMS_DESSERT;
                break;
            }
            case "drink": {
                items = Constant.ITEMS_DRINK;
                break;
            }
        }
        for (Item item:items) {
            Bundle bundle = new Bundle();
            bundle.putString("name", item.getName());
            bundle.putString("description", item.getDescription());
            bundle.putInt("timesOrdened", item.getTimesOrdened());
            bundle.putDouble("price", item.getPrice());
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment fragment = new ItemFragment();
            fragment.setArguments(bundle);
            ft.add(R.id.linearLayoutItems, fragment, "fragment_one");
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
}