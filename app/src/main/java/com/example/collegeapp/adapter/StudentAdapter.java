package com.example.collegeapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.collegeapp.listener.OnRecyclerItemClickListener;

import com.example.collegeapp.model.Student;

import com.example.collegeapp.R;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    Context context;
    int resources;
    ArrayList<Student> objects;

    OnRecyclerItemClickListener recyclerItemClickListener;



    public StudentAdapter(Context context, int resources, ArrayList<Student> objects) {
        this.context = context;
        this.resources = resources;
        this.objects = objects;
    }



    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(resources, parent, false);
        final StudentAdapter.ViewHolder holder=new StudentAdapter.ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(holder.getAdapterPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Student student = objects.get(position);
        holder.txtName.setText(student.Name);
        holder.txtEmail.setText(student.Email);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.recyclerItemClickListener = onRecyclerItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtEmail;
        public ViewHolder(View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.Name);
            txtEmail=itemView.findViewById(R.id.Email);
        }
    }
}
