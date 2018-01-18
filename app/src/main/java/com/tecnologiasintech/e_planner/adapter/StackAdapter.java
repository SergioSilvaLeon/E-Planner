package com.tecnologiasintech.e_planner.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tecnologiasintech.e_planner.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergiosilva on 1/17/18.
 */

public class StackAdapter extends RecyclerView.Adapter<StackAdapter.ViewHolder> {

    private List<String> mList;
    private Context mContext;

    public StackAdapter(Context context){
        mContext = context;
        mList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stack_item, parent, false);

        return new StackAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String stack = mList.get(position);
        holder.mTextView.setText(stack);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void update(String stack){
        mList.add(stack);
        notifyDataSetChanged();
    }

    public void clear(){
        mList.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.stackTxt);
        }
    }
}
