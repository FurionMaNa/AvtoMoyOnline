package com.project.avtomoy.ui.info.fragments.Sales;

import android.view.View;
import android.widget.TextView;
import com.project.avtomoy.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SalesViewHolder extends RecyclerView.ViewHolder {

    private TextView
            headText,
            bodyText;

    public SalesViewHolder(@NonNull View itemView) {
        super(itemView);
        headText = itemView.findViewById(R.id.headText);
        bodyText = itemView.findViewById(R.id.bodyText);

    }

    public void bindData(final Sale sale){
        headText.setText(sale.getTitle());
        bodyText.setText(sale.getDescription());

    }
}
