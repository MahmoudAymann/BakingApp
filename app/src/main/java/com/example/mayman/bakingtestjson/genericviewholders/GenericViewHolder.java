package com.example.mayman.bakingtestjson.genericviewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by MahmoudAyman on 8/19/2017.
 */

public abstract class GenericViewHolder extends RecyclerView.ViewHolder
{
    public GenericViewHolder(View itemView) {
        super(itemView);
    }

    public abstract  void bind(int position);
}
