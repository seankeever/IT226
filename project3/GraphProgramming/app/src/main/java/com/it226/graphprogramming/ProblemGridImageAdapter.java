package com.it226.graphprogramming;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import java.lang.*;

/**
 * Created by ashesh on 11/11/2015.
 */
public class ProblemGridImageAdapter extends BaseAdapter
{
    private Context mContext;

    private Integer[] ots = new Integer[16];
    private Integer[] other = {R.drawable.left, R.drawable.left};
    //private Integer[] tsts = new Integer[25];

    public ProblemGridImageAdapter(Context c)
    {
        mContext = c;

        int start = (int)(Math.random() *15 + .5);//As this was tested i saw start not show up once

        for(int i=0; i< 16; i++)
        {

            if(i==start)
                ots[i] = R.drawable.start;
            else
            {
                int others =  (int)(Math.random() *15 + .5);
                if(others<=5)
                    ots[i] = R.drawable.black;
                else
                    ots[i] = R.drawable.white;
            }
        }

    }

    private Integer[] ts =
            {


                    R.drawable.white,
                    R.drawable.yellow,
                    R.drawable.black,
                    R.drawable.fill,
                    R.drawable.left,
                    R.drawable.right,
                    R.drawable.up,
                    R.drawable.down,
                    R.drawable.black,
                    R.drawable.white,
                    R.drawable.yellow,
                    R.drawable.black,
                    R.drawable.white,
                    R.drawable.yellow,
                    R.drawable.black,
                    R.drawable.yellow,
                    R.drawable.black,
                    R.drawable.white,
                    R.drawable.yellow,
                    R.drawable.black,
                    R.drawable.fill,
                    R.drawable.left,
                    R.drawable.right,
                    R.drawable.up,
                    R.drawable.down,
            };
    Integer[][] arrays = {ts,other,ots};

    @Override
    public int getCount() {
        return arrays[2].length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ImageView imageView;

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            //imageView.setLayoutParams(new GridView.LayoutParams(200,200));

            // imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setPadding(2,2,2,2);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(arrays[2][position]);

        imageView.setAdjustViewBounds(true);
        return imageView;
    }

}
