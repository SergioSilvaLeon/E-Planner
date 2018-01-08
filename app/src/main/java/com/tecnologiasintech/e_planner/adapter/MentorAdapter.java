package com.tecnologiasintech.e_planner.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tecnologiasintech.e_planner.R;
import com.tecnologiasintech.e_planner.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergiosilva on 1/8/18.
 */

public class MentorAdapter extends RecyclerView.Adapter<MentorAdapter.ViewHolder> {

    private List<User> mUserList;
    private Context mContext;

    public MentorAdapter(Context context) {
        mContext = context;
        mUserList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mentor_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MentorAdapter.ViewHolder holder, int position) {
        User user = mUserList.get(position);
        holder.mTextView.setText(user.getFullName());
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public void update(User user) {
        mUserList.add(user);
        notifyDataSetChanged();;
    }

    public void reset(){
        mUserList.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public ImageView mImageViewDelete;
        public ImageView mImageViewOptions;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.nameTxt);
            mImageViewDelete = (ImageView) itemView.findViewById(R.id.imageViewDelete);
            mImageViewOptions = (ImageView) itemView.findViewById(R.id.imageViewOptions);

            // Add click listeners
        }
    }
}
