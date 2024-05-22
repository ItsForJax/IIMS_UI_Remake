package com.epds.dev.iimsuiremake.buttonbasedchatbot.viewholder;

import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.epds.dev.iimsuiremake.R;

public class OptionsViewHolder extends MainViewHolder {
    public LinearLayout optionsLayout;
    public OptionsViewHolder(@NonNull View itemView) {
        super(itemView);
        this.optionsLayout = itemView.findViewById(R.id.options_layout);
    }
}
