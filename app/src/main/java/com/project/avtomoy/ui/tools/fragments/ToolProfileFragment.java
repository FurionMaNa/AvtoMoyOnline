package com.project.avtomoy.ui.tools.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.avtomoy.R;
import com.project.avtomoy.AutoRegActivity;
import com.project.avtomoy.LoadChangeSettingClass;
import com.project.avtomoy.SettingsPageClass;
import com.project.avtomoy.ThreadRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.redmadrobot.inputmask.MaskedTextChangedListener;
import com.redmadrobot.inputmask.helper.AffinityCalculationStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ToolProfileFragment extends Fragment {

    EditText editTextPhoneNumber;
    String token;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tools_profile, container, false);
        try {
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                token = bundle.getString("token", "false");
            }
            editTextPhoneNumber = view.findViewById(R.id.editTextPhoneNumber);
            EditText editTextMail = view.findViewById(R.id.editTextRegion1);
            editTextMail.setText(AutoRegActivity.login);
            setupPrefixSample(editTextPhoneNumber);
            editTextPhoneNumber.setFilters(new InputFilter[]{new InputFilter() {
                @Override
                public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                    return null;
                }
            }});

            String str_answer = new ThreadRequest().execute("settings-page", token).get();
            SettingsPageClass settingsPageClass=deserializeSettings(str_answer);
            editTextPhoneNumber.setText(settingsPageClass.getResponse().getSettings().getPhone().substring(1));
            editTextMail.setText(settingsPageClass.getResponse().getSettings().getEmail());

            final Button saveContactsButton = (Button) view.findViewById(R.id.saveCarButton);
            saveContactsButton.setOnClickListener(new BottomNavigationView.OnClickListener() {
                public void onClick(View v) {
                    try {
                        if (!editTextPhoneNumber.getText().toString().equals("")) {
                            Activity activity = getActivity();

                            String str_answer;
                            LoadChangeSettingClass LCSC = null;
                            try {
                                str_answer = new ThreadRequest().execute("change-settings", token, "phone=" + editTextPhoneNumber.getText().toString()).get();
                                LCSC = deserializeChangeResult(str_answer);
                                Toast.makeText(activity, LCSC.getResponse(), Toast.LENGTH_SHORT).show();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }catch (Exception e){
                        Toast.makeText(getActivity(),"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }catch (Exception e){
            Toast.makeText(AutoRegActivity.context,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    private void setupPrefixSample(EditText editText) {
        final List<String> affineFormats = new ArrayList<>();
        affineFormats.add("+7 ([000]) [000] [00] [00]");

        final MaskedTextChangedListener listener = MaskedTextChangedListener.Companion.installOn(
                editText,
                "+7 ([000]) [000] [00] [00]",
                affineFormats,
                AffinityCalculationStrategy.PREFIX,
                new MaskedTextChangedListener.ValueListener() {
                    @Override
                    public void onTextChanged(boolean maskFilled, @NonNull final String extractedValue, @NonNull String formattedText) {
                    }
                }
        );

    }

    private SettingsPageClass deserializeSettings(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,SettingsPageClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private LoadChangeSettingClass deserializeChangeResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,LoadChangeSettingClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}