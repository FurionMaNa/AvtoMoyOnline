package com.project.avtomoy;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserAgreementActivity extends AppCompatActivity {

    public Button nextButtonRegistry;
    public Boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_user_agreement);
        nextButtonRegistry=findViewById(R.id.nextButtonRegistry);
        nextButtonRegistry.setBackgroundColor(Color.GRAY);
        CheckBox checkBox=findViewById(R.id.yes);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    nextButtonRegistry.setBackgroundColor(Color.rgb(255,102,52));
                    nextButtonRegistry.setEnabled(true);
                }else{
                    nextButtonRegistry.setBackgroundColor(Color.GRAY);
                    nextButtonRegistry.setEnabled(false);
                }
            }
        });
        TextView textView=findViewById(R.id.link);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("https://www.avtomoy.online/cabinet/privacy-policy"));
                startActivity(browserIntent);
            }
        });
        nextButtonRegistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserAgreementActivity.this,RegistryActivity.class);
                startActivity(intent);
            }
        });
    }
}
