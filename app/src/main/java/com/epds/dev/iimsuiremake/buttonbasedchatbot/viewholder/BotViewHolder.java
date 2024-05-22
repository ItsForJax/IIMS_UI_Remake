package com.epds.dev.iimsuiremake.buttonbasedchatbot.viewholder;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.epds.dev.iimsuiremake.R;

public class BotViewHolder extends MainViewHolder {
    public TextView botTextView;
    public BotViewHolder(@NonNull View itemView) {
        super(itemView);
        this.botTextView = itemView.findViewById(R.id.bot_message);
    }
}

