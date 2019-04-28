package com.example.collegeapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.collegeapp.listener.OnRecyclerItemClickListener;
import com.example.collegeapp.model.Courses;
import com.example.collegeapp.ui.CoursesActivity;
import com.example.collegeapp.R;

import java.util.ArrayList;

public class CoursesAdapter  extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> {
    Context context;
    int resources;
    ArrayList<Courses> objects;

    OnRecyclerItemClickListener recyclerItemClickListener;

    public CoursesAdapter(CoursesActivity context, int list_item, ArrayList<Courses> coursesArrayList) {
        this.context = context;
        this.resources = list_item;
        this.objects = coursesArrayList;
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }



    @NonNull
    @Override
    public CoursesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(resources, parent, false);
        final CoursesAdapter.ViewHolder holder = new CoursesAdapter.ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(holder.getAdapterPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesAdapter.ViewHolder viewHolder, int i) {
        Courses courses = objects.get(i);
        viewHolder.txtTitle.setText(courses.Name);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.textViewTitleCourse);
        }
    }
}
