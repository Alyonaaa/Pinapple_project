package com.example.pinapple_;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskAdapterHolder> {
    List<Task> data;
    Context context;

    TaskAdapter(List<Task> data) {
        this.data = data;
    }


    @NonNull
    @Override
    public TaskAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task, parent, false);
        return new TaskAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapterHolder holder, int position) {
        Task task = data.get(position);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.parent.getContext(), "CLick", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class TaskAdapterHolder extends RecyclerView.ViewHolder {
        View parent;

        public TaskAdapterHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView;
        }
    }
}
