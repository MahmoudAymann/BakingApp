package com.example.mayman.bakingtestjson.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mayman.bakingtestjson.BakingObjs;
import com.example.mayman.bakingtestjson.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by MahmoudAyman on 8/12/2017.
 */

public class RecyclerMainAdapter extends RecyclerView.Adapter<RecyclerMainAdapter.ViewHolder> {
    //dah bta3 eladapter
    public interface OnItemClickListener {
        void onItemClick(BakingObjs item);

    }

    private List<BakingObjs> bakingObjsList;
    private final OnItemClickListener listener;

    public RecyclerMainAdapter(List<BakingObjs> bakingObjsList, OnItemClickListener listner) {
        this.bakingObjsList = bakingObjsList;
        this.listener = listner;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.thumbnail_product_item_view, parent, false);
        return new ViewHolder(view);
    }//end onCreateViewHolder

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Context context = holder.itemView.getContext();
        BakingObjs bakingObjs = bakingObjsList.get(position);
        holder.bind(bakingObjs, listener);
    }

    @Override
    public int getItemCount() {
        if (bakingObjsList == null) {
            return 0;
        }
        return bakingObjsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_viewID);
            textView = (TextView) itemView.findViewById(R.id.text_viewID);
        }

        void bind(final BakingObjs item, final OnItemClickListener listener) {

            final Context context = itemView.getContext();

            textView.setText(item.getName());

                if(item.getImage().length()>0)
                {
                    Picasso.with(context).load(item.getImage()).into(imageView);

                }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

        }//end bind

    }//end class ViewHolder
}//end class