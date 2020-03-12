package com.project.avtomoy;

import android.app.Dialog;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.avtomoy.ui.tools.fragments.Car.Car;
import com.project.avtomoy.ui.tools.fragments.ToolCarFragment;
import com.redmadrobot.inputmask.MaskedTextChangedListener;
import com.redmadrobot.inputmask.helper.AffinityCalculationStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

public class RegistryCar extends DialogFragment {

    private ArrayList<Car> carsList;
    private static RecyclerView recyclerView;
    EditText editTextCarNumber;
    public RegistryCar( ArrayList<Car> carsList, RecyclerView recyclerView) {
        this.carsList = carsList;
        this.recyclerView = recyclerView;
    }


    private void logValueListener(boolean maskFilled, @NonNull String extractedValue, @NonNull String formattedText) {
        Log.i("My", extractedValue);
        Log.i("My", String.valueOf(maskFilled));
        Log.i("My", formattedText);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setContentView(R.layout.fragment_add_car_popup);

        Spinner spinner = dialog.findViewById(R.id.spinnerCarType);
        String[] items = new String[]{"Легковая", "Кроссовер", "Внедорожник", "Микроавтобус","Другое"};
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,items);
        spinner.setAdapter(adapter);
        editTextCarNumber= dialog.findViewById(R.id.editTextCarNumber);
        InputFilter customFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if(((source.charAt(i)>='a')&&(source.charAt(i)<'z'))||((source.charAt(i)>='A')&&(source.charAt(i)<'Z'))) {
                        return "";
                    }
                }
                return null;
            }
        };
        editTextCarNumber.setFilters(new InputFilter[]{ customFilter});
        setupPrefixSample(editTextCarNumber);
        Button addCarButton = dialog.findViewById(R.id.saveCarButton);
        addCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextRegion = dialog.findViewById(R.id.editTextRegion);
                Spinner spinnerCarType = dialog.findViewById(R.id.spinnerCarType);
                String str_answer;
                if((!editTextCarNumber.getText().toString().equals(""))&&(!editTextRegion.getText().toString().equals(""))) {
                    try {
                        str_answer = new ThreadRequest().execute("add-transport", ToolCarFragment.token, "car_number=" + editTextCarNumber.getText().toString() + "&car_region=" + editTextRegion.getText().toString() + "&car_type=" + spinnerCarType.getSelectedItemId()).get();
                        LoadAddTransport LAT = deserializeAddCarResult(str_answer);
                        if ((LAT != null) && (!LAT.getError())) {
                            if (carsList.size() == 0) {
                                carsList.add(new Car(editTextCarNumber.getText().toString(), null, Integer.parseInt(editTextRegion.getText().toString()), (int) spinnerCarType.getSelectedItemId(), null, null, 1));
                            } else {
                                carsList.add(new Car(editTextCarNumber.getText().toString(), null, Integer.parseInt(editTextRegion.getText().toString()), (int) spinnerCarType.getSelectedItemId(), null, null, 0));
                            }
                            if (carsList.size() == 3) {

                                ToolCarFragment.addCarButton.setVisibility(View.GONE);
                                //ToolCarFragment.addCarButton.setEnabled(false);
                                //ToolCarFragment.addCarButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(143, 143, 143)));
                            }
                            ToolCarFragment.onAddTransport();
                            dialog.cancel();
                        } else {
                            Toast.makeText(getActivity(), "Проверьте подключение к интернету!!!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getActivity(),"Заполните все поля!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return dialog;
    }

    private void setupPrefixSample(EditText editText) {
        final List<String> affineFormats = new ArrayList<>();
        affineFormats.add("[A] [000] [AA]");

        final MaskedTextChangedListener listener = MaskedTextChangedListener.Companion.installOn(
                editText,
                "[A] [000] [AA]",
                affineFormats,
                AffinityCalculationStrategy.PREFIX,
                new MaskedTextChangedListener.ValueListener() {
                    @Override
                    public void onTextChanged(boolean maskFilled, @NonNull final String extractedValue, @NonNull String formattedText) {
                        logValueListener(maskFilled, extractedValue, formattedText);
                    }
                }
        );

    }

    private static LoadAddTransport deserializeAddCarResult(String JSonString) {
        Gson gson = new Gson();
        try {
            return gson.fromJson(JSonString, LoadAddTransport.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
