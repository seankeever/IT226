package com.it226.graphprogramming;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment
{
    public MainActivityFragment()
    {
    }

    @Override
    public void onAttach(Context v)
    {
        super.onAttach(v);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        GridView problemView = (GridView)v.findViewById(R.id.problemview);

        problemView.setNumColumns(4); //the grid has 4 columns
        //problemView.setNumRows(5); //the grid has 5 rows

        problemView.setAdapter(new ProblemGridImageAdapter(getContext()));


        return v;
    }
}