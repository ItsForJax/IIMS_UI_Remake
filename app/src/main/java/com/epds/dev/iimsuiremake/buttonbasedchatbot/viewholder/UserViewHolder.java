package com.epds.dev.iimsuiremake.buttonbasedchatbot.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.epds.dev.iimsuiremake.R;
public class UserViewHolder extends MainViewHolder {
    public TextView userMessage;
    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        this.userMessage = itemView.findViewById(R.id.user_message);
    }
}
