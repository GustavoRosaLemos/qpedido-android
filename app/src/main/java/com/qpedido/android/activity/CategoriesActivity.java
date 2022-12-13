package com.qpedido.android.activity;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.qpedido.android.R;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
    }

    public void onClickReturn(View view) {
        finish();
    }

    public void onClickOrderConfirmation(View view) {
        Intent intent = new Intent(this, OrderConfirmationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onClickStarter(View view) {
        Intent intent = new Intent(this, ItemsActivity.class);
        intent.putExtra("category", "starter");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onClickPlate(View view) {
        Intent intent = new Intent(this, ItemsActivity.class);
        intent.putExtra("category", "plate");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onClickPizza(View view) {
        Intent intent = new Intent(this, ItemsActivity.class);
        intent.putExtra("category", "pizza");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onClickSandwich(View view) {
        Intent intent = new Intent(this, ItemsActivity.class);
        intent.putExtra("category", "sandwich");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onClickDessert(View view) {
        Intent intent = new Intent(this, ItemsActivity.class);
        intent.putExtra("category", "dessert");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onClickDrink(View view) {
        Intent intent = new Intent(this, ItemsActivity.class);
        intent.putExtra("category", "drink");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}