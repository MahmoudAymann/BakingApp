package com.example.mayman.bakingtestjson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mayman.bakingtestjson.Adapters.DetailViewAdapter;

import java.util.ArrayList;

public class StepsIngredientsFragment extends Fragment implements DetailViewAdapter.OnItemClickListener {

    RecyclerView recyclerView;
    DetailViewAdapter adapter;
    ArrayList<StepObjs> StepsData;
    ArrayList<String> strings;
    private OnFragmentInteractionListener mListener;

    public StepsIngredientsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_steps_andingreds, container, false);

        TextView textView = (TextView) rootView.findViewById(R.id.ingredients_idx);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.detail_recyclerViewx);
        strings = new ArrayList<>();
        StepsData = new ArrayList<>();
        try {
            strings = getActivity().getIntent().getStringArrayListExtra("ingred");
            StepsData = getActivity().getIntent().getParcelableArrayListExtra("steps");
            Log.v("aym", strings.size() + " x");

            Log.v("aym", StepsData.size() + " xOP");

        } catch (Exception e) {
            e.printStackTrace();
        }


        for (int i = 0; i < StepsData.size(); i++) {
            Log.v("aym", StepsData.get(i).getShortDescription());
        }
        if (strings != null) {
            for (int i = 0; i < strings.size(); i++) {
                textView.append(strings.get(i) + "\n");
            }
        }//end if
        setlast(textView.getText().toString());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DetailViewAdapter(StepsData, this);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(int item) {
        Intent intent = new Intent(getContext(), StepDetailActivity.class);
        intent.putParcelableArrayListExtra("list", StepsData);
        intent.putExtra("idx", item);

        //    startActivity(intent);
        mListener.onFragmentInteractionde(item);

    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteractionde(int uri);
    }

    void setlast(String f) {
        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("eta", f).apply();
    }

}//end class StepsIngredientsFragment