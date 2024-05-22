package com.epds.dev.iimsuiremake.buttonbasedchatbot.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.epds.dev.iimsuiremake.R;

public class DateTimeViewHolder extends MainViewHolder {
    public TextView dateTime;
    public DateTimeViewHolder(@NonNull View itemView) {
        super(itemView);
        this.dateTime = itemView.findViewById(R.id.dateTime);
    }
}