package com.tecnologiasintech.e_planner.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
        public ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.stackTxt);
            mImageView = (ImageView) itemView.findViewById(R.id.imageViewDeleteStack);

            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItem();
                }
            });
        }

        private void removeItem() {
            int pos = getAdapterPosition();

            if (pos != RecyclerView.NO_POSITION) {

                String key = mList.get(pos);
                mList.remove(pos);

                notifyDataSetChanged();

                // remove from firebase
                DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                        .getReference("EPlanner/Stacks");
                databaseReference.child(key).removeValue();
            }
        }
    }
}
