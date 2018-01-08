package com.tecnologiasintech.e_planner.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tecnologiasintech.e_planner.R;
import com.tecnologiasintech.e_planner.model.Event;
import com.tecnologiasintech.e_planner.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergiosilva on 1/8/18.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder>{

    private List<Event> mEventList;
    private Context mContext;

    public EventAdapter(Context context) {
        mContext = context;
        mEventList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item, parent, false);

        return new EventAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Event event = mEventList.get(position);
        if (event.isPopular()){
            holder.mImageViewPopular.setColorFilter(ContextCompat.getColor
                    (mContext, R.color.bg_login));
        }

        holder.mTextViewName.setText(event.getName());
        holder.mTextViewDate.setText(event.getDate());
    }

    public void updateList(Event event){
        mEventList.add(event);
        notifyDataSetChanged();
    }

    public void reset(){
        mEventList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewName;
        public TextView mTextViewDate;
        public ImageView mImageViewPopular;
        public ImageView mImageViewDelete;
        public ImageView mImageViewOptions;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextViewName = (TextView) itemView.findViewById(R.id.nameTxt);
            mTextViewDate = (TextView) itemView.findViewById(R.id.dateTxt);
            mImageViewPopular = (ImageView) itemView.findViewById(R.id.imageViewPopular);
            mImageViewDelete = (ImageView) itemView.findViewById(R.id.imageViewDelete);
            mImageViewOptions = (ImageView) itemView.findViewById(R.id.imageViewOptions);
        }
    }
}
