package com.tecnologiasintech.e_planner.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tecnologiasintech.e_planner.R;
import com.tecnologiasintech.e_planner.activity.CreateStudentActivity;
import com.tecnologiasintech.e_planner.activity.StudentActivity;
import com.tecnologiasintech.e_planner.activity.StudentVeiwActivity;
import com.tecnologiasintech.e_planner.activity.UpdateStudentActivity;
import com.tecnologiasintech.e_planner.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergiosilva on 1/7/18.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private List<Student> mStudentList;
    private Context mContext;

    public StudentAdapter(Context context) {
        mContext = context;
        mStudentList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Student student = mStudentList.get(position);
        holder.mTextView.setText(student.getName());
    }


    @Override
    public int getItemCount() {
        return mStudentList.size();
    }

    public void updateList(Student student){
        mStudentList.add(student);
        notifyDataSetChanged();
    }

    public void reset(){
        mStudentList.clear();
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION){

                        Student student = mStudentList.get(pos);
                        // Get Information and pass to new activity;
                        Intent intent = new Intent(mContext, StudentVeiwActivity.class);
                        intent.putExtra(StudentActivity.EXTRA_NAME, student.getName());
                        intent.putExtra(StudentActivity.EXTRA_EMAIL, student.getEmail());
                        intent.putExtra(StudentActivity.EXTRA_SCHOOL, student.getSchool());
                        intent.putExtra(StudentActivity.EXTRA_TSHIRTSIZE, student.gettShirtSize());
                        intent.putExtra(StudentActivity.EXTRA_TECHNOLOGY, student.getTechnology());
                        intent.putExtra(StudentActivity.EXTRA_ORGINIAZTION, student.getOranization());
                        intent.putExtra(StudentActivity.EXTRA_SEMESTER, student.getSemester());
                        mContext.startActivity(intent);

                    }

                }
            });

            mImageViewOptions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION){

                        Student student = mStudentList.get(pos);
                        // Get Information and pass to new activity;
                        Intent intent = new Intent(mContext, UpdateStudentActivity.class);
                        intent.putExtra(StudentActivity.EXTRA_NAME, student.getName());
                        intent.putExtra(StudentActivity.EXTRA_EMAIL, student.getEmail());
                        intent.putExtra(StudentActivity.EXTRA_SCHOOL, student.getSchool());
                        intent.putExtra(StudentActivity.EXTRA_TSHIRTSIZE, student.gettShirtSize());
                        intent.putExtra(StudentActivity.EXTRA_TECHNOLOGY, student.getTechnology());
                        intent.putExtra(StudentActivity.EXTRA_ORGINIAZTION, student.getOranization());
                        intent.putExtra(StudentActivity.EXTRA_SEMESTER, student.getSemester());
                        intent.putExtra(StudentActivity.EXTRA_KEY, student.getKey());

                        mContext.startActivity(intent);

                    }
                }
            });

            mImageViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new AlertDialog.Builder(mContext)
                            .setTitle("Delete Confirmation")
                            .setMessage("Do you really want to delete?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    deleteItem();
                                }})
                            .setNegativeButton(android.R.string.no, null).show();
                }
            });
        }

        private void deleteItem(){
            int pos = getAdapterPosition();

            if (pos != RecyclerView.NO_POSITION){

                String key = mStudentList.get(pos).getKey();

                mStudentList.remove(pos);

                notifyDataSetChanged();

                // remove from firebase
                DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                        .getReference("EPlanner/Student");
                databaseReference.child(key).removeValue();


            }
        }

    }


}
