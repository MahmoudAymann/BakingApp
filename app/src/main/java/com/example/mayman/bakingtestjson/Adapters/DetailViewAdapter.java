package com.example.mayman.bakingtestjson.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mayman.bakingtestjson.StepObjs;

import java.util.List;

/**
 * Created by MahmoudAyman on 8/12/2017.
 */

public class DetailViewAdapter extends RecyclerView.Adapter<DetailViewAdapter.ViewHolder> {
    //dah bta3 eladapter
    public interface OnItemClickListener {
        void onItemClick(int item);

    }

    private List<StepObjs> bakingObjsList;
    private final OnItemClickListener listener;
    Context context;

    public DetailViewAdapter(List<StepObjs> bakingObjsList, OnItemClickListener listner) {
        this.bakingObjsList = bakingObjsList;
        this.listener = listner;
    }

    public DetailViewAdapter(List<StepObjs> bakingObjsList, Context context, OnItemClickListener listner) {
        this.bakingObjsList = bakingObjsList;
        this.listener = listner;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }//end onCreateViewHolder

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Context context = holder.itemView.getContext();
        StepObjs bakingObjs = bakingObjsList.get(position);
        holder.bind(bakingObjs, listener);
    }

    @Override
    public int getItemCount() {
        if (bakingObjsList == null) {
            return 0;
        }
        return bakingObjsList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            //imageView = (ImageView) itemView.findViewById(R.id.image_viewID);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }

        void bind(final StepObjs item, final OnItemClickListener listener) {

            final Context context = itemView.getContext();

            textView.setText(item.getShortDescription());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getAdapterPosition());
                }
            });

        }//end bind

    }//end class ViewHolder
}//end class