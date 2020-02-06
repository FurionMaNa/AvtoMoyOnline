package com.project.avtomoy.ui.history;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.avtomoy.AutoRegActivity;
import com.project.avtomoy.HistoryAllClass;
import com.project.avtomoy.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryViewHolder  extends RecyclerView.ViewHolder {

    private TextView
            DateTVHistory,
            TimeTVHistory,
            CommentTVHistory,
            CommentAdminTVHistory,
            CommentAdminTVHead,
            CommentHead,
            CommentUserTVHistory,
            CommentUserTVHead,
            FinalPrice,
            dateComment,
            dateAnswer,
            name;
    private LinearLayout LLServices;
    private Button RecordReturn;
    private View line;

    public HistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        DateTVHistory = itemView.findViewById(R.id.dateTVHistory);
        TimeTVHistory = itemView.findViewById(R.id.TimeTVHistory);
        CommentTVHistory = itemView.findViewById(R.id.CommentTVHistory);
        CommentHead = itemView.findViewById(R.id.CommentHeadTVHistory);
        CommentUserTVHead = itemView.findViewById(R.id.CommentnHeadTVHistory);
        CommentAdminTVHistory = itemView.findViewById(R.id.CommentAdminTVHistory);
        CommentUserTVHistory = itemView.findViewById(R.id.CommentUserTVHistory);
        CommentAdminTVHead = itemView.findViewById(R.id.CommentAdminHeadTVHistory);
        dateComment= itemView.findViewById(R.id.dateTVCom);
        dateAnswer= itemView.findViewById(R.id.dateTVAdmin);
        LLServices=itemView.findViewById(R.id.LLServiceHistorys);
        FinalPrice=itemView.findViewById(R.id.TVFinalPrice);
        RecordReturn=itemView.findViewById(R.id.nextButtonMain);
        name=itemView.findViewById(R.id.NameTVHistory);
        line=itemView.findViewById(R.id.line);
    }

    public void bindData(final HistoryAllClass history){
        LLServices.removeAllViews();
        try {
            if(history!=null) {
                name.setText(history.getCarWashName());
                DateTVHistory.setText(history.getRecord().getDate());
                int time_start = history.getRecord().getTime_start();
                int time_end = history.getRecord().getTime_end();
                String hours_start = String.valueOf(time_start / 60);
                String minutes_start = String.valueOf(time_start % 60);
                String hours_end = String.valueOf(time_end / 60);
                String minutes_end = String.valueOf(time_end % 60);
                if (hours_start.length() == 1) {
                    hours_start = "0" + hours_start;
                }
                if (minutes_start.length() == 1) {
                    minutes_start = "0" + minutes_start;
                }
                if (hours_end.length() == 1) {
                    hours_end = "0" + hours_end;
                }
                if (minutes_end.length() == 1) {
                    minutes_end = "0" + minutes_end;
                }
                TimeTVHistory.setText(hours_start + ":" + minutes_start + "-" + hours_end + ":" + minutes_end);
                if (history.getRecord().getComment().equals("")) {
                    CommentTVHistory.setVisibility(View.INVISIBLE);
                    CommentHead.setVisibility(View.INVISIBLE);
                    CommentTVHistory.setVisibility(View.GONE);
                    CommentHead.setVisibility(View.GONE);
                } else {
                    CommentTVHistory.setVisibility(View.VISIBLE);
                    CommentHead.setVisibility(View.VISIBLE);
                    CommentTVHistory.setText(history.getRecord().getComment());
                }
            }
            if(history.getFeedback()!=null) {
                if(history.getFeedback().getMessage().equals("")){
                    CommentUserTVHistory.setVisibility(View.INVISIBLE);
                    CommentUserTVHead.setVisibility(View.INVISIBLE);
                    CommentUserTVHistory.setVisibility(View.GONE);
                    CommentUserTVHead.setVisibility(View.GONE);
                    dateComment.setVisibility(View.INVISIBLE);
                    dateComment.setVisibility(View.GONE);
                }else{
                    CommentUserTVHistory.setVisibility(View.VISIBLE);
                    CommentUserTVHead.setVisibility(View.VISIBLE);
                    dateComment.setVisibility(View.VISIBLE);
                    dateComment.setText(history.getFeedback_datetime());
                    CommentUserTVHistory.setText(history.getFeedback().getMessage());
                }
            }else{
                CommentUserTVHistory.setVisibility(View.INVISIBLE);
                CommentUserTVHead.setVisibility(View.INVISIBLE);
                CommentUserTVHistory.setVisibility(View.GONE);
                CommentUserTVHead.setVisibility(View.GONE);
                dateComment.setVisibility(View.INVISIBLE);
                dateComment.setVisibility(View.GONE);
            }
            if(history.getFeedbackAnswer()!=null) {
                if(history.getFeedbackAnswer().getMessage().equals("")){
                    CommentAdminTVHistory.setVisibility(View.INVISIBLE);
                    CommentAdminTVHead.setVisibility(View.INVISIBLE);
                    CommentAdminTVHistory.setVisibility(View.GONE);
                    CommentAdminTVHead.setVisibility(View.GONE);
                    dateAnswer.setVisibility(View.INVISIBLE);
                    dateAnswer.setVisibility(View.GONE);
                }else{
                    CommentAdminTVHistory.setVisibility(View.VISIBLE);
                    CommentAdminTVHead.setVisibility(View.VISIBLE);
                    dateAnswer.setVisibility(View.VISIBLE);
                    dateAnswer.setText(history.getFeedbackAnswer_datetime());
                    CommentAdminTVHistory.setText(history.getFeedbackAnswer().getMessage());
                }
            }else{
                CommentAdminTVHistory.setVisibility(View.INVISIBLE);
                CommentAdminTVHead.setVisibility(View.INVISIBLE);
                CommentAdminTVHistory.setVisibility(View.GONE);
                CommentAdminTVHead.setVisibility(View.GONE);
                dateAnswer.setVisibility(View.INVISIBLE);
                dateAnswer.setVisibility(View.GONE);
            }
            if(((history.getFeedback()==null)||(history.getFeedback().getMessage().equals("")))&&(history.getRecord().getComment().equals(""))){
                line.setVisibility(View.INVISIBLE);
                line.setVisibility(View.GONE);
            }else{
                line.setVisibility(View.VISIBLE);
            }
            if(history.getServices()!=null) {
                for (int i = 0; i < history.getServices().size(); i++) {
                    TextView t = new TextView(LLServices.getContext());
                    t.setTextColor(Color.BLACK);
                    t.setText(history.getServices().get(i).getName());
                    LLServices.addView(t);
                }
            }
            if(history.getAdditionServices()!=null) {
                for (int i = 0; i < history.getAdditionServices().size(); i++) {
                    TextView t = new TextView(LLServices.getContext());
                    t.setTextColor(Color.BLACK);
                    t.setText(history.getAdditionServices().get(i).getName());
                    LLServices.addView(t);
                }
            }
            String s = String.valueOf(history.getRecord().getPrice());
            if ((s.charAt(s.length() - 1) == '0') && (s.charAt(s.length() - 2) == '.')) {
                s = s.substring(0, s.length() - 2);
            }
            if(history!=null) {
                RecordReturn.setTag(history.getRecord().getCar_washes_id());
            }
            FinalPrice.setText("Итого-" + s + "\u20BD");
            RecordReturn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HistoryFragment.ReturnRecord(v);
                }
            });
        }catch (Exception e){
            Log.e("MyLog",e.getMessage());
            Toast.makeText(AutoRegActivity.context,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
        }
    }
}
