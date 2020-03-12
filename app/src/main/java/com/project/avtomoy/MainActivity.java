package com.project.avtomoy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity {

    public SharedPreferences myPrefereces;
    public String login="";
    public String password="";
    public static Context context;
    ProgressBar pd;
    static ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        super.onCreate(savedInstanceState);
        try {
            context = this;
            setContentView(R.layout.activity_main);
            pd = findViewById(R.id.progressBar);
            Intent intents = this.getIntent();
            String s = (String) intents.getSerializableExtra("Param");
            myPrefereces = this.getSharedPreferences("uses", Context.MODE_PRIVATE);
            password = myPrefereces.getString("password", "");
            login = myPrefereces.getString("login", "");
            if ((!login.equals("")) && (!password.equals("")) && (s == null)) {
                //pd.setVisibility(View.VISIBLE);
                String server_answer = null;
                try {
                    server_answer = new ThreadRequest().execute("login", "login=" + login + "&password=" + password).get();
                    LoginClass Res = deserializeLoginResult(server_answer);
                    if (!Res.getError()) {
                        Intent intent = new Intent(MainActivity.this, AutoRegActivity.class);
                        intent.putExtra("login", login);
                        intent.putExtra("password", password);
                        intent.putExtra("token", Res.getResponse().getToken());
                        intent.putExtra("id", Res.getResponse().getCarWashId().getId());
                        startActivity(intent);
                    }else{
                        Toast.makeText(this,"Обновите логин и пароль!",Toast.LENGTH_LONG).show();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                if (s != null) {
                    myPrefereces.edit().remove("login").apply();
                    myPrefereces.edit().remove("password").apply();
                    myPrefereces.edit().remove("token").apply();
                }
            }
        }catch (Exception e){
            Toast.makeText(this,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
        }
    }

    public void resetTextViewMain_onClick(View view) {
        Intent intent = new Intent(MainActivity.this,ResetPasswordActivity.class);
        startActivity(intent);
    }

    public void registryTextViewMain_onClick(View view) {
        //Intent intent = new Intent(MainActivity.this,RegistryActivity.class);
        Intent intent = new Intent(MainActivity.this,UserAgreementActivity.class);
        startActivity(intent);
    }

    public void nextButtonMain_onClick(View view){
        pd.setVisibility(View.VISIBLE);
        try {
            EditText tEmail=findViewById(R.id.loginEditTextMain);
            EditText tPassword=findViewById(R.id.passwordEditTextMain);
            if((!tEmail.getText().toString().equals(""))&&(!tPassword.getText().toString().equals(""))) {
                String server_answer = new ThreadRequest().execute("login","login="+tEmail.getText().toString()+"&password="+tPassword.getText().toString()).get();
                LoginClass Res=deserializeLoginResult(server_answer);
                if(!Res.getError()){
                    SharedPreferences.Editor editor=myPrefereces.edit();
                    editor.putString("login",tEmail.getText().toString());
                    editor.putString("password",tPassword.getText().toString());
                    //editor.putString("token",Res.getResponse().getToken());
                    //editor.putString("id",Res.getResponse().getCarWashId().getId());
                    editor.apply();
                    Intent intent = new Intent(MainActivity.this,AutoRegActivity.class);
                    intent.putExtra("token",Res.getResponse().getToken());
                    intent.putExtra("login",tEmail.getText().toString());
                    intent.putExtra("id",Res.getResponse().getCarWashId().getId());
                    startActivity(intent);
                    this.finish();
                }else {
                    Toast.makeText(this,"Неверный логин или пароль!",Toast.LENGTH_LONG).show();
                    pd.setVisibility(View.INVISIBLE);
                }
            }else{
                Toast.makeText(this,"Укажи логин и пароль",Toast.LENGTH_SHORT).show();
                pd.setVisibility(View.INVISIBLE);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e){
            pd.setVisibility(View.INVISIBLE);
            Toast.makeText(MainActivity.this,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
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


}
