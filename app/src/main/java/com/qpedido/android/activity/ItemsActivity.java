package com.qpedido.android.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.qpedido.android.R;
import com.qpedido.android.fragment.ItemFragment;
import com.qpedido.android.fragment.LoginFragment;
import com.qpedido.android.model.Item;

import static com.qpedido.android.constant.Constant.ITEMS;

public class ItemsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        loadIteams(findViewById(R.id.linearLayoutItems));
    }

    public void loadIteams(LinearLayout itemsLayout) {
        for (Item item:ITEMS) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment fragment = new ItemFragment();
            ft.add(R.id.linearLayoutItems, fragment, "fragment_one");
            Fragment fragment2 = new ItemFragment();
            ft.add(R.id.linearLayoutItems, fragment2, "fragment_two");
            ft.commit();
        }
    }

    public void onClickOrderConfirmation(View view) {
        Intent intent = new Intent(this, OrderConfirmationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}