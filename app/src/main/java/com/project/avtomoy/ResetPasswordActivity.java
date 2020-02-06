package com.project.avtomoy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

public class ResetPasswordActivity extends Activity {

    EditText tEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        try {
            Button sendButton = findViewById(R.id.sendButtonReset);
            tEmail = findViewById(R.id.emailEditTextReset);
            sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (!tEmail.getText().toString().equals("")) {
                            try {
                                String server_answer = new ThreadRequest().execute("forget-password", "login=" + tEmail.getText().toString()).get();
                                RegistryClass Res = deserializeLoginResult(server_answer);
                                if (!Res.getError()) {
                                    Toast.makeText(ResetPasswordActivity.this, "Ссылка для востановления пароля отпралена вам на почту", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(ResetPasswordActivity.this, MainActivity.class);
                                    intent.putExtra("Param", "param");
                                    startActivity(intent);
                                    ResetPasswordActivity.this.finish();
                                } else {
                                    Toast.makeText(ResetPasswordActivity.this, "Пользователь не найден", Toast.LENGTH_LONG).show();
                                }
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(ResetPasswordActivity.this, "Введи e-mail", Toast.LENGTH_LONG).show();
                        }
                    }catch (Exception e){
                        Toast.makeText(ResetPasswordActivity.this,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }catch (Exception e){
            Toast.makeText(this,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
        }
    }

    private RegistryClass deserializeLoginResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,RegistryClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
