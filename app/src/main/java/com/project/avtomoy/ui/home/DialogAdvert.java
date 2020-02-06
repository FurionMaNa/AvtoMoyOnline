package com.project.avtomoy.ui.home;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.project.avtomoy.R;
import com.project.avtomoy.ui.AdvetFinishClass;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogAdvert extends DialogFragment {

    private AdvetFinishClass advetFinishClass;
    private TextView title,site,text,phone,address;
    private ImageButton close;

    public DialogAdvert(AdvetFinishClass advetFinishClass) {
        this.advetFinishClass = advetFinishClass;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setContentView(R.layout.fragment_advert);
        title=dialog.findViewById(R.id.title);
        site=dialog.findViewById(R.id.site);
        text=dialog.findViewById(R.id.text);
        phone=dialog.findViewById(R.id.phone);
        address=dialog.findViewById(R.id.address);
        close=dialog.findViewById(R.id.close);
        title.setText(advetFinishClass.getResponse().getTitle());
        site.setText(advetFinishClass.getResponse().getSite());
        text.setText(advetFinishClass.getResponse().getText());
        phone.setText(advetFinishClass.getResponse().getPhone());
        address.setText(advetFinishClass.getResponse().getAddress());
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("https://"+advetFinishClass.getResponse().getSite()));
                startActivity(browserIntent);
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+advetFinishClass.getResponse().getPhone()));
                startActivity(callIntent);
            }
        });
        return dialog;
    }
}
