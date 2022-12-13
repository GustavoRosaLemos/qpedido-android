package com.qpedido.android.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.qpedido.android.R;
import com.qpedido.android.constant.Constant;
import com.qpedido.android.model.Item;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.UUID;

public class ItemFragment extends Fragment {

    private Item item;

    public ItemFragment() {
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
        return inflater.inflate(R.layout.fragment_item, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            item = new Item(getArguments().getString("name"), getArguments().getString("description"), getArguments().getDouble("price"), getArguments().getInt("timesOrdened"));
            TextView name = getView().findViewById(R.id.textViewName);
            name.setText(getArguments().getString("name"));
            TextView description = getView().findViewById(R.id.textViewDescription);
            description.setText(getArguments().getString("description"));
            TextView timesOrdened = getView().findViewById(R.id.textViewTimesOrdened);
            timesOrdened.setText(getArguments().getInt("timesOrdened") + " Pedidos");
            TextView price = getView().findViewById(R.id.textViewPrice);
            price.setText(new DecimalFormat("R$ #,##0.00").format(getArguments().getDouble("price")));
        }
    }
}