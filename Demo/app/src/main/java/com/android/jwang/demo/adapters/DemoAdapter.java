package com.android.jwang.demo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.jwang.demo.R;
import com.android.jwang.demo.activities.DetailsActivity;

/**
 * Created by Jiajun Wang on 8/19/16.
 * Copyright (c) 2015 Tank Exchange, Inc. All rights reserved.
 */
public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder>
{
    private Context mContext;
    private static final int TYPE_SHORT = 0;
    private static final int TYPE_LONG = 1;

    public DemoAdapter(Context context)
    {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        //There is two ways to implement: 
        //1. Use one layout and handle view's visibility at onBindViewHolder
        //2. Like below, use two different layout to implemented list item. And create two different ViewHolder to control different UI.
        //The reason I only use one ViewHolder is that both of two views have the same action and no other different things need to change.
        View view;
        if (viewType == TYPE_LONG)
        {
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item_long, parent, false);
        }
        else
        {
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
    }

    @Override
    public int getItemCount()
    {
        return 10;
    }

    @Override
    public int getItemViewType(int position)
    {
        return position % 2 == 1 ? TYPE_LONG : TYPE_SHORT;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        private View mRootView;

        public ViewHolder(View itemView)
        {
            super(itemView);
            mRootView = itemView.findViewById(R.id.root_view);
            mRootView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.root_view:
                    DetailsActivity.startActivity(mContext, mRootView);
                    break;
            }
        }
    }
}
