package com.qpedido.android.fragment;

import android.os.Bundle;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.qpedido.android.R;

import java.text.DecimalFormat;

public class ItemFragment extends Fragment {

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
            TextView name = getView().findViewById(R.id.textViewName);
            name.setText(getArguments().getString("name"));
            TextView description = getView().findViewById(R.id.textViewDescription);
            description.setText(getArguments().getString("description"));
            TextView timesOrdened = getView().findViewById(R.id.textViewTimesOrdened);
            timesOrdened.setText(getArguments().getInt("timesOrdened") + " Pedidos");
            TextView price = getView().findViewById(R.id.textViewPrice);
            price.setText("R$ " + new DecimalFormat("#,##0.00").format(getArguments().getDouble("price")));
        }
    }
}