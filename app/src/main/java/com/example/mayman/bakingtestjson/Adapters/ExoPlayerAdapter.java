package com.example.mayman.bakingtestjson.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mayman.bakingtestjson.R;
import com.example.mayman.bakingtestjson.StepObjs;
import com.example.mayman.bakingtestjson.genericviewholders.GenericViewHolder;
import com.squareup.picasso.Picasso;


/**
 * Created by MahmoudAyman on 8/19/2017.
 */

public class ExoPlayerAdapter extends RecyclerView.Adapter<GenericViewHolder> {

    private StepObjs stepObjs;
    private Context context;
    private onClickListener mCallback;

    public ExoPlayerAdapter(StepObjs stepObjs, Context context, onClickListener onClickListener) {
        this.stepObjs = stepObjs;
        this.context = context;
        this.mCallback = onClickListener;
    }

    public void changeDataSet(StepObjs data2) {
        this.stepObjs = data2;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_txt_step_detail_view, parent, false);

        return new Viewholder2(view);
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return (1);
    }

    private class Viewholder2 extends GenericViewHolder implements View.OnClickListener {
        TextView textView;
        Button nxt, prev;
        ImageView thup;

        public Viewholder2(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item2text3);
            nxt = (Button) itemView.findViewById(R.id.button2);
            prev = (Button) itemView.findViewById(R.id.button);
            thup = (ImageView) itemView.findViewById(R.id.thump);
            nxt.setOnClickListener(this);
            prev.setOnClickListener(this);
        }

        @Override
        public void bind(int position) {
            if (!stepObjs.getThumbnailURL().equals("")) {
                thup.setVisibility(View.VISIBLE);
                Picasso.with(context).load(stepObjs.getThumbnailURL()).fit().into(thup);
            } else {
                thup.setVisibility(View.INVISIBLE);
            }
            textView.setText(stepObjs.getDescription());
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == nxt.getId())
                mCallback.OnClick(0);
            else
                mCallback.OnClick(1);
        }
    }

    public interface onClickListener {
        void OnClick(int c);

        void change(int idx);
    }
}
