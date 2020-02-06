package com.project.avtomoy.ui.chat;

import android.content.Intent;
import android.net.Uri;
import android.util.Base64;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.project.avtomoy.AutoRegActivity;
import com.project.avtomoy.ChatMessagesClass;
import com.project.avtomoy.R;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ChatViewHolder extends RecyclerView.ViewHolder {

    private TextView dateText,timeText,commentText;
    private ConstraintLayout chat,advert;
    private WebView myWebView;

    public ChatViewHolder(@NonNull final View itemView) {
        super(itemView);
        dateText = itemView.findViewById(R.id.dateTVChat);
        timeText = itemView.findViewById(R.id.TimeTVChat);
        commentText = itemView.findViewById(R.id.CommentChat);
        chat = itemView.findViewById(R.id.chat);
        advert = itemView.findViewById(R.id.advert);
        myWebView=itemView.findViewById(R.id.browAdvert);

    }


    public void bindData(final ChatMessagesClass duty){
        chat.setVisibility(View.VISIBLE);
        if(duty.getMessages().getFeedback().getType()==2) {
            chat.setVisibility(View.INVISIBLE);
            chat.setVisibility(View.GONE);
            advert.setVisibility(View.VISIBLE);
            try {
                myWebView.setWebViewClient(new WebViewClient(){
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW);

                        browserIntent.setData(Uri.parse(url));
                        AutoRegActivity.context.startActivity (browserIntent);
                        return true;
                    }
                });
                String encodedHtml = Base64.encodeToString(("<html><head><title></title><style type=\"text/css\">.advert-in-chat {position: relative;padding-left: 27px;}.modal__content {margin-Left:10px; display: block; background-color: #fffbed;}.modal-mailing__title {font-size: 25px;line-height: 42px;font-weight: 600;margin-top: 0;margin-bottom: 5px;}.modal-mailing__site {    font-size: 18px;    font-weight: normal;    color: #ff6634;    text-decoration: none;    margin-top: 10px;}.modal-mailing__text {    font-size: 22px;    font-weight: normal;    margin: 6px 0;}.modal-mailing__info {    font-size: 18px;    font-weight: normal;}.modal-mailing__phone {    display: inline-block;    color: #ff6634;    text-decoration: none;    margin-right: 15px;}.modal-mailing__address {    display: inline-block;}</style></head><body style=\" background-color: #fffbed; \">" + duty.getMessages().getFeedback().getMessage()).getBytes(), Base64.NO_PADDING);
                myWebView.loadData(encodedHtml, "text/html", "base64");


            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            advert.setVisibility(View.INVISIBLE);
            advert.setVisibility(View.GONE);
            if (duty.getMessages().getFeedback().getType() == 0) {
                dateText.setText(duty.getMessages().getRecord().getDate());
                //int time_start = duty.getTime_start();
                //String hours_start = String.valueOf(time_start / 60);
                //String minutes_start = String.valueOf(time_start % 60);
                //int time_end = duty.getTime_start();
                //String hours_end = String.valueOf(time_end / 60);
                //String minutes_end = String.valueOf(time_end % 60);
                //if (hours_start.length() == 1) {
                //    hours_start = "0" + hours_start;
                //}
                //if (minutes_start.length() == 1) {
                //    minutes_start = "0" + minutes_start;
                //}
                //if (hours_end.length() == 1) {
                //    hours_end = "0" + hours_end;
                //}
                //if (minutes_end.length() == 1) {
                //    minutes_end = "0" + minutes_end;
                //}
                //timeText.setText(hours_start + ":" + minutes_start + "-" + hours_end + ":" + minutes_end);
                commentText.setText(duty.getMessages().getFeedback().getMessage());
            }
        }
    }


}
