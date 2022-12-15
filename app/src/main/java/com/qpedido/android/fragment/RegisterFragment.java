package com.qpedido.android.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.qpedido.android.R;

public class RegisterFragment extends Fragment {

    EditText birthField;
    EditText phoneField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        birthField = view.findViewById(R.id.editTextBirth);
        birthListener(view);
        phoneField = view.findViewById(R.id.editTextPhone);
        phoneListener(view);
        return view;
    }

    public void phoneListener(View view) {
        phoneField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                editable.setFilters(new InputFilter[]{
                        new InputFilter() {
                            @Override
                            public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                                if (!charSequence.toString().isEmpty() && editable.length() == 3){ {
                                    return charSequence+"-";
                                }}
                                if (editable.length() > 8) {
                                    return "";
                                }
                                return null;
                            }
                        }
                });
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void birthListener(View view) {
        birthField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                editable.setFilters(new InputFilter[]{
                        new InputFilter() {
                            @Override
                            public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                                if (!charSequence.toString().isEmpty() && (editable.length() == 1 || editable.length() == 4)){
                                    return charSequence+"/";
                                }
                                if (editable.length() > 9) {
                                    return "";
                                }
                                return null;
                            }
                        }
                });
            }
        });
    }
}