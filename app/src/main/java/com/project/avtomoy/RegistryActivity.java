package com.project.avtomoy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.redmadrobot.inputmask.MaskedTextChangedListener;
import com.redmadrobot.inputmask.helper.AffinityCalculationStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;

public class RegistryActivity extends Activity {

    public EditText tPhone;
    public EditText tEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        tPhone = findViewById(R.id.phoneEditTextRegistry);
        setupPrefixSample(tPhone);
        tEmail = findViewById(R.id.emailEditTextRegistry);
        tEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    try {
                        String server_answer = new ThreadRequest().execute("email-exist", "email=" + tEmail.getText().toString()).get();
                        EmailExistClass e=deserializeEmailResult(server_answer);
                        if(!e.getError()) {
                            Toast.makeText(RegistryActivity.this, "Такой email уже зарегистрированн", Toast.LENGTH_SHORT).show();
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        try {
            Button nextButton = findViewById(R.id.nextButtonRegistry);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText tPassword = findViewById(R.id.passwordEditTextRegistry);
                    EditText tPasswordNew = findViewById(R.id.admitPasswordEditTextRegistry);
                    EditText tKeyAuto = findViewById(R.id.editTextKeyAuto);
                    try {
                        if ((!tEmail.getText().toString().equals("")) && (!tPassword.getText().toString().equals("")) && (!tPhone.getText().toString().equals("")) && (!tPasswordNew.getText().toString().equals("")) && (!tKeyAuto.getText().toString().equals(""))) {
                            if (tPassword.getText().toString().equals(tPasswordNew.getText().toString())) {
                                try {
                                    String server_answer = new ThreadRequest().execute("registration", "email=" + tEmail.getText().toString() + "&phone=" + tPhone.getText().toString() + "&password=" + tPassword.getText().toString() + "&passwordRepeat=" + tPasswordNew.getText().toString() + "&cwCode=" + tKeyAuto.getText().toString()).get();
                                    RegistryClass Res = deserializeRegistryResult(server_answer);
                                    if(Res!=null) {
                                        Toast.makeText(RegistryActivity.this, Res.getResponse(), Toast.LENGTH_SHORT).show();
                                        if(!Res.error) {
                                            Intent intent = new Intent(RegistryActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            RegistryActivity.this.finish();
                                        }
                                    }else{
                                        Toast.makeText(RegistryActivity.this,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
                                    }
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Toast.makeText(RegistryActivity.this, "Пароли должны совпадать!!!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegistryActivity.this, "Заполните все параметры!!!", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        Toast.makeText(RegistryActivity.this,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }catch (Exception e){
            Toast.makeText(this,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
        }
    }

    private RegistryClass deserializeRegistryResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,RegistryClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    private LoginClass deserializeLoginResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,LoginClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private EmailExistClass deserializeEmailResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,EmailExistClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void setupPrefixSample(EditText editText) {
        final List<String> affineFormats = new ArrayList<>();
        affineFormats.add("+7 ([000]) [000] [00] [00]");

        final MaskedTextChangedListener listener = MaskedTextChangedListener.Companion.installOn(
                editText,
                "+7 ([000]) [000] [00] [00",
                affineFormats,
                AffinityCalculationStrategy.PREFIX,
                new MaskedTextChangedListener.ValueListener() {
                    @Override
                    public void onTextChanged(boolean maskFilled, @NonNull final String extractedValue, @NonNull String formattedText) {
                    }
                }
        );

    }
}
